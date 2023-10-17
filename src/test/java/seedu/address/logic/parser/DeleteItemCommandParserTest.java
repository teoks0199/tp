package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ITEM_INDEX_DESC_NEGATIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteItemCommand;

public class DeleteItemCommandParserTest {

    private DeleteItemCommandParser parser = new DeleteItemCommandParser();

    // Valid stall and item index
    @Test
    public void parse_validArgs_returnsDeleteItemCommand() {
        assertParseSuccess(parser, VALID_STALL_INDEX_DESC + VALID_ITEM_INDEX_DESC,
                new DeleteItemCommand(INDEX_FIRST_STALL, INDEX_FIRST_ITEM));
    }

    //Invalid stall index, valid item index
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + VALID_ITEM_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItemCommand.MESSAGE_USAGE));
    }

    //Invalid stall index, valid item index
    @Test
    public void parse_invalidArgs_throwsParseException2() {
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + VALID_ITEM_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItemCommand.MESSAGE_USAGE));
    }

    //Invalid stall index, invalid item index
    @Test
    public void parse_invalidArgs_throwsParseException3() {
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + INVALID_ITEM_INDEX_DESC_NEGATIVE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteItemCommand.MESSAGE_USAGE));
    }

}