package seedu.address.testutil;

<<<<<<< HEAD
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
=======
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_CHICKEN_RICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_NASI_LEMAK;
import static seedu.address.testutil.TypicalStalls.getTypicalStalls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.item.Item;
import seedu.address.model.stall.Stall;
>>>>>>> master

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {
<<<<<<< HEAD
    public static  final Item VALID_ITEM_1 = new Item(new ItemName("Roasted Chicken Rice"));
    public static  final Item VALID_ITEM_2 = new Item(new ItemName("Steamed Chicken Rice"));
    public static  final Item VALID_ITEM_3 = new Item(new ItemName("Char Siew Rice"));
    public static  final Item VALID_ITEM_4 = new Item(new ItemName("Duck Rice"));
    private static final Item VALID_ITEM_5 = new Item(new ItemName("Ice Milo"));
    private static final Item VALID_ITEM_6 = new Item(new ItemName("Teh Peng"));
=======

    public static final Item FRIED_RICE = new ItemBuilder().withName("Fried Rice").build();
    public static final Item LAKSA = new ItemBuilder().withName("Laksa").build();
    public static final Item NASI_LEMAK = new ItemBuilder().withName(VALID_ITEM_NAME_NASI_LEMAK).build();
    public static final Item CHICKEN_RICE = new ItemBuilder().withName(VALID_ITEM_NAME_CHICKEN_RICE).build();

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
>>>>>>> master
}
