---
layout: page
title: User Guide
---
## Table of Contents <a id="toc"></a>

---
1. [Introduction](#1-introduction)
   - [1.1. Purpose](#11-purpose)
2. [About the User Guide](#2-about-the-user-guide)
    - [2.1. Navigating the User Guide](#21-navigating-the-user-guide)
    - [2.2. Reading the User Guide](#22-reading-the-user-guide)
        * [2.2.1. Icons](#icons)
        * [2.2.2. Parameters](#parameters)
        * [2.2.3. GUI](#GUI)
3. [Quick Start](#3-quick-start)
4. [Features](#4-features)
   - 4.1 [General](#41-general)
   - 4.2 [Stalls](#42-stalls)
   - 4.3 [Items](#43-items)
5. [Glossary](#5-glossary)
6. [Command summary](#6-glossary)
   - 6.1 [Stall Commands](#61-stall-commands)
   - 6.2 [Item Commands](#62-item-commands)
   - 6.3 [General Commands](#63-general-commands)

<div style="page-break-after: always;"></div>

# 1. Introduction <a id="1-introduction"></a>
Welcome to the User Guide of **FoodNotes**!

Ever find yourself forgetting the delightful dishes you savored? Hungry for a way to recall your favorite food experiences? FoodNotes has the solution.

FoodNotes is a quick, simple and beautiful food diary made specifically for you, NUS student foodies. With FoodNotes, you can add stalls that you want to keep track of and manage your reviews for them.

This application is optimised for use via a Command Line Interface (CLI) this means that you operate the application by typing commands into a Command Box. If you are fast at typing, you can manage your food reviews faster than other Graphical User Interface (GUI) applications; GUI applications allow users to interact with the application through graphical icons such as buttons.
FoodNotes is available for the Linux, Unix, Windows and Mac OS operating systems.
<div markdown="block" class="index">

<div style="page-break-after: always;"></div>


# 2. About the user guide <a id="2-about-the-user-guide"></a>
## 2.1 Navigating the user guide <a id="21-navigating-the-user-guide"></a>

**For first time users**: 

1) the [Quick start](#quick-start) section provides instructions for you on how to get started. 
2) Once you have set up FoodNotes, you can check out the [Screen layout](#screen-layout) section to get familiar with the different components of FoodNotes. To learn the basics of using FoodNotes, head over to the [Features](#features) section.

**For experienced users:**
1) you can refer to the [Command summary](#command-summary) section for an overview of FoodNotes' commands.

If you have any queries about using FoodNotes, you can check out the [FAQ](#faq) section.

## 2.2 Reading the User Guide <a id="22-reading-the-user-guide"></a>
Before you jump into the next section, take a moment to familiarise yourself with the technical terms, symbols, and syntax used throughout this document. This subsection contains all the essential information you need to understand the content better.

### 2.2.1 Terminology related to the Graphical User Interface (GUI)
The following figure shows the GUI of FoodNotes. It is annotated with the name of the GUI components.

<p align="center">
<img src="images/userGuide/Frame.png" alt="Layout of GUI">
</p>
What the main components of the GUI do:

* _Data Display_: This is where **FoodNotes** displays the information of the page that you are viewing.

* _Result Display_: This is where **FoodNotes** displays guiding messages.

* _Command Box_: This is where you type your commands.

### 2.2.2 Icons <a id="icons"></a>

This section will run you through the icons used in this guide.

| Icon                 | Meaning                                                          |
|----------------------|------------------------------------------------------------------|
| `command`            |  A light blue highlight (called a code-block markup) indicates that this is a command that can be typed into the command box and executed by the application.        |              
| :information_source: | An information icon indicates that the enclosed text are notes regarding this section. |
| :warning:            | A warning sign indicates that the enclosed text is important, and usually entails details about potential errors. |
|  :bulb:              | A light bulb indicates that the enclosed text is a tip.|

<div style="page-break-after: always;"></div>

### 2.2.3 Command Syntax and Usage <a id="icons"></a>
Since FoodNotes is a CLI application, knowing how to use commands is very important. The following subsection will teach you how to read and use commands in FoodNotes. All commands in FoodNotes follow similar formats as described below and examples will be provided to help you understand their usage. Examples of commands and their formats will be written in code-blocks.

The table below explains some important technical terms. An example will be provided to help you visualize these terms.

|**Technical Term** | **Meaning**                                                                                                                |
|---------------------|----------------------------------------------------------------------------------------------------------------------------|
| Command word | The first word of a command. It determines the action that **FoodNotes** should perform.                                   |
| Parameter | The word or group of words following the command word. They are values given to a command to perform the specified action. |
| Prefix | A prefix is a word that precedes a parameter. It is used to identify the type of parameter that is being inputted.         |

### 2.2.2 Input parameters <a id="parameters"></a>

This section provides a summary of the parameters used when inputting commands into the application.

| Prefix | Parameter        | Meaning                                            | Input                                                                        |
|--------|------------------|----------------------------------------------------|------------------------------------------------------------------------------|
| `n/`   | `STALL_NAME`     | Name of the stall                                  | Alphanumeric value with 1 to 27 characters (inclusive)                       |
| `s/`   | `STALL_INDEX`    | Index of the stall in the list                     | Integer from 0 to 2147483647 (inclusive)                                     |
| `l/`   | `STALL_LOCATION` | Location of the stall                              | Alphanumeric value with 1 to 27 characters (inclusive)                       |
| `n/`   | `ITEM_NAME`      | Name of the item                                   | Alphanumeric value with 1 to 27 characters (inclusive)                       |
| `r/`   | `STALL_RATING`   | Rating of the stall                                | Integer from 0 to 5 (inclusive)                                              |
| `r/`   | `ITEM_RATING`    | Rating of the item                                 | Integer from 0 to 5 (inclusive)                                              |
| `d/`   | `DESCRIPTION`    | Descriptive review for the stalls or items         | Alphanumeric value with 1 to 27 characters (inclusive)                       |

--------------------------------------------------------------------------------------------------------------------

## 3. Quick start (To be updated) <a id="3-quick-start"></a>

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `FoodNotes.jar` from [here](https://github.com/se-edu/FoodNotes-level3/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your FoodNotes.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar foodnotes.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all stalls.

   * `add-stall n/Japanese stall l/Deck` : Adds a stall named `Japanese stall` located at `Deck` to the list of stalls.

   * `view-stall s/3` : Shows the 3rd stall shown in the current list.

   * `delete-stall s/3` : Deletes the 3rd stall shown in the current list.


1. Refer to the [Features](#features) below for details of each command.

<div markdown="block" class="alert alert-info">

:bulb: **Note:**<br>

* For new users, the application will contain all the data of NUS food stalls and their menu items
* If double-clicking `foodNotes.jar` does not work,

    1. Search for "Command Prompt" or "Terminal" on your computer.
    2. Navigate to the location where `foodNotes.jar` is saved via the terminal. (_Unsure how to navigate?_ You can try running this in your terminal: `cd Downloads`.)
    3. Run the following in the terminal: `java -jar foodNotes.jar`.


</div>

<div markdown="block" class="alert alert-warning">

:exclamation: **Warning:**<br>

Upon launching the application, some files responsible for the storage of your data will be created in a folder called `data` located in the same folder as `foodNotes.jar`. If you are a new user, you are advised not to edit these files. If the changes you made to the data file invalidates its format, FoodNotes will discard all your data and start with an empty data file.

</div>
</div>

--------------------------------------------------------------------------------------------------------------------

# 4. Features <a id="4-features"></a>

----
## 4.1 General Features <a id="41-general"></a>

-----

### 4.1.1 Viewing help : `help`

You can use this command to view the online user guide, which contains information about all the instructions in **FoodNotes**.

<div markdown="block" class="alert alert-info">

:information_source: Ensure that you have a stable internet connection and have a web browser on your computer in order to access the user guide.

</div>

**Format:**

`help`

**Example:**

In this example, you want to view the user guide to get information about the various commands in **FoodNotes**.

<div markdown="block" class="alert alert-white">

Requesting for help:

1. Type `help` into the *Command Box*.
1. Press `Enter` to execute.
1. A new window will appear as shown below. Click on the `Copy URL` button to copy the URL containing the **FoodNotes** user guide.
   ![HelpOutcome](images/userGUide/HelpOutcome.png)
1. Open your preferred web browser on your computer and paste the link into the address bar.
1. Press `Enter` to load the website.

Outcome:

1. Your browser will now show the user guide of **FoodNotes**. 
2. You can now access information about all commands in **FoodNotes** via the browser.

</div>

 ### 4.1.2 Exiting from the application: `exit`

You can use this command to exit **FoodNotes**.

<div markdown="block" class="alert alert-info">

**:information_source: This command will exit the application immediately. All data is saved automatically and will be reloaded upon restart of the application**

</div>

**Format:**

`exit`

**Example:**

In this example, you want to exit **FoodNotes**.

<div markdown="block" class="alert alert-white">

Exiting the application:

1. Type `exit` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The **FoodNotes** application window is now closed.

----
## 4.2 Stall Management <a id="41-general"></a>

-----
### 4.2.1 Listing all stalls : `list`

You can use this command to show a list of all stalls in the database.

**Format:**

`list`

**Example:**

<div markdown="block" class="alert alert-white">

Listing all your stalls:

1. Type `list` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The Result Display will show a success message.
1. You can now see all your stalls in the *lsit Panel*.
</div>

![ListOutcome](images/userGuide/listOutcome.png)

### 4.2.2 Viewing a stall : `view-stall`

You can use this command to view details from a specific stall from the database.

**Format:**

`view-stall s/[STALL_NUMBER]`

**Example:**

<div markdown="block" class="alert alert-white">

Viewing a specific stall:

1. Type `view-stall s/1` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The Result Display will show a success message.
1. You can now see the details of the specific stall you entered.
</div>

![ListOutcome](images/userGuide/viewStallOutcome.png)

### 4.2.3 Adding a stall : `add-stall`

You can use this command to add a new stall to the database.

**Format:**

`add-stall n/[STALL_NAME] l/[LOCATION]`

**Example:**

<div markdown="block" class="alert alert-white">

Adding a new stall:

1. Type `add-stall n/Subway l/Utown` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The Result Display will show a success message.
1. You have now added a new stall with the specified name and location.
</div>

![ListOutcome](images/userGuide/addStallOutcome.png)

### 4.2.4 Deleting a stall : `delete-stall`

You can use this command to delete a stall from the database.

**Format:**

`delete-stall s/[STALL_INDEX]`

**Example:**

<div markdown="block" class="alert alert-white">

Deleting the third stall from the list:

1. Type `delete-stall s/3` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The Result Display will show a success message.
1. You have now deleted the third stall from the list, and the list of stalls will be updated.
</div>

![ListOutcome](images/userGuide/deleteStallOutcome.png)

### 4.2.5 Editing a stall : `edit-stall`

You can use this command to edit existing stall name and location from the database.

**Format:**

`edit-stall s/[STALL_INDEX]`

**Example:**

<div markdown="block" class="alert alert-white">

Editing the third stall from the list:

1. Type `delete-stall s/3` into the *Command Box*.
1. Press `Enter` to execute.

Outcome:

1. The Result Display will show a success message.
1. You have now deleted the third stall from the list, and the list of stalls will be updated.
</div>

![ListOutcome](images/userGuide/deleteStallOutcome.png)



### 4.1.1 View all stalls
**Command function:** list

**Command format:** `list`

**Expected output (Success):**
```
Here is the list of all stalls,
sorted by price!
```
**Expected output (Fail):**
```
please enter list only
```
---
### 4.1.2 Find stall
**Command function:** Find stalls by name

**Command format:** `find-stall <KEYWORD>`

**Ex.:** `find-stall japan`

**Expected output (Success):**
```
Here is the list of stalls containing the keyword
```
**Expected output (Fail):**
```
re-enter in the format : find-stall <KEYWORD>
```
---
### 4.1.3 Find item

**Command function:** Find stalls by item

**Command format:** `find-item <KEYWORD>`

**Ex.:** find-item chicken

**Expected output (Success):**
```
Here is the list of stall containing the food items with this keyword
```
Chicken Rice at Deck has an item called Chicken Rice, and Western Food at Deck has an item called Chicken Chop,
thus both will be shown when you search for chicken.


**Expected output (Fail):**
```
re-enter in the format : find-item <KEYWORD>
```
--------------------------------------------------------------------------------------------------------------------
## 4.2 Stall Features <a id="42-stalls"></a>

----
### 4.2.1 Add Stall
**Command function:** Adding a stall

**Command format:** `add-stall n/<STALL_NAME>  l/<LOCATION>`

**Ex.:** `add-stall n/Japanese stall l/Deck`

**Expected Output (Success):**
```
Yay! Japanese stall at Deck is successfully added to list of stalls.
```
**Expected Output (Fail):**
```
re-enter in the format : add-stall n/STALL_NAME  l/LOCATION
```
---
### 4.2.2 View Stall
**Command function: Viewing a stall

**Command format:** `view-stall s/<STALL_NUMBER>`

**Ex.:** `view-stall s/1`

**Expected output (Success):**
```
Here are the stall details
```
<div markdown="block" class="alert alert-info">

:bulb: **Note:**<br>

The stall review and list of menu items will be shown on the right panel

</div>

**Expected output (Fail)**:
```
re-enter in the format : view-stall s/STALL_NUMBER
```
---
### 4.2.3 Delete Stall
**Command function:** Deleting a stall

**Command format:** `delete-stall s/<STALL_NUMBER>`

**Ex.:** `delete-stall s/1`

**Expected output (Success):**
```
Japanese Stall ★★★★☆ has been deleted.
```

**Expected output (Fail):**

```
re-enter in the format : delete-stall s/STALL_NUMBER
```
---
### 4.2.4 Review Stall
**Command function:** Reviewing a stall

**Command format:** `review-stall s/<STALL_NUMBER> r/<STALL_RATING> d/<DESCRIPTION>`

**Ex.:** `review-stall s/1 r/5 d/the auntie very chio`

**Expected output (Success):**
```
Yay! You have added a rating of ★★★★★ for the Japanese Stall
Review: the auntie very chio
```
**Expected output (Fail):**
```
re-enter in the format : review-stall s/STALL_NUMBER r/STALL_RATING d/DESCRIPTION
```
**Acceptable values:**
- s/: Positive Integer less than or equal to the size of list
- r/: Integer from 0 to 5
- d/: String

**1.5 Sort stalls by location:** sort-stalls-locations

**Command format:** sort-stalls-locations

**Ex.:** sort-stalls-locations

**Expected output (Success):**
```
Sorted all stalls by location in alphabetical order.
```
**Expected output (Fail):**
```

```
**Acceptable values:**
- s/: Positive Integer less than or equal to the size of list
- r/: Integer from 0 to 5
- d/: String

--------------------------------------------------------------------------------------------------------------------
## 4.3 Item Features <a id="43-items"></a>

----
### 4.3.1 Add item to a stall
**Command function:** Adding an item to a stall

**Command format:** `add-item s/<STALL_NUMBER> i/<ITEM>`

**Ex.:** `add-item s/1 i/Chicken Rice`

**Expected output (Success):**
```
Yay! Chicken Rice is successfully added as a menu item for Japanese Stall at Deck
```
**Expected output (Fail):**
```
re-enter in the format : add-item s/STALL_NUMBER i/ITEM
```
**Acceptable values:**

- s/: Positive Integer less than or equal to the size of list,

- i/: String
---
### 4.3.2 View item
**Command function:** Viewing an item from a stall

**Command format:** `view-item s/<STALL_NUMBER> i/<ITEM_NUMBER>`

**Ex.:** `view-item s/1 i/1`

**Expected output (Success):**
```
Here are the details of this menu item
```

**Expected output (Fail):**

```
re-enter in the format : view-item s/STALL_NUMBER i/ITEM_NUMBER
```
**Acceptable values:**
- s/: Positive Integer less than or equal to the size of list,
- i/: Positive Integer less than or equal to the size of list
---
### 4.3.3 Delete an item from a stall
**Command function:** Deleting an item from a stall

**Command format:** `delete-item s/<STALL_NUMBER> i/<ITEM_NUMBER>`

**Ex.:** `delete-item s/1 i/1`

**Expected output (Success):**
```
Chicken Karaage Rice ★★★★☆ has been deleted from Japanese Stall
```
**Expected output (Fail):**
```
re-enter in the format : delete-item s/STALL_NUMBER i/ITEM_NUMBER
```
**Acceptable values:**
- s/: Positive Integer less than or equal to the size of list
- i/: Positive Integer less than or equal to the size of list

---
### 4.3.4 Reviewing an item from a stall
**Command function:** Reviewing an item

**Command format:** `review-item s/<STALL_NUMBER> i/<ITEM_NUMBER> r/<ITEM_RATING> d/<DESCRIPTION>`

**Ex.:** `review-item s/1 i/1 r/5 d/Tasty chicken rice with a good amount of meat and rice. The chilli is also very good.`

**Expected output (Success):**
```
Yay! You have added a review for the White Chicken Rice at the Chicken Rice Stall at Deck.
Review:
★★★★★
Tasty chicken rice with a good amount of meat and rice. The chilli is also very good.
```
**Expected output (Fail):**
```
re-enter in the format: review-item s/STALL_NUMBER i/ITEM_NUMBER r/ITEM_RATING d/DESCRIPTION
```
**Acceptable values:**
- s/: Positive Integer less than or equal to the size of stall list
- i/: Positive Integer less than or equal to the size of menu
- r/: Integer from 0 to 5
- d/: String
---
### 4.3.5 Delete an item review
**Command function:** Deleting an item review

**Command format:** `delete-item-review s/<STALL_NUMBER> i/<ITEM_NUMBER>`

**Ex.:** `delete-item-review s/1 i/1`

**Expected output (Success):**
```You have deleted the review for the White Chicken Rice at the Chicken Rice Stall at Deck.```

**Expected output (Fail):**
```
re-enter in the format: delete-item-review s/STALL_NUMBER i/ITEM_NUMBER
```

**Acceptable values:**
- s/: Positive Integer less than or equal to the size of stall list
- i/: Positive Integer less than or equal to the size of menu
--------------------------------------------------------------------------------------------------------------------


# 5. Glossary <a id="5-glossary"></a>

| Term              | Explanation                                                                                                                                             |
|-------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **FoodNotes**     | Can refer to name of the application as a whole or to the app’s storage file.<br>                                                                       |
| **Stalls**        | Refers to the name of the food stall.<br>                                                                                                               |
| **Item**          | Refers to a specific food item. <br>                                                                                                                    |
| **CLI**           | A Command Line Interface (CLI) is a text-based user interface that allows users to type text commands instructing the program to do specific tasks.     |
| **GUI**           | A Graphical User Interface (GUI) is a form of user interface that allows users to interact with the program through graphical icons instead of text-based user interfaces. |
| **Absolute Path** | The complete details needed to locate a file or folder, starting from the root element.                                                                 |

--------------------------
# 6. Command summary <a id="6-command-summary"></a>

## 6.1 Stall-Related Commands <a id="61-stall-commands"></a>

| Features                 | Format, Examples                                                                                                                                                     |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add stall**            | `add-stall n/<STALL_NAME> l/<LOCATION>` <br> e.g.`add-stall` n/Japanese Stall l/Deck                                                                                 |
| **View stall**           | `view-stall s/<STALL_NUMBER>` <br> e.g. `view-stall` s/1                                                                                                             |
| **Delete stall**         | `delete-stall s/<STALL_NUMBER>` <br> e.g. `delete-stall` s/1                                                                                                         |
| **Review stall**         | `review-stall s/<STALL_NUMBER> r/<STALL_RATING> d/<DESCRIPTION>` <br> e.g. review-stall s/1 r/5 d/the auntie very chio                                               |                                                                                                                                                             |

## 6.2 Item-Related Commands <a id="62-item-commands"></a>

| Features                                  | Format, Examples                                                                                                         |
|-------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|
| **Add item**                              | `add-item s/<STALL_NUMBER> i/<ITEM>`<br> e.g. `add-item` add-item s/1 i/ChickenRice                                      |
|
| **View item**                             | `view-item s/<STALL_NUMBER> i/<ITEM_NUMBER>`<br> e.g. `view-item` s/1 i/1                                                |
|
|
| **Delete item**                           | `delete-item s/<STALL_NUMBER> i/<ITEM_NUMBER>`<br> e.g. `delete-item` s/1 i/1                                            |
| **Rate item**                             | `rate-item s/STALL_NUMBER i/ITEM_NUMBER r/ITEM_RATING d/DESCRIPTION` <br> e.g. `rate-item` s/1 i/1 r/5 d/th chicken good |
|

## 6.3 General Commands <a id="63-general-commands"></a>

| Features | Format, Examples |
|----------|------------------|
| **Help** | `help`           |
| **Exit** | `exit`           |
| **list** | `list`           |

