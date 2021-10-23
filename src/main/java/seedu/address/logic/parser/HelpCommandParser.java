package seedu.address.logic.parser;

import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class HelpCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) {
        assert args != null : "args must be a string";
        String trimmedArgs = args.trim();
        if (trimmedArgs.equals("")) {
            return new HelpCommand();
        }
        return new HelpCommand(trimmedArgs);
    }

}
