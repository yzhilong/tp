package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final ExitCommand DUMMY = new ExitCommand();
    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Game Book as requested ...";
    public static final String MESSAGE_USAGE = "Exit GameBook.\n\nFormat: exit";

    @Override
    public String getCommandWord() {
        return ExitCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandUsage() {
        return ExitCommand.MESSAGE_USAGE;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
    }

}
