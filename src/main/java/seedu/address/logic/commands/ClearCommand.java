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
    public static final String MESSAGE_USAGE = "clear";
    public static final ClearCommand DUMMY = new ClearCommand();

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
        requireNonNull(model);
        model.setGameBook(new GameBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
