package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePlayed implements Comparable<DatePlayed> {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in \"dd/MM/yy HH:mm\" or \"dd/MM/yy\" format.";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final DateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat DATE_INPUT_FORMAT = new SimpleDateFormat("dd/MM/yy");
    private static final DateFormat DATETIME_INPUT_FORMAT = new SimpleDateFormat("dd/MM/yy HH:mm");
    private static final DatePlayed EMPTY = new DatePlayed();
    private final Date datePlayed;
    private boolean isTimeIndicated = true;

    /**
     * Constructs DatePlayed object with minutes timestamp indicated.
     */
    private DatePlayed() {
        this.datePlayed = new Date();
    }

    /**
     * Constructs DatePlayed object from a String.
     *
     * @param datePlayedString String representing DatePlayed.
     */
    public DatePlayed(String datePlayedString) throws IllegalArgumentException {
        requireNonNull(datePlayedString);
        checkArgument(isValidDatePlayed(datePlayedString), MESSAGE_CONSTRAINTS);
        String trimmedString = datePlayedString.trim();
        Date date;
        try {
            date = trimmedString.equals("")
                    ? new Date()
                    : DATETIME_INPUT_FORMAT.parse(trimmedString);
        } catch (ParseException e) {
            try {
                date = DATE_INPUT_FORMAT.parse(trimmedString);
                this.isTimeIndicated = false;
            } catch (ParseException parseException) {
                // Will never happen
                date = null;
            }
        }

        datePlayed = date;
    }

    /**
     * Constructs DatePlayed.
     *
     * @param date
     */
    public DatePlayed(Date date) {
        requireNonNull(date);
        datePlayed = date;
    }

    public static DatePlayed empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    /**
     * Checks if given string is valid input string for DatePlayed constructor.
     *
     * @param datePlayedString Given input string.
     * @return Whether input string is valid DatePlayed constructor string.
     */
    public static boolean isValidDatePlayed(String datePlayedString) {
        String trimmedString = datePlayedString.trim();
        if (trimmedString.equals("")) {
            return true;
        }
        Date date;
        try {
            date = DATETIME_INPUT_FORMAT.parse(trimmedString);
        } catch (ParseException e) {
            date = null;
        }

        try {
            date = date == null
                    ? DATE_INPUT_FORMAT.parse(trimmedString)
                    : date;
        } catch (ParseException e) {
            date = null;
        }
        return date != null
                && (trimmedString.equals(DATETIME_INPUT_FORMAT.format(date))
                || trimmedString.equals(DATE_INPUT_FORMAT.format(date)));
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

    @Override
    public int compareTo(DatePlayed other) {
        if (this == other || equals(other)) {
            return 0;
        } else if (sameDay(other) && !isTimeIndicated && other.isTimeIndicated) {
            return 1;
        } else {
            return datePlayed.compareTo(other.datePlayed);
        }
    }

    @Override
    public String toString() {
        return isTimeIndicated
                ? DATE_FORMAT.format(datePlayed)
                : DATETIME_FORMAT.format(datePlayed);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
