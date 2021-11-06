package seedu.gamebook.logic.parser;

// import static seedu.gamebook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static seedu.gamebook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.gamebook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.gamebook.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_validArgs_returnsSpecificHelpCommand() {
        assertParseSuccess(parser, "add", new HelpCommand("add"));
    }

    @Test
    public void parse_trailingValues_returnsErroneousHelpCommand() {
        assertParseSuccess(parser, "add abcde", HelpCommand.ERROR);
    }

    @Test
    public void parse_invalidArgs_returnsErroneousHelpCommand() {
        assertParseSuccess(parser, "abcde", HelpCommand.ERROR);
    }

    @Test
    public void parse_noArgs_returnsGenericHelpCommand() {
        assertParseSuccess(parser, "", HelpCommand.DUMMY);
    }
}
