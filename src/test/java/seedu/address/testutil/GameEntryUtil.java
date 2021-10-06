package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditGameEntryDescriptor;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;

/**
 * A utility class for GameEntry.
 */
public class GameEntryUtil {

    /**
     * Returns an add command string for adding the {@code gameEntry}.
     */
    public static String getAddCommand(GameEntry gameEntry) {
        return AddCommand.COMMAND_WORD + " " + getGameEntryDetails(gameEntry);
    }

    /**
     * Returns the part of command string for the given {@code gameEntry}'s details.
     */
    public static String getGameEntryDetails(GameEntry gameEntry) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_GAMETYPE + gameEntry.getGameType() + " ");
        sb.append(PREFIX_STARTAMOUNT + gameEntry.getStartAmount().toString() + " ");
        sb.append(PREFIX_ENDAMOUNT + gameEntry.getEndAmount().toString() + " ");
        sb.append(PREFIX_DATE + gameEntry.getDate().toString() + " ");
        sb.append(PREFIX_DURATION + gameEntry.getDurationMinutes().toString() + " ");
        sb.append(PREFIX_LOCATION + gameEntry.getLocation() + " ");
        gameEntry.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGameEntryDescriptor}'s details.
     */
    public static String getEditGameEntryDescriptorDetails(EditGameEntryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getGameType().ifPresent(game ->   sb.append(PREFIX_GAMETYPE + descriptor.getGameType() + " "));
        descriptor.getStartAmount().ifPresent(start -> sb.append(PREFIX_STARTAMOUNT + descriptor.getStartAmount() + " "));
        descriptor.getEndAmount().ifPresent(end -> sb.append(PREFIX_ENDAMOUNT + descriptor.getEndAmount() + " "));
        descriptor.getDate().ifPresent(date-> sb.append(PREFIX_DATE + descriptor.getDate() + " "));
        descriptor.getDurationMinutes().ifPresent(duration ->  sb.append(PREFIX_DURATION + descriptor.getDurationMinutes() + " "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION + descriptor.getLocation() + " "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
