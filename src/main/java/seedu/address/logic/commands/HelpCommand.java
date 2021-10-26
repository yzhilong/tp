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

    public static final HelpCommand DUMMY = new HelpCommand();
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = "Show program usage instructions.\n\n"
        + "Format for general help:\n" + COMMAND_WORD + "\n"
        + "Format for specific command's help:\n"
        + COMMAND_WORD + " add\n"
        + COMMAND_WORD + " edit\n"
        + COMMAND_WORD + " delete\n"
        + COMMAND_WORD + " find\n";

    public static final String USERGUIDE_URL = "https://ay2122s1-cs2103t-w13-3.github.io/tp/UserGuide.html";

    //Will edit this later
    public static final String SHOWING_HELP_MESSAGE = "For more information "
        + "visit " + USERGUIDE_URL + "\n";
    public static final String ADD_HELP_MESSAGE = "Format for add command: "
        + "add /g GAMENAME /s INITIALCASH /e FINALCASH [/date DATE] "
        + "[/dur DURATION] [/loc LOCATION] [/tag TAGS]";
    public static final String EDIT_HELP_MESSAGE = "Format for edit command: "
        + "edit INDEX [/g GAMENAME] [/p PROFIT] "
        + "[/date DATE] [/dur DUgRATION] [/loc LOCATION] [/tag TAGS]";
    public static final String DELETE_HELP_MESSAGE = "Format for delete command: delete INDEX";
    public static final String FIND_HELP_MESSAGE = "Format for find command: find";
    public static final String EXIT_HELP_MESSAGE = "Format for exit command: exit";
    public static final String IS_NOT_A_COMMAND = " is not a command.\n";

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
            helpMessage = "\"" + keyword + "\"" + IS_NOT_A_COMMAND + SHOWING_HELP_MESSAGE;
            hasKeyword = false;
        }
    }

    @Override
    public String getCommandWord() {
        return HelpCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandUsage() {
        return HelpCommand.MESSAGE_USAGE;
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
