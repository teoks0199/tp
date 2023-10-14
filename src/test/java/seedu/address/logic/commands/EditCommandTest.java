package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showStallAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalStalls.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditStallDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.EditStallDescriptorBuilder;
import seedu.address.testutil.StallBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Stall editedStall = new StallBuilder().build();
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder(editedStall).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STALL, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());

        StallBuilder stallInList = new StallBuilder(lastStall);
        Stall editedStall = stallInList.withName(VALID_NAME_BOB).withLocation(VALID_LOCATION_BOB).build();

        EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_BOB)
                .withLocation(VALID_LOCATION_BOB).build();
        EditCommand editCommand = new EditCommand(indexLastStall, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(lastStall, editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STALL, new EditStallDescriptor());
        Stall editedStall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        Stall stallInFilteredList = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        Stall editedStall = new StallBuilder(stallInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_STALL_SUCCESS, Messages.format(editedStall));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setStall(model.getFilteredStallList().get(0), editedStall);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStallUnfilteredList_failure() {
        Stall firstStall = model.getFilteredStallList().get(INDEX_FIRST_STALL.getZeroBased());
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder(firstStall).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_STALL, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_duplicateStallFilteredList_failure() {
        showStallAtIndex(model, INDEX_FIRST_STALL);

        // edit stall in filtered list into a duplicate in address book
        Stall stallInList = model.getAddressBook().getStallList().get(INDEX_SECOND_STALL.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_STALL,
                new EditStallDescriptorBuilder(stallInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_STALL);
    }

    @Test
    public void execute_invalidStallIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStallList().size() + 1);
        EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidStallIndexFilteredList_failure() {
        showStallAtIndex(model, INDEX_FIRST_STALL);
        Index outOfBoundIndex = INDEX_SECOND_STALL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getStallList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditStallDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_STALL, DESC_AMY);

        // same values -> returns true
        EditStallDescriptor copyDescriptor = new EditStallDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_STALL, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_STALL, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_STALL, DESC_BOB)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditStallDescriptor editStallDescriptor = new EditStallDescriptor();
        EditCommand editCommand = new EditCommand(index, editStallDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editStallDescriptor="
                + editStallDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
