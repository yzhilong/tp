package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
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
import seedu.address.logic.parser.exceptions.TokenizerException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.Duration;
import seedu.address.model.gameentry.EndAmount;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.gameentry.StartAmount;
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
        assert args != null : "args must be a string";
        ArgumentMultimap argMultimap;
        GameEntry gameEntry;

        try {
            argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_GAMETYPE, PREFIX_STARTAMOUNT, PREFIX_ENDAMOUNT, PREFIX_DATE,
                            PREFIX_DURATION, PREFIX_LOCATION, PREFIX_TAG);
        } catch (TokenizerException te) {
            throw new ParseException(ArgumentTokenizer.MESSAGE_DUPLICATE_FLAGS);
        }
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String profit = argMultimap.getValue(PREFIX_PROFIT).orElse("");

        try {
            GameType gameType = argMultimap.getValue(PREFIX_GAMETYPE)
                    .map(ParserUtil::parseGameType)
                    .orElse(GameType.empty());

            StartAmount startAmount = argMultimap.getValue(PREFIX_STARTAMOUNT)
                    .map(ParserUtil::parseStartAmount)
                    .orElse(StartAmount.empty());

            EndAmount endAmount = argMultimap.getValue(PREFIX_ENDAMOUNT)
                    .map(ParserUtil::parseEndAmount)
                    .orElse(EndAmount.empty());

            DatePlayed date = argMultimap.getValue(PREFIX_DATE)
                    .map(ParserUtil::parseDate)
                    .orElse(DatePlayed.empty());

            Duration durationMinutes = argMultimap.getValue(PREFIX_DURATION)
                    .map(ParserUtil::parseDuration)
                    .orElse(Duration.empty());

            Location location = argMultimap.getValue(PREFIX_LOCATION)
                    .map(ParserUtil::parseLocation)
                    .orElse(Location.empty());

            Set<Tag> tagList = argMultimap.getValue(PREFIX_TAG)
                    .map(ParserUtil::parseTags)
                    .orElse(Tag.empty());

            gameEntry = new GameEntry(gameType, startAmount, endAmount, date, durationMinutes, location, tagList);

        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }

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
