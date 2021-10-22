package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.Duration;
import seedu.address.model.gameentry.EndAmount;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.gameentry.StartAmount;
import seedu.address.model.tag.Tag;

public class ParserTestUtil {

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    protected static final GameType VALID_GAMETYPE_1 = new GameType("Poker");
    protected static final StartAmount VALID_STARTAMOUNT_1 = new StartAmount("0.0");
    protected static final EndAmount VALID_ENDAMOUNT_1 = new EndAmount("100.0");
    protected static final DatePlayed VALID_DATE_1 = new DatePlayed("01/01/21 10:00");
    protected static final Duration VALID_DURATION_1 = new Duration("10");
    protected static final Location VALID_LOCATION_1 = new Location("Sentosa");
    protected static final String VALID_TAG_1 = "lucky";
    protected static final Set<Tag> VALID_TAGSET_1 = Tag.parseTagList(VALID_TAG_1);

    protected static final GameType VALID_GAMETYPE_2 = new GameType("Black Jack");
    protected static final StartAmount VALID_STARTAMOUNT_2 = new StartAmount("10.0");
    protected static final EndAmount VALID_ENDAMOUNT_2 = new EndAmount("200.0");
    protected static final DatePlayed VALID_DATE_2 = new DatePlayed("10/10/21");
    protected static final Duration VALID_DURATION_2 = new Duration("20");
    protected static final Location VALID_LOCATION_2 = new Location("Marina Bay");
    protected static final String VALID_TAG_2 = "drunk";
    protected static final Set<Tag> VALID_TAGSET_2 = Tag.parseTagList(VALID_TAG_2);

    protected static final String STARTAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_STARTAMOUNT + "abc";
    protected static final String ENDAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_ENDAMOUNT + "abc";
    protected static final String DATE_INVALID_WITH_PREFIX = " " + PREFIX_DATE + "2021/01 10:00";
    protected static final String DURATION_INVALID_WITH_PREFIX = " " + PREFIX_DURATION + "abc";


    protected static final ParserTestUtil GAMEONE = new ParserTestUtil(VALID_GAMETYPE_1, VALID_STARTAMOUNT_1,
            VALID_ENDAMOUNT_1, VALID_DATE_1, VALID_DURATION_1, VALID_LOCATION_1, VALID_TAGSET_1);
    protected static final ParserTestUtil GAMETWO = new ParserTestUtil(VALID_GAMETYPE_2, VALID_STARTAMOUNT_2,
            VALID_ENDAMOUNT_2, VALID_DATE_2, VALID_DURATION_2, VALID_LOCATION_2, VALID_TAGSET_2);

    protected static final String TAG_EMPTY = " " + PREFIX_TAG;

    protected final String gameTypeWithPrefix;
    protected final String startAmountWithPrefix;
    protected final String endAmountWithPrefix;
    protected final String dateWithPrefix;
    protected final String durationWithPrefix;
    protected final String locationWithPrefix;
    protected final String tagWithPrefix;

    ParserTestUtil(GameType gameType, StartAmount startAmount, EndAmount endAmount, DatePlayed date, Duration duration,
                   Location location, Set<Tag> tags) {
        gameTypeWithPrefix = " " + PREFIX_GAMETYPE + " " + gameType.toCommandString();
        startAmountWithPrefix = " " + PREFIX_STARTAMOUNT + " " + startAmount.toCommandString();
        endAmountWithPrefix = " " + PREFIX_ENDAMOUNT + " " + endAmount.toCommandString();
        dateWithPrefix = " " + PREFIX_DATE + " " + date.toCommandString();
        durationWithPrefix = " " + PREFIX_DURATION + duration.toCommandString();
        locationWithPrefix = " " + PREFIX_LOCATION + " " + location.toCommandString();
        tagWithPrefix = " " + PREFIX_TAG + " " + Tag.toCommandString(tags);
    }



}
