package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GAMETYPE, PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_PROFIT,
                        PREFIX_DATE, PREFIX_DURATION, PREFIX_LOCATION, PREFIX_TAG);
        //Either profit or endAmount AND startAmount must be present
        if (!arePrefixesPresent(argMultimap, PREFIX_GAMETYPE)
            || (!arePrefixesPresent(argMultimap, PREFIX_ENDAMOUNT, PREFIX_STARTAMOUNT)
            && !arePrefixesPresent(argMultimap, PREFIX_PROFIT))
            || (arePrefixesPresent(argMultimap, PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_PROFIT))
            || (arePrefixesPresent(argMultimap, PREFIX_PROFIT, PREFIX_STARTAMOUNT))
            || (arePrefixesPresent(argMultimap, PREFIX_ENDAMOUNT, PREFIX_PROFIT))
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String profit = argMultimap.getValue(PREFIX_PROFIT).orElse("");
        String gameType = ParserUtil.parseGameType(argMultimap.getValue(PREFIX_GAMETYPE).get());
        Double startAmount = ParserUtil.parseStartAmount(argMultimap.getValue(PREFIX_STARTAMOUNT).orElse("0.0"));
        Double endAmount = ParserUtil.parseEndAmount(argMultimap.getValue(PREFIX_ENDAMOUNT).orElse(""),
            profit);

        DatePlayed date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).orElse(""));
        Integer durationMinutes = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).orElse(""));
        String location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).orElse(""));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        GameEntry gameEntry = new GameEntry(gameType, startAmount, endAmount, date, durationMinutes, location, tagList);

        return new AddCommand(gameEntry);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
