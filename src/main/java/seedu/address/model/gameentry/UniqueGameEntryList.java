package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.gameentry.exceptions.DuplicateGameEntryException;
import seedu.address.model.gameentry.exceptions.GameEntryNotFoundException;

public class UniqueGameEntryList implements Iterable<GameEntry> {
    private final ObservableList<GameEntry> internalList = FXCollections.observableArrayList();
    private final ObservableList<GameEntry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(GameEntry toCheck) {
//        requireNonNull(toCheck);
//        return internalList.stream().anyMatch(toCheck::isSameGameEntry);
        return false;
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(GameEntry toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateGameEntryException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setGameEntry(GameEntry target, GameEntry editedGameEntry) {
        requireAllNonNull(target, editedGameEntry);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new GameEntryNotFoundException();
        }

        if (!target.equals(editedGameEntry) && contains(editedGameEntry)) {
            throw new DuplicateGameEntryException();
        }

        internalList.set(index, editedGameEntry);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(GameEntry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new GameEntryNotFoundException();
        }
    }

    public void setGameEntries(UniqueGameEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGameEntries(List<GameEntry> persons) {
        requireAllNonNull(persons);
        if (!gameEntriesAreUnique(persons)) {
            throw new DuplicateGameEntryException();
        }

        internalList.setAll(persons);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<GameEntry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<GameEntry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueGameEntryList // instanceof handles nulls
                && internalList.equals(((UniqueGameEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code gameEntries} contains only unique game entries.
     */
    private boolean gameEntriesAreUnique(List<GameEntry> gameEntries) {
        for (int i = 0; i < gameEntries.size() - 1; i++) {
            for (int j = i + 1; j < gameEntries.size(); j++) {
                if (gameEntries.get(i).equals(gameEntries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
