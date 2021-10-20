package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.GameBook;
import seedu.address.model.Model;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Game book has been cleared!";
    public static final String MESSAGE_REQUEST_CONFIRMATION = "Clear data?";
    private final boolean isConfirmation;

    /**
     * Creates an ClearCommand to clear data based on {@code confirmation}.
     */
    public ClearCommand(String confirmation) {
        if (confirmation == null) {
            isConfirmation = false;
        }
        else if (confirmation.equals("yes")) {
            isConfirmation = true;
        }
        else {
            isConfirmation = false;
        }
    }

    /**
     * Creates an ClearCommand to clear data.
     */
    public ClearCommand() {
        isConfirmation = false;
    }

    @Override
    public CommandResult execute(Model model) {
        if (isConfirmation) {
            requireNonNull(model);
            model.setGameBook(new GameBook());
            return new CommandResult(MESSAGE_SUCCESS, false, false, false);
        } else {
            return new CommandResult(MESSAGE_REQUEST_CONFIRMATION, false, false, true);
        }
    }
}
