---
layout: page
title: User Guide
---

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `addressbook.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `/add /name GAMENAME /start INITIALCASH /end FINALCASH`, `GAMENAME`, `INTIIALCASH`, and `FINALCASH` are 
  parameters which can be used as `/add /name poker /start 0.01 /end 1.02`.

* Items in square brackets are optional.<br>
  e.g `/name GAMENAME /start INITIALCASH /end FINALCASH [/date DATE]` can be used as 
  `/add /name poker /start 0.01 /end 1.02` or as `/add /name poker /start 0.01 /end 1.02 /date 11/9/21 21:20`.

</div>


### Adding a person: `/add`

Adds a person to the game book.

Format: `/add /name GAMENAME /start INITIALCASH /end FINALCASH [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A game can have any number of tags (including 0)
</div>

Examples:
* `/add /name blackjack /start 12.34 /end -56.78 /date 13/9/21 /duration 1:23 /location Marina Bay Sands`
* `/add /name poker /start 0.01 /end 1.02 /date 11/9/21 21:20 /duration 3:14 /location Home`
* `/add /name poker /end 0.2 /tag loose run-good`

### Listing all games : `/list`

Shows a list of all games in the game book.

Format: `/list`

### Reads a specific game : `/read`

Shows the details of a specific game.

Format: `/read INDEX`

Examples:
* `/read 5` shows the game record at index 5 of the game list.

### Editing a game : `/edit`

Edits an existing game in the game book.

Format: `/edit INDEX [/name GAMENAME] [/start INITIALCASH] [/end FINALCASH] [/date DATE] [/duration DURATION] [/location LOCATION] [/tag TAGS]`

* Edits the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which 
  **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Only selected properties of the game record will be edited, all other properties will remain unchanged.
* Edited tags will replace existing tags completely.
* If the selected property was initially empty, it would be updated to be the value the user gave in the flag.
* Updated values will be reflected in the file saved to the disk.

Examples:
*  `/edit 1 /name roulette /start 1` edits the game type of the 1st game to roulette, and the amount of cash the user 
   started the game with to 1.
*  `/edit 3 /start 1 /location John’s house` edits the location where the first game was played to be “John’s house”, 
   regardless of whether the initial location was empty or not.

### Deleting a game: `/delete`

Deletes the game at the specified index.

Format: `/delete INDEX`

* Deletes the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which 
  **must be a positive integer** 1, 2, 3, …​
* Selected game will also be deleted from the file in the disk.
* Indices of all remaining tasks will be updated.
  * Suppose game record `3` has been deleted, then all game records with `INDEX > 3` will be decremented by 1.

Examples:
* `/delete 2` deletes the 2nd game in the list.

### Exiting the program : `/exit`

Exits the program.

Format: `/exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List** | `list`
**Help** | `help`
