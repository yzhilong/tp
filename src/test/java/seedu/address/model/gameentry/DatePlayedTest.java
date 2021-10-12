package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class DatePlayedTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DatePlayed(null));
    }

    @Test
    public void equals_condition_equalIfSameMinute() {
        // Exact same time
        DatePlayed date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9));
        DatePlayed date1 = new DatePlayed(new Date(2021, 10, 5, 11, 9));
        assertEquals(date0, date1);

        // Same minute different second
        date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9, 5));
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 9));
        assertEquals(date0, date1);

        // Same minute different second and millisecond
        Date tmpDate = new Date(2021, 10, 5, 11, 9, 5);
        date0 = new DatePlayed(tmpDate.getTime() + 100);
        date1 = new DatePlayed(tmpDate);
        assertEquals(date0, date1);

        // Different minute
        date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9, 59));
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10));
        assertNotEquals(date0, date1);
    }

    @Test
    public void equal_condition_equalIfTimeNotIndicated() {
        DatePlayed date0;
        DatePlayed date1;

        // Same date, different minute but ignore minute field of both objects
        date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9, 59), false);
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10), false);
        assertEquals(date0, date1);

        // Same date, different minute but ignore minute field of one object
        date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9, 59), true);
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10), false);
        assertNotEquals(date0, date1);

        // Same date, different minute but ignore minute field of neither object
        date0 = new DatePlayed(new Date(2021, 10, 5, 11, 9, 42), true);
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10), true);
        assertNotEquals(date0, date1);

        // Different date, different minute but ignore minute field of both objects
        date0 = new DatePlayed(new Date(2021, 10, 6, 11, 9, 59), false);
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10), false);
        assertNotEquals(date0, date1);

        // Different date, different minute and ignore minute field of one object
        date0 = new DatePlayed(new Date(2021, 10, 6, 11, 9, 59), true);
        date1 = new DatePlayed(new Date(2021, 11, 6, 11, 10), false);
        assertNotEquals(date0, date1);

        // Different date, different minute and ignore minute field of neither object
        date0 = new DatePlayed(new Date(2021, 10, 6, 11, 9, 59), true);
        date1 = new DatePlayed(new Date(2021, 10, 5, 11, 10), true);
        assertNotEquals(date0, date1);
    }

    @Test
    public void toStringTest() {
        DatePlayed date0;

        // toString does not print time if isMinuteIndicated is false
        date0 = new DatePlayed(new Date(121, 9, 5, 11, 9, 59), false);
        assertEquals(date0.toString(), "2021-10-05");

        // toString prints time if isMinuteIndicated is true
        date0 = new DatePlayed(new Date(121, 9, 5, 11, 9, 59), true);
        assertEquals(date0.toString(), "2021-10-05 11:09");

        // toString prints time if isMinuteIndicated is not specified
        date0 = new DatePlayed(new Date(121, 9, 5, 11, 9, 59));
        assertEquals(date0.toString(), "2021-10-05 11:09");

        // toString prints time if isMinuteIndicated is not specified
        date0 = new DatePlayed(new Date(121, 9, 5, 13, 9, 59));
        assertEquals(date0.toString(), "2021-10-05 13:09");

        // toString prints time if isMinuteIndicated is not specified
        date0 = new DatePlayed(new Date(121, 9, 5, 1, 9, 59));
        assertEquals(date0.toString(), "2021-10-05 01:09");
    }

}
