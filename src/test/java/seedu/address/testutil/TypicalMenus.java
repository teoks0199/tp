package seedu.address.testutil;
import seedu.address.model.stall.Menu;

import static seedu.address.testutil.TypicalItems.VALID_ITEM_1;
import static seedu.address.testutil.TypicalItems.VALID_ITEM_2;
import static seedu.address.testutil.TypicalItems.VALID_ITEM_3;
import static seedu.address.testutil.TypicalItems.VALID_ITEM_4;


/**
 * A utility class containing a list of {@code Menu} objects to be used in tests.
 */
public class TypicalMenus {


    public static final Menu VALID_MENU_1 = new MenuBuilder().withItem(VALID_ITEM_1).withItem(VALID_ITEM_2).build();
    public static final Menu VALID_MENU_2 = new MenuBuilder().withItem(VALID_ITEM_3).withItem(VALID_ITEM_4).build();

    private TypicalMenus() {} // prevents instantiation
}
