package seedu.address.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidItemName(String)}
 */
public class ItemName {

    public static final String MESSAGE_CONSTRAINTS =
            "ItemNames should not be blank";

    /**
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * This regex matches any string containing at least one non-whitespace character
     */
    public static final String VALIDATION_REGEX = "(.|\\s)*\\S(.|\\s)*";

    public final String fullName;

    /**
     * Constructs a {@code ItemName}.
     *
     * @param itemName A valid name.
     */
    public ItemName(String itemName) {
        requireNonNull(itemName);
        checkArgument(isValidItemName(itemName), MESSAGE_CONSTRAINTS);
        fullName = itemName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidItemName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ItemName)) {
            return false;
        }

        ItemName otherItemName = (ItemName) other;
        return fullName.toLowerCase().equals(otherItemName.fullName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

}
