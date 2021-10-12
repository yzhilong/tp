package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.gameentry.GameEntry;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final GameBook gameBook;
    private final UserPrefs userPrefs;
    private final FilteredList<GameEntry> filteredGameEntries;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyGameBook gameBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(gameBook, userPrefs);

        logger.fine("Initializing with address book: " + gameBook + " and user prefs " + userPrefs);

        this.gameBook = new GameBook(gameBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredGameEntries = new FilteredList<>(this.gameBook.getGameEntryList());
    }

    public ModelManager() {
        this(new GameBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getGameBookFilePath() {
        return userPrefs.getGameBookFilePath();
    }

    @Override
    public void setGameBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setGameBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    public void setGameBook(ReadOnlyGameBook gameBook) {
        this.gameBook.resetData(gameBook);
    }

    @Override
    public ReadOnlyGameBook getGameBook() {
        return gameBook;
    }

    @Override
    public boolean hasGameEntry(GameEntry gameEntry) {
        requireNonNull(gameEntry);
        return gameBook.hasGameEntry(gameEntry);
    }

    @Override
    public void deleteGameEntry(GameEntry target) {
        gameBook.removeGameEntry(target);
    }

    @Override
    public void addGameEntry(GameEntry gameEntry) {
        gameBook.addGameEntry(gameEntry);
        updateFilteredGameEntryList(PREDICATE_SHOW_ALL_GAME_ENTRIES);
    }

    @Override
    public void setGameEntry(GameEntry target, GameEntry editedGameEntry) {
        requireAllNonNull(target, editedGameEntry);

        gameBook.setGameEntry(target, editedGameEntry);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<GameEntry> getFilteredGameEntryList() {
        return filteredGameEntries;
    }

    @Override
    public void updateFilteredGameEntryList(Predicate<GameEntry> predicate) {
        requireNonNull(predicate);
        filteredGameEntries.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return gameBook.equals(other.gameBook)
                && userPrefs.equals(other.userPrefs)
                && filteredGameEntries.equals(other.filteredGameEntries);
    }

}
