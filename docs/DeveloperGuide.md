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

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/Main.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete-stall s/1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`,
`StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that
are in the `src/main/resources/view` folder. For example, the layout of the
[`MainWindow`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete-stall s/1")` API call as an example.

![Interactions Inside the Logic Component for the `delete-stall s/1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a stall).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `FoodNotesParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Stall` objects (which are contained in a `UniqueStallList` object).
* stores the currently 'selected' `Stall` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Stall>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W10-4/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.address.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Sort stalls by price feature

#### Implementation

The proposed sort stalls price functionality is facilitated by `SortStallsPriceCommand`.

The following sequence diagram shows how the sort operation works:

![SortPriceSequenceDiagram](images/SortPriceSequenceDiagram.png)

`sortStallPrice` is a method in `ModelManager` that sorts the filtered stall list by price.
It calls `sortByPrice` in `UniqueStallList` which sorts the list of stalls by price which makes use of
`StallPriceComparator` to compare the prices of the stalls.

#### Design considerations:
**Aspect: How sort stalls price executes:**

* **Alternative 1 (Current choice):** Saves the sorted list of stalls in FoodNotes.
    * Pros: User does not need to re-sort list after every command.
    * Cons: Original ordering of stalls will be lost.

* **Alternative 2:** Displays the sorted list of stalls only.
    * Pros: Easy to implement.
    * Cons: User needs to re-sort stalls by price after every command if they want to view the sorted list.


### Add item and delete item feature

#### Implementation:

The add-item/delete-item mechanism is facilitated by `ModelManager`. It extends `Model`, stored internally as an `UniqueItemLis`.

These operations are exposed in the `Model` interface as `Model#addItem()` and `Model#deleteItem()` respectively with the addition of getters and setters.

The following sequence diagram shows how the add-item operation works:

![AddItemSequenceDiagram](images/AddItemSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddItemCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `delete-item` command does the opposite — it calls `Model#deleteItem()`, which deletes the specified item from its specified stall.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `stallIndex` or `itemIndex` is at index out of range of `filteredStallList.size() - 1` or , then there are no stalls to delete the item from.
</div>

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/AddItemActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: Number of fields needed to be entered by the user:**

* **Alternative 1 (current choice): Only require them to enter the stall they belong to and the name of the item**
    * Pros: The review field is optional and users can add items without a review.
    * Cons: Causes some fields to be null when initialised (e.g. `rating` and `review`) and more code is needed to implement.

* **Alternative 2: Require all fields required to be present when adding an item:**
    * Pros: Less code is needed to implement.
    * Cons: Users are restricted to only adding items when they have a review.


### Find-by-item feature

#### Implementation

The find item feature is facilitated by `FindItemCommand` that extends `Command`.

The following sequence diagram shows how the find item operation works:

![FindItemSequenceDiagram](images/FindItemSequenceDiagram.png)

The `MenuContainsKeywordsPredicate` is used to filter the list of stalls in FoodNotes. It is created with a list of keywords, and it checks if the menu items of a stall contains any of the keywords.
#### Design considerations:

**Aspect: Number of fields needed to be entered by the user:**

* **Alternative 1 (current choice):** Allows the user to search for stalls containing any of the keywords.
    * Pros: Users can search for multiple items at once, for example they can look for stalls that sell either "chicken" or "apple".
    * Cons: More complicated to implement.

* **Alternative 2:** Only allow the user to search for one keyword at a time.
    * Pros: Easy to implement as parsing one keyword is more simple than parsing multiple keywords.
    * Cons: Less flexible for the user.


### Add stall review feature

#### Implementation

The add stall review feature is facilitated by `AddStallReviewCommand` that extends `Command`.

The following sequence diagram shows how the add stall review operation works:

<img src="images/ReviewStallDiagram.png"/>

#### Design considerations:

**Aspect: Number of fields needed to be entered by the user:**

* **Alternative 1 (current choice):** Allows the user to enter the stall's review, rating and description.
    * Pros: Users can enter multiple fields for the stall's review at once, do not have to add individual fields one by one.
    * Cons: Some users may feel that it is too troublesome to enter multiple fields at once.

* **Alternative 2:** Only allow the user to enter one field at a time.
    * Pros: Easy to implement as parsing one keyword is more simple than parsing multiple keywords.
    * Cons: More troublesome for the user as they have to enter multiple fields one by one.

--------------------------------------------------------------------------------------------------------------------
### View stall feature

#### Implementation

The view stall feature is facilitated by `ViewStallCommand` that extends `Command`.

The following sequence diagram shows how the find item operation works:

![ViewStallDiagram](images/ViewStallDiagram.png)

The `updateFilteredStallListPredicate` is used to filter the list of stalls in FoodNotes. It is created with a the name of the stall entered by the user.

#### Design considerations:
**Aspect: Details displayed:**

* **Alternative 1 (current choice):** Details of the stalls (menu items and review) are only displayed when the view-stall command is entered. Otherwise, only the average price of the stall and star rating will be shown.
  * Pros: The interface will not be cluttered with information. The menu items and detailed reviews will only be shown when users want to find out more.
  * Cons: More cards and fxml panels will be needed, making it more complicated to implement.

* **Alternative 2:** All the information is displayed in the list of stalls.
  * Pros: Easy to implement as only one card and fxml stallPanel is required.
  * Cons: The interface will be cluttered with information and when there is many menu items, the users will have to scroll within each card, making it less user-friendly.

--------------------------------------------------------------------------------------------------------------------


## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------
## **Planned Enhancements**

### Make it clearer to the user that the list of stalls is filtered.
**Current Implementation:**
* **Current Issue:** Users can view a filtered list of stalls when they use commands such as `find-by-location` and `find-by-item`. In the case where there is only 1 stall in the list and the user performs a stall deletion, the user will see a page showing an empty list of stall. This might cause confusion as the user might think that all the stalls are deleted.
* **Example:**
1. User entered `find-by-location Deck`.
2. A list of stall containing one stall is displayed.
3. User entered `view-stall s/1` to view the details of the stall.
4. User entered `delete-stall s/1` to remove the stall from FoodNotes.
5. List of stall with 0 stall is displayed.
6. The list of stall is still filtered by location which is the Deck, but it might give the wrong impression that there is 0 stall in the list now.

**Proposed Solution:**

We propose to enhance the filter stalls commands to display the list of stalls with a short description saying that the list is filtered.
1. Edit the card that displays the list of stalls.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* Student foodie from NUS
* Has the need to remember reviews of many food stores and food items
* Has a tight budget
* Prefers desktop apps over other types
* Can type fast
* Prefers typing to mouse interactions
* Is comfortable using CLI apps

**Value proposition**: Manage and view food reviews faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …                            | I want to …                                                              | So that I can…                                                    |
|----------|-----------------------------------|--------------------------------------------------------------------------|-------------------------------------------------------------------|
| `* * *`  | new user                          | add reviews to stalls                                                    | remember what I think about the food stall.                       |
| `* * *`  | new user                          | delete reviews from stalls                                               | delete review of the food stall if I change my mind about it      |
| `* * *`  | new user                          | add restaurant                                                           | add restaurants if I want to                                      |
| `* * *`  | new user                          | delete restaurant                                                        | change my mind about the restaurant                               |
| `* * *`  | new user                          | add menu item                                                            | review specific food from a specific restaurant                   |
| `* * *`  | new user                          | delete menu item                                                         | remove it if I changed my mind                                    |
| `* * *`  | new user                          | view restaurants                                                         | see the list of restaurants I have saved                          |
| `* * *`  | new user                          | view the user guide easily                                               | learn more about the product when needed                          |
| `* * *`  | new user                          | add reviews to menu items                                                | so that I can remember what I think about the menu item           |
| `* * *`  | new user                          | delete reviews from menu items                                           | delete review of the menu item if I change my mind about it       |
| `* *`    | hungry university student         | browse the ‘discount’ page for nearby campus eateries offering discounts | save money on meals                                               |
| `* *`    | busy student                      | browse the daily specials page                                           | find on-campus restaurants and quickly decide where to grab lunch |
| `* *`    | experienced user                  | save my favorite places                                                  | easily access them                                                |
| `* *`    | new user                          | look at menu items of each store                                         | know what I can order                                             |
| `* *`    | vegetarian student                | filter food options to only show vegetarian choices                      | find what I can eat                                               |
| `* *`    | student with allergies            | filter food options by allergen information                              | eat safely                                                        |
| `* *`    | health-conscious student          | see nutritional information for menu items                               | make informed choices about what I eat                            |
| `* *`    | new user                          | sort by the most highly rated stores                                     | see what is popular                                               |
| `* *`    | student trying to save money      | sort food items of stores by price                                       | plan what to eat to save money                                    |
| `* *`    | student who often studies late    | search for food places by filtering by opening hours                     | quickly find food places to go for late-night suppers             |
| `*`      | see how to travel to the stalls   | See how to travel to the stalls                                          | find my way easily                                                |
| `*`      | user interested in sustainability | identify local ingredients                                               | support environmentally conscious dining choices                  |
| `*`      | student always on the move        | receive alerts about pop ups                                             | seize food opportunities wherever I go.                           |
| `*`      | Muslim student                    | know which halal certified                                               | eat halal food.                                                   |
*{More to be added}*

### Use cases

(For all use cases below, the **System** is `FoodNotes` and the **Actor** is the `user`, unless specified otherwise)



**Use case: Add a stall**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to add a stall to the list
4.  FoodNotes add the stall

    Use case ends.

**Extensions**

* 3a. The stall name and location is not specified

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

**Use case: Add a review to a stall**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to add a review to a stall
4.  FoodNotes adds a review to the stall

    Use case ends.

**Extensions**

* 2a. The list of stalls is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

* 3b. The star rating or description is not specified

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

**Use case: Delete a review from stall**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to view a stall
4.  FoodNotes shows the review and menu of the stall
5.  User requests to delete the review
6.  FoodNotes deletes the review

    Use case ends.

**Extensions**

* 3a. The given index is invalid.

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

* 4a. There are no reviews.

  Use case ends.

**Use case: Add a menu item to a stall**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to add a menu item to a stall
4.  FoodNotes add the menu item

    Use case ends.

**Extensions**

* 2a. The list of stalls is empty.

  Use case ends.

* 3a. The given index is invalid

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

**Use case: Add a review to a menu item**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to view a stall
4.  FoodNotes shows the menu items and review of the stall
5.  User requests to add a review to a menu item in the stall
6.  FoodNotes add review to the menu item

    Use case ends.

**Extensions**

* 2a. The list of stalls is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. FoodNotes shows an error message.

      Use case resumes at step 2.

* 4a. The star rating or description is not specified

    * 4a1. FoodNotes shows an error message.

      Use case resumes at step 2.

**Use case: Delete a menu item from stall**

**MSS**

1.  User requests to list stalls
2.  FoodNotes shows a list of stalls
3.  User requests to view a stall
4.  FoodNotes shows the review and menu of the stall
5.  User requests to delete a menu item
6.  FoodNotes deletes the menu item

    Use case ends.

**Extensions**

* 4a. There are no menu items.

  Use case ends.

* 5a. The given index is invalid.

    * 5a1. FoodNotes shows an error message.

      Use case resumes at step 4.

**Use Case: View All Stalls**

**MSS**

1. User requests to view all stalls using the command `list`.
2. FoodNote retrieves and displays a list of all available stalls, including their names and locations.
3. User reviews the list of stalls.

   Use case ends.

**Extensions**

* 1a. No stalls are available in the database:
    * FoodNote displays a message indicating that there are no stalls available.

  Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 stalls without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Card**: Used to display information including texts and images, in a structured and organised way.
* **GUI**: Graphical User Interface
* **Jar file**: A Java Archive file used to distribute and run Java applications
* **Result display**: The component in the main window that displays the success/error messages to users whenever a command is entered.
* **Left panel**: The component in the main window that displays the list of stalls in FoodNotes. When filtering commands such as `find-by-location` are used, the left panel will display the filtered list of stalls.
* **Right panel**: The component in the main window that displays the list of valid commands to be used in FoodNotes. When `view-stall` or `view-item` commands are used, the right panel will display the details of the stall/menu item.


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


### Adding a stall

1. Adding a stall to FoodNotes.

1. Prerequisites: None.

1. Test case: `add-stall n/Pasta Express l/Deck`<br>
   Expected: Pasta Express located in Deck is added to the list. The result display shows a success message. The list of the stalls on the left panel is updated.

1. Test case: `add-stall n/Indian Shop`<br>
   Expected: No stall is deleted. Error details shown in the result display.

1. Other incorrect add commands to try: `add-stall`, `add-stall n/abc l/Deck l/Frontier`, `...` (Stall exists at multiple locations)<br>
   Expected: Similar to previous.

### Deleting a stall

1. Deleting a stall while all stalls are being shown

   1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

   1. Test case: `delete-stall s/1`<br>
      Expected: First stall is deleted from the list. Details of the deleted stall shown in the result display. List of stalls in the left panel is updated.

   1. Test case: `delete-stall s/0`<br>
      Expected: No stall is deleted. Error details shown in the result display.

   1. Other incorrect delete commands to try: `delete-stall`, `delete-stall s/x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Viewing a stall

1. Viewing a stall while all stalls are being shown

  1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

  1. Test case: `view-stall s/1`<br>
     Expected: Result display shows a success message. Details of the stall is shown on the left panel.

  1. Test case: `view-stall s/0`<br>
     Expected: No stall is deleted. Error details shown in the result display.

  1. Other incorrect view commands to try: `view-stall`, `view-stall s/x`, `...` (where x is larger than the list size)<br>
     Expected: Similar to previous.

### Reviewing a stall

1. Reviewing a stall while the stall details are being shown.

  1. Prerequisites: View the stall using the `view-stall` command.

  1. Test case: `review-stall s/1 r/4 d/The food here is really good`<br>
     Expected: The result display shows a success message. The stall details in the right panel is updated with the star ratings and review descriptions.

  1. Test case: `review-stall s/1 r/4`<br>
     Expected: No stall review is added. Error details shown in the result display.

  1. Other incorrect review commands to try: `review-stall s/1 r/3 r/5`(duplicate prefix), `review-stall s/1 d/xyz`, `...` (missing rating)<br>
     Expected: Similar to previous.


### Adding a menu item to a stall

1. Adding a menu item while the stall details are shown

   1. Prerequisites: View the stall details with the `view-stall` command. The menu items of the stall is displayed in the left panel.

   1. Test case: `add-item s/1 n/Pork Chop p/4.50`<br>
      Expected: Result display shows a success message. The menu item is added the list of menu items in the left panel.

   1. Test case: `add-item s/1 n/PorkChop p/$4.00`<br>
      Expected: No item is added. Error details shown in the result display.

   1. Other incorrect add commands to try: `add-item`, `add-item s/x n/x p/1.00`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Deleting a menu item to a stall

1. Deleting a menu item while the stall details are shown

  1. Prerequisites: View the stall details with the `view-stall` command. The menu items of the stall is displayed in the left panel.

  1. Test case: `delete-item s/1`<br>
     Expected: First item is deleted from the list. Details of the deleted item shown in the result display. List of items in the left panel is updated.

  1. Test case: `delete-item s/0`<br>
     Expected: No item is deleted. Error details shown in the result display.

  1. Other incorrect delete commands to try: `delete-item`, `delete-item s/x`, `...` (where x is larger than the list size)<br>
     Expected: Similar to previous.

### Viewing a menu item to a stall

1. Viewing a menu item while the stall details are shown

   1. Prerequisites: View the stall details with the `view-stall` command. The menu items of the stall is displayed in the left panel.

   1. Test case: `view-item s/1`<br>
      Expected: Result display shows a success message. Details of the item are shown on the right panel.

   1. Test case: `view-item s/0`<br>
      Expected: No item details are shown. Error details shown in the result display.

   1. Other incorrect delete commands to try: `view-item`, `view-item s/x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

### Sorting the stalls by location

1. Sorting the stalls by locations while all stalls are shown.

  1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

  1. Test case: `sort-stalls-location`<br>
     Expected: Result display shows a success message. The list displayed in the left panel is sorted by alphabetical order based on their location.

  1. Test case: `sort-stalls-location deck`<br>
     Expected: Stalls are not sorted. Error details shown in the result display.

### Sorting the stalls by location

1. Sorting the stalls by rating while all stalls are shown

   1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

   1. Test case: `sort-stalls-rating`<br>
      Expected: Result display shows a success message. The list displayed in the left panel is sorted in descending order based on ratings.

   1. Test case: `sort-stalls-rating 1`<br>
      Expected: Stalls are not sorted. Error details shown in the result display.

### Sorting the stalls by price

1. Sorting the stalls by price while all stalls are shown

  1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

  1. Test case: `sort-stalls-price`<br>
     Expected: Result display shows a success message. The list displayed in the left panel is sorted in descending order based on ratings.

  1. Test case: `sort-stalls-price 1.00`<br>
     Expected: Stalls are not sorted. Error details shown in the result display.

### Filter stalls by stall name

1. Filtering out the stalls that contains the keyword in the name.

   1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

   1. Test case: `find-by-name Subway`<br>
      Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the name containing the keyword 'Subway'.

   1. Test case: `find-by-name Subway Express`<br>
       Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the name containing the keyword 'Subway' or 'Express'.

   1. Other incorrect find commands to try: `find-by-name`
      Expected: No stall is found. Error details shown in the result display.

### Filter stalls by stall location

1. Filtering out the stalls that contains the keyword in the location.

  1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

  1. Test case: `find-by-location Utown`<br>
     Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the location containing the keyword 'Utown'.

  1. Test case: `find-by-location Utown Frontier`<br>
     Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the location containing the keyword 'Utown' or 'Frontier'.

  1. Other incorrect find commands to try: `find-by-location`
     Expected: No stall is found. Error details shown in the result display.

### Filter stalls by item name

1. Filtering out the stalls that contains the keyword in the menu items.

  1. Prerequisites: List all stalls using the `list` command. Multiple stalls in the list.

  1. Test case: `find-by-item Chicken`<br>
     Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the name containing the menu item 'Chicken'.

  1. Test case: `find-by-item Chicken Pork`<br>
     Expected: Result display shows a success message. The list displayed in the left panel contains all the stalls with the name containing the keyword 'Chicken' or 'Pork'.

  1. Other incorrect find commands to try: `find-by-item`
     Expected: No stall is found. Error details shown in the result display.

### Help
1. Test case: `help`<br>
   Expected: The help window pops out and shows a general help message.

### Exit the application
1. Test case: `exit`<br>
   Expected: FoodNotes application closes


### Saving data

1. Dealing with missing/corrupted data files

   1. To simulate a corrupted file, you can edit addressbook.json directly and introduce some texts that does not follow the correct format.
   2. To fix the issue, delete addressbook.json from the data folder and re-launch the app. FoodNotes will now only contain the original pre-loaded information.

