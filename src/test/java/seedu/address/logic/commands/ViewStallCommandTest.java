package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Stall;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ViewStallCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewStallCommand(null));
    }

    @Test
    public void execute_validIndex_success() throws CommandException {
        ModelStub modelStub = new ModelStub();
        Name stallName = new Name("jap");
        Location location = new Location("deck");
        Stall validStall = new Stall(stallName, location);
        modelStub.addStall(validStall);

        ViewStallCommand viewStallCommand = new ViewStallCommand(Index.fromOneBased(1));

        CommandResult commandResult = viewStallCommand.execute(modelStub);

        assertEquals(String.format(ViewStallCommand.MESSAGE_VIEW_STALL_SUCCESS, Messages.format(validStall)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStub modelStub = new ModelStub();

        ViewStallCommand viewStallCommand = new ViewStallCommand(Index.fromOneBased(1));

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX, () ->
                viewStallCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        ViewStallCommand viewFirstCommand = new ViewStallCommand(Index.fromOneBased(1));
        ViewStallCommand viewSecondCommand = new ViewStallCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewStallCommand viewFirstCommandCopy = new ViewStallCommand(Index.fromOneBased(1));
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different stall index -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    // Create a ModelStub class to mock the Model component
    private class ModelStub implements Model {
        private final List<Stall> stalls = new ArrayList<>();

        @Override
        public void addStall(Stall stall) {
            stalls.add(stall);
        }

        @Override
        public void setStall(Stall target, Stall editedStall) {

        }

        @Override
        public ObservableList<Stall> getFilteredStallList() {
            return null;
        }

        @Override
        public void updateFilteredStallList(Predicate<Stall> predicate) {

        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {

        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        @Override
        public boolean hasStall(Stall stall) {
            return false;
        }

        @Override
        public void deleteStall(Stall target) {
            requireNonNull(target);
        }

        @Override
        public void showStall(Stall stall) {
            requireNonNull(stall);
        }
    }
}
