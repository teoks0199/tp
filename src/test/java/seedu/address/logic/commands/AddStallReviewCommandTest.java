package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

public class AddStallReviewCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

}
