package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Item;

/**
 * Jackson-friendly version of {@link Item}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedTag(Item source) {
        tagName = source.tagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Item toModelType() throws IllegalValueException {
        if (!Item.isValidTagName(tagName)) {
            throw new IllegalValueException(Item.MESSAGE_CONSTRAINTS);
        }
        return new Item(tagName);
    }

}
