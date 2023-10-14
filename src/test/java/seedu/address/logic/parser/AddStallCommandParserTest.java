package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStalls.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddStallCommand;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;
import seedu.address.testutil.StallBuilder;

public class AddStallCommandParserTest {
    private AddStallCommandParser parser = new AddStallCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Stall expectedStall = new StallBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + LOCATION_DESC_BOB, new AddStallCommand(expectedStall));


        // multiple tags - all accepted
        Stall expectedStallMultipleTags = new StallBuilder(BOB).build();
        assertParseSuccess(parser,
                NAME_DESC_BOB + LOCATION_DESC_BOB,
                new AddStallCommand(expectedStallMultipleTags));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = NAME_DESC_BOB + LOCATION_DESC_BOB;

        // multiple names
        assertParseFailure(parser, NAME_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple locations
        assertParseFailure(parser, LOCATION_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));


        // multiple fields repeated
        assertParseFailure(parser,
                validExpectedPersonString + NAME_DESC_AMY + LOCATION_DESC_AMY
                        + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));


        // invalid location
        assertParseFailure(parser, INVALID_LOCATION_DESC + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedPersonString + INVALID_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));


        // invalid location
        assertParseFailure(parser, validExpectedPersonString + INVALID_LOCATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStallCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + LOCATION_DESC_BOB,
                expectedMessage);

        // missing location prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_LOCATION_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_LOCATION_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + LOCATION_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_LOCATION_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                + LOCATION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStallCommand.MESSAGE_USAGE));
    }
}
