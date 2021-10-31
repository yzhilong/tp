---
layout: page
title: User Guide
---
Welcome to **GameBook**!

**GameBook** is a desktop app designed for you to track your gambling performance.
With simple commands, you will be able to add your gambling sessions into **GameBook**
and view instant analysis of your gambling records.

If you enjoy casual gambling sessions with your friends and family or love going to the casinos, do try out **GameBook**!

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that you have Java 11 or above installed in your computer.
  * To check if you have Java 11 or above installed, open command prompt (search "cmd" on your computer) and type `java -version`.
    The command prompt will tell you if you have Java and which version you have. Proceed to step 2 if you have Java 11 or above installed.
  * If you do not have Java 11 or above installed, please visit the [Java installation guide](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A)
    and install Java 11 or above.
2. Download the latest `gamebook.jar` from [here](https://github.com/AY2122S1-CS2103T-W13-3/tp/releases).

3. Copy the JAR file to the folder you want to use as the home folder for **GameBook**. Your gambling records will later be saved in your home folder.

4. Double-click the JAR file to start the app. The GUI similar to below should appear in a few seconds.
   <br>
   ![GUI](images/Ui.png)

5. Type the command in the command box and press “Enter” on your keyboard to execute it. <br>
   Here are some example commands that you can try:

  * **`add /g Poker /s 500 /e 650 /date 20/06/21 /dur 142 /loc Sentosa Casino`**: <br>
    Adds an entry of Poker where you started with $500 and ended with $650 (played on 20th June 2021 for 142min at
    Sentosa Casino) into the **GameBook**.

  * **`delete 2`** : Deletes the 2nd entry shown in the current list

  * **`help`** : Shows a list of commands available to use in **GameBook**.

6. Refer to the [Features](#features) section for more details of each command.

--------------------------------------------------------------------------------------------------------------------
## Terminology
### Syntax and Symbols used in the User Guide:
Format | Description
--------|------------------
**`command`** | Used to denote an input command
**:information_source:** | Used to signify important information/notes to the user
**:bulb:** | Used to indicate tips to the user
**:warning:** | Used to warn the user before proceeding with a potentially risky action

### Technical Terms:
Term | Description
--------|------------------
**parameter** | A parameter is a piece of information that the user needs to supply in a command.
**JSON** | Abbreviation of JavaScript Object Notation. Format in which the user's data is stored.


### GameEntry Fields:

Parameter | Description
--------|------------------
**GAME_NAME** | Refers to the name of the game you wish to record. Eg: Poker, Roulette, Blackjack, etc.
**INITIAL_CASH** | The amount of cash you have at the beginning of a game
**FINAL_CASH** | The amount of cash you have at the end of a game
**PROFIT** | The overall gain/loss from the game. Effectively, the difference between `FINAL_CASH` and `INITIAL_CASH`
**DATE** | The date on which the game was played
**DURATION** | The amount of time for which the game was played
**LOCATION** | The place where the game was played
**TAG** | A single word (or dash-separated word) attribute assigned to the game which can be used to categorize the game. <br> Eg: birthday, friends-house, etc.


--------------------------------------------------------------------------------------------------------------------


## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>

  e.g. In `add /g GAME_NAME /s INITIAL_CASH /e FINAL_CASH`, `GAME_NAME`, `INTIIAL_CASH`, and `FINAL_CASH` are
  parameters the user needs to supply. An example is `add /g poker /s 0.01 /e 1.02`.
  <br> <br>

* Items in square brackets are optional.<br>

  e.g. `add /g GAME_NAME /s INITIAL_CASH /e FINAL_CASH [/date DATE]` can be used as
  `add /g poker /s 0.01 /e 1.02` or as `add /g poker /s 0.01 /e 1.02 /date 11/9/21 21:20`.
</div>


### Adding a game entry: `add`

Adds a game entry to **GameBook**.<br>

Parameters:<br>
GAME_NAME, INITIAL_CASH, FINAL_CASH, PROFIT, [DATE], [DURATION], [LOCATION], [TAGS] <br><br>
Format:<br>
1. `add /g GAME_NAME /s INITIAL_CASH /e FINAL_CASH [/date DATE] [/dur DURATION] [/loc LOCATION] [/tag TAGS]` <br>
2. `add /g GAME_NAME /p PROFIT [/date DATE] [/dur DURATION] [/loc LOCATION]`
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
There are 2 valid formats for adding a game entry. You can choose to input the INITIAL_CASH with the FINAL_CASH or only input the PROFIT. <br>
</div>

* A game entry can have any number of TAGS (including 0). To add multiple tags, follow the format of `/tag TAG_1,TAG_2,...`.
  * eg. `/tag drunk,lucky`
  * Take note that there should be no whitespace within a tag. Use hyphens `-` to separate words within a tag. Tags should be separated by a comma.
* DATE has two valid input formats:
  * To specify date only:
    * `/date yyyy-MM-dd`
      * eg. `/date 01/10/21` - Oct 1, 2021
  * To specify date with time:
    * `/date yyyy-MM-dd HH:mm`
      * eg. `/date 01/10/21 10:21` - Oct 1, 2021 10:21am
* DURATION has four valid input formats:
  * To specify hours with minutes:
    * `/dur HH:mm`
      * eg.`/dur 12:20` - 12 hrs 20 min
    * `/dur INT_h INT_m`
      * eg. `/dur 12h 20m` - 12hrs 20 min
  * To specify hours only:
    * `/dur INT_h`
      * eg. `/dur 12h` - 12 hrs
  * To specify minutes only:
    * `/dur INT_m`
      * eg. `/dur 20m` - 20 min
    * `/dur INT`
      * eg. `/dur 20` - 20 min



Examples:
* `add /g blackjack /s 12.34 /e -56.78 /date 13/9/21 /dur 1:23 /loc Marina Bay Sands`<br>
  Adds an entry of blackjack where you started with $12.34 and ended with -$56.78 (played on 13th Sept. 2021 for 1hr 23 min
  at Marina Bay Sands) to **GameBook**.
* `add /g poker /s 0.01 /e 1.02 /date 11/9/21 21:20 /dur 3:14 /loc Home`<br>
  Adds an entry of poker where you started with $0.01 and ended with $1.02 (played on 11th Sept. 2021 21:20  for 3hr 14 min
  at Home) to **GameBook**.
* `add /g poker /p 0.2 /tag run-good`<br>
  Adds an entry of poker where you gained a profit of $0.20 to **GameBook** and tags the entry as "run-good".

### Listing all game entries : `list`

Shows a list of all game entries in **GameBook**.<br>

Format:<br>
`list`

[UPDATE SCREENSHOT]

### Editing a game entry : `edit`

Edits an existing game entry in **GameBook**. <br>

Parameters:<br>
INDEX, [GAME_NAME], [PROFIT], [DATE], [DURATION], [LOCATION], [TAGS]<br><br>
Format:<br>
`edit INDEX [/g GAME_NAME] [/p PROFIT] [/date DATE] [/dur DURATION] [/loc LOCATION] [/tag TAGS]`

* Edits the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which
  **must be a positive integer** 1, 2, 3, …​
* **At least one** of the optional fields must be provided.
* Only selected properties of the game record will be edited, all other properties will remain unchanged.
* Edited tags will replace existing tags completely.
* If the selected property was initially empty, it would be updated to be the value the user gave in the flag.
* Updated values will be reflected in the file saved to the disk.
* Please refer to "Adding a game entry" section for specific notes on the formats of DATE, DURATION, and TAGS

Examples:
*  `edit 1 /g roulette /p 1`<br>Changes the name of the 1st game in the list to roulette and the profit to $1.
*  `edit 3 /s 1 /loc John’s house`<br>Changes the location where the 3rd game in the list was played to “John’s house”,
   regardless of whether the initial location was empty or not.

### Deleting a game: `delete`

Deletes the game at the specified index.<br>

Parameter: <br>
INDEX

Format:<br>
`delete INDEX`

* Deletes the game record at the specified `INDEX`. `INDEX` refers to the index of the game within the game list, which
  **must be a positive integer** 1, 2, 3, …​
* Selected game will also be deleted from the file in the disk.
* Indices of all remaining tasks will be updated.
  * Suppose game record `3` has been deleted, then all game records with `INDEX > 3` will be decremented by 1.

Examples:
* `delete 2`
  <br>deletes the 2nd game in the list.

### Finding game entries: `find`
Lists all the game entries that contain the specified keyword.

Parameter:
KEYWORD

Format:<br>
`find KEYWORD` <br>

Examples:
* `find poker`<br>shows a list of game entries that contains the keyword "poker" (keyword may be found in the game entry's TAGS or GAME_NAME)

[UPLOAD SCREENSHOT]
### Clearing all data: `clear`
Clears all game entries.

Format:<br>
`clear`

* After `clear` is entered as a command, a popup window that looks like the following will appear.<br>
  ![Clear Command Image](images/ClearConfirmationWindow.png)<br>
* To confirm clearing all game entries from **GameBook**, you must click on the button [Clear]. Once you click on [Clear],
  all your game entries will be removed.
* If you decided
  not to clear your data, please click on the [X] to close the window.

### Getting help : `help`
Shows a list of commands available or the format of a specific command.

Format:<br>
`help` - shows a list of commands available.<br>
`help add` - shows the format of the command to add a game entry.<br>
`help delete` - shows the format of the command to delete a game entry.<br>
`help edit` - shows the format of the command to edit a game entry.<br>
`help find` - shows the format of the command to find specific game entries. <br>
### Exiting the program : `exit`

Exits the program.

Format:<br> `exit`

### Notes about flags

* the `[/dur DURATION]` flag accepts multiple input formats
  *  `... /dur 123` indicates a duration of 123 minutes.
  *  `... /dur 1:23` indicates a duration of 1 hour, 23 minutes.
  *  `... /dur 1h` indicates a duration of 1 hour.
  *  `... /dur 1h 23m` indicates a duration of 1 hour, 23 minutes.
  *  `... /dur 23m` indicates a duration of 23 minutes.

* Different tags should be separated by `, `.
  *  `... /tag some-tag` tags a game with `some-tag`.
  *  `... /tag some-tag, some-other-tag` tags a game with `some-tag` and `some-other-tag`.

### Saving the data

**GameBook** data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

**GameBook** data are saved as a JSON file `[JAR file location]/data/gamebook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, GameBook will discard all data and start with an empty data file at the next run.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer (refer to Quick Start) and replace the empty gamebook.json file it creates within the data folder with your gamebook.json file in your original computer.

**Q**: Can I edit the data by directly modifying the data file?<br>
**A**: Technically, you can if you follow the exact storage format. However, we strongly advise against it as any
mistakes will cause errors in the app.

**Q**: (Follow up from previous question) What is the storage format of the data file?<br>
**A**: (To be answered)


--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | Format 1: <br> `add /g GAME_NAME /s INITIAL_CASH /e FINAL_CASH [/date DATE] [/dur DURATION] [/loc LOCATION] [/tag TAGS]` <br> <br> e.g., <br> `add /g blackjack /s 12.34 /e -56.78 /date 13/9/21 /dur 1:23 /loc Marina Bay Sands /tag loose,run-good` <br> <br> Format 2: <br> `add /g GAME_NAME /p PROFIT [/date DATE] [/dur DURATION] [/loc LOCATION] [/tag TAGS]` <br> <br> e.g.,<br> `add /g poker /p 200`
**List** | `list`
**Delete** | `delete INDEX`<br> <br> e.g., `delete 1`
**Edit** | `edit INDEX [/g GAME_NAME] [/p PROFIT] [/date DATE] [/dur DURATION] [/loc LOCATION] [/tag TAGS]` <br> <br> e.g., <br>`edit 1 /g roulette /p 20` <br> `edit 3  /loc John’s house`
**Find** | `find KEYWORD`<br><br> e.g., `find tag1`
**Clear** | `clear`
**Help** | `help`<br> `help add` `help delete` `help edit` `help find`
**Exit** | `exit`
