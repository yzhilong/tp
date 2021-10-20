package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;


/**
 * Controller for a help page
 */
public class ClearWindow extends UiPart<Stage> {

    public static final String CLEAR_MESSAGE = "Are you sure you want to clear all game entries?";

    private static final Logger logger = LogsCenter.getLogger(ClearWindow.class);
    private static final String FXML = "ClearWindow.fxml";

    @FXML
    private Button clearButton;

    @FXML
    private Label clearMessage;

    private final Logic logic;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ClearWindow(Stage root, Logic logic) {
        super(FXML, root);
        clearMessage.setText(CLEAR_MESSAGE);
        this.logic = logic;
    }

    /**
     * Creates a new HelpWindow.
     */
    public ClearWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the clear game entries window.
     *
     * @throws IllegalStateException <ul>
     *                                   <li>
     *                                       if this method is called on a thread other than the JavaFX Application
     *                                       Thread.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called during animation or layout processing.
     *                                   </li>
     *                                   <li>
     *                                       if this method is called on the primary stage.
     *                                   </li>
     *                                   <li>
     *                                       if {@code dialogStage} is already showing.
     *                                   </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void clearData() {
        try {
            logic.execute("clear yes");
            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
