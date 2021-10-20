package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePlayed implements Comparable<DatePlayed> {
    private static final DateFormat DATE_FORMAT_WITH_MINUTES = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat DATE_FORMAT_WITHOUT_MINUTES = new SimpleDateFormat("yyyy-MM-dd");
    private final Date datePlayed;
    private boolean isTimeIndicated = true;

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
     * @param isTimeIndicated boolean to indicate whether to ignore minute timestamp.
     */
    public DatePlayed(Date date, boolean isTimeIndicated) {
        this.datePlayed = date;
        this.isTimeIndicated = isTimeIndicated;
    }

    /**
     * Constructs DatePlayed object.
     *
     * @param date Date that this object represents.
     */
    public DatePlayed(Date date) {
        requireNonNull(date);
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
    public boolean getIsTimeIndicated() {
        return isTimeIndicated;
    }

    private boolean sameMinute(DatePlayed other) {
        Calendar otherCalendar = new GregorianCalendar();
        otherCalendar.setTime(other.datePlayed);
        Calendar thisCalendar = new GregorianCalendar();
        thisCalendar.setTime(datePlayed);
        return sameDay(other)
                && thisCalendar.get(Calendar.HOUR_OF_DAY) == otherCalendar.get(Calendar.HOUR_OF_DAY)
                && thisCalendar.get(Calendar.MINUTE) == otherCalendar.get(Calendar.MINUTE);
    }

    private boolean sameDay(DatePlayed other) {
        Calendar otherCalendar = new GregorianCalendar();
        otherCalendar.setTime(other.datePlayed);
        Calendar thisCalendar = new GregorianCalendar();
        thisCalendar.setTime(datePlayed);
        return thisCalendar.get(Calendar.DAY_OF_YEAR) == otherCalendar.get(Calendar.DAY_OF_YEAR);
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
            if (isTimeIndicated && !tmp.isTimeIndicated || !isTimeIndicated && tmp.isTimeIndicated) {
                return false;
            }
            return isTimeIndicated && tmp.isTimeIndicated
                    ? sameMinute(tmp)
                    : sameDay(tmp);
        }
        return false;
    }

    /**
     * Compares this DatePlayed object with another DatePlayed object.
     * @param other Other DatePlayed to be compared to
     * @return A negative integer, zero, or a positive integer if the date of this DatePlayed is earlier than, same as,
     * or later than date of otherGameEntry respectively. Note that if a DatePlayed does not have a time, the time will
     * be defaulted to 12am.
     */
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
        return isTimeIndicated
                ? DATE_FORMAT_WITH_MINUTES.format(datePlayed)
                : DATE_FORMAT_WITHOUT_MINUTES.format(datePlayed);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
