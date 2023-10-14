package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.stall.Stall;

/**
 * A utility class containing a list of {@code Stall} objects to be used in tests.
 */
public class TypicalStalls {

    public static final Stall ALICE = new StallBuilder().withName("Alice Pauline")
            .withLocation("Deck").build();
    public static final Stall BENSON = new StallBuilder().withName("Benson Meier")
            .withLocation("Deck").build();
    public static final Stall CARL = new StallBuilder().withName("Carl Kurz").withLocation("wall street").build();
    public static final Stall DANIEL = new StallBuilder().withName("Daniel Meier").withLocation("10th street").build();
    public static final Stall ELLE = new StallBuilder().withName("Elle Meyer").withLocation("michegan ave").build();
    public static final Stall FIONA = new StallBuilder().withName("Fiona Kunz").withLocation("little tokyo").build();
    public static final Stall GEORGE = new StallBuilder().withName("George Best").withLocation("4th street").build();

    // Manually added
    public static final Stall HOON = new StallBuilder().withName("Hoon Meier").withLocation("little india").build();
    public static final Stall IDA = new StallBuilder().withName("Ida Mueller").withLocation("chicago ave").build();

    // Manually added - Stall's details found in {@code CommandTestUtil}
    public static final Stall AMY = new StallBuilder().withName(VALID_NAME_AMY).withLocation(VALID_LOCATION_AMY)
            .build();
    public static final Stall BOB = new StallBuilder().withName(VALID_NAME_BOB).withLocation(VALID_LOCATION_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStalls() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Stall stall : getTypicalStalls()) {
            ab.addStall(stall);
        }
        return ab;
    }

    public static List<Stall> getTypicalStalls() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
