package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Tag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be alphanumerical values separated by dashes"
            + " (e.g. \"some-tag-value\")";
    public static final String VALIDATION_REGEX = "([a-zA-Z0-9]{1,}(-[a-zA-Z0-9]{1,}){0,})";

    private static final Set<Tag> EMPTY = new HashSet<>();

    public final String tagName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = tagName.strip();
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        return test.strip().matches(VALIDATION_REGEX);
    }

    /**
     * Returns a set of tags from a comma-separated string.
     *
     * @param tagsString
     * @return Set of tags.
     */
    public static Set<Tag> parseTagList(String tagsString) {
        String[] tags = tagsString.split(",");
        Set<Tag> outputSet = new HashSet<>();
        for (String tag : tags) {
            if (tag.length() > 0) {
                outputSet.add(new Tag(tag));
            }
        }
        return outputSet;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Tag // instanceof handles nulls
                && tagName.equals(((Tag) other).tagName)); // state check
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName + ']';
    }

    public static Set<Tag> empty() {
        return EMPTY;
    }

    public static boolean isEmpty(Set<Tag> set) {
        return set == EMPTY;
    }

}
