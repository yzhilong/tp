package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gameentry.GameEntry;


/**
 * Adds a game to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a game to the address book. "
            + "Parameters: "
            + PREFIX_GAMETYPE + "GAMETYPE "
            + PREFIX_STARTAMOUNT + "START AMOUNT "
            + PREFIX_ENDAMOUNT + "END AMOUNT "
            + PREFIX_DATE + "DATE "
            + PREFIX_DURATION + "DURATION "
            + PREFIX_LOCATION + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GAMETYPE + "blackjack"
            + PREFIX_STARTAMOUNT + "200 "
            + PREFIX_ENDAMOUNT + "250 "
            + PREFIX_DATE + "03/10/21 "
            + PREFIX_DURATION + "50 "
            + PREFIX_LOCATION + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends ";

    public static final String MESSAGE_SUCCESS = "New game added: %1$s";
    // public static final String MESSAGE_DUPLICATE_PERSON = "This game already exists in the address book";

    private final GameEntry toAdd;

    /**
     * Creates an AddCommand to add the specified {@code GameEntry}
     */
    public AddCommand(GameEntry gameEntry) {
        requireNonNull(gameEntry);
        toAdd = gameEntry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // if (model.hasPerson(toAdd)) {
        //     throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        // }

        model.addGameEntry(toAdd);
        // should work if toAdd has toString()
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
