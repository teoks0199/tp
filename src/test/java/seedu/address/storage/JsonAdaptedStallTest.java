package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStalls.BEVERAGES;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;

import java.util.ArrayList;
import java.util.List;

public class JsonAdaptedStallTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_LOCATION = " ";

    private static final String VALID_NAME = BEVERAGES.getName().toString();
    private static final String VALID_LOCATION = BEVERAGES.getLocation().toString();

    private static final List EMPTY_MENU_LIST = new ArrayList();

    @Test
    public void toModelType_validStallDetails_returnsStall() throws Exception {
        JsonAdaptedStall stall = new JsonAdaptedStall(BEVERAGES);
        assertEquals(BEVERAGES, stall.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStall stall =
                new JsonAdaptedStall(INVALID_NAME, VALID_LOCATION, EMPTY_MENU_LIST);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(null, VALID_LOCATION, EMPTY_MENU_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }


    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        JsonAdaptedStall stall =
                new JsonAdaptedStall(VALID_NAME, INVALID_LOCATION, EMPTY_MENU_LIST);
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, null, EMPTY_MENU_LIST);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Location.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }
}
