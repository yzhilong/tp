package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GameBook;
import seedu.address.model.Model;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.testutil.EditGameEntryDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_GAMETYPE_1 = "Poker";
    // TODO - after 1.2 change to String and use constructors.
    public static final Double VALID_STARTAMOUNT_1 = 0.0;
    public static final Double VALID_ENDAMOUNT_1 = 100.0;
    public static DatePlayed VALID_DATE_1;

    static {
        try {
            VALID_DATE_1 = new DatePlayed(new SimpleDateFormat("dd/MM/yy").parse("01/01/21")) ;
        } catch (ParseException e) {
            VALID_DATE_1 = null;
        }
    }

    public static final Integer VALID_DURATION_1 = 10;
    public static final Location VALID_LOCATION_1 = new Location("Sentosa");
    public static final String VALID_TAG_1 = "lucky";
    public static final String VALID_TAG_2 = "drunk";

    public static final String VALID_GAMETYPE_2 = "Black Jack";
    public static final Double VALID_STARTAMOUNT_2 = 10.0;
    public static final Double VALID_ENDAMOUNT_2 = 200.0;
    public static DatePlayed VALID_DATE_2;

    static {
        try {
            VALID_DATE_2 = new DatePlayed(new SimpleDateFormat("dd/MM/yy").parse("10/10/21")) ;
        } catch (ParseException e) {
            VALID_DATE_2 = null;
        }
    }

    public static final Integer VALID_DURATION_2 = 20;
    public static final Location VALID_LOCATION_2 = new Location("Marina Bay");


    public static final EditCommand.EditGameEntryDescriptor GAME_ONE;
    public static final EditCommand.EditGameEntryDescriptor GAME_TWO;

    static {
        GAME_ONE = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE_1)
                .withStartAmount(VALID_STARTAMOUNT_1).withEndAmount(VALID_ENDAMOUNT_1).withDatePlayed(VALID_DATE_1)
                .withDuration(VALID_DURATION_1).withLocation(VALID_LOCATION_1).withTags(VALID_TAG_1).build();
        GAME_TWO = new EditGameEntryDescriptorBuilder().withGameType(VALID_GAMETYPE_2)
                .withStartAmount(VALID_STARTAMOUNT_2).withEndAmount(VALID_ENDAMOUNT_2).withDatePlayed(VALID_DATE_2)
                .withDuration(VALID_DURATION_2).withLocation(VALID_LOCATION_2).withTags(VALID_TAG_2).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the game book, filtered game entry list and selected game entry in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        GameBook expectedGameBook = new GameBook(actualModel.getGameBook());
        List<GameEntry> expectedFilteredList = new ArrayList<>(actualModel.getFilteredGameEntryList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedGameBook, actualModel.getGameBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredGameEntryList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the game entry at the given {@code targetIndex} in the
     * {@code model}'s game book.
     */
    public static void showGameEntryAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredGameEntryList().size());

        GameEntry gameEntry = model.getFilteredGameEntryList().get(targetIndex.getZeroBased());
        model.updateFilteredGameEntryList(game -> game.isSameGameEntry(gameEntry));
        assertEquals(1, model.getFilteredGameEntryList().size());
    }

}
