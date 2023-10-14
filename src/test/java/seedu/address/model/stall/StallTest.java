package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.AUNTIES_COOKING;
import static seedu.address.testutil.TypicalStalls.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StallBuilder;

public class StallTest {

    //    @Test
    //    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
    //        Stall stall = new StallBuilder().build();
    //        assertThrows(UnsupportedOperationException.class, () -> stall.getTags().remove(0));
    //    }
    @Test
    public void isSameStall() {
        // same object -> returns true
        assertTrue(AUNTIES_COOKING.isSameStall(AUNTIES_COOKING));

        // null -> returns false
        assertFalse(AUNTIES_COOKING.isSameStall(null));

        // same name, all other attributes different -> returns false
        Stall editedAlice = new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(AUNTIES_COOKING.isSameStall(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StallBuilder(AUNTIES_COOKING).withName(VALID_NAME_BOB).build();
        assertFalse(AUNTIES_COOKING.isSameStall(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Stall editedBob = new StallBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStall(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StallBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStall(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Stall aliceCopy = new StallBuilder(AUNTIES_COOKING).build();
        assertTrue(AUNTIES_COOKING.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AUNTIES_COOKING.equals(AUNTIES_COOKING));

        // null -> returns false
        assertFalse(AUNTIES_COOKING.equals(null));

        // different type -> returns false
        assertFalse(AUNTIES_COOKING.equals(5));

        // different stall -> returns false
        assertFalse(AUNTIES_COOKING.equals(BOB));

        // different name -> returns false
        Stall editedAlice = new StallBuilder(AUNTIES_COOKING).withName(VALID_NAME_BOB).build();
        assertFalse(AUNTIES_COOKING.equals(editedAlice));

        // different location -> returns false
        editedAlice = new StallBuilder(AUNTIES_COOKING).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(AUNTIES_COOKING.equals(editedAlice));

    }

    @Test
    public void toStringMethod() {
        String expected = Stall.class.getCanonicalName() + "{name=" + AUNTIES_COOKING.getName() + ", location="
                + AUNTIES_COOKING.getLocation() + "}";
        assertEquals(expected, AUNTIES_COOKING.toString());
    }
}
