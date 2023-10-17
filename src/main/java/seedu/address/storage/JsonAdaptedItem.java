package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemName;
import seedu.address.model.item.review.Description;
import seedu.address.model.item.review.Rating;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;

import java.util.List;

import static seedu.address.storage.JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT;

/**
 * Jackson-friendly version of {@link Item}.
 */
class JsonAdaptedItem {

    private final String itemName;
//    private final String review;
//    private final String rating;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
//    @JsonCreator
//    public JsonAdaptedItem(@JsonProperty("itemName") String name)
//    {
//    public JsonAdaptedItem(@JsonProperty("itemName") String name, @JsonProperty("review") String review,
//                            @JsonProperty("rating") String rating) {
//        this.itemName = name;
//        this.review = review;
//        this.rating = rating;
//    }

    @JsonCreator
    public JsonAdaptedItem(String name) {
        this.itemName = name;
    }

    /**
     * Converts a given {@code Item} into this class for Jackson use.
     */
    public JsonAdaptedItem(Item source) {
        itemName = source.getName().fullName;
//        review = source.getItemReview().getDescription().toString();
//        rating = source.getItemReview().getRating().toString();
    }

    @JsonValue
    public String getItemName() {
        return itemName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Item} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Item toModelType() throws IllegalValueException {
        if (itemName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, ItemName.class.getSimpleName()));
        }
        if (!ItemName.isValidItemName(itemName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final ItemName modelName = new ItemName(itemName);
//
//        if (review == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
//                    Description.class.getSimpleName()));
//        }
//        if (!Description.isValidDescription(review)) {
//            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
//        }
//        final Description modelDescription = new Description(review);
//
//        if (rating == null) {
//            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
//                    Rating.class.getSimpleName()));
//        }
//        if (!Rating.isValidRating(rating)) {
//            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
//        }
//        final Rating modelRating = new Rating(rating);

        return new Item(modelName);
    }

}
