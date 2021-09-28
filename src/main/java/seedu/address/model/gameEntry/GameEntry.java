package seedu.address.model.gameEntry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class GameEntry {

    private final GameType gameType;
    private final Float startAmount;
    private final Float endAmount;
    private final DatePlayed date;
    private final Integer durationMinutes;
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Constructs GameEntry.
     *
     * @param gameType
     * @param startAmount
     * @param endAmount
     * @param date
     * @param durationMinutes
     * @param location
     * @param tags
     */
    public GameEntry(String gameType, Float startAmount, Float endAmount, DatePlayed date, Integer durationMinutes,
                     String location, Set<Tag> tags) {
        requireAllNonNull(gameType, startAmount, endAmount);
        this.gameType = new GameType(gameType);
        this.startAmount = startAmount;
        this.endAmount = endAmount;
        this.date = date != null ? date : new DatePlayed();
        this.durationMinutes = durationMinutes != null ? durationMinutes : Integer.MIN_VALUE;
        this.location = location != null ? new Location(location) : new Location("");
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    public String getGameType() {
        return gameType.toString();
    }

    public Float getStartAmount() {
        return startAmount;
    }

    public Float getEndAmount() {
        return endAmount;
    }

    /**
     * Gets the date on which this game was played.
     *
     * @return Date the game was played.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets the duration of the game. Negative values indicate that this field is
     * empty.
     *
     * @return Duration of game in minutes.
     */
    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * Gets the location where the game was played. Empty string indicates that
     * the field is empty.
     *
     * @return Location where the game was played.
     */
    public String getLocation() {
        return location.toString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public boolean containsTag(Tag tag) {
        return tags.contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof GameEntry) {
            GameEntry tmp = (GameEntry) other;
            return gameType.equals(tmp.gameType)
                    && startAmount.equals(tmp.startAmount)
                    && endAmount.equals(tmp.endAmount)
                    && date.equals(date)
                    && durationMinutes.equals(tmp.durationMinutes)
                    && location.equals(tmp.location)
                    && tags.equals(tmp.tags);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameType, startAmount, endAmount, date, durationMinutes, location, tags);
    }

    @Override
    public String toString() {
        String output = String.format(
                "Game type: %s; Start amount: %.2f; End amount: %.2f; Date played: %s",
                gameType,
                startAmount,
                endAmount,
                date);
        if (durationMinutes >= 0) {
            output += "; Game duration: " + durationMinutes.toString();
        }
        if (!location.equals("")) {
            output += "; location: " + location;
        }
        if (tags.size() > 0) {
            output += "; tags: " + tags.toString();
        }
        return output;
    }

}
