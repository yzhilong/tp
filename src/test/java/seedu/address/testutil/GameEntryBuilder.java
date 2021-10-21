package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building GameEntry objects.
 */
public class GameEntryBuilder {


    public static final String DEFAULT_GAMETYPE = "Poker";
    public static final DatePlayed DEFAULT_DATE;

    static {
        DatePlayed defaultDate1 = new DatePlayed("01/01/21 10:00");
        DEFAULT_DATE = defaultDate1;
    }
    public static final Integer DEFAULT_DURATION = 10;
    public static final Double DEFAULT_ENDAMOUNT = 100.0;
    public static final String DEFAULT_LOCATION = "Sentosa";
    public static final Double DEFAULT_STARTAMOUNT = 0.0;

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
        date = DEFAULT_DATE;
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
    public GameEntryBuilder withStartAmount(String startAmount) {
        if (startAmount.equals("")) {
            this.startAmount = 0.0;
            return this;
        }
        String trimmedStartAmount = startAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedStartAmount);
            this.startAmount = amount;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the end amount of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@endAmount} is invalid.
     */
    public GameEntryBuilder withEndAmount(String endAmount) {
        String trimmedEndAmount = endAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedEndAmount);
            this.endAmount = amount;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Sets the {@code DatePlayed} of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@code datePlayed} is invalid.
     */
    public GameEntryBuilder withDatePlayed (String datePlayed) {

        try {
            this.date = new DatePlayed(datePlayed);
        } catch (IllegalArgumentException e) {
            assert(false);
        }
        return this;
    }

    /**
     * Sets the duration of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@duration} is invalid.
     */
    public GameEntryBuilder withDuration(String duration) {
        if (duration.equals("")) {
            this.duration = Integer.MIN_VALUE;
            return this;
        }
        String trimmedDuration = duration.trim();
        Integer durationInMinutes;
        try {
            durationInMinutes = Integer.parseInt(trimmedDuration);
            this.duration = durationInMinutes;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
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
