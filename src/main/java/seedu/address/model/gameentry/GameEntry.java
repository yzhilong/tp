package seedu.address.model.gameentry;

// import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

public class GameEntry {

    private final GameType gameType;
    private final StartAmount startAmount;
    private final EndAmount endAmount;
    private final DatePlayed date;
    private final Duration durationMinutes;
    private final Location location;
    private final Set<Tag> tags;


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
        this.startAmount = new StartAmount(startAmount.toString());
        this.endAmount = new EndAmount(endAmount.toString());
        this.date = date;
        this.durationMinutes = new Duration(durationMinutes.toString());
        this.location = new Location(location);
        if (tags != null) {
            this.tags = new HashSet<>();
            this.tags.addAll(tags);
        } else {
            this.tags = new HashSet<>();
        }
    }

    /**
     * Constructs GameEntry.
     *
     * @param gameType
     * @param startAmount
     * @param endAmount
     * @param datePlayed
     * @param duration
     * @param location
     * @param tags
     */
    public GameEntry(String gameType, String startAmount, String endAmount, String datePlayed, String duration,
                     String location, String tags) {
        this.gameType = new GameType(gameType);
        this.startAmount = new StartAmount(startAmount);
        this.endAmount = new EndAmount(endAmount);
        this.date = new DatePlayed(datePlayed);
        this.durationMinutes = new Duration(duration);
        this.location = new Location(location);
        this.tags = Tag.parseTagList(tags);
    }

    /**
     * Gets the game type of this game entry.
     *
     * @return Game type.
     */
    public String getGameType() {
        return gameType.toString();
    }

    /**
     * Gets the start amount of this game entry.
     *
     * @return Start amount.
     */
    public Double getStartAmount() {
        return startAmount.getAmount();
    }

    /**
     * Gets the end amount of this game entry.
     *
     * @return End amount.
     */
    public Double getEndAmount() {
        return endAmount.getAmount();
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
        return durationMinutes.getDurationMinutes();
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

    /**
     * Returns a boolean indicating whether there are any tags.
     */
    public boolean hasTags() {
        return !tags.isEmpty();
    }

    /**
     * Returns true if {@code otherGameEntry} is considered the same.
     *
     * @param otherGameEntry Other object to compare with.
     * @return Whether the objects are considered the same.
     */

    // TODO - might remove if we are not checking for identical game entries
    public boolean isSameGameEntry(GameEntry otherGameEntry) {
        if (otherGameEntry == null) {
            return false;
        }
        return equals(otherGameEntry)
                || (gameType.equals(otherGameEntry.gameType)
                        && date.getIsTimeIndicated()
                        && otherGameEntry.date.getIsTimeIndicated()
                        && date.equals(otherGameEntry.date));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof GameEntry) {
            GameEntry tmp = (GameEntry) other;
            // If either game entry does not have minute field indicated in date, then they will
            // not be considered as equal
            return gameType.equals(tmp.gameType)
                    && startAmount.equals(tmp.startAmount)
                    && endAmount.equals(tmp.endAmount)
                    && (date.getIsTimeIndicated() && tmp.date.getIsTimeIndicated() && date.equals(tmp.date))
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
                startAmount.getAmount(),
                endAmount.getAmount(),
                date);
        if (durationMinutes.getDurationMinutes() >= 0) {
            output += "; Game duration: " + durationMinutes.getDurationMinutes();
        }
        if (!location.equals("")) {
            output += "; Location: " + location.toString();
        }
        if (tags.size() > 0) {
            output += "; Tags: " + tags.toString();
        }
        return output;
    }

}
