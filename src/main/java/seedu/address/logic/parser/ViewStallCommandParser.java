package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStallCommand;
import seedu.address.logic.commands.ViewStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class ViewStallCommandParser implements Parser<ViewStallCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewStallCommand
     * and returns a ViewStallCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewStallCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STALL);

        if (!arePrefixesPresent(argMultimap, PREFIX_STALL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStallCommand.MESSAGE_USAGE));
        }
        try {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STALL, PREFIX_ITEM);
            Index index = ParserUtil.parseStallIndex(argMultimap.getValue(PREFIX_STALL).get());
            return new ViewStallCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStallCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
