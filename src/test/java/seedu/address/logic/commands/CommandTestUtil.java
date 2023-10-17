package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.stall.NameContainsKeywordsPredicate;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.EditStallDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //Stall related
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_LOCATION_AMY = "Block 312, Amy Street 1";
    public static final String VALID_LOCATION_BOB = "Block 123, Bobby Street 3";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String LOCATION_DESC_AMY = " " + PREFIX_LOCATION + VALID_LOCATION_AMY;
    public static final String LOCATION_DESC_BOB = " " + PREFIX_LOCATION + VALID_LOCATION_BOB;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " "; // empty names not allowed
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // empty locations not allowed

    //Item related
    public static final String VALID_STALL_INDEX_1 = "1";
    public static final String VALID_ITEM_NAME_NASI_LEMAK = "Nasi Lemak";
    public static final String VALID_ITEM_NAME_CHICKEN_RICE = "Chicken Rice";
    public static final String VALID_STALL_INDEX_DESC = " " + PREFIX_STALL + VALID_STALL_INDEX_1; // stall index 1 is valid
    public static final String VALID_ITEM_INDEX_DESC = " " + PREFIX_ITEM + VALID_STALL_INDEX_1; // item index 1 is valid
    public static final String ITEM_DESC_NASI_LEMAK = " " + PREFIX_ITEM + VALID_ITEM_NAME_NASI_LEMAK;
    public static final String ITEM_DESC_CHICKEN_RICE = " " + PREFIX_ITEM + VALID_ITEM_NAME_CHICKEN_RICE;
    public static final String INVALID_ITEM_NAME_DESC = " " + PREFIX_ITEM + " "; // empty items not allowed
    public static final String INVALID_STALL_INDEX_DESC = " " + PREFIX_STALL + " "; // empty stall index not allowed
    public static final String INVALID_ITEM_INDEX_DESC = " " + PREFIX_STALL + " "; // empty item index not allowed
    public static final String INVALID_ITEM_INDEX_DESC_NEGATIVE = " " + PREFIX_STALL + "-1"; // negative item index not allowed

    //Others
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStallDescriptor DESC_AMY;
    public static final EditCommand.EditStallDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStallDescriptorBuilder().withName(VALID_NAME_AMY).withLocation(VALID_LOCATION_AMY).build();
        DESC_BOB = new EditStallDescriptorBuilder().withName(VALID_NAME_BOB).withLocation(VALID_LOCATION_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered stall list and selected stall in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Stall> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStallList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStallList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the stall at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStallAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStallList().size());

        Stall stall = model.getFilteredStallList().get(targetIndex.getZeroBased());
        final String[] splitName = stall.getName().fullName.split("\\s+");
        model.updateFilteredStallList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStallList().size());
    }

}
