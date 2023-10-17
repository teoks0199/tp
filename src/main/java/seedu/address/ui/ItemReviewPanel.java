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
    private Label id;

    @FXML
    private Label itemReview;

    /**
     * Creates an {@code ItemNamePanel} with the given item.
     */
    public ItemReviewPanel(Item item) {
        super(FXML);
        itemReview.setText(item.getItemDescriptionString());
    }
}
