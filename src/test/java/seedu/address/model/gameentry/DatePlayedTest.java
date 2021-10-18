package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DatePlayedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DatePlayed(null));
    }

    @Test
    public void constructor_invalidDatetime_throwsIllegalArgumentException() {
        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("123/12/12 12:12"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("32/12/12 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/123/12 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/0/12 12:12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/13/12 12:12"));

        // Invalid year
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/123 12:12"));

        // Invalid year
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/0 12:12"));

        // Invalid hour
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/12 123:12"));

        // Invalid hour
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/12 25:12"));

        // Invalid minute
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/12 12:123"));

        // Invalid minute
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/12 25:60"));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("123/12/12"));

        // Invalid date
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("32/12/12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/123/12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/0/12"));

        // Invalid month
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/13/12"));

        // Invalid year
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/123"));

        // Invalid year
        assertThrows(IllegalArgumentException.class, () -> new DatePlayed("12/12/0"));
    }

    @Test
    public void equals_condition_equalIfSameMinute() {
        // Exact same time
        DatePlayed date0 = new DatePlayed("05/10/21 11:09");
        DatePlayed date1 = new DatePlayed("05/10/21 11:09");
        assertEquals(date0, date1);

        // Different minute
        date0 = new DatePlayed("05/10/21 11:09");
        date1 = new DatePlayed("05/10/21 11:06");
        assertNotEquals(date0, date1);
    }

    @Test
    public void equals_condition_equalIfSameDate() {
        // Same date
        DatePlayed date0 = new DatePlayed("05/10/21");
        DatePlayed date1 = new DatePlayed("05/10/21");
        assertEquals(date0, date1);

        // Different date
        date0 = new DatePlayed("02/10/21");
        date1 = new DatePlayed("05/10/21");
        assertNotEquals(date0, date1);

        // Different month
        date0 = new DatePlayed("05/11/21");
        date1 = new DatePlayed("05/10/21");
        assertNotEquals(date0, date1);

        // Different year
        date0 = new DatePlayed("05/10/20");
        date1 = new DatePlayed("05/10/21");
        assertNotEquals(date0, date1);
    }

    @Test
    public void equal_condition_notEqualIfDifferentFormat() {
        DatePlayed date0;
        DatePlayed date1;

        // Same date, but one of them has time indicated
        date0 = new DatePlayed("02/10/21 12:34");
        date1 = new DatePlayed("02/10/21");
        assertNotEquals(date0, date1);
    }

    @Test
    public void toStringTest() {
        DatePlayed date0;

        // toString does not print time if time is not specified
        date0 = new DatePlayed("05/10/21");
        assertEquals(date0.toString(), "2021-10-05");

        // toString prints time if time is specified
        date0 = new DatePlayed("05/10/21 11:09");
        assertEquals(date0.toString(), "2021-10-05 11:09");
    }

}
