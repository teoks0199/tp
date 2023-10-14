package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.ALICE;
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
        assertTrue(ALICE.isSameStall(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStall(null));

        // same name, all other attributes different -> returns false
        Stall editedAlice = new StallBuilder(ALICE).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(ALICE.isSameStall(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StallBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStall(editedAlice));

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
        Stall aliceCopy = new StallBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different stall -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Stall editedAlice = new StallBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different location -> returns false
        editedAlice = new StallBuilder(ALICE).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void toStringMethod() {
        String expected = Stall.class.getCanonicalName() + "{name=" + ALICE.getName() + ", location="
                + ALICE.getLocation() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
