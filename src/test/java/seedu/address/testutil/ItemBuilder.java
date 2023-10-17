package seedu.address.testutil;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Chicken Rice";
    private ItemName name;


    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    public Item build() {
        return new Item(name);
    }

}
