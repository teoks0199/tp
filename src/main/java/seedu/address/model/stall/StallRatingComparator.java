package seedu.address.model.stall;

import java.util.Comparator;

/**
 * Compares two stalls based on their ratings.
 */
public class StallRatingComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        return stall2.getStallRatingValue() - stall1.getStallRatingValue();
    }
}
