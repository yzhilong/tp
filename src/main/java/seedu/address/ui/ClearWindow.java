package seedu.address.ui;

import static seedu.address.logic.commands.ClearCommand.COMMAND_CONFIRMATION;
import static seedu.address.logic.commands.ClearCommand.COMMAND_WORD;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;


/**
 * Controller for a clear data confirmation page
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
     * Creates a new ClearWindow.
     *
     * @param root Stage to use as the root of the ClearWindow.
     */
    public ClearWindow(Stage root, Logic logic) {
        super(FXML, root);
        clearMessage.setText(CLEAR_MESSAGE);
        this.logic = logic;
    }

    /**
     * Creates a new ClearWindow.
     */
    public ClearWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the clear game entry confirmation window.
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
        logger.fine("Showing clear data confirmation pop up.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the clear window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the clear window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the clear window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Clears user's data by passing a clear data confirmation command to logic.
     */
    @FXML
    private void clearData() {
        try {
            logic.execute(COMMAND_WORD + " " + COMMAND_CONFIRMATION);
            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
