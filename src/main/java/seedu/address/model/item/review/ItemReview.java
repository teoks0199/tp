package seedu.address.model.item.review;

import seedu.address.commons.util.ToStringBuilder;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class ItemReview {
    private final Rating rating;
    private final Description description;

    public ItemReview(Rating rating, Description description) {
        requireAllNonNull(rating, description);
        this.rating = rating;
        this.description = description;
    }

    public Rating getRating() {
        return rating;
    }

    public Description getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(rating, description);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("rating", rating)
                .add("description", description)
                .toString();
    }
}
