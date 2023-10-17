package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ASIAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STALL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.testutil.EditStallDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ASIAN, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_ASIAN, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_ASIAN, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid address
        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_LOCATION_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STALL;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_ASIAN;

        EditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withLocation(VALID_LOCATION_ASIAN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STALL;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN;

        EditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_ASIAN)
                .withLocation(VALID_LOCATION_ASIAN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STALL;
        String userInput = targetIndex.getOneBased() + NAME_DESC_ASIAN;
        EditCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withName(VALID_NAME_ASIAN).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_ASIAN;
        descriptor = new EditStallDescriptorBuilder().withLocation(VALID_LOCATION_ASIAN).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_STALL;
        String userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_ASIAN;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + LOCATION_DESC_ASIAN + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN
                + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN + NAME_DESC_BRITISH + LOCATION_DESC_BRITISH;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_NAME_DESC + INVALID_LOCATION_DESC
                + INVALID_NAME_DESC + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION));
    }
}
