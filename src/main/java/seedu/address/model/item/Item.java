package seedu.address.model.item;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Item in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Item {

    // Identity fields
    private final ItemName itemName;
    /**
     * Every field must be present and not null.
     */
    public Item(ItemName itemName) {
        requireAllNonNull(itemName);
        this.itemName = itemName;
    }

    public ItemName getName() {
        return itemName;
    }


    /**
     * Returns true if both items have the same itemName.
     * This defines a weaker notion of equality between two stalls.
     */
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        return otherItem != null
                && otherItem.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Item)) {
            return false;
        }

        Item otherItem = (Item) other;
        return itemName.equals(otherItem.itemName);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("itemName", itemName)
                .toString();
    }

}
