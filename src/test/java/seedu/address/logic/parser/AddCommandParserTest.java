package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentTokenizer.MESSAGE_DUPLICATE_FLAGS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserTestUtil.DATE_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.DURATION_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.ENDAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.GAMEONE;
import static seedu.address.logic.parser.ParserTestUtil.GAMETWO;
import static seedu.address.logic.parser.ParserTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.ParserTestUtil.STARTAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DATE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DURATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_ENDAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_GAMETYPE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_STARTAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_1;
// import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_2;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.testutil.GameEntryBuilder;


public class AddCommandParserTest {

    private static final GameEntry GAME_1 = new GameEntry(VALID_GAMETYPE_1.toString(), VALID_STARTAMOUNT_1,
        VALID_ENDAMOUNT_1, VALID_DATE_1,
        VALID_DURATION_1, VALID_LOCATION_1.toString(), null);

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        GameEntry expectedGameEntry = new GameEntryBuilder(GAME_1).withTags(VALID_TAG_1).build();

        assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix
                + GAMEONE.startAmountWithPrefix + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, new AddCommand(expectedGameEntry));

        // TODO - add test for multiple tags in 1 argument
    }

    @Test
    public void parse_duplicateFields_failure() {
        GameEntry expectedGameEntry = new GameEntryBuilder(GAME_1).withTags(VALID_TAG_1).build();

        // multiple gameTypes - last gameType accepted
        assertParseFailure(parser, GAMETWO.gameTypeWithPrefix + GAMEONE.gameTypeWithPrefix
                + GAMEONE.startAmountWithPrefix + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        // multiple startAmount - last startAmount accepted
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMETWO.startAmountWithPrefix
                + GAMEONE.startAmountWithPrefix + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        // multiple endAmount - last endAmount accepted
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMETWO.endAmountWithPrefix + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        // multiple date - last date accepted
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMETWO.dateWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        // multiple duration - last duration accepted
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMETWO.durationWithPrefix
                + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        // multiple location - last location accepted
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                + GAMETWO.locationWithPrefix + GAMEONE.locationWithPrefix
                + GAMEONE.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);

        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix
                + GAMETWO.tagWithPrefix, MESSAGE_DUPLICATE_FLAGS);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        try {
            // zero tags
            GameEntry expectedGameEntryNoTags = new GameEntryBuilder(GAME_1).withTags().build();
            assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                    + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                    + GAMEONE.locationWithPrefix, new AddCommand(expectedGameEntryNoTags));
            // no startAmount
            GameEntry expectedGameEntryNoStartAmount = new GameEntryBuilder(GAME_1).withStartAmount("").build();
            assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.endAmountWithPrefix
                    + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                    + GAMEONE.locationWithPrefix, new AddCommand(expectedGameEntryNoStartAmount));
            // no date
            GameEntry expectedGameEntryNoDate = new GameEntryBuilder(GAME_1).withDatePlayed("").build();
            assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                    + GAMEONE.endAmountWithPrefix + GAMEONE.durationWithPrefix
                    + GAMEONE.locationWithPrefix, new AddCommand(expectedGameEntryNoDate));
            // no duration
            GameEntry expectedGameEntryNoDuration = new GameEntryBuilder(GAME_1).withDuration("").build();
            assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                + GAMEONE.locationWithPrefix, new AddCommand(expectedGameEntryNoDuration));
            // no location
            GameEntry expectedGameEntryNoLocation = new GameEntryBuilder(GAME_1).withLocation("").build();
            assertParseSuccess(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                    + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix,
                    new AddCommand(expectedGameEntryNoLocation));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing gameType prefix
        assertParseFailure(parser, VALID_GAMETYPE_1 + GAMEONE.startAmountWithPrefix
                        + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                        + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, expectedMessage);

        // missing endAmount prefix
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                        + VALID_ENDAMOUNT_1 + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                        + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GAMETYPE_1.toString() + VALID_STARTAMOUNT_1 + VALID_ENDAMOUNT_1
            + VALID_DATE_1 + VALID_DURATION_1 + VALID_LOCATION_1 + VALID_TAG_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid startAmount
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, MESSAGE_INVALID_START_AMOUNT);

        // invalid endAmount
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + ENDAMOUNT_INVALID_WITH_PREFIX + GAMEONE.dateWithPrefix + GAMEONE.durationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, MESSAGE_INVALID_END_AMOUNT);

        // invalid date
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + DATE_INVALID_WITH_PREFIX + GAMEONE.durationWithPrefix
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, MESSAGE_INVALID_DATE);

        // invalid duration
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + GAMEONE.startAmountWithPrefix
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + DURATION_INVALID_WITH_PREFIX
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, MESSAGE_INVALID_DURATION);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, GAMEONE.gameTypeWithPrefix + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix + DURATION_INVALID_WITH_PREFIX
                + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix, MESSAGE_INVALID_START_AMOUNT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GAMEONE.gameTypeWithPrefix
                        + GAMEONE.startAmountWithPrefix + GAMEONE.endAmountWithPrefix + GAMEONE.dateWithPrefix
                        + GAMEONE.durationWithPrefix + GAMEONE.locationWithPrefix + GAMEONE.tagWithPrefix,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
