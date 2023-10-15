package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.stall.Stall;

/**
 * Deletes a stall identified using it's displayed index from the address book.
 */
public class ViewStallCommand extends Command {

    public static final String COMMAND_WORD = "view-stall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the details of the stall identified by the index number used in the displayed stall list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_STALL_SUCCESS = "Here are the details of this stall.";

    private final Index targetIndex;

    public ViewStallCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }

        Stall stallToView = lastShownList.get(targetIndex.getZeroBased());
        model.showStall(stallToView);
        return new CommandResult(String.format(MESSAGE_VIEW_STALL_SUCCESS, Messages.format(stallToView)),
                false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewStallCommand)) {
            return false;
        }

        ViewStallCommand otherViewStallCommand = (ViewStallCommand) other;
        return targetIndex.equals(otherViewStallCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
