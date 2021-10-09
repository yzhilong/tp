package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import static seedu.address.testutil.Assert.assertThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GameBook;
import seedu.address.model.Model;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.UniqueGameEntryList;
import seedu.address.model.gameentry.GameEntry;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    private static final String VALID_GAMETYPE = "Poker";
    private static final Double VALID_STARTAMOUNT = 0.0;
    private static final Double VALID_ENDAMOUNT = 100.0;
    private static DatePlayed VALID_DATE;

    static {
        try {
            VALID_DATE = new DatePlayed(new SimpleDateFormat("dd/MM/yy").parse("01/01/21")) ;
        } catch (ParseException e) {
            VALID_DATE = null;
        }
    }

    private static final Integer VALID_DURATION = 10;
    private static final String VALID_LOCATION = "Sentosa";
    private static final String VALID_TAG_1 = "lucky";
    private static final String VALID_TAG_2 = "drunk";

    private static final String STARTAMOUNT_INVALID_STARTAMOUNT = " " + PREFIX_STARTAMOUNT  + "abc";
    private static final String ENDAMOUNT_INVALID_ENDAMOUNT = " " + PREFIX_ENDAMOUNT  + "abc";
    private static final String DATE_INVALID_DATE = " " + PREFIX_DATE + "2021/01/01";
    private static final String DURATION_INVALID_DURATION = " " + PREFIX_DURATION + "abc";


    private static final String GAMETYPE_VALID_GAMETYPE_1 = " "+ PREFIX_GAMETYPE + "Poker";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_1 = " " + PREFIX_STARTAMOUNT + "0.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_1 = " " + PREFIX_ENDAMOUNT + "100.0";
    private static final String DATE_VALID_DATE_1 = " " + PREFIX_DATE  + "01/01/21";
    private static final String DURATION_VALID_DURATION_1 = " " + PREFIX_DURATION  + "10";
    private static final String LOCATION_VALID_LOCATION_1 = " " + PREFIX_LOCATION  + "Sentosa";
    private static final String TAG_VALID_TAG_1 = " " + PREFIX_TAG + "lucky";


    private static final String GAMETYPE_VALID_GAMETYPE_2 = " "+ PREFIX_GAMETYPE+ " " + "Black Jack";
    private static final String STARTAMOUNT_VALID_STARTAMOUNT_2 = " " + PREFIX_STARTAMOUNT + " " + "10.0";
    private static final String ENDAMOUNT_VALID_ENDAMOUNT_2 = " " + PREFIX_ENDAMOUNT + " " + "200.0";
    private static final String DATE_VALID_DATE_2 = " " + PREFIX_DATE + " " + "10/10/21";
    private static final String DURATION_VALID_DURATION_2 = " " + PREFIX_DURATION + " " + "20";
    private static final String LOCATION_VALID_LOCATION_2 = " " + PREFIX_LOCATION + " " + "Marina Bay";
    private static final String TAG_VALID_TAG_2 = " " + PREFIX_TAG + " " + "drunk";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditGameEntryDescriptor GAME_ONE;
    public static final EditCommand.EditGameEntryDescriptor GAME_TWO;

    static {
        GAME_ONE = new EditCommand.EditGameEntryDescriptorBuilder().withGame(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
