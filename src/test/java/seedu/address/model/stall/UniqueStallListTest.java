package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.ALICE;
import static seedu.address.testutil.TypicalStalls.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.stall.exceptions.DuplicateStallException;
import seedu.address.model.stall.exceptions.StallNotFoundException;
import seedu.address.testutil.StallBuilder;

public class UniqueStallListTest {

    private final UniqueStallList uniqueStallList = new UniqueStallList();

    @Test
    public void contains_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.contains(null));
    }

    @Test
    public void contains_stallNotInList_returnsFalse() {
        assertFalse(uniqueStallList.contains(ALICE));
    }

    @Test
    public void contains_stallInList_returnsTrue() {
        uniqueStallList.add(ALICE);
        assertTrue(uniqueStallList.contains(ALICE));
    }

    @Test
    public void contains_stallWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStallList.add(ALICE);
        Stall editedAlice = new StallBuilder(ALICE).withLocation(VALID_LOCATION_BOB)
                .build();
        assertTrue(uniqueStallList.contains(editedAlice));
    }

    @Test
    public void add_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.add(null));
    }

    @Test
    public void add_duplicateStall_throwsDuplicateStallException() {
        uniqueStallList.add(ALICE);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.add(ALICE));
    }

    @Test
    public void setStall_nullTargetStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(null, ALICE));
    }

    @Test
    public void setStall_nullEditedStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(ALICE, null));
    }

    @Test
    public void setStall_targetStallNotInList_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.setStall(ALICE, ALICE));
    }

    @Test
    public void setStall_editedStallIsSameStall_success() {
        uniqueStallList.add(ALICE);
        uniqueStallList.setStall(ALICE, ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(ALICE);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasSameIdentity_success() {
        uniqueStallList.add(ALICE);
        Stall editedAlice = new StallBuilder(ALICE).withLocation(VALID_LOCATION_BOB)
                .build();
        uniqueStallList.setStall(ALICE, editedAlice);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(editedAlice);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasDifferentIdentity_success() {
        uniqueStallList.add(ALICE);
        uniqueStallList.setStall(ALICE, BOB);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStall_editedStallHasNonUniqueIdentity_throwsDuplicateStallException() {
        uniqueStallList.add(ALICE);
        uniqueStallList.add(BOB);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStall(ALICE, BOB));
    }

    @Test
    public void remove_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.remove(null));
    }

    @Test
    public void remove_stallDoesNotExist_throwsStallNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.remove(ALICE));
    }

    @Test
    public void remove_existingStall_removesStall() {
        uniqueStallList.add(ALICE);
        uniqueStallList.remove(ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullUniqueStallList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((UniqueStallList) null));
    }

    @Test
    public void setStalls_uniqueStallList_replacesOwnListWithProvidedUniqueStallList() {
        uniqueStallList.add(ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        uniqueStallList.setStalls(expectedUniqueStallList);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((List<Stall>) null));
    }

    @Test
    public void setStalls_list_replacesOwnListWithProvidedList() {
        uniqueStallList.add(ALICE);
        List<Stall> stallList = Collections.singletonList(BOB);
        uniqueStallList.setStalls(stallList);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setStalls_listWithDuplicateStalls_throwsDuplicateStallException() {
        List<Stall> listWithDuplicateStalls = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStalls(listWithDuplicateStalls));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStallList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStallList.asUnmodifiableObservableList().toString(), uniqueStallList.toString());
    }
}
