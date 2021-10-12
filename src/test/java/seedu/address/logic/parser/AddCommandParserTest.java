package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserTestUtil.DATE_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.GAMEONE;
import static seedu.address.logic.parser.ParserTestUtil.GAMETWO;
import static seedu.address.logic.parser.ParserTestUtil.DURATION_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.ENDAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.ParserTestUtil.STARTAMOUNT_INVALID_WITH_PREFIX;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DATE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_DURATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_ENDAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_GAMETYPE_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_LOCATION_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_STARTAMOUNT_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_1;
import static seedu.address.logic.parser.ParserTestUtil.VALID_TAG_2;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;


import seedu.address.logic.commands.AddCommand;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.testutil.GameEntryBuilder;


public class AddCommandParserTest {

    private static final GameEntry GAME_1 = new GameEntry(VALID_GAMETYPE_1.toString(), VALID_STARTAMOUNT_1, VALID_ENDAMOUNT_1,
            VALID_DATE_1, VALID_DURATION_1, VALID_LOCATION_1.toString(), null);

    private AddCommandParser parser = new AddCommandParser();



    @Test
    public void parse_allFieldsPresent_success() {
        GameEntry expectedGameEntry = new GameEntryBuilder(GAME_1).withTags(VALID_TAG_1).build();


        // multiple gameTypes - last gameType accepted
        assertParseSuccess(parser, GAMETWO.GAMETYPE_WITH_PREFIX + GAMEONE.GAMETYPE_WITH_PREFIX
                + GAMEONE.STARTAMOUNT_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple startAmount - last startAmount accepted
        assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMETWO.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.STARTAMOUNT_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple endAmount - last endAmount accepted
        assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMETWO.ENDAMOUNT_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple date - last date accepted
        assertParseSuccess(parser,  GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMETWO.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMETWO.DURATION_WITH_PREFIX
                + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple location - last location accepted
        assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                + GAMETWO.LOCATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX
                + GAMEONE.TAG_WITH_PREFIX, new AddCommand(expectedGameEntry));

        // multiple tags - all accepted
        GameEntry expectedGameEntryMultipleTags = new GameEntryBuilder(GAME_1)
                .withTags(VALID_TAG_1, VALID_TAG_2)
                .build();
        assertParseSuccess(parser,GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                        + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                        + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX
                        + GAMETWO.TAG_WITH_PREFIX, new AddCommand(expectedGameEntryMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        try {
            // zero tags
            GameEntry expectedGameEntryNoTags = new GameEntryBuilder(GAME_1).withTags().build();
            assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                    + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                    + GAMEONE.LOCATION_WITH_PREFIX, new AddCommand(expectedGameEntryNoTags));
            // no startAmount
            GameEntry expectedGameEntryNoStartAmount = new GameEntryBuilder(GAME_1).withStartAmount("").build();
            assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX
                    + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                    + GAMEONE.LOCATION_WITH_PREFIX, new AddCommand(expectedGameEntryNoStartAmount));
            // no date
            GameEntry expectedGameEntryNoDate = new GameEntryBuilder(GAME_1).withDatePlayed("").build();
            assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                    + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                    + GAMEONE.LOCATION_WITH_PREFIX, new AddCommand(expectedGameEntryNoDate));
            // no duration
            GameEntry expectedGameEntryNoDuration = new GameEntryBuilder(GAME_1).withDuration("").build();
            assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                    + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX +
                    GAMEONE.LOCATION_WITH_PREFIX, new AddCommand(expectedGameEntryNoDuration));
            // no location
            GameEntry expectedGameEntryNoLocation = new GameEntryBuilder(GAME_1).withLocation("").build();
            assertParseSuccess(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                    + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX,
                    new AddCommand(expectedGameEntryNoLocation));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing gameType prefix
        assertParseFailure(parser, VALID_GAMETYPE_1 + GAMEONE.STARTAMOUNT_WITH_PREFIX
                        + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                        + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, expectedMessage);

        // missing endAmount prefix
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                        + VALID_ENDAMOUNT_1 + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                        + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GAMETYPE_1.toString() + VALID_STARTAMOUNT_1 + VALID_ENDAMOUNT_1
                + VALID_DATE_1 +  VALID_DURATION_1 + VALID_LOCATION_1 + VALID_TAG_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid startAmount
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, MESSAGE_INVALID_START_AMOUNT);

        // invalid endAmount
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + ENDAMOUNT_INVALID_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, MESSAGE_INVALID_END_AMOUNT);

        // invalid date
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + DATE_INVALID_WITH_PREFIX + GAMEONE.DURATION_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, MESSAGE_INVALID_DATE);

        // invalid duration
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + GAMEONE.STARTAMOUNT_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + DURATION_INVALID_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, MESSAGE_INVALID_DURATION);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, GAMEONE.GAMETYPE_WITH_PREFIX + STARTAMOUNT_INVALID_WITH_PREFIX
                + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX + DURATION_INVALID_WITH_PREFIX
                + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX, MESSAGE_INVALID_START_AMOUNT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GAMEONE.GAMETYPE_WITH_PREFIX
                        + GAMEONE.STARTAMOUNT_WITH_PREFIX + GAMEONE.ENDAMOUNT_WITH_PREFIX + GAMEONE.DATE_WITH_PREFIX
                        + GAMEONE.DURATION_WITH_PREFIX + GAMEONE.LOCATION_WITH_PREFIX + GAMEONE.TAG_WITH_PREFIX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
