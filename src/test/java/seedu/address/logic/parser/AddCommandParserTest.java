package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.testutil.GameEntryBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddCommandParserTest {

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    private static final String VALID_GAMETYPE = "Poker";
    private static final Double VALID_STARTAMOUNT = 0.0;
    private static final Double VALID_ENDAMOUNT = 100.0;
    private static DatePlayed VALID_DATE;

    static {
        try {
            VALID_DATE = new DatePlayed(new SimpleDateFormat("dd/MM/yy").parse("01/01/21")) ;
        } catch (ParseException e) {
            VALID_DATE = null;
        }
    }

    private static final Integer VALID_DURATION = 10;
    private static final String VALID_LOCATION = "Sentosa";
    private static final String VALID_TAG_1 = "lucky";
    private static final String VALID_TAG_2 = "drunk";

    private static final String STARTAMOUNT_INVALID_STARTAMOUNT = " " + PREFIX_STARTAMOUNT + " " + "abc";
    private static final String ENDAMOUNT_INVALID_ENDAMOUNT = " " + PREFIX_ENDAMOUNT + " " + "abc";
    private static final String DATE_INVALID_DATE = " " + PREFIX_DATE + " " + "2021/01/01";
    private static final String DURATION_INVALID_DURATION = " " + PREFIX_DURATION + " " + "abc";


    private static final String GAMETYPE_VALID_GAMETYPE_1 = " "+ PREFIX_GAMETYPE+ " " + "Poker";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_1 = " " + PREFIX_STARTAMOUNT + " " + "0.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_1 = " " + PREFIX_ENDAMOUNT + " " + "100.0";
    private static final String DATE_VALID_DATE_1 = " " + PREFIX_DATE + " " + "01/01/21";
    private static final String DURATION_VALID_DURATION_1 = " " + PREFIX_DURATION + " " + "10";
    private static final String LOCATION_VALID_LOCATION_1 = " " + PREFIX_LOCATION + " " + "Sentosa";
    private static final String TAG_VALID_TAG_1 = " " + PREFIX_TAG + " " + "lucky";


    private static final String GAMETYPE_VALID_GAMETYPE_2 = " "+ PREFIX_GAMETYPE+ " " + "Black Jack";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_2 = " " + PREFIX_STARTAMOUNT + " " + "10.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_2 = " " + PREFIX_ENDAMOUNT + " " + "200.0";
    private static final String DATE_VALID_DATE_2 = " " + PREFIX_DATE + " " + "10/10/21";
    private static final String DURATION_VALID_DURATION_2 = " " + PREFIX_DURATION + " " + "20";
    private static final String LOCATION_VALID_LOCATION_2 = " " + PREFIX_LOCATION + " " + "Marina Bay";
    private static final String TAG_VALID_TAG_2 = " " + PREFIX_TAG + " " + "drunk";

    private static final GameEntry GAME_1 = new GameEntry(VALID_GAMETYPE, VALID_STARTAMOUNT, VALID_ENDAMOUNT,
            VALID_DATE, VALID_DURATION, VALID_LOCATION, null);




    private AddCommandParser parser = new AddCommandParser();



    @Test
    public void parse_allFieldsPresent_success() {
        GameEntry expectedGameEntry = new GameEntryBuilder(GAME_1).withTags(VALID_TAG_1).build();


        // multiple gameTypes - last gameType accepted
        assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_2 + GAMETYPE_VALID_GAMETYPE_1
                + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1
                + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple startAmount - last startAmount accepted
        assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_2
                + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1
                + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple endAmount - last endAmount accepted
        assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_2 + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1
                + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple date - last date accepted
        assertParseSuccess(parser,  GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_2 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_2
                + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple location - last location accepted
        assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_2 + LOCATION_VALID_LOCATION_1
                + TAG_VALID_TAG_1, new AddCommand(expectedGameEntry));

        // multiple tags - all accepted
        GameEntry expectedGameEntryMultipleTags = new GameEntryBuilder(GAME_1).withTags(VALID_TAG_1, VALID_TAG_2)
                .build();
        assertParseSuccess(parser,      // multiple duration - last duration accepted
                assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                        + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                        + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1
                        + TAG_VALID_TAG_2, new AddCommand(expectedGameEntryMultipleTags)));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        try {
            // zero tags
            GameEntry expectedGameEntryNoTags = new GameEntryBuilder(GAME_1).withTags().build();
            assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                    + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                    + LOCATION_VALID_LOCATION_1, new AddCommand(expectedGameEntryNoTags));
            // no startAmount
            GameEntry expectedGameEntryNoStartAmount = new GameEntryBuilder(GAME_1).withStartAmount("0.0").build();
            assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + ENDAMOUNT_VALID_ENDAMOUNT_1
                    + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                    + LOCATION_VALID_LOCATION_1, new AddCommand(expectedGameEntryNoStartAmount));
            // no date
            GameEntry expectedGameEntryNoDate = new GameEntryBuilder(GAME_1).withDatePlayed("").build();
            assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                    + ENDAMOUNT_VALID_ENDAMOUNT_1 + DURATION_VALID_DURATION_1
                    + LOCATION_VALID_LOCATION_1, new AddCommand(expectedGameEntryNoDate));
            // no duration
            GameEntry expectedGameEntryNoDuration = new GameEntryBuilder(GAME_1).withDuration("").build();
            assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                    + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 +
                    LOCATION_VALID_LOCATION_1, new AddCommand(expectedGameEntryNoDuration));
            // no location
            GameEntry expectedGameEntryNoLocation = new GameEntryBuilder(GAME_1).withLocation("").build();
            assertParseSuccess(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                    + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1, new AddCommand(expectedGameEntryNoDuration));
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing gameType prefix
        assertParseFailure(parser, VALID_GAMETYPE + STARTAMOUNT_VALID_STARTAMOUNT_1
                        + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                        + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, expectedMessage);

        // missing endAmount prefix
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                        + VALID_ENDAMOUNT + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                        + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_GAMETYPE + VALID_STARTAMOUNT + VALID_ENDAMOUNT + VALID_DATE +
                        VALID_DURATION + VALID_LOCATION + VALID_TAG_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid startAmount
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_INVALID_STARTAMOUNT
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, MESSAGE_INVALID_START_AMOUNT);

        // invalid endAmount
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_INVALID_ENDAMOUNT + DATE_VALID_DATE_1 + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, MESSAGE_INVALID_END_AMOUNT);

        // invalid date
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_INVALID_DATE + DURATION_VALID_DURATION_1
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, MESSAGE_INVALID_DATE);

        // invalid duration
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_VALID_STARTAMOUNT_1
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_INVALID_DURATION
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, MESSAGE_INVALID_DURATION);


        // two invalid values, only first invalid value reported
        assertParseFailure(parser, GAMETYPE_VALID_GAMETYPE_1 + STARTAMOUNT_INVALID_STARTAMOUNT
                + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1 + DURATION_INVALID_DURATION
                + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1, MESSAGE_INVALID_START_AMOUNT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + GAMETYPE_VALID_GAMETYPE_1
                        + STARTAMOUNT_VALID_STARTAMOUNT_1 + ENDAMOUNT_VALID_ENDAMOUNT_1 + DATE_VALID_DATE_1
                        + DURATION_VALID_DURATION_1 + LOCATION_VALID_LOCATION_1 + TAG_VALID_TAG_1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
