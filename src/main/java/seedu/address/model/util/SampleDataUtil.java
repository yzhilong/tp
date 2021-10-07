package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.GameBook;
import seedu.address.model.ReadOnlyGameBook;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static GameEntry[] getSampleGameEntries() {
        return new GameEntry[] {
                new GameEntry("Poker", 10.96, 0.23, null, 10,
                    "Home", getTagSet("drunk")),
                new GameEntry("Roulette", 31.01, 1.12, new DatePlayed(60000),
                        10, "Marina Bay Sands", getTagSet("drunk")),
                new GameEntry("Poker", 110., 23.64, null, null,
                        "School", getTagSet()),
                new GameEntry("Blackjack", 10.2, 12.94, null, 10,
                        "john's home", getTagSet("hungry", "angry", "friends")),
                new GameEntry("Baccarat", 21.12, 0.26, new DatePlayed(60000000),
                        12, "resorts world Sentosa", getTagSet("smoking", "late-night")),
        };
    }

    public static ReadOnlyGameBook getSampleGameBook() {
        GameBook sampleAb = new GameBook();
        for (GameEntry sampleGameEntry : getSampleGameEntries()) {
            sampleAb.addGameEntry(sampleGameEntry);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
