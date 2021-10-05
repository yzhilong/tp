package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class GameEntryTest {

    private static final GameEntry POKER = new GameEntry("Poker", 100., 80.,
            new DatePlayed(), 10, "Marina bay sands", new HashSet<>()
    );
    private static final GameEntry ROULETTE = new GameEntry("Roulette", 100., 80.12,
            new DatePlayed(), 10, "Marina bay sands", new HashSet<>()
    );

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> POKER.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(POKER.isSameGameEntry(POKER));

        // null -> returns false
        assertFalse(POKER.isSameGameEntry(null));

        // same gameType and date, all other attributes different -> returns true
        HashSet<Tag> tmpTags = new HashSet<>();
        tmpTags.add(new Tag("Tmp"));
        GameEntry editedPoker = new GameEntry("Poker", 101., 81.,
                POKER.getDate(), 11, "Resort World Sentosa", tmpTags
        );
        assertTrue(POKER.isSameGameEntry(editedPoker));

        // different name, all other attributes same -> returns false
        editedPoker = new GameEntry("Blackjack", 100., 80.,
                POKER.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.isSameGameEntry(editedPoker));

        // different date, all other attributes same -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.,
                new DatePlayed(1234567890L), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.isSameGameEntry(editedPoker));
    }

    @Test
    public void equals() {
        // same values -> returns true
        GameEntry pokerCopy = new GameEntry("Poker", 100., 80.,
                new DatePlayed(), 10, "Marina bay sands", new HashSet<>()
        );
        assertTrue(POKER.equals(pokerCopy));

        // same object -> returns true
        assertTrue(POKER.equals(POKER));

        // null -> returns false
        assertFalse(POKER.equals(null));

        // different type -> returns false
        assertFalse(POKER.equals(5));

        // different person -> returns false
        assertFalse(POKER.equals(ROULETTE));

        // different name -> returns false
        GameEntry editedPoker = new GameEntry("Blackjack", 100., 80.,
                POKER.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));

        // different startAmount -> returns false
        editedPoker = new GameEntry("Poker", 100.1, 80.,
                POKER.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));

        // different endAmount -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));

        // different date -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                new DatePlayed(1234567890L), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));

        // different duration -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER.getDate(), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));

        // different tags -> returns false
        HashSet<Tag> tmpTags = new HashSet<>();
        tmpTags.add(new Tag("Tmp"));
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER.getDate(), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER.equals(editedPoker));
    }

}
