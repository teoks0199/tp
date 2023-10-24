package seedu.address.testutil;

import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;

/**
 * A utility class containing a list of {@code Item} objects to be used in tests.
 */
public class TypicalItems {
    public static  final Item VALID_ITEM_1 = new Item(new ItemName("Roasted Chicken Rice"));
    public static  final Item VALID_ITEM_2 = new Item(new ItemName("Steamed Chicken Rice"));
    public static  final Item VALID_ITEM_3 = new Item(new ItemName("Char Siew Rice"));
    public static  final Item VALID_ITEM_4 = new Item(new ItemName("Duck Rice"));
    private static final Item VALID_ITEM_5 = new Item(new ItemName("Ice Milo"));
    private static final Item VALID_ITEM_6 = new Item(new ItemName("Teh Peng"));
}
