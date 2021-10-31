package seedu.gamebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_PROFIT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.gamebook.model.Model;
import seedu.gamebook.model.gameentry.GameEntry;


/**
 * Adds a game to the game book.
 */
public class AddCommand extends Command {

    public static final AddCommand DUMMY = new AddCommand();

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_WITH_START_AND_END_AMOUNT_EXAMPLE = COMMAND_WORD + " "
        + PREFIX_GAMETYPE + "blackjack "
        + PREFIX_STARTAMOUNT + "200 "
        + PREFIX_ENDAMOUNT + "250 "
        + PREFIX_DATE + "03/10/21 "
        + PREFIX_DURATION + "50 "
        + PREFIX_LOCATION + "Sentosa"
        + PREFIX_TAG + "friends";

    public static final String COMMAND_WITH_PROFIT_EXAMPLE = COMMAND_WORD + " "
        + PREFIX_GAMETYPE + "blackjack "
        + PREFIX_PROFIT + "10.0";

    public static final String COMMAND_FORMAT = String.format(
            "add %sGAME_NAME [%sINITIAL_CASH] [%sFINAL_CASH] [%sPROFIT] [%sDATE] [%sDURATION] [%sLOCATION] [%sTAGS]",
            PREFIX_GAMETYPE, PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_PROFIT, PREFIX_DATE, PREFIX_DURATION,
            PREFIX_LOCATION, PREFIX_TAG
    );

    public static final String COMMAND_SPECIFICATION = String.format("Either \"%s\" and \"%s\" or \"%s\" flags "
        + "must be present.", PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_PROFIT);

    public static final String COMMAND_SUMMARY = "Adds a game to the game book. \n\n"
        + "Format:\n "
        + COMMAND_FORMAT + "\n\n"
        + COMMAND_SPECIFICATION + "\n\n"
        + "Examples:\n"
        + COMMAND_WITH_START_AND_END_AMOUNT_EXAMPLE + "\n"
        + COMMAND_WITH_PROFIT_EXAMPLE;

    public static final String MESSAGE_USAGE = COMMAND_FORMAT + "\n" + COMMAND_SPECIFICATION;
    public static final String MESSAGE_SUCCESS = "New game added: \n%1$s\n%2$s";
    public static final String MESSAGE_DUPLICATE_GAME_ENTRY = "Alert: A game entry with the same "
        + "game type, date and time already exists.";

    public final GameEntry toAdd;

    /**
     * Creates an AddCommand dummy object.
     */
    private AddCommand() {
        toAdd = null;
    }

    /**
     * Creates an AddCommand to add the specified {@code GameEntry}
     */
    public AddCommand(GameEntry gameEntry) {
        requireNonNull(gameEntry);
        toAdd = gameEntry;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // if (model.hasPerson(toAdd)) {
        //     throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        // }
        String sameEntryAlert = model.hasGameEntry(toAdd)
                ? MESSAGE_DUPLICATE_GAME_ENTRY
                : "";
        model.addGameEntry(toAdd);
        // should work if toAdd has toString()
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, sameEntryAlert));
    }

    @Override
    public String getCommandWord() {
        return AddCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandSummary() {
        return AddCommand.COMMAND_SUMMARY;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
