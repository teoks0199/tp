package seedu.address.model.stall;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class StallReview {
    public static final String MESSAGE_CONSTRAINTS =
            "Review should only contain alphanumeric characters and spaces, and it should not be BLANK! ";
    public static final String STAR_CONSTRAINTS =
            "Rating should only be an integer between 1-5!!!!!!!!!!";
    public final String review;
    public final int star;

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String SINGLE_DIGIT_0_TO_5_REGEX = "[0-5]";



    /**
     * Constructs a {@code review}.
     *
     * @param review A valid review.
     * @param star
     */
    public StallReview(String review, int star) {
        requireNonNull(review);
        requireNonNull(star);
        checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        checkArgument(isValidStar(star),STAR_CONSTRAINTS);
        this.review = review;
        this.star = star;
    }

    /**
     * Returns true if a given string is a valid review.
     */
    public static boolean isValidReview(String test) {
        return test.matches(VALIDATION_REGEX);
    }


        public static boolean isValidStar(int test) {
            String testString = String.valueOf(test);
            return testString.matches(SINGLE_DIGIT_0_TO_5_REGEX);

        }


    @Override
    public String toString() {
        return "rating:" + this.star + "review:" + review;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StallReview)) {
            return false;
        }

        StallReview otherreview = (StallReview) other;
        return review.equals(otherreview.review);
    }

    @Override
    public int hashCode() {
        return review.hashCode();
    }
}
