package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidLocation = "";
        assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void constructor_capitalizationTest() {
        Location lowerCaseGameType = new Location("some location name");
        Location upperCaseLocation = new Location("Some Location Name");
        Location mixedCaseLocation = new Location("SoMe LoCatioN nAmE");
        assertEquals(lowerCaseGameType, upperCaseLocation);
        assertEquals(lowerCaseGameType, mixedCaseLocation);
        assertEquals(mixedCaseLocation, upperCaseLocation);
    }
}
