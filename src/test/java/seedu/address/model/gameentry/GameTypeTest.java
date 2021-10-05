package seedu.address.model.gameentry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GameTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GameType(null));
    }


    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidGameType = "";
        assertThrows(IllegalArgumentException.class, () -> new GameType(invalidGameType));
    }

    @Test
    public void constructor_capitalizationTest() {
        GameType lowerCaseGameType = new GameType("some game name");
        GameType upperCaseGameType = new GameType("Some Game Name");
        GameType mixedCaseGameType = new GameType("SoMe gAME nAmE");
        assertEquals(lowerCaseGameType, upperCaseGameType);
        assertEquals(lowerCaseGameType, mixedCaseGameType);
        assertEquals(mixedCaseGameType, upperCaseGameType);
    }

}
