package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_PRICE_2;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_2;
import static seedu.address.testutil.TypicalStalls.getTypicalStalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {

    public static final Item FRIED_RICE = new ItemBuilder().withName("Fried Rice").withPrice("5.50").build();
    public static final Item LAKSA = new ItemBuilder().withName("Laksa").withPrice("4.50").build();
    public static final Item NASI_LEMAK = new ItemBuilder()
            .withName(VALID_ITEM_NAME_NASI_LEMAK).withPrice(VALID_ITEM_PRICE_1).build();
    public static final Item CHICKEN_RICE = new ItemBuilder()
            .withName(VALID_ITEM_NAME_CHICKEN_RICE).withPrice(VALID_ITEM_PRICE_2).build();
    public static final Item DUCK_RICE = new ItemBuilder()
            .withName("Duck Rice").withPrice("3.50").withItemReview(ITEM_REVIEW_2)
            .build();

    private TypicalItems() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical stalls.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        List<Stall> stalls = getTypicalStalls();
        for (Stall stall : stalls) {
            for (Item item : getTypicalItems()) {
                stall.addItem(item);
            }
            ab.addStall(stall);
        }
        return ab;
    }

    public static List<Item> getTypicalItems() {
        return new ArrayList<>(Arrays
                .asList(FRIED_RICE, LAKSA, NASI_LEMAK, CHICKEN_RICE));
    }
}
