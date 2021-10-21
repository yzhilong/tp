package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.gameentry.GameEntry;

/**
 * An UI component that displays information of a {@code GameEntry}.
 */
public class GameEntryCard extends UiPart<Region> {

    private static final String FXML = "GameEntryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final GameEntry gameEntry;

    @FXML
    private HBox cardPane;
    @FXML
    private Label gameType;
    @FXML
    private Label id;
    @FXML
    private Label startAmount;
    @FXML
    private Label endAmount;
    @FXML
    private Label date;
    @FXML
    private Label durationMinutes;
    @FXML
    private Label gameLocation;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code GameEntryCode} with the given {@code GameEntry} and index to display.
     */
    public GameEntryCard(GameEntry gameEntry, int displayedIndex) {
        super(FXML);
        this.gameEntry = gameEntry;
        id.setText(displayedIndex + ". ");
        gameType.setText(gameEntry.getGameType().toString());
        date.setText(gameEntry.getDate().toString());
        startAmount.setText("Started with: $" + gameEntry.getStartAmount().toString());
        endAmount.setText("Ended with: $" + gameEntry.getEndAmount().toString());
        durationMinutes.setText("Played for: " + gameEntry.getDuration().toString());
        gameLocation.setText("Location: " + gameEntry.getLocation());
        gameEntry.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GameEntryCard)) {
            return false;
        }

        // state check
        GameEntryCard card = (GameEntryCard) other;
        return date.getText().equals(card.date.getText())
                && gameEntry.equals(card.gameEntry);
    }
}
