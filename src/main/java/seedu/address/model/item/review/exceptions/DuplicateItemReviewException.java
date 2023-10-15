package seedu.address.model.item.review.exceptions;

public class DuplicateItemReviewException extends RuntimeException {
    public DuplicateItemReviewException() {
        super("Item review already exists");
    }
}
