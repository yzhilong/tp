package seedu.gamebook.model.gameentry;

import java.util.List;
import java.util.function.Predicate;

import seedu.gamebook.commons.util.StringUtil;

/**
 * Tests that a {@code GameEntry}'s text matches any of the keywords given.
 */
public class GameEntryContainsKeywordPredicate implements Predicate<GameEntry> {
    private final List<String> keywords;

    public GameEntryContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(GameEntry gameEntry) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(gameEntry.getSearchableCorpus(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GameEntryContainsKeywordPredicate // instanceof handles nulls
                && keywords.equals(((GameEntryContainsKeywordPredicate) other).keywords)); // state check
    }

}
