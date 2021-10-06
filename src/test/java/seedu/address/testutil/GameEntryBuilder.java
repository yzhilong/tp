package seedu.address.testutil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DATE;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_END_AMOUNT;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_START_AMOUNT;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;


/**
 * A utility class to help with building GameEntry objects.
 */
public class GameEntryBuilder {
    ;

    public static final String DEFAULT_GAMETYPE = "Poker";
    public static final Double DEFAULT_STARTAMOUNT = 0.0;
    public static final Double DEFAULT_ENDAMOUNT = 100.0;
    public static final Date DEFAULT_DATE = new Date();
    public static final Integer DEFAULT_DURATION = 10;
    public static final String DEFAULT_LOCATION = "Marina Bay Sands";

    private String gameType;
    private Double startAmount;
    private Double endAmount;
    private DatePlayed date;
    private Integer duration;
    private String location;
    private Set<Tag> tags;

    /**
     * Creates a {@code GameEntryBuilder} with the default details.
     */
    public GameEntryBuilder() {
        gameType = DEFAULT_GAMETYPE;
        startAmount = DEFAULT_STARTAMOUNT;
        endAmount = DEFAULT_ENDAMOUNT;
        date = new DatePlayed(DEFAULT_DATE);
        duration = DEFAULT_DURATION;
        location = DEFAULT_LOCATION;
        tags = new HashSet<>();
    }

    /**
     * Initializes the GameEntryBuilder with the data of {@code gameEntryToCopy}.
     */
    public GameEntryBuilder(GameEntry gameEntryToCopy) {
        gameType = gameEntryToCopy.getGameType();
        startAmount = gameEntryToCopy.getStartAmount();
        endAmount = gameEntryToCopy.getEndAmount();
        date = gameEntryToCopy.getDate();
        duration = gameEntryToCopy.getDurationMinutes();
        location = gameEntryToCopy.getLocation();
        tags = new HashSet<>(gameEntryToCopy.getTags());
    }

    /**
     * Sets the {@code GameType} of the {@code GameEntry} that we are building.
     */
    public GameEntryBuilder withGameType(String gameType) {
        String trimmedGameType = gameType.trim();
        this.gameType = trimmedGameType;
        return this;
    }

    /**
     * Sets the start amount of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@startAmount} is invalid.
     */
    public GameEntryBuilder withStartAmount(String startAmount) throws ParseException {
        String trimmedStartAmount = startAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedStartAmount);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_START_AMOUNT);
        }
        this.startAmount = amount;
        return this;
    }

    /**
     * Sets the end amount of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@endAmount} is invalid.
     */
    public GameEntryBuilder withEndAmount(String endAmount) throws ParseException {
        String trimmedEndAmount = endAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedEndAmount);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_END_AMOUNT);
        }
        this.endAmount = amount;
        return this;
    }

    /**
     * Sets the {@code DatePlayed} of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@code datePlayed} is invalid.
     */
    public GameEntryBuilder withDatePlayed (String datePlayed) throws ParseException {
        if (datePlayed.equals("")) {
            this.date = new DatePlayed();
            return this;
        }
        String trimmedDatePlayed = datePlayed.trim();
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yy HH:mm").parse(trimmedDatePlayed);
            this.date = new DatePlayed(date);
        } catch (java.text.ParseException e) {
            date = null;
        }

        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(trimmedDatePlayed);
            this.date = new DatePlayed(date);
        } catch (java.text.ParseException e) {
            date = null;
        }

        if (date == null) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
        return this;
    }

    /**
     * Sets the duration of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@duration} is invalid.
     */
    public GameEntryBuilder withDuration(String duration) throws ParseException {
        if (duration.equals("")) {
            this.duration = Integer.MIN_VALUE;
            return this;
        }
        String trimmedDuration = duration.trim();
        Integer durationInMinutes;
        try {
            durationInMinutes = Integer.parseInt(trimmedDuration);
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }
        this.duration = durationInMinutes;
        return this;
    }

    /**
     * Sets the location of the {@code GameEntry} that we are building.
     *
     */
    public GameEntryBuilder withLocation(String location) {
        if (location.equals("")) {
            this.location = "";
            return this;
        }
        String trimmedLocation = location.trim();
        this.location = trimmedLocation;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public GameEntryBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public GameEntry build() {
        return new GameEntry(gameType, startAmount, endAmount, date, duration, location, tags);
    }

}
