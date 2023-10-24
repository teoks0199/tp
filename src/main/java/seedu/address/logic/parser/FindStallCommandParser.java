package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.FindStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.stall.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStallCommand object
 */
public class FindStallCommandParser implements Parser<FindStallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStallCommand
     * and returns a FindStallCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStallCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStallCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStallCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
