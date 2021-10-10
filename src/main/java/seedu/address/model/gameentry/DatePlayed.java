package seedu.address.model.gameentry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePlayed {

    private static DateFormat DATE_FORMAT_WITH_MINUTES = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    private static DateFormat DATE_FORMAT_WITHOUT_MINUTES = new SimpleDateFormat("yyyy-MM-dd");
    private final Date datePlayed;
    private boolean minuteIndicated = true;

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
     * @param minuteIndicated boolean to indicate whether to ignore minute timestamp.
     */
    public DatePlayed(Date date, boolean minuteIndicated) {
        this.datePlayed = date;
        this.minuteIndicated = minuteIndicated;
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
     * Checks if another object is equal to this DatePlayed. Two DatePlayed objects are
     * equal if and only if they occur within the same minute of each other.
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
            return minuteIndicated && tmp.minuteIndicated
                    ? datePlayed.equals(tmp.datePlayed)
                    : sameMinute(tmp);
        }
        return false;
    }

    private boolean sameMinute(DatePlayed other) {
        int millisecondsPerMinute = 60000;
        long minutesSinceEpoch = datePlayed.getTime() / millisecondsPerMinute;
        long otherMinutesSinceEpoch = other.datePlayed.getTime() / millisecondsPerMinute;
        return minutesSinceEpoch == otherMinutesSinceEpoch;
    }

    @Override
    public String toString() {
        return minuteIndicated
                ? DATE_FORMAT_WITH_MINUTES.format(datePlayed)
                : DATE_FORMAT_WITHOUT_MINUTES.format(datePlayed);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
