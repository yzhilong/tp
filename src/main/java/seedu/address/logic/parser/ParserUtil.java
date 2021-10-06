package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
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
    public static final String MESSAGE_INVALID_DURATION = "DURATION must be an integer.";


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
        if (datePlayed.equals("")) {
            return new DatePlayed();
        }
        String trimmedDatePlayed = datePlayed.trim();
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yy HH:mm").parse(trimmedDatePlayed);
            return new DatePlayed(date);
        } catch (java.text.ParseException e) {
            date = null;
        }

        try {
            date = new SimpleDateFormat("dd/MM/yy").parse(trimmedDatePlayed);
            return new DatePlayed(date);
        } catch (java.text.ParseException e) {
            date = null;
        }

        if (date == null) {
            throw new ParseException(MESSAGE_INVALID_DATE);
        }
        return new DatePlayed();
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
        } catch (NumberFormatException e) {
            //should we use initial cash or start amount?
            throw new ParseException(MESSAGE_INVALID_DURATION);
        }
        return durationMinutes;
    }

    /**
     * Parses a {@code String location} and trim the whitespaces around it.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static String parseLocation(String location) {
        requireNonNull(location);
        if (location.equals("")) {
            return null;
        }
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
