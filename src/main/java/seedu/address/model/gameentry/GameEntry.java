package seedu.address.model.gameentry;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class GameEntry {

    private final GameType gameType;
    private final Double startAmount;
    private final Double endAmount;
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
    public GameEntry(String gameType, Double startAmount, Double endAmount, DatePlayed date, Integer durationMinutes,
                     String location, Set<Tag> tags) {
        requireAllNonNull(gameType, startAmount, endAmount);
        this.gameType = new GameType(gameType);
        this.startAmount = startAmount;
        this.endAmount = endAmount;
        this.date = date != null ? date : new DatePlayed();
        this.durationMinutes = durationMinutes != null ? durationMinutes : Integer.MIN_VALUE;
        this.location = location != null ? new Location(location) : new Location();
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    public String getGameType() {
        return gameType.toString();
    }

    public Double getStartAmount() {
        return startAmount;
    }

    public Double getEndAmount() {
        return endAmount;
    }

    /**
     * Gets the date on which this game was played.
     *
     * @return Date the game was played.
     */
    public DatePlayed getDate() {
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

    public boolean isSameGameEntry(GameEntry otherGameEntry) {
        if (this == otherGameEntry || this.equals(otherGameEntry)) {
            return true;
        }
        return otherGameEntry != null
                && otherGameEntry.getGameType().equals(getGameType())
                && otherGameEntry.getDate().equals(getDate());
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
