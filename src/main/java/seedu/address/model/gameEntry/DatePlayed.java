package seedu.address.model.gameEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePlayed extends Date {

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd hh:mm");

    public DatePlayed() {
        super();
    }

    public DatePlayed(Date date) {
        super(date.getTime());
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
            return super.getYear() == (tmp.getYear())
                    && super.getMonth() == tmp.getMonth()
                    && super.getDay() == tmp.getDay()
                    && super.getHours() == tmp.getHours()
                    && super.getMinutes() == tmp.getMinutes();
        }
        return false;
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(this);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
