package seedu.address.model.util;

import seedu.address.model.gameentry.GameEntry;

import java.util.Comparator;

/**
 * A comparator to compare game entries by date. A game entry with an earlier date is deemed smaller.
 */
public class GameEntriesDateComparator implements Comparator<GameEntry> {
    @Override
    public int compare(GameEntry gameOne, GameEntry gameTwo) {
        return gameOne.compareTo(gameTwo);
    }
}

