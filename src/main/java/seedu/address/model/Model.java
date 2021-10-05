package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.gameentry.GameEntry;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<GameEntry> PREDICATE_SHOW_ALL_GAME_ENTRIES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getGameBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setGameBookFilePath(Path gameBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setGameBook(ReadOnlyGameBook gameBook);

    /** Returns the AddressBook */
    ReadOnlyGameBook getGameBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasGameEntry(GameEntry GameEntry);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deleteGameEntry(GameEntry target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addGameEntry(GameEntry GameEntry);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setGameEntry(GameEntry target, GameEntry editedGameEntry);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<GameEntry> getFilteredGameEntryList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGameEntryList(Predicate<GameEntry> predicate);
}
