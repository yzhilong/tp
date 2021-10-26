package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.gameentry.GameEntry;


/**
 * Deletes a game entry identified using it's displayed index from the game book.
 */
public class DeleteCommand extends Command {

    public static final DeleteCommand DUMMY = new DeleteCommand();
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "Delete the game entry identified by the given index number. (Index "
        + "number is obtained from the displayed game list.)\n\n"
        + "Format: delete INDEX\n\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_GAMEENTRY_SUCCESS = "Deleted Game Entry: %1$s";

    private final Index targetIndex;

    private DeleteCommand() {
        targetIndex = null;
    }

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<GameEntry> lastShownList = model.getFilteredGameEntryList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GAMEENTRY_DISPLAYED_INDEX);
        }

        GameEntry gameEntryToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteGameEntry(gameEntryToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_GAMEENTRY_SUCCESS, gameEntryToDelete));
    }

    @Override
    public String getCommandWord() {
        return DeleteCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandUsage() {
        return DeleteCommand.MESSAGE_USAGE;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
