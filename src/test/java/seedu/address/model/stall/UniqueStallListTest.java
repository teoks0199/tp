package seedu.address.model.stall;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueStallList.contains(ALICE));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueStallList.add(ALICE);
        assertTrue(uniqueStallList.contains(ALICE));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStallList.add(ALICE);
        Stall editedAlice = new StallBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStallList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueStallList.add(ALICE);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.add(ALICE));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(null, ALICE));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStall(ALICE, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.setStall(ALICE, ALICE));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueStallList.add(ALICE);
        uniqueStallList.setStall(ALICE, ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(ALICE);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueStallList.add(ALICE);
        Stall editedAlice = new StallBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStallList.setStall(ALICE, editedAlice);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(editedAlice);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueStallList.add(ALICE);
        uniqueStallList.setStall(ALICE, BOB);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueStallList.add(ALICE);
        uniqueStallList.add(BOB);
        assertThrows(DuplicateStallException.class, () -> uniqueStallList.setStall(ALICE, BOB));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(StallNotFoundException.class, () -> uniqueStallList.remove(ALICE));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueStallList.add(ALICE);
        uniqueStallList.remove(ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((UniqueStallList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueStallList.add(ALICE);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        uniqueStallList.setStalls(expectedUniqueStallList);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStallList.setStalls((List<Stall>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueStallList.add(ALICE);
        List<Stall> stallList = Collections.singletonList(BOB);
        uniqueStallList.setStalls(stallList);
        UniqueStallList expectedUniqueStallList = new UniqueStallList();
        expectedUniqueStallList.add(BOB);
        assertEquals(expectedUniqueStallList, uniqueStallList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
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
