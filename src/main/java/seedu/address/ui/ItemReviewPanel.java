package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import seedu.address.model.item.Item;

/**
 * An UI component that displays information of a {@code Item}.
 */
public class ItemReviewPanel extends UiPart<Region> {
    private static final String FXML = "ItemReviewPanel.fxml";

    @FXML
    private VBox cardPane;

    @FXML
    private Text itemReview;

    @FXML
    private Text itemName;

    @FXML
    private Text itemPrice;
    /**
     * Creates an {@code ItemNamePanel} with the given item.
     */
    public ItemReviewPanel(Item item) {
        super(FXML);
        itemPrice.setText(item.getPriceString());
        itemName.setText(item.getName().fullName.concat(" ").concat(item.getItemRatingString()));
        itemReview.wrappingWidthProperty().bind(cardPane.widthProperty());
        itemReview.setText(item.getItemDescriptionString());
    }
}
