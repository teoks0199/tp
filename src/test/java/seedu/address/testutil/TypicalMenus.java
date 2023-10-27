package seedu.address.testutil;

import seedu.address.model.stall.Menu;

import static seedu.address.testutil.TypicalItems.CHICKEN_RICE;
import static seedu.address.testutil.TypicalItems.FRIED_RICE;
import static seedu.address.testutil.TypicalItems.LAKSA;
import static seedu.address.testutil.TypicalItems.NASI_LEMAK;

/**
 * A utility class containing a list of {@code Menu} objects to be used in tests.
 */
public class TypicalMenus {
    public static final Menu VALID_MENU_1 = new MenuBuilder().withItem(CHICKEN_RICE).withItem(NASI_LEMAK).build();

    public static final Menu VALID_MENU_2 = new MenuBuilder().withItem(FRIED_RICE).withItem(LAKSA).build();

    private TypicalMenus() {} // prevents instantiation
}
