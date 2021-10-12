package seedu.address.storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link GameEntry}.
 */
class JsonAdaptedGameEntry {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Game Entry's %s field is missing!";

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
        gameType = source.getGameType();
        startAmount = String.valueOf(source.getStartAmount());
        endAmount = String.valueOf(source.getEndAmount());
        date = source.getDate().toString();
        durationMinutes = String.valueOf(source.getDurationMinutes());
        location = source.getLocation();
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
        }
        // todo: add validation check for gameType and MESSAGE_CONSTRAINTS to GameType class; possible usage below
        // if (!GameType.isValidGameType(gameType)) {
        //     throw new IllegalValueException(GameType.MESSAGE_CONSTRAINTS);
        // }
        final GameType modelGameType = new GameType(gameType);

        if (startAmount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "start amount"));
        }
        // todo: add validation check for start amount
        final Double modelStartAmount = Double.parseDouble(startAmount);

        if (endAmount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "end amount"));
        }
        // todo: add validation check for end amount
        final Double modelEndAmount = Double.parseDouble(endAmount);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "date played"));
        }
        // todo: add input validation check for date
        final DatePlayed modelDate = new DatePlayed(new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(date));

        if (durationMinutes == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration"));
        }
        // todo: add input validation check for durationMinutes
        final Integer modelDurationMinutes = Integer.valueOf(durationMinutes);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "location"));
        }
        // todo: add input validation check for location (similar to gameType validation)
        final Location modelLocation = new Location(location);

        final Set<Tag> modelTags = new HashSet<>(gameEntryTags);
        return new GameEntry(modelGameType.toString(), modelStartAmount,
                modelEndAmount, modelDate, modelDurationMinutes,
                modelLocation.toString(), modelTags);
    }

}
