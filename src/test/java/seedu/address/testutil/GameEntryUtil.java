package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTAMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditGameEntryDescriptor;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.tag.Tag;

/**
 * A utility class for GameEntry.
 */
public class GameEntryUtil {

    private static final DateFormat DATE_FORMAT_WITH_MINUTES = new SimpleDateFormat("dd/MM/yy HH:mm");
    private static final DateFormat DATE_FORMAT_WITHOUT_MINUTES = new SimpleDateFormat("dd/MM/yy");
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
        boolean containsTime = gameEntry.getDate().getIsTimeIndicated();
        String date;
        if (containsTime) {
            try {
                date = DATE_FORMAT_WITH_MINUTES.format(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(gameEntry.getDate().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
                date = null;
            }
        } else {
            try {
                date = DATE_FORMAT_WITHOUT_MINUTES.format(new SimpleDateFormat("yyyy-MM-dd")
                        .parse(gameEntry.getDate().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
                date = null;
            }
        }
        sb.append(PREFIX_GAMETYPE + gameEntry.getGameType() + " ");
        sb.append(PREFIX_STARTAMOUNT + gameEntry.getStartAmount().toString() + " ");
        sb.append(PREFIX_ENDAMOUNT + gameEntry.getEndAmount().toString() + " ");
        sb.append(PREFIX_DATE + date + " ");
        sb.append(PREFIX_DURATION + gameEntry.getDurationMinutes().toString() + " ");
        sb.append(PREFIX_LOCATION + gameEntry.getLocation() + " ");
        if (gameEntry.hasTags()) {
            sb.append(PREFIX_TAG + " ");
            sb.append(gameEntry
                    .getTags()
                    .stream()
                    .map(x -> x.toString())
                    .reduce("", (x, y) -> x + ", " + y));
        }
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditGameEntryDescriptor}'s details.
     */
    public static String getEditGameEntryDescriptorDetails(EditGameEntryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDate().ifPresent(date -> {
            boolean containsTime = date.getIsTimeIndicated();
            String dateString = date.toString();
            if (containsTime) {
                try {
                    dateString = DATE_FORMAT_WITH_MINUTES.format(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse(dateString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    dateString = DATE_FORMAT_WITHOUT_MINUTES.format(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(dateString));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            sb.append(PREFIX_DATE + "" + dateString + " ");
        });


        descriptor.getGameType().ifPresent(game -> sb.append(PREFIX_GAMETYPE + ""
                + descriptor.getGameType().get() + " "));
        descriptor.getStartAmount().ifPresent(start -> sb.append(PREFIX_STARTAMOUNT + ""
                + descriptor.getStartAmount().get() + " "));
        descriptor.getEndAmount().ifPresent(end -> sb.append(PREFIX_ENDAMOUNT + ""
                + descriptor.getEndAmount().get() + " "));
        descriptor.getDuration().ifPresent(duration -> sb.append(PREFIX_DURATION + ""
                + descriptor.getDuration().get() + " "));
        descriptor.getLocation().ifPresent(location -> sb.append(PREFIX_LOCATION + ""
                + descriptor.getLocation().get() + " "));
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
