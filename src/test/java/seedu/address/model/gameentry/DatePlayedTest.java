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

}
