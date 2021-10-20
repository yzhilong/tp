package seedu.address.logic.commands;



import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND;

import seedu.address.model.Model;
/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
        + "Example: " + COMMAND_WORD;

    //Will edit this later
    public static final String SHOWING_HELP_MESSAGE = "Command and Format\n"
        + "Add:\n add /g GAMENAME /s INITIALCASH /e FINALCASH [/date DATE] [/dur DURATION] [/loc"
        + " LOCATION] [/tag TAGS]\n"
        + "Edit:\n edit INDEX [/g GAMENAME] [/s INITIALCASH] [/e FINALCASH] [/date DATE] [/dur DURATION] [/loc"
        + " LOCATION] [/tag TAGS]\n"
        + "Delete:\n delete Index\n"
        + "Find:\n find KEYWORD\n"
        + "Exit:\n exit";
    public static final String ADD_HELP_MESSAGE = "Format for add command: \n"
        + "add /g GAMENAME [/s INITIALCASH] /e FINALCASH [/date DATE] "
        + "[/dur DURATION] [/loc LOCATION] [/tag TAGS]";
    public static final String EDIT_HELP_MESSAGE = "Format for edit command: \n"
        + "edit INDEX [/g GAMENAME] [/s INITIALCASH] [/e FINALCASH] "
        + "[/date DATE] [/dur DUgRATION] [/loc LOCATION] [/tag TAGS]";
    public static final String DELETE_HELP_MESSAGE = "Format for delete command: \ndelete INDEX";
    public static final String FIND_HELP_MESSAGE = "Format for find command: \nfind";
    public static final String EXIT_HELP_MESSAGE = "Format for exit command: \nexit";

    private String helpMessage;
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
            helpMessage = SHOWING_HELP_MESSAGE;
        }
    }

    @Override
    public CommandResult execute(Model model) {
        if (hasKeyword) {
            return new CommandResult(helpMessage);
        } else {
            return new CommandResult(helpMessage, true, false);
        }
    }
}
