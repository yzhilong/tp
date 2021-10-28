package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.model.Model;
import seedu.address.model.gameentry.GameEntry;


/**
 * Adds a game to the game book.
 */
public class AddCommand extends Command {

    public static final AddCommand DUMMY = new AddCommand();

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a game to the game book. "
            + String.format("Either \"%s\" and \"%s\" or \"%s\" flags must be present.\n",
                PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_PROFIT)
            + "Parameters: "
            + PREFIX_GAMETYPE + "GAMENAME "
            + PREFIX_STARTAMOUNT + "INITIALCASH "
            + PREFIX_ENDAMOUNT + "FINALCASH "
            + PREFIX_PROFIT + "PROFIT_AMOUNT "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_DURATION + "DURATION] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_TAG + "TAGS ... ]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GAMETYPE + "blackjack "
            + PREFIX_STARTAMOUNT + "200 "
            + PREFIX_ENDAMOUNT + "250 "
            + PREFIX_DATE + "03/10/21 "
            + PREFIX_DURATION + "50 "
            + PREFIX_LOCATION + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New game added: %1$s\n%2$s";
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
    public String getCommandUsage() {
        return AddCommand.MESSAGE_USAGE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddCommand // instanceof handles nulls
            && toAdd.equals(((AddCommand) other).toAdd));
    }
}
