package seedu.address.model.stall;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the average price of the items in a stall.
 */
public class AveragePrice implements Comparable<AveragePrice> {
    public final double averagePrice;

    /**
     * Constructs a {@code AveragePrice}.
     *
     * @param averagePrice A valid average price.
     */
    public AveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    @Override
    public int compareTo(AveragePrice other) {
        if (other == null) {
            // null is considered to be the largest value
            return -1;
        }
        return Double.compare(averagePrice, other.averagePrice);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AveragePrice
                && averagePrice == ((AveragePrice) other).averagePrice);
    }

    @Override
    public int hashCode() {
        return Double.hashCode(averagePrice);
    }

    @Override
    public String toString() {
        return String.format("$%.2f", averagePrice);
    }
}
