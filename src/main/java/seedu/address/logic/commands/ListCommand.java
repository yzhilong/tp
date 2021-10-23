package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GAME_ENTRIES;

import seedu.address.model.Model;

/**
 * Lists all game entries in the game book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all games";

    public static final String MESSAGE_USAGE = "list";

    @Override
    public String getCommandWord() {
        return ListCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandUsage() {
        return ListCommand.MESSAGE_USAGE;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredGameEntryList(PREDICATE_SHOW_ALL_GAME_ENTRIES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
