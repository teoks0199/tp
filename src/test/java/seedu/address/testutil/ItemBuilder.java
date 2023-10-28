package seedu.address.testutil;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.Price;

/**
 * A utility class to help with building Item objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Chicken Rice";
    public static final String DEFAULT_PRICE = "5.50";
    private ItemName name;
    private Price price;


    /**
     * Creates a {@code StallBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new ItemName(DEFAULT_NAME);
        price = new Price(DEFAULT_PRICE);
    }

    /**
     * Initializes the StallBuilder with the data of {@code stallToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        price = itemToCopy.getPrice();
    }

    /**
     * Sets the {@code Name} of the {@code Stall} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new ItemName(name);
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Stall} that we are building.
     */
    public ItemBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    public Item build() {
        return new Item(name, price);
    }

}
