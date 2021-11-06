package seedu.gamebook.logic.commands;


import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_EXIT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_FIND;

import seedu.gamebook.model.Model;
/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final HelpCommand DUMMY = new HelpCommand();
    public static final String COMMAND_WORD = "help";

    public static final String COMMAND_SUMMARY = "Shows program usage instructions.\n\n"
        + "Format for general help:\n" + COMMAND_WORD + "\n\n"
        + "Format for specific command usage help:\n"
        + COMMAND_WORD + " [COMMAND]\n\n" + "Example:\nhelp add\n";

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w13-3.github.io/tp/UserGuide.html";
    public static final String SHOWING_HELP_MESSAGE = "For more information "
        + "visit " + USERGUIDE_URL + "\n" + "To return to the game list, enter \"list\" or a new command."
            + "\nFor specific help, type help [command]. E.g.: help add";

    public static final String ADD_HELP_MESSAGE = "Format for add command:\n" + AddCommand.MESSAGE_USAGE;
    public static final String EDIT_HELP_MESSAGE = "Format for edit command:\n" + EditCommand.MESSAGE_USAGE;
    public static final String DELETE_HELP_MESSAGE = "Format for delete command:\n" + DeleteCommand.MESSAGE_USAGE;
    public static final String FIND_HELP_MESSAGE = "Format for find command: \n" + FindCommand.MESSAGE_USAGE;
    public static final String EXIT_HELP_MESSAGE = "Format for exit command: \n" + EditCommand.MESSAGE_USAGE;
    public static final String INVALID_COMMAND_MESSAGE = "Invalid command. Try the command \"help\".\n";

    private final String helpMessage;
    private boolean hasKeyword;

    /**
     * Creates a HelpCommand object with a general message for the user.
     */
    public HelpCommand() {
        helpMessage = SHOWING_HELP_MESSAGE;
        hasKeyword = false;
    }

    /**
     * Creates a HelpCommand object with specific help message based on keyword.
     */
    public HelpCommand(String keyword) {
        assert keyword != null : "Keyword must be a string";
        hasKeyword = true;
        if (keyword.equals(PREFIX_ADD.getPrefix())) {
            helpMessage = ADD_HELP_MESSAGE;
        } else if (keyword.equals(PREFIX_EDIT.getPrefix())) {
            helpMessage = EDIT_HELP_MESSAGE;
        } else if (keyword.equals(PREFIX_DELETE.getPrefix())) {
            helpMessage = DELETE_HELP_MESSAGE;
        } else if (keyword.equals(PREFIX_FIND.getPrefix())) {
            helpMessage = FIND_HELP_MESSAGE;
        } else if (keyword.equals(PREFIX_EXIT.getPrefix())) {
            helpMessage = EXIT_HELP_MESSAGE;
        } else {
            helpMessage = "\"" + keyword + "\"" + INVALID_COMMAND_MESSAGE;
            hasKeyword = false;
        }
    }

    @Override
    public String getCommandWord() {
        return HelpCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandSummary() {
        return HelpCommand.COMMAND_SUMMARY;
    }

    @Override
    public CommandResult execute(Model model) {
        if (hasKeyword) {
            return new CommandResult(helpMessage);
        } else {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false, false);
        }
    }
}
