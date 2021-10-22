package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.Amount;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.Duration;
import seedu.address.model.gameentry.EndAmount;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.gameentry.StartAmount;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_START_AMOUNT = Amount.MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_INVALID_END_AMOUNT = Amount.MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_INVALID_DATE = DatePlayed.MESSAGE_CONSTRAINTS;
    public static final String MESSAGE_INVALID_DURATION = Duration.MESSAGE_CONSTRAINTS;


    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String gameType} by trimming the white spaces around it.
     *
     */
    public static GameType parseGameType(String gameType) throws ParseException {
        requireNonNull(gameType);
        if (!GameType.isValidGameType(gameType)) {
            throw new ParseException(GameType.MESSAGE_CONSTRAINTS);
        }
        return new GameType(gameType);
    }

    /**
     * Parses a {@code String startAmount} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startAmount} is invalid.
     */
    public static StartAmount parseStartAmount(String startAmount) throws ParseException {
        requireNonNull(startAmount);
        if (!StartAmount.isValidAmount(startAmount)) {
            throw new ParseException(StartAmount.MESSAGE_CONSTRAINTS);
        }
        return new StartAmount(startAmount);
    }

    /**
     * Parses a {@code String endAmount} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endAmount} is invalid.
     */
    public static EndAmount parseEndAmount(String endAmount) throws ParseException {
        requireNonNull(endAmount);
        if (!EndAmount.isValidAmount(endAmount)) {
            throw new ParseException(EndAmount.MESSAGE_CONSTRAINTS);
        }
        return new EndAmount(endAmount);
    }

    /**
     * Parses a {@code String datePlayed} into a {@code DatePlayed}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code datePlayed} is invalid.
     */
    public static DatePlayed parseDate(String datePlayed) throws ParseException {
        requireNonNull(datePlayed);
        if (!DatePlayed.isValidDatePlayed(datePlayed)) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
        return new DatePlayed(datePlayed);
    }

    /**
     * Parses a {@code String duration} into an Integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code duration} is invalid.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        if (!Duration.isValidDuration(duration)) {
            throw new ParseException(Duration.MESSAGE_CONSTRAINTS);
        }
        return new Duration(duration);
    }

    /**
     * Throws IllegalValueException if fieldValue is negative.
     * @param field Name of the field to be used for generating the IllegalValueException message
     * @param fieldValue The Integer to check for
     * @throws IllegalValueException if fieldValue is negative
     */
    private static void requireIntegerNonNegative(String field, Integer fieldValue) throws IllegalValueException {
        if (fieldValue < 0) {
            throw new IllegalValueException(field + " must be a non-negative integer");
        }
    }

    /**
     * Parses a {@code String location} and trim the whitespaces around it.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        if (!Location.isValidLocation(location)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(location);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

}
