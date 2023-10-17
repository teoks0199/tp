package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.item.UniqueItemList;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Stall}.
 */
class JsonAdaptedStall {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stall's %s field is missing!";

    private final String name;
    private final String location;
    private final List<JsonAdaptedItem> menuItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStall} with the given stall details.
     */
    @JsonCreator
    public JsonAdaptedStall(@JsonProperty("name") String name, @JsonProperty("location") String location,
                            @JsonProperty("menuItems") List<JsonAdaptedItem> menuItems) {
        this.name = name;
        this.location = location;
        this.menuItems.addAll(menuItems);
    }

    /**
     * Converts a given {@code Stall} into this class for Json use.
     */
    public JsonAdaptedStall(Stall source) {
        name = source.getName().fullName;
        location = source.getLocation().locationName;
        menuItems.addAll(source.getMenu().getInternalList().stream()
                .map(JsonAdaptedItem::new)
                .collect(Collectors.toList()));
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Location.class.getSimpleName()));
        }
        if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }
        final Location modelLocation = new Location(location);

        if (menuItems == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    UniqueItemList.class.getSimpleName()));
        }

        final List<Item> itemList = new ArrayList<>();
        for (JsonAdaptedItem item : menuItems) {
            itemList.add(item.toModelType());
        }
        final UniqueItemList modelMenu = new UniqueItemList(itemList);

        return new Stall(modelName, modelLocation, modelMenu);
    }

}
