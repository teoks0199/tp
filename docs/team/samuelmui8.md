---
layout: page
title: Samuel Mui's Project Portfolio Page
---

### Project: FoodNotes

FoodNotes is a desktop address book application used to record reviews on food stalls and menus. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort stalls by average price in FoodNotes.
    * What it does: Allows the user to sort stalls by average price in ascending order to find the cheapest stalls.
    * Justification: This feature improves the product significantly because it allows the user to find the cheapest stalls easily,
  considering that our target users are students who are on a budget.
    * Highlights: This enhancement required the creation of a new `Comparator` class, `StallAveragePriceComparator`, 
  to handle the sorting of stalls by average price, a new attribute, `averagePrice`, in the `Stall` class
  and a new `Price` class to represent the price of each menu item.
    * Credits:
  
* **New Feature**: Added the ability to review menu items in FoodNotes.
  * What it does: Allows the user to review menu items in a stall.
  * Justification: This feature improves the product significantly because it allows the user to review items in a stall,
  helping users to remember their experience with the menu item.
  * Highlights: This enhancement required the creation of a new `ItemReview` class to represent the review of a menu item,
  the review makes use of the `Rating` class to represent the rating of the item which uses star ratings for improved 
  user-friendliness.


* **Code contributed**:
  * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=samuelmui8&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=samuelmui8&tabRepo=AY2324S1-CS2103T-W10-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false) 

* **Project management**:
  * Released v1.3.trial along with the jar file on GitHub.

* **Enhancements to existing features**:
    * Adapted the existing storage features to support the new classes that are added into FoodNotes, by writing new
  storage components such as `JsonAdaptedItemReview`, `JsonAdaptedStallReview` and `JsonSerializableMenu`. (Pull requests [#83](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/83),  [#90](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/90))
    * Wrote additional tests for the storage components, increasing coverage from 54% to 58% (Pull request [#124](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/124))

* **Documentation**:
* User Guide:
    * Added documentation for stall sorting features in section 4.3 of the UG.
    * Added screenshots for stall finding features in section 4.4 of the UG.
  * Developer Guide:
    * Added implementation details of the `sort-stalls-price` feature.
    
* **Community**:
  * PRs reviewed (with non-trivial review comments): [#27](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/27),
  [#41](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/41), [#57](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/57),
  [#65](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/65), [#97](https://github.com/AY2324S1-CS2103T-W10-4/tp/pull/97)
  * Reported bugs and suggestions for other teams in the class (examples: [#1](https://github.com/samuelmui8/ped/issues/1), 
  [#2](https://github.com/samuelmui8/ped/issues/2), [#3](https://github.com/samuelmui8/ped/issues/3), [#4](https://github.com/samuelmui8/ped/issues/4),
  [#5](https://github.com/samuelmui8/ped/issues/5), [#6](https://github.com/samuelmui8/ped/issues/6))
