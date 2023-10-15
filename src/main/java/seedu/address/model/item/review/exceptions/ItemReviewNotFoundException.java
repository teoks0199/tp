package seedu.address.model.item.review.exceptions;

public class ItemReviewNotFoundException extends RuntimeException {
    public ItemReviewNotFoundException() {
        super("Item review does not exist");
    }
}
