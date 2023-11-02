package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemReviewPanel extends UiPart<Region> {
    private static final String FXML = "ItemReviewPanel.fxml";

    @FXML
    private HBox cardPane;

    @FXML
    private Label itemReview;

    @FXML
    private Label itemName;

    @FXML
    private Label itemPrice;
    /**
     * Creates an {@code ItemNamePanel} with the given item.
     */
    public ItemReviewPanel(Item item) {
        super(FXML);
        itemPrice.setText(item.getPriceString());
        itemName.setText(item.getName().fullName.concat(" ").concat(item.getItemRatingString()));
        itemReview.setText(item.getItemDescriptionString());
    }
}
