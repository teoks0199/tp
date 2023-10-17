package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ViewStallCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ViewStallCommandParserTest {

    private final ViewStallCommandParser parser = new ViewStallCommandParser();

    @Test
    public void parse_validArgs_returnsViewStallCommand() throws ParseException {
        String validArgs = "1";
        ViewStallCommand expectedCommand = new ViewStallCommand(Index.fromOneBased(1));
        assertEquals(expectedCommand, parser.parse(validArgs));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String invalidArgs = "abc"; // Non-integer argument
        assertThrows(ParseException.class, () -> parser.parse(invalidArgs));
    }
}
