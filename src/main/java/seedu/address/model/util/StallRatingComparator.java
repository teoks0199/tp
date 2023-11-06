package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.stall.Stall;

/**
 * Compares two stalls based on their ratings.
 */
public class StallRatingComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        return stall2.getStallRatingValue() - stall1.getStallRatingValue();
    }
}
