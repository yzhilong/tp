---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2122S1-CS2103T-W13-3/tp/tree/master/docs/diagrams) folder. Refer to the
[_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create
and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** has two classes called [`Main`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/Main.java) and
[`MainApp`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.


**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API
  `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using
the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component
through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the
implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `GameEntryListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files
that are in the `src/main/resources/view` folder. For example, the layout of the
[`MainWindow`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `GameEntry` object residing in the `Model`. The graph
and statistics displays also depend on `GameEntryList`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="600"/>

How the `Logic` component works:
1. When `Logic` is called upon to execute a command, it uses the `GameBookParser` class to parse the user command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `AddCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to add a game entry).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

The Sequence Diagram below illustrates the interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the 
destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `GameBookParser` class creates an `XYZCommandParser` (`XYZ` is a
  placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse
  the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `GameBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser`
  interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/model/Model.java)

<img src="images/ModelClassDiagram.png" width="800" />


The `Model` component,

* stores the game book data i.e., all `GameEntry` objects (which are contained in a `GameEntryList` object).
* stores the currently 'selected' `GameEntry` objects (e.g., results of a search query) as a separate _filtered_ list
  which is exposed to outsiders as an unmodifiable `ObservableList<GameEntry>` that can be 'observed' e.g. the UI can
  be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they
  should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2122S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/gamebook/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component helps save Game Book data and User Preferences to a json file after every use, and read them from a json file while restarting the app.
* The `Storage` component also plays a key role in supporting other functions such as the analysis of average profits.
* It inherits from both `GameBookStorage` and `UserPrefsStorage`, which means it can be treated as either one.
* It depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.gamebook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Add feature

The below provides a step-by-step break down of the mechanism for adding a game entry. Assume that the user has already
launched `GameBook` and the app has loaded data from storage.

1. The user inputs a command, such as `add /g Poker /p 35 /dur 40m /loc Resort World Sentosa Casino
   /dur 50m /date 2021-10-21 15:10` which calls upon `MainWindow#executeCommand()`.
2. `MainWindow#executeCommand()` passes the user's input to `LogicManager#execute()`to process, which calls upon `GameBookParser#parseCommand()`.
3. `GameBookParser#parseCommand()` parses the input with the help of `AddCommandParser#parse()`. If input is valid, a new `GameEntry` object is created,
   followed by an `AddCommand` object containing the `GameEntry`. The `AddCommand` object is then returned by `GameBookParser#parseCommand()`. 
4. `LogicManager#execute()` executes `AddCommand` by calling `AddCommand#execute()`, which adds the new entry to a `List` and sorts it by date.
5. `AddCommand#execute()` then encapsulates the result of the command execution in a new `CommandResult` object
   to its caller, `LogicManager#execute()`.
6. `LogicManager#execute()` calls `Storage` to store the new game entry list and returns `CommandResult` to `MainWindow#executeCommand()`.
7. `MainWindow#executeCommand()` executes `resultDisplay#setFeedbackToUser()` to display the message from `CommandResult` to the user.
8. `MainWindow#executeCommand()` calls `StatsPanel#updateStats()` and `GraphPanel#updateGameEntryList()` to update the statistics and graph with the new game entry list.

The following activity and sequence diagrams illustrate the mechanism of adding a new game entry. To reduce clutter, the
sequence diagram will only focus on Logic and Model components.
![Activity diagram of an add command](images/AddActivityDiagram.png)
![Sequence diagram of an add command (Ui)](images/AddSequenceDiagram(Ui).png)
![Sequence diagram of an add command (Logic onwards)](images/AddSequenceDiagram(Logic).png)


### Edit feature
Editing a game entry requires user input from the CLI. The `GameBook` parser will check the validity of the input. It
is valid if
* The list of games currently displayed is not empty, and the chosen index is a valid index.
* At least one field is chosen to be edited.
* The formats of all fields entered, such as game type, start amount, end amount, location etc must be in the correct format.

Assume that the user has already launched `GameBook` and the app currently displays this:
![GameBook UI](images/GameBook.png)

The below provides a step-by-step break down of the mechanism for editing a game entry.
1. The user inputs `edit 1 /g Mahjong` which calls upon which calls upon `MainWindow#executeCommand()`.
2. `MainWindow#executeCommand()` passes the user's input to `LogicManager#execute()` to process.
3. `LogicManager#execute()` calls `GameBookParser#parseCommand()` to parse the input.
4. `GameBookParser#parseCommand()` parses the input and returns an `EditCommand`.
5. `LogicManger#execute()` executes `EditCommand` by calling `EditCommand#execute()`.
6. `LogicManager#execute()` selects the `GameEntry` to be edited, creates an edited copy of it, and calls `ModelManager#setGameEntry()`
   to replace the original `GameEntry` with the edited one. It then returns a `CommandResult` to `LogicManager#execute()`.
7. `LogicManager#execute()` calls `Storage` to store the new game entry list and returns `CommandResult` to `MainWindow#executeCommand()`.
8. `MainWindow#executeCommand()` executes `resultDisplay#setFeedbackToUser()` to display the message from `CommandResult` to the user.

The following diagrams illustrates the process of executing an `edit` command.

![Activity diagram of an edit command](images/EditActivityDiagram.png)
![Sequence diagram of an edit command](images/EditSequenceDiagram.png)


### Deleting a Game Entry
Deleting a game entry requires user input from the CLI. The user should obtain the index of the game entry to be deleted
from `GameEntryListPanel`, which will show a list of game entries previously added by the user. The format of input should
be `delete [INDEX]`. `GameBookParser` will check for the validity of the input. It is valid if
* The index specified by the user is bigger than 0 and smaller or equal to the number of game entries in the list.

The below provides a step-by-step break down of the mechanism for deleting a game entry. Assume that the user has already
launched `GameBook` and the app has loaded data from storage. Assume also that the current game entry list contains more than 1 game entry.
1. The user inputs `delete 1` which calls upon `MainWindow#executeCommand()`.
2. `MainWindow#executeCommand()` passes the user's input to `LogicManager#execute()` to process.
3. `LogicManager#execute()` calls `GameBookParser#parse()` to parse the input.
4. `GameBookParser#parse()` parses the input and returns a `DeleteCommand`.
5. `LogicManger#execute()` executes `DeleteCommand` by calling `DeleteCommand#execute()`.
6. `DeleteCommand#execute()` calls `ModelManager#deleteGameEntry()` to delete the game entry from the game entry
   list and returns a `CommandResult`to `LogicManager#execute()`.
7. `LogicManager#execute()` calls `Storage` to store the new game entry list and returns `CommandResult` to `MainWindow#executeCommand()`.
8. `MainWindow#executeCommand()` executes `resultDisplay#setFeedbackToUser()` to display the message from `CommandResult` to the user.

### Graphical Analysis of Average Profits by Date

The graphical feature is facilitated by the `GraphPanel` and `Average` classes along with the `MainWindow` class. 
It is implemented using the JavaFX `LineChart` and `XYSeries` Classes.

`GraphPanel` currently supports two methods:
* `drawGraphOfLatestKDates(int)` - gets the TreeMap with the specified number of latest dates and average profits and adds them to the series and 
  the line chart.
* `updateGameEntryList()` - reassigns the value of the new GameEntry list to the current GameEntry list

In addition, the following method from `Average` is also used:
* `Average#getAverageData()` - returns a TreeMap with the dates as values and average profit as keys

Found below is a step-by-step break down of the mechanism of creating and updating the graph: 
1. Upon initialising the `MainWindow`, a new `GraphPanel` is created with the game entry list from `logic`.
   During this, a new `XYChart.Series` is also initialised.
2. This new `GraphPanel` is then added to the `graphPanelPlaceholder` in the main window after which 
   `GraphPanel#drawGraphOfLatestKDates(int)` is called with the k value of `ModelManager.NUMBER_OF_DATES_TO_PLOT` which is set to 20.
3. When `GraphPanel#drawGraphOfLatestKDates(int)` is called, the data for average profits is added to the `averageProfits` TreeMap by 
   calling `Average#getAverageDate()`on the game entry list.
4. Then, the line chart is cleared and the series is added to the line chart
5. The series is then cleared and then the data from the latest k dates from `averageProfits` is added to the series, after which the 
   graph is plotted.
6. After executing a command, the graph panel is updated by calling the `GraphPanel#updateGameEntryList()` method
    on the graph panel with the updated game entry list. 
7. This resets the value of the current game entry list in the graph panel to the updated game entry list and the graph 
   is drawn again by calling the `GraphPanel#drawGraph()` method.
   
#### Mechanism:
* A `GraphPanel` object is created and initialised in the main window using the filtered list from `Storage`
  `drawGraph()` is called on the graph panel to draw the graph based on existing entries as the user starts the app.
* When the user enters a command, `executeCommand(String commandText)` in MainWindow is run during which
  `clearList()` is called on the graphPanel object
  to clear the existing series after which the command is executed.
* Before returning the result, `updateList()` is called on the graphPanel object to update the value of the
  modified list of game entries.
* This results in a new series being created with `StatsByDate#getStats()`, when it is called on the updated list
  value to generate a new graph.
* These steps repeat for every command entered by the user until the user exits the app.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to track earnings/losses across various casino games
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: manage gambling earnings/losses faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | add a new game entry         |                  |
| `* * *`  | new user                                   | delete my game entries         | clear entries with erroneous data                 |
| `* * *`  | new user                                   | log my gambling statistics on a per game basis    | keep track of my spending                 |
| `* * *`  | new user                                   | input any type of game         | include any game I want instead of choosing from preset list of games                 |
| `* * *`  | new user                                   | save my net earnings and losses         | view data from my previous games                 |
| `* * *`  | forgetful user                             | edit my previous game entries         | add details I missed out on previously                 |
| `* *`    | user who frequents multiple gambling locations   | input location I played at               | organize and sort my data by location                                                                       |
| `* *`    | user who does not like scrolling                | find game entries using relevant keywords                | view my data quickly                                  |
| `* *`    | expert user                | see analysis of my game statistics               | evaluate my game performance                                   |
| `* *`    | user who vlogs                | View an aesthetically pleasant UI                | show it to my audience in my videos                                   |
| `* *`    | user who is easily affected by emotions                | tag games which I made emotional decisions in                | understand how it has affected my earnings                                   |
| `* *`    | user who gambles frequently                | see the statistics on my expenditure                | justify my gambling habits to my family, that it is not an addiction                              |
| `*`      | user who switches between computers frequently | backup my data securely           | easily create copies of it to other computers                                                 |
| `*`      | user whose hard drive is almost full | specify where the app data is stored           |                                                  |
| `*`      | user who gambles against friends frequently | analyze my performance when playing against specific friends           | avoid betting large amounts when playing against stronger friends                                                 |
| `*`      | busy user | see how much time I spend on each game           | utilize my time better                                                 |
| `*`      | busy user | see the profit per unit time analysis of different games           | decide which game to play to maximize rate of earnings                                                 |
| `*`      | expert user | see the statistics of games with specific tags           | evaluate my performance on games with the selected tags                                                 |
| `*`      | expert user | compare statistics across different days of the week           | evaluate my performance on different days                                                 |
| `*`      | thrillseeking user | see mean and variance calculations for different games           | choose the one with the highest variance to have fun                                                 |
| `*`      | user who frequents places with an entry fee | add costs such as entry-fee into the overall calculation           | get a more accurate view of my profits                                                 |
| `*`      | organized user | save different filters or sort conditions           | quickly view custom selections that are important to me                                                 |
| `*`      | user who loves alcohol | tags games that I played when I am drinking           | see how alcohols affect my performance                                                |


### Use cases

(For all use cases below, the **System** is the `GameBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add an entry**

**MSS**

1. User requests to add a new game entry.
2. GameBook adds in the new entry and displays a success message, along with any accompanying warnings.

   Use case ends.

**Extensions**

* 1a. User entered the entry in an incorrect format.
  * 1a1. GameBook shows an error message to inform user what went wrong.

    Use case resumes at step 1.
  
  
**Use case: View entries**

**MSS**

1. User requests to list all entries.
2. GameBook shows all entries.
3. 
   Use case ends.

**Extensions**

* 1a. User requests to <u>find entries by keyword (UC**)</u>.
    * 1a1. GameBook shows a filtered list of entries.
  
      Use case ends.

**Use case: Edit an entry**

**MSS**

1. User enters an edit command.
2. GameBook updates itself with the edited entry.

   Use case ends.

**Extensions**

* 1a. User entered the edit command in an incorrect format.
    * 1a1. GameBook shows an error message, telling the user the correct command format.

      Use case resumes at step 1.
    
* 1b. User does not choose any field to edit
    * 1b1. GameBook shows an error message, telling the user to select at least 1 field to edit.
    
      Use case resumes at step 1.
    
* 1c. User's edit does not change at least one field of the selected game entry.
    * 1c1. GameBook shows an error message, telling the user that the selected edit does not change the selected game entry.

      Use case resumes at step 1.
    
**Use case: Delete an entry**

**MSS**

1.  User <u>requests to view entries (UC**)</u>
2.  User requests to delete a specific entry in the list
3.  GameBook deletes the entry

    Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.

    * 2a1. GameBook shows an error message.

      Use case resumes at step 2.

**Use case: Clear all entries**

**MSS**

1. User requests to clear all entries
2. GameBook prompts for confirmation
3. User confirms
4. The list is cleared

    Use case ends.

**Extensions**

* 3a. User cancels the operation

    Use case ends.

**Use case: Find help**

**MSS**

1. User requests for help
2. A link is provided

   Use case ends.

**Extensions**

* 3a. User cancels the operation

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to hold up to 1000 entries without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4. A novice should be able to grasp the basic functionalities of the system without too much difficulty.
5. The user interface should be clear, so that new users can use the app without too much difficulty.
6. A user should be able to easily back up data.
7. The product is offered free online.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_
### Adding a game entry
1. Adding a game entry:
   1. Test case: `add /g poker /s 20 /e 34 /date 2021-11-05 10:15`
      
      Expected: A new poker entry with $14 profit played on 2021-11-05 10:15 is added into the list of game entries, which
      is sorted by date.
   2. Test case: `add /g poker /p 30 /date 2021-11-05 10:15`
      
      Expected (assuming this command is executed after (i)): A new poker entry with $30 profit played on 2021-11-05 10:15 is added into the list of game entries, which
      is sorted by date. GameBook also displays an alert to inform the user that an entry with the same name and date already
      exists.
   3. Test case: `add /g blackjack`

      Expected: GameBook displays an error message, because user must provide a way for GameBook to know the profit, either
      by providing start amount (/s) and end amount (/e), or by providing profit directly (/p).
   4. Other incorrect add commands to try: `add`, `add /g poker /p ten`, `add /g poker /p 30 /date 1st january`.
### Editing a game entry

Suppose GameBook currently displays this:<br>
<img src="images/ArchitectureDiagram.png" width="280" />

1. Editing a game entry when the list of games displayed is not empty.

    1. Prerequisites: The list of games shown is non-empty.

    1. Test case: `edit 1 /g mahjong`<br>
       Expected: First game entry has its name change from `Poker` to `Mahjong`. No other changes are observed.

    1. Test case: `edit 1 /date 2021-09-22 12:40`<br>
       Expected: First game is now at index 2, while the game originally at index 2 is now at index 1. The list of games shown are still sorted by date.

    1. Test case: `edit 0 ...`<br>
       Expected: No game entry is edited. Error details shown in the status message.

    1. Other incorrect edit commands to try: `edit x`, `edit y /s 10`, `edit y /someWrongFlag`, `...` (where x is larger than list size, and y is a valid index)<br>
       Expected: Similar to previous.

_{ more test cases …​ }_

### Deleting a game entry

1. Deleting a game entry while all game entries are being shown

    1. Prerequisites: The list of game entries is shown by default, or the `list` command is used to list all game entries.

    1. Test case: `delete 1`<br>
       Expected: First game entry is deleted from the list. Details of the deleted game shown in the status message.

    1. Test case: `delete 0`<br>
       Expected: No game entry is deleted. Error details shown in the status message.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

_{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
