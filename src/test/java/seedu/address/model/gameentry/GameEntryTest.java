package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class GameEntryTest {

    private static final Date DATE = new Date(121, 9, 11, 22, 38, 12);
    private static final GameEntry POKER_WITH_TIME = new GameEntry("Poker", 100., 80.,
            new DatePlayed(DATE), 10, "Marina bay sands", new HashSet<>()
    );
    private static final GameEntry ROULETTE_WITH_TIME = new GameEntry("Roulette", 100., 80.12,
            new DatePlayed(DATE), 10, "Marina bay sands", new HashSet<>()
    );
    private static final GameEntry POKER_WITHOUT_TIME = new GameEntry("Poker", 100., 80.12,
            new DatePlayed(DATE, false), 10, "Marina bay sands", new HashSet<>()
    );
    private static final GameEntry ROULETTE_WITHOUT_TIME = new GameEntry("Roulette", 100., 80.12,
            new DatePlayed(DATE, false), 10, "Marina bay sands", new HashSet<>()
    );

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> POKER_WITH_TIME.getTags().remove(0));
    }

    @Test
    public void isSameGameEntry() {
        // same object -> returns true
        assertTrue(POKER_WITH_TIME.isSameGameEntry(POKER_WITH_TIME));

        // null -> returns false
        assertFalse(POKER_WITH_TIME.isSameGameEntry(null));

        // same gameType and date, all other attributes different -> returns true
        HashSet<Tag> tmpTags = new HashSet<>();
        tmpTags.add(new Tag("Tmp"));
        GameEntry editedPoker = new GameEntry("Poker", 101., 81.,
                POKER_WITH_TIME.getDate(), 11, "Resort World Sentosa", tmpTags
        );
        assertTrue(POKER_WITH_TIME.isSameGameEntry(editedPoker));

        // different name, all other attributes same -> returns false
        editedPoker = new GameEntry("Blackjack", 100., 80.,
                POKER_WITH_TIME.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.isSameGameEntry(editedPoker));

        // different date, all other attributes same -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.,
                new DatePlayed(1234567890L), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.isSameGameEntry(editedPoker));

        // all attributes same except date without time indicated -> returns false
        assertFalse(POKER_WITH_TIME.isSameGameEntry(POKER_WITHOUT_TIME));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Date date = new Date();
        GameEntry pokerCopy = new GameEntry("Poker", 100., 80.,
                POKER_WITH_TIME.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertTrue(POKER_WITH_TIME.equals(pokerCopy));

        // same object -> returns true
        assertTrue(POKER_WITH_TIME.equals(POKER_WITH_TIME));

        // null -> returns false
        assertFalse(POKER_WITH_TIME.equals(null));

        // different type -> returns false
        assertFalse(POKER_WITH_TIME.equals(5));

        // different game entries -> returns false
        assertFalse(POKER_WITH_TIME.equals(ROULETTE_WITH_TIME));

        // different name -> returns false
        GameEntry editedPoker = new GameEntry("Blackjack", 100., 80.,
                POKER_WITH_TIME.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // different startAmount -> returns false
        editedPoker = new GameEntry("Poker", 100.1, 80.,
                POKER_WITH_TIME.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // different endAmount -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER_WITH_TIME.getDate(), 10, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // different date -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                new DatePlayed(1234567890L), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // different duration -> returns false
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER_WITH_TIME.getDate(), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // different tags -> returns false
        HashSet<Tag> tmpTags = new HashSet<>();
        tmpTags.add(new Tag("Tmp"));
        editedPoker = new GameEntry("Poker", 100., 80.1,
                POKER_WITH_TIME.getDate(), 11, "Marina bay sands", new HashSet<>()
        );
        assertFalse(POKER_WITH_TIME.equals(editedPoker));

        // Time field indicated for one but not the other -> returns false
        assertFalse(POKER_WITH_TIME.equals(POKER_WITHOUT_TIME));
        assertFalse(POKER_WITHOUT_TIME.equals(POKER_WITH_TIME));
    }

    @Test
    public void toStringTest() {
        assertEquals(
                POKER_WITH_TIME.toString(),
                "Game type: Poker; Start amount: 100.00; End amount: 80.00; Date played: 2021-10-11 22:38; "
                        + "Game duration: 10; Location: Marina Bay Sands"
        );
        assertEquals(
                ROULETTE_WITH_TIME.toString(),
                "Game type: Roulette; Start amount: 100.00; End amount: 80.12; Date played: 2021-10-11 22:38; "
                        + "Game duration: 10; Location: Marina Bay Sands"
        );
        assertEquals(
                POKER_WITHOUT_TIME.toString(),
                "Game type: Poker; Start amount: 100.00; End amount: 80.12; Date played: 2021-10-11; "
                        + "Game duration: 10; Location: Marina Bay Sands"
        );
        assertEquals(
                ROULETTE_WITHOUT_TIME.toString(),
                "Game type: Roulette; Start amount: 100.00; End amount: 80.12; Date played: 2021-10-11; "
                        + "Game duration: 10; Location: Marina Bay Sands"
        );
    }

}
