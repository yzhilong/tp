package seedu.address.logic.parser;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class ParserTestUtil {

    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    protected static final GameType VALID_GAMETYPE_1 = new GameType("Poker");
    protected static final Double VALID_STARTAMOUNT_1 = 0.0;
    protected static final Double VALID_ENDAMOUNT_1 = 100.0;
    protected static DatePlayed VALID_DATE_1;

    static {
        try {
            VALID_DATE_1 = new DatePlayed(new SimpleDateFormat("dd/MM/yy").parse("01/01/21")) ;
        } catch (ParseException e) {
            VALID_DATE_1 = null;
        }
    }

    protected static final Integer VALID_DURATION_1 = 10;
    protected static final Location VALID_LOCATION_1 = new Location("Sentosa");
    protected static final String VALID_TAG_1 = "lucky";
    protected static final String VALID_TAG_2 = "drunk";

    protected static final String STARTAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_STARTAMOUNT + "abc";
    protected static final String ENDAMOUNT_INVALID_WITH_PREFIX = " " + PREFIX_ENDAMOUNT + "abc";
    protected static final String DATE_INVALID_WITH_PREFIX = " " + PREFIX_DATE + "2021/01/01";
    protected static final String DURATION_INVALID_WITH_PREFIX = " " + PREFIX_DURATION + "abc";


    protected static final String TAG_EMPTY = " " + PREFIX_TAG;

    protected final String GAMETYPE_WITH_PREFIX;
    protected final String STARTAMOUNT_WITH_PREFIX;
    protected final String ENDAMOUNT_WITH_PREFIX;
    protected final String DATE_WITH_PREFIX;
    protected final String DURATION_WITH_PREFIX;
    protected final String LOCATION_WITH_PREFIX;
    protected final String TAG_WITH_PREFIX;

    ParserTestUtil(String gameType, String startAmount, String endAmount, String date, String duration,
                   String location, String tag) {
        GAMETYPE_WITH_PREFIX =  " "+ PREFIX_GAMETYPE + gameType;
        STARTAMOUNT_WITH_PREFIX = " " + PREFIX_STARTAMOUNT + startAmount;
        ENDAMOUNT_WITH_PREFIX = " " + PREFIX_ENDAMOUNT + endAmount;
        DATE_WITH_PREFIX = " " + PREFIX_DATE + date;
        DURATION_WITH_PREFIX = " " + PREFIX_DURATION + duration;
        LOCATION_WITH_PREFIX = " " + PREFIX_LOCATION + location;
        TAG_WITH_PREFIX = " " + PREFIX_TAG + tag;
        
    }
    
    protected static final ParserTestUtil GAMEONE = new ParserTestUtil("Poker", "0.0", "100.0",
            "01/01/21", "10", "Sentosa", "lucky");
    protected static final ParserTestUtil GAMETWO = new ParserTestUtil("Black Jack", "10.0",
            "200.0", "10/10/21", "20", "Marina Bay", "drunk");

}
