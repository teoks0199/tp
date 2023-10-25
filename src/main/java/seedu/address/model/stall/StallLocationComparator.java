package seedu.address.model.stall;

import java.util.Comparator;

/**
 * Compares two stalls based on their locations.
 */
public class StallLocationComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        return stall1.getLocationString().compareTo(stall2.getLocationString());
    }
}
