package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.stall.Stall;

/**
 * Compares two stalls based on their locations.
 */
public class StallLocationComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        return stall1.getLocationString().toUpperCase().compareTo(stall2.getLocationString().toUpperCase());
    }
}
