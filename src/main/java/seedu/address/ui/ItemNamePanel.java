package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemNamePanel extends UiPart<Region> {
    private static final String FXML = "ItemNamePanel.fxml";

    @FXML
    private HBox cardPane;

    @FXML
    private Label id;

    @FXML
    private Label itemName;

    @FXML
    private Label itemRating;
    /**
     * Creates an {@code ItemNamePanel} with the given item.
     */
    public ItemNamePanel(Item item) {
        super(FXML);
        itemName.setText(item.getNameString());
        itemRating.setText(item.getItemRatingString());
    }
}
