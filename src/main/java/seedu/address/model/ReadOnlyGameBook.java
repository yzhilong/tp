package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.gameentry.GameEntry;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyGameBook {

    /**
     * Returns an unmodifiable view of the game entries list.
     */
    ObservableList<GameEntry> getGameEntryList();

}
