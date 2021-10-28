package seedu.gamebook.model.util;

import java.util.Comparator;

import seedu.gamebook.model.gameentry.GameEntry;

/**
 * A comparator to compare game entries by date. A game entry with an earlier date is deemed smaller.
 */
public class GameEntriesDateComparator implements Comparator<GameEntry> {
    @Override
    public int compare(GameEntry gameOne, GameEntry gameTwo) {
        return gameOne.compareTo(gameTwo);
    }
}

