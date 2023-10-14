package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditStallDescriptor;
import seedu.address.testutil.EditStallDescriptorBuilder;

public class EditStallDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditStallDescriptor descriptorWithSameValues = new EditStallDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditStallDescriptor editedAmy = new EditStallDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different location -> returns false
        editedAmy = new EditStallDescriptorBuilder(DESC_AMY).withLocation(VALID_LOCATION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }

    @Test
    public void toStringMethod() {
        EditCommand.EditStallDescriptor editStallDescriptor = new EditCommand.EditStallDescriptor();
        String expected = EditCommand.EditStallDescriptor.class.getCanonicalName() + "{name="
                + editStallDescriptor.getName().orElse(null) + ", location="
                + editStallDescriptor.getLocation().orElse(null) + "}";
        assertEquals(expected, editStallDescriptor.toString());
    }
}
