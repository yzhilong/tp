package seedu.address.logic.parser;


import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) {
        assert args != null : "args must be a string";
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals(PREFIX_ADD.getPrefix())) {
            return new HelpCommand(PREFIX_ADD.getPrefix());
        } else if (trimmedArgs.equals(PREFIX_EDIT.getPrefix())) {
            return new HelpCommand(PREFIX_EDIT.getPrefix());
        } else if (trimmedArgs.equals(PREFIX_FIND.getPrefix())) {
            return new HelpCommand(PREFIX_FIND.getPrefix());
        } else if (trimmedArgs.equals(PREFIX_DELETE.getPrefix())) {
            return new HelpCommand(PREFIX_DELETE.getPrefix());
        } else if (trimmedArgs.equals(PREFIX_EXIT.getPrefix())) {
            return new HelpCommand(PREFIX_EXIT.getPrefix());
        }
        return new HelpCommand();
    }

}
