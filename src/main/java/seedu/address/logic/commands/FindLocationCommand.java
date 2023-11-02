package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.stall.LocationContainsKeywordsPredicate;

/**
 * Finds and lists all stalls in FoodNotes whose location contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindLocationCommand extends Command {

    public static final String COMMAND_WORD = "find-location";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all stalls whose location contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " deck techno terrace";

    private final LocationContainsKeywordsPredicate predicate;

    public FindLocationCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStallList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STALLS_LISTED_OVERVIEW, model.getFilteredStallList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindLocationCommand)) {
            return false;
        }

        FindLocationCommand otherFindLocationCommand = (FindLocationCommand) other;
        return predicate.equals(otherFindLocationCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
