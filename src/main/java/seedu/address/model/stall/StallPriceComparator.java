package seedu.address.model.stall;

import java.util.Comparator;

/**
 * Compares two stalls by their average price.
 */
public class StallPriceComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        return stall1.getAveragePrice().compareTo(stall2.getAveragePrice());
    }
}
