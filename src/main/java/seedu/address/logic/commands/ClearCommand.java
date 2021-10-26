package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.GameBook;
import seedu.address.model.Model;

/**
 * Clears the game entries in GameBook.
 */
public class ClearCommand extends Command {


    public static final ClearCommand DUMMY = new ClearCommand();
    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_CONFIRMATION = "CONFIRM-CLEAR-ALL-ENTRIES";
    public static final String MESSAGE_SUCCESS = "Game book has been cleared!";
    public static final String MESSAGE_USAGE = "Clear all game entries.\n\nFormat: clear";

    public static final String MESSAGE_REQUEST_CONFIRMATION = "Clear data?";
    private final boolean isConfirmation;

    /**
     * Creates a ClearCommand to clear data based on {@code confirmation}.
     */
    public ClearCommand(String confirmation) {
        if (confirmation == null) {
            isConfirmation = false;
        } else if (confirmation.equals(COMMAND_CONFIRMATION)) {
            isConfirmation = true;
        } else {
            isConfirmation = false;
        }
    }

    /**
     * Creates a ClearCommand that asks for user's confirmation before clearing data.
     */
    public ClearCommand() {
        isConfirmation = false;
    }

    @Override
    public String getCommandWord() {
        return ClearCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandUsage() {
        return ClearCommand.MESSAGE_USAGE;
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
