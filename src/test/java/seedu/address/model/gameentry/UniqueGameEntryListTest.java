package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.gameentry.exceptions.DuplicateGameEntryException;
import seedu.address.model.gameentry.exceptions.GameEntryNotFoundException;

public class UniqueGameEntryListTest {

    private static final GameEntry POKER = new GameEntry("Poker", 100., 80.,
            new DatePlayed(), 10, "Marina bay sands", new HashSet<>()
    );
    private static final GameEntry ROULETTE = new GameEntry("Roulette", 100., 80.12,
            new DatePlayed(), 10, "Marina bay sands", new HashSet<>()
    );
    private final UniqueGameEntryList uniqueGameEntryList = new UniqueGameEntryList();

    @Test
    public void contains_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.contains(null));
    }

    @Test
    public void contains_gameEntryNotInList_returnsFalse() {
        assertFalse(uniqueGameEntryList.contains(POKER));
    }

    @Test
    public void contains_gameEntryInList_returnsTrue() {
        uniqueGameEntryList.add(POKER);
        assertTrue(uniqueGameEntryList.contains(POKER));
    }

    @Test
    public void contains_gameEntryWithSameIdentityFieldsInList_returnsTrue() {
        uniqueGameEntryList.add(POKER);
        GameEntry editedPoker = new GameEntry("Poker", 100., 80.,
                POKER.getDate(), 100, "Resort World Sentosa", new HashSet<>()
        );
        assertTrue(uniqueGameEntryList.contains(editedPoker));
    }

    @Test
    public void add_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.add(null));
    }

    @Test
    public void add_duplicateGameEntry_throwsDuplicateGameEntryException() {
        uniqueGameEntryList.add(POKER);
        assertThrows(DuplicateGameEntryException.class, () -> uniqueGameEntryList.add(POKER));
    }

    @Test
    public void setPerson_nullTargetGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.setGameEntry(null, POKER));
    }

    @Test
    public void setPerson_nullEditedGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.setGameEntry(POKER, null));
    }

    @Test
    public void setPerson_targetGameEntryNotInList_throwsGameEntryNotFoundException() {
        assertThrows(GameEntryNotFoundException.class, () -> uniqueGameEntryList.setGameEntry(POKER, POKER));
    }

    @Test
    public void setGameEntry_editedGameEntryIsSameGameEntry_success() {
        uniqueGameEntryList.add(POKER);
        uniqueGameEntryList.setGameEntry(POKER, POKER);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        expectedUniqueGameEntryList.add(POKER);
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntry_editedGameEntryHasSameIdentity_success() {
        uniqueGameEntryList.add(POKER);
        GameEntry editedPoker = new GameEntry("Poker", 100., 80.,
                new DatePlayed(new Date(121, 10, 12, 11, 50), false), 100, "Resort World Sentosa", new HashSet<>()
        );
        uniqueGameEntryList.setGameEntry(POKER, editedPoker);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        expectedUniqueGameEntryList.add(editedPoker);
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntry_editedGameEntryHasDifferentIdentity_success() {
        uniqueGameEntryList.add(POKER);
        uniqueGameEntryList.setGameEntry(POKER, ROULETTE);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        expectedUniqueGameEntryList.add(ROULETTE);
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntry_editedGameEntryHasNonUniqueIdentity_throwsDuplicateGameEntryException() {
        uniqueGameEntryList.add(POKER);
        uniqueGameEntryList.add(ROULETTE);
        assertThrows(DuplicateGameEntryException.class, () -> uniqueGameEntryList.setGameEntry(POKER, ROULETTE));
    }

    @Test
    public void remove_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.remove(null));
    }

    @Test
    public void remove_gameEntryDoesNotExist_throwsGameEntryNotFoundException() {
        assertThrows(GameEntryNotFoundException.class, () -> uniqueGameEntryList.remove(POKER));
    }

    @Test
    public void remove_existingGameEntry_removesGameEntry() {
        uniqueGameEntryList.add(POKER);
        uniqueGameEntryList.remove(POKER);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntries_nullUniqueGameEntryList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.setGameEntries((UniqueGameEntryList) null));
    }

    @Test
    public void setGameEntries_uniqueGameEntryList_replacesOwnListWithProvidedUniqueGameEntryList() {
        uniqueGameEntryList.add(POKER);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        expectedUniqueGameEntryList.add(ROULETTE);
        uniqueGameEntryList.setGameEntries(expectedUniqueGameEntryList);
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntries_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGameEntryList.setGameEntries((List<GameEntry>) null));
    }

    @Test
    public void setGameEntries_list_replacesOwnListWithProvidedList() {
        uniqueGameEntryList.add(POKER);
        List<GameEntry> gameEntryList = Collections.singletonList(POKER);
        uniqueGameEntryList.setGameEntries(gameEntryList);
        UniqueGameEntryList expectedUniqueGameEntryList = new UniqueGameEntryList();
        expectedUniqueGameEntryList.add(POKER);
        assertEquals(expectedUniqueGameEntryList, uniqueGameEntryList);
    }

    @Test
    public void setGameEntries_listWithDuplicateGameEntries_throwsDuplicateGameEntryException() {
        List<GameEntry> listWithDuplicatePersons = Arrays.asList(POKER, POKER);
        assertThrows(
                DuplicateGameEntryException.class,
                () -> uniqueGameEntryList.setGameEntries(listWithDuplicatePersons));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueGameEntryList.asUnmodifiableObservableList().remove(0));
    }

}
