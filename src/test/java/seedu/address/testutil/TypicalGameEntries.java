package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.GameBook;
import seedu.address.model.gameentry.GameEntry;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalGameEntries {
    static final GameEntry POKER1;
    static final GameEntry POKER2;
    static final GameEntry POKER3;
    static final GameEntry POKER4;
    static final GameEntry BLACKJACK1;
    static final GameEntry BLACKJACK2;
    static final GameEntry BLACKJACK3;
    static final GameEntry DARTS1;
    static final GameEntry DARTS2;

    static {
        try {
            POKER1 = new GameEntryBuilder().withGameType("Poker")
                    .withStartAmount("123").withEndAmount("134")
                    .withDatePlayed("10/10/20").withDuration("30")
                    .withLocation("Marina Bay Sands")
                    .withTags("friends").build();
            POKER2 = new GameEntryBuilder().withGameType("Poker")
                    .withStartAmount("0").withEndAmount("50")
                    .withDatePlayed("11/10/20 12:34").withDuration("1:10")
                    .withLocation("Marina Bay Sands")
                    .withTags("solo morning").build();
            POKER3 = new GameEntryBuilder().withGameType("Poker")
                    .withStartAmount("0").withEndAmount("-10")
                    .withDatePlayed("09/10/20").withDuration("30")
                    .withLocation("Home").build();
            BLACKJACK1 = new GameEntryBuilder().withGameType("Blackjack")
                    .withStartAmount("0").withEndAmount("5")
                    .withDatePlayed("10/11/20 18:00").withDuration("10")
                    .withLocation("Friend's Place")
                    .withTags("friends drunk").build();
            BLACKJACK2 = new GameEntryBuilder().withGameType("Blackjack")
                    .withStartAmount("200").withEndAmount("199")
                    .withDatePlayed("10/11/20 19:00").withDuration("30")
                    .withLocation("Friend's Place")
                    .withTags("friends drunk").build();
            DARTS1 = new GameEntryBuilder().withGameType("Darts")
                    .withStartAmount("0").withEndAmount("0")
                    .withDatePlayed("10/12/20").withDuration("30")
                    .withLocation("Local Bar")
                    .withTags("solo").build();
            DARTS2 = new GameEntryBuilder().withGameType("Darts")
                    .withStartAmount("0").withEndAmount("0")
                    .withDatePlayed("10/12/20").withDuration("30")
                    .withLocation("Local Bar")
                    .withTags("solo").build();

            // Manually added
            POKER4 = new GameEntryBuilder().withGameType("Poker")
                    .withStartAmount("0").withEndAmount("1")
                    .withDatePlayed("22/10/20 12:34").withDuration("20")
                    .withLocation("Sentosa")
                    .withTags("vacation").build();

            BLACKJACK3 = new GameEntryBuilder().withGameType("Blackjack")
                    .withStartAmount("30").withEndAmount("15")
                    .withDatePlayed("22/05/20").withDuration("20")
                    .withLocation("Sentosa")
                    .withTags("vacation").build();

        } catch (ParseException e) {
            // Manually added so should not be here
            assert (false);
            e.printStackTrace();
        }
    }

    // Manually added - Person's details found in {@code CommandTestUtil}
    // public static final Person AMY = new GameEntryBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
    //        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND).build();
    // public static final Person BOB = new GameEntryBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
    //        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
    //        .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalGameEntries() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static GameBook getTypicalGameBook() {
        GameBook gb = new GameBook();
        for (GameEntry gameEntry : getTypicalGameEntries()) {
            gb.addGameEntry(gameEntry);
        }
        return gb;
    }

    public static List<GameEntry> getTypicalGameEntries() {
        return new ArrayList<>(Arrays.asList(POKER1, POKER2, POKER3,
                BLACKJACK1, BLACKJACK2, DARTS1, DARTS2));
    }
}
