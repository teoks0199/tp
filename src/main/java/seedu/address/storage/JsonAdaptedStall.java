package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

/**
 * Jackson-friendly version of {@link Stall}.
 */
class JsonAdaptedStall {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stall's %s field is missing!";

    private final String name;
    private final String location;

    /**
     * Constructs a {@code JsonAdaptedStall} with the given stall details.
     */
    @JsonCreator
    public JsonAdaptedStall(@JsonProperty("name") String name, @JsonProperty("locationName") String location) {
        this.name = name;
        this.location = location;
    }

    /**
     * Converts a given {@code Stall} into this class for Jackson use.
     */
    public JsonAdaptedStall(Stall source) {
        name = source.getName().fullName;
        location = source.getLocation().locationName;
    }

    /**
     * Converts this Jackson-friendly adapted stall object into the model's {@code Stall} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall.
     */
    public Stall toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        return new Stall(modelName, modelLocation);
    }

}
