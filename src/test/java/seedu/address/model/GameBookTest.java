package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGameEntries.getTypicalGameBook;
import static seedu.address.testutil.TypicalGameEntries.POKER1_WITH_TIME;
import static seedu.address.testutil.TypicalGameEntries.POKER1_WITHOUT_TIME;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.exceptions.DuplicateGameEntryException;
import seedu.address.testutil.GameEntryBuilder;


public class GameBookTest {
    private final GameBook gameBook = new GameBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), gameBook.getGameEntryList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGameBook_replacesData() {
        GameBook newData = getTypicalGameBook();
        gameBook.resetData(newData);
        assertEquals(newData, gameBook);
    }

    @Test
    public void resetData_withDuplicateGameEntry_throwsDuplicateGameEntryException() {
        // Two persons with the same identity fields
        GameEntry editedPoker = new GameEntryBuilder(POKER1_WITH_TIME)
                .withDuration("321")
                .withTags("friends", "drunk", "late-night")
                .build();
        List<GameEntry> newPersons = Arrays.asList(POKER1_WITH_TIME, editedPoker);
        GameBookStub newData = new GameBookStub(newPersons);

        assertThrows(DuplicateGameEntryException.class, () -> gameBook.resetData(newData));
    }

    @Test
    public void hasGameEntry_nullGameEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> gameBook.hasGameEntry(null));
    }

    @Test
    public void hasGameEntry_gameEntryNotInGameBook_returnsFalse() {
        assertFalse(gameBook.hasGameEntry(POKER1_WITHOUT_TIME));
    }

    @Test
    public void hasGameEntry_gameEntryWithSameIdentityFieldsInGameBook_returnsTrue() {
        gameBook.addGameEntry(POKER1_WITH_TIME);

        GameEntry editedPoker = new GameEntryBuilder(POKER1_WITH_TIME).withStartAmount("1321.231").withDuration("31")
                .build();
        assertTrue(gameBook.hasGameEntry(editedPoker));
    }

    @Test
    public void addGameEntry_gameEntryWithSameIdentityFieldsInGameBook_throwsDuplicateGameEntryException() {
        gameBook.addGameEntry(POKER1_WITH_TIME);

        GameEntry editedPoker = new GameEntryBuilder().withGameType("Poker")
                .withStartAmount("0").withEndAmount("50")
                .withDatePlayed("11/10/20 12:34").withDuration("110")
                .withLocation("Marina Bay Sands")
                .withTags("solo-morning").build();

        System.out.println(POKER1_WITH_TIME.getDate().toString());
        System.out.println(POKER1_WITH_TIME.getDate().getIsTimeIndicated());

        assertThrows(DuplicateGameEntryException.class, () -> gameBook.addGameEntry(editedPoker));
    }

    @Test
    public void addGameEntry_gameEntryWithDifferentIdentityFieldsInGameBook_addsGameEntry() {
        gameBook.addGameEntry(POKER1_WITH_TIME);

        GameEntry editedPoker = new GameEntryBuilder(POKER1_WITH_TIME)
                .withDatePlayed("12/10/21 11:36")
                .withStartAmount("1321.12")
                .withEndAmount("0.12")
                .withLocation("school")
                .withDuration("31")
                .build();
        gameBook.addGameEntry(editedPoker);
        assertTrue(gameBook.hasGameEntry(editedPoker));
    }

    @Test
    public void getGameEntryList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> gameBook.getGameEntryList().remove(0));
    }

    /**
     * A stub ReadOnlyGameBook whose gameEntries list can violate interface constraints.
     */
    private static class GameBookStub implements ReadOnlyGameBook {
        private final ObservableList<GameEntry> gameEntries = FXCollections.observableArrayList();

        GameBookStub(Collection<GameEntry> persons) {
            this.gameEntries.setAll(persons);
        }

        @Override
        public ObservableList<GameEntry> getGameEntryList() {
            return gameEntries;
        }
    }
}
