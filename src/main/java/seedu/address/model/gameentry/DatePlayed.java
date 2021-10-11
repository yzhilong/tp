package seedu.address.model.gameentry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePlayed implements Comparable<DatePlayed> {

    private static DateFormat DATE_FORMAT_WITH_MINUTES = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private static DateFormat DATE_FORMAT_WITHOUT_MINUTES = new SimpleDateFormat("yyyy-MM-dd");
    private final Date datePlayed;
    private boolean isMinuteIndicated = true;

    /**
     * Constructs DatePlayed object with minutes timestamp indicated.
     */
    public DatePlayed() {
        this.datePlayed = new Date();
    }

    /**
     * Constructs DatePlayed object.
     *
     * @param date Date that this object represents.
     * @param isMinuteIndicated boolean to indicate whether to ignore minute timestamp.
     */
    public DatePlayed(Date date, boolean isMinuteIndicated) {
        this.datePlayed = date;
        this.isMinuteIndicated = isMinuteIndicated;
    }

    /**
     * Constructs DatePlayed object.
     *
     * @param date Date that this object represents.
     */
    public DatePlayed(Date date) {
        this.datePlayed = date;
    }

    /**
     * Constructs DatePlayed object.
     *
     * @param milliSecondsSinceEpoch long representing number of milliseconds since epoch start.
     */
    public DatePlayed(long milliSecondsSinceEpoch) {
        this.datePlayed = new Date(milliSecondsSinceEpoch);
    }

    /**
     * Gets isMinuteIndicated field of DatePlayed object.
     *
     * @return Whether minute field is indicated.
     */
    public boolean getIsMinuteIndicated() {
        return isMinuteIndicated;
    }

    private boolean sameMinute(DatePlayed other) {
        return sameDay(other)
                && datePlayed.getHours() == other.datePlayed.getHours()
                && datePlayed.getMinutes() == other.datePlayed.getMinutes();
    }

    private boolean sameDay(DatePlayed other) {
        return datePlayed.getDate() == other.datePlayed.getDate()
                && datePlayed.getMonth() == other.datePlayed.getMonth()
                && datePlayed.getYear() == other.datePlayed.getYear();
    }

    /**
     * Checks if another object is equal to this DatePlayed.
     *
     * @param other Object to check if is equal.
     * @return Whether the other object is equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DatePlayed) {
            DatePlayed tmp = (DatePlayed) other;
            return isMinuteIndicated && tmp.isMinuteIndicated
                    ? sameDay(tmp)
                    : sameMinute(tmp);
        }
        return false;
    }

    @Override
    public int compareTo(DatePlayed other) {
        if (this == other || equals(other)) {
            return 0;
        } else {
            return datePlayed.compareTo(other.datePlayed);
        }
    }

    @Override
    public String toString() {
        return isMinuteIndicated
                ? DATE_FORMAT_WITH_MINUTES.format(datePlayed)
                : DATE_FORMAT_WITHOUT_MINUTES.format(datePlayed);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
