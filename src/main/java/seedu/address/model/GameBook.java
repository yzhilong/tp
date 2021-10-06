package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.UniqueGameEntryList;

public class GameBook implements ReadOnlyGameBook {

    private final UniqueGameEntryList gameEntries = new UniqueGameEntryList();

    public GameBook() {}

    public GameBook(ReadOnlyGameBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the game entries list with {@code gameEntries}.
     * {@code gameEntries} must not contain duplicate game entries.
     */
    public void setGameEntries(List<GameEntry> gameEntries) {
        this.gameEntries.setGameEntries(gameEntries);
    }

    /**
     * Resets the existing data of this {@code GameBook} with {@code newData}.
     */
    public void resetData(ReadOnlyGameBook newData) {
        requireNonNull(newData);
        setGameEntries(newData.getGameEntryList());
    }

    /**
     * Returns true if a game entry with the same identity as {@code gameEntry} exists in the game book.
     */
    public boolean hasGameEntry(GameEntry gameEntry) {
        requireNonNull(gameEntry);
        return gameEntries.contains(gameEntry);
    }

    /**
     * Adds a game entry to the game book.
     * The game entry must not already exist in the game book.
     */
    public void addGameEntry(GameEntry gameEntry) {
        gameEntries.add(gameEntry);
    }

    /**
     * Replaces the given game entry {@code target} in the list with {@code editedGameEntry}.
     * {@code target} must exist in the game book.
     * The game entry identity of {@code editedGameEntry} must not be the same as another existing game entry in the
     * game book.
     */
    public void setGameEntry(GameEntry target, GameEntry editedGameEntry) {
        requireNonNull(editedGameEntry);

        gameEntries.setGameEntry(target, editedGameEntry);
    }

    /**
     * Removes {@code key} from this {@code GameBook}.
     * {@code key} must exist in the game book.
     */
    public void removeGameEntry(GameEntry key) {
        gameEntries.remove(key);
    }

    @Override
    public String toString() {
        return gameEntries.asUnmodifiableObservableList().size() + " game entries";
        // TODO: refine later
    }

    @Override
    public ObservableList<GameEntry> getGameEntryList() {
        return gameEntries.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof GameBook) {
            GameBook tmp = (GameBook) other;
            return gameEntries.equals(tmp.gameEntries);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return gameEntries.hashCode();
    }
}
