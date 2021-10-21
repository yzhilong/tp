package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_START_AMOUNT = "Initial cash must be a float number.";
    public static final String MESSAGE_INVALID_END_AMOUNT = "Final cash must be a float number.";
    public static final String MESSAGE_INVALID_DATE = "Date should be in DD/MM/YY HH:MM or DD/MM/YY format.";
    public static final String MESSAGE_INVALID_DURATION = "DURATION must be a non-negative integer";

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
    public static String parseGameType(String gameType) {
        requireNonNull(gameType);
        String trimmedGameType = gameType.trim();
        return trimmedGameType;
    }

    /**
     * Parses a {@code String startAmount} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startAmount} is invalid.
     */
    public static Double parseStartAmount(String startAmount) throws ParseException {
        requireNonNull(startAmount);
        String trimmedStartAmount = startAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedStartAmount);
        } catch (NumberFormatException e) {
            //should we use initial cash or start amount?
            throw new ParseException(MESSAGE_INVALID_START_AMOUNT);
        }
        return amount;
    }

    /**
     * Parses a {@code String endAmount} into a Double.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endAmount} is invalid.
     */
    public static Double parseEndAmount(String endAmount) throws ParseException {
        requireNonNull(endAmount);
        String trimmedEndAmount = endAmount.trim();
        Double amount;
        try {
            amount = Double.parseDouble(trimmedEndAmount);
        } catch (NumberFormatException e) {
            //should we use final cash or end amount?
            throw new ParseException(MESSAGE_INVALID_END_AMOUNT);
        }
        return amount;
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
    public static Integer parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        if (duration.equals("")) {
            return Integer.MIN_VALUE;
        }
        String trimmedDuration = duration.trim();
        Integer durationMinutes;
        try {
            durationMinutes = Integer.parseInt(trimmedDuration);
            requireIntegerNonNegative("DURATION", durationMinutes);
        } catch (NumberFormatException | IllegalValueException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }
        return durationMinutes;
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
    public static String parseLocation(String location) {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        return trimmedLocation;
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
     * Parses {@code String tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(String tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        if (tags.equals("")) {
            return tagSet;
        }
        for (String tagName : tags.split(",")) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tag : tags) {
            tagSet.add(parseTag(tag));
        }
        return tagSet;
    }

}
