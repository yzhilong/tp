package seedu.address.storage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.gameentry.Duration;
import seedu.address.model.gameentry.EndAmount;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.gameentry.StartAmount;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link GameEntry}.
 */
class JsonAdaptedGameEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Game Entry's %s field is missing!";
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATE_INPUT_FORMAT = new SimpleDateFormat("dd/MM/yy");
    private static final DateFormat DATETIME_INPUT_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm");

    private final String gameType;
    private final String startAmount;
    private final String endAmount;
    private final String date;
    private final String durationMinutes;
    private final String location;

    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedGameEntry} with the given game entry details.
     */
    @JsonCreator
    public JsonAdaptedGameEntry(@JsonProperty("gameType") String gameType,
                                @JsonProperty("startAmount") String startAmount,
            @JsonProperty("endAmount") String endAmount, @JsonProperty("date") String date,
                                @JsonProperty("durationMinutes") String durationMinutes,
                                @JsonProperty("location") String location,
                                @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.gameType = gameType;
        this.startAmount = startAmount;
        this.endAmount = endAmount;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.location = location;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code GameEntry} into this class for Jackson use.
     */
    public JsonAdaptedGameEntry(GameEntry source) {
        gameType = source.getGameType().toString();
        startAmount = String.valueOf(source.getStartAmount());
        endAmount = String.valueOf(source.getEndAmount());
        date = source.getDate().toString();
        durationMinutes = String.valueOf(source.getDuration().getDurationMinutes());
        location = source.getLocation().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted gameEntry object into the model's {@code GameEntry} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted gameEntry.
     */
    public GameEntry toModelType() throws IllegalValueException, ParseException {
        final List<Tag> gameEntryTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            gameEntryTags.add(tag.toModelType());
        }

        if (gameType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, GameType.class.getSimpleName()));
        } else if (!GameType.isValidGameType(gameType)) {
            throw new IllegalValueException(GameType.MESSAGE_CONSTRAINTS);
        }

        if (startAmount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start amount"));
        } else if (!StartAmount.isValidAmount(startAmount)) {
            throw new IllegalValueException(StartAmount.MESSAGE_CONSTRAINTS);
        }

        if (endAmount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end amount"));
        } else if (!StartAmount.isValidAmount(endAmount)) {
            throw new IllegalValueException(EndAmount.MESSAGE_CONSTRAINTS);
        }

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date played"));
        }
        // todo: add input validation check for date
        String datePlayedString = null;
        try {
            datePlayedString = DATETIME_INPUT_FORMAT.format(DATETIME_FORMAT.parse(date));
        } catch (ParseException e) {
            try {
                datePlayedString = DATE_INPUT_FORMAT.format(DATE_FORMAT.parse(date));
            } catch (ParseException ee) {
                // do nothing
            }
        }

        if (durationMinutes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration"));
        } else if (!Duration.isValidDuration(durationMinutes)) {
            throw new IllegalValueException(Duration.MESSAGE_CONSTRAINTS);
        }

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "location"));
        } else if (!Location.isValidLocation(location)) {
            throw new IllegalValueException(Location.MESSAGE_CONSTRAINTS);
        }

        String tagString = "";
        for (Tag tag : gameEntryTags) {
            tagString += tag.tagName + ",";
        }
        tagString = tagString.length() > 0 ? tagString.substring(0, tagString.length() - 1) : tagString;
        return new GameEntry(gameType, startAmount, endAmount, datePlayedString, durationMinutes, location,
                tagString);
    }

}
