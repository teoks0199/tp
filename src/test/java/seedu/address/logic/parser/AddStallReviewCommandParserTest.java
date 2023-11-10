package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STALL_RATING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_STALL_INDEX;
import static seedu.address.model.review.Rating.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_3;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStallReviewCommand;
import seedu.address.logic.parser.AddStallReviewCommandParser;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.CliSyntax;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.review.StallReview;
import seedu.address.testutil.StallReviewBuilder;

public class AddStallReviewCommandParserTest {

    private AddStallReviewCommandParser parser = new AddStallReviewCommandParser();
    @Test
    public void parse_validInput_returnsAddStallReviewCommand() {
       assertParseSuccess(parser,VALID_STALL_INDEX_DESC + VALID_STALL_RATING + VALID_STALL_DESC,
               new AddStallReviewCommand(STALL_REVIEW_3, INDEX_FIRST_STALL));
    }

    //Invalid stall index
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_STALL_INDEX_DESC + VALID_STALL_RATING + VALID_STALL_DESC,
               MESSAGE_INVALID_STALL_INDEX);
    }

    //Invalid stall rating
    @Test
    public void parse_invalidArgs_throwsParseException2() {
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + INVALID_STALL_RATING + VALID_STALL_DESC,
               MESSAGE_CONSTRAINTS);
    }

}
