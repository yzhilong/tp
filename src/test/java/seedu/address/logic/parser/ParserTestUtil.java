package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.Duration;
import seedu.address.model.gameentry.EndAmount;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.gameentry.StartAmount;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

public class ParserTestUtil {

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    protected static final GameType VALID_GAMETYPE_1 = new GameType("Poker");
    protected static final StartAmount VALID_STARTAMOUNT_1 = new StartAmount("0.0");
    protected static final EndAmount VALID_ENDAMOUNT_1 = new EndAmount("100.0");
    protected static final DatePlayed VALID_DATE_1 = new DatePlayed("01/01/21 10:00");
    protected static final Duration VALID_DURATION_1 = new Duration("10");
    protected static final Location VALID_LOCATION_1 = new Location("Sentosa");
    protected static final String VALID_TAG_1 = "lucky";
    protected static final String VALID_TAG_2 = "drunk";
    protected static final Set<Tag> VALID_TAGSET_1 = Tag.parseTagList(VALID_TAG_1);
    protected static final String STARTAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_STARTAMOUNT + "abc";
    protected static final String ENDAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_ENDAMOUNT + "abc";
    protected static final String DATE_INVALID_WITH_PREFIX = " " + PREFIX_DATE + "2021/01 10:00";
    protected static final String DURATION_INVALID_WITH_PREFIX = " " + PREFIX_DURATION + "abc";


    protected static final ParserTestUtil GAMEONE = new ParserTestUtil("Poker", "0.0",
        "100.0", "01/01/21 10:00", "10", "Sentosa", "lucky");
    protected static final ParserTestUtil GAMETWO = new ParserTestUtil("Black Jack", "10.0",
        "200.0", "10/10/21 10:00", "20", "Marina Bay", "drunk");

    protected static final String TAG_EMPTY = " " + PREFIX_TAG;

    protected final String gameTypeWithPrefix;
    protected final String startAmountWithPrefix;
    protected final String endAmountWithPrefix;
    protected final String dateWithPrefix;
    protected final String durationWithPrefix;
    protected final String locationWithPrefix;
    protected final String tagWithPrefix;

    ParserTestUtil(String gameType, String startAmount, String endAmount, String date, String duration,
                   String location, String tag) {
        gameTypeWithPrefix = " " + PREFIX_GAMETYPE + " " + gameType;
        startAmountWithPrefix = " " + PREFIX_STARTAMOUNT + " " + startAmount;
        endAmountWithPrefix = " " + PREFIX_ENDAMOUNT + " " + endAmount;
        dateWithPrefix = " " + PREFIX_DATE + " " + date;
        durationWithPrefix = " " + PREFIX_DURATION + duration;
        locationWithPrefix = " " + PREFIX_LOCATION + " " + location;
        tagWithPrefix = " " + PREFIX_TAG + " " + tag;
    }

    ParserTestUtil(GameType gameType, StartAmount startAmount, EndAmount endAmount, DatePlayed date, Duration duration,
                   Location location, Set<Tag> tags) {
        gameTypeWithPrefix = " " + PREFIX_GAMETYPE + " " + gameType.toString();
        startAmountWithPrefix = " " + PREFIX_STARTAMOUNT + " " + startAmount.toString();
        endAmountWithPrefix = " " + PREFIX_ENDAMOUNT + " " + endAmount.toString();
        dateWithPrefix = " " + PREFIX_DATE + " " + date.toString();
        durationWithPrefix = " " + PREFIX_DURATION + duration.toString();
        locationWithPrefix = " " + PREFIX_LOCATION + " " + location.toString();
        tagWithPrefix = " " + PREFIX_TAG + " " + Tag.toCommandString(tags);
    }



}
