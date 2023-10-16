package seedu.address.model.stall;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a review of a stall in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class StallReview {
    public static final String MESSAGE_CONSTRAINTS =
            "Review should only contain alphanumeric characters, punctuations and spaces, and it should not be BLANK!";
    public static final String STAR_CONSTRAINTS =
            "Rating should only be an integer between 0-5!";

    private final String review;
    private final int star;

    /**
     * Constructs a StallReview object.
     *
     * @param review A valid review.
     * @param star   An integer representing the star rating.
     */
    public StallReview(String review, int star) {
        requireNonNull(review);
        requireNonNull(star);
        AppUtil.checkArgument(isValidReview(review), MESSAGE_CONSTRAINTS);
        AppUtil.checkArgument(isValidStar(star), STAR_CONSTRAINTS);
        this.review = review;
        this.star = star;
    }

    /**
     * Returns true if a given string is a valid review.
     *
     * @param test The review string to validate.
     * @return True if the review is valid, false otherwise.
     */
    public static boolean isValidReview(String test) {
        return test.matches("[\\p{Alnum}][\\p{Alnum} \\p{Punct}]*");
    }

    /**
     * Returns true if a given integer is a valid star rating.
     *
     * @param test The star rating to validate.
     * @return True if the star rating is valid, false otherwise.
     */
    public static boolean isValidStar(int test) {
        return test >= 0 && test <= 5;
    }

    /**
     * Returns the string representation of the StallReview.
     *
     * @return The formatted string representation of the StallReview.
     */
    @Override
    public String toString() {
        return "Rating: " + star + "/5, Review: " + review;
    }

    /**
     * Checks if this StallReview is equal to another object.
     *
     * @param other The other object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof StallReview)) {
            return false;
        }

        StallReview otherReview = (StallReview) other;
        return review.equals(otherReview.review) && star == otherReview.star;
    }

    /**
     * Generates a hash code for the StallReview object.
     *
     * @return The hash code of the StallReview object.
     */
    @Override
    public int hashCode() {
        return review.hashCode() + Integer.hashCode(star);
    }
}
