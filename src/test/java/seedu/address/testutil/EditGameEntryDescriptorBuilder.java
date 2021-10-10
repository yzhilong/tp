package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static seedu.address.logic.parser.ParserUtil.*;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_DURATION;

public class EditGameEntryDescriptorBuilder {
    public static final String DEFAULT_GAMETYPE = "Poker";
    public static final Double DEFAULT_STARTAMOUNT = 0.0;
    public static final Double DEFAULT_ENDAMOUNT = 100.0;
    public static final Date DEFAULT_DATE = new Date();
    public static final Integer DEFAULT_DURATION = 10;
    public static final String DEFAULT_LOCATION = "Marina Bay Sands";

    private EditCommand.EditGameEntryDescriptor descriptor;


    public EditGameEntryDescriptorBuilder() {
        descriptor = new EditCommand.EditGameEntryDescriptor();
    }

    public EditGameEntryDescriptorBuilder(EditCommand.EditGameEntryDescriptor descriptor) {
        this.descriptor = new EditCommand.EditGameEntryDescriptor(descriptor);
    }

    /**
     * Initializes the GameEntryBuilder with the data of {@code gameEntryToCopy}.
     */
    public EditGameEntryDescriptorBuilder(GameEntry gameEntryToCopy) {
        descriptor = new EditCommand.EditGameEntryDescriptor();
        descriptor.setGameType(new GameType(gameEntryToCopy.getGameType()));
        descriptor.setStartAmount(gameEntryToCopy.getStartAmount());
        descriptor.setEndAmount(gameEntryToCopy.getEndAmount());
        descriptor.setDate(gameEntryToCopy.getDate());
        descriptor.setDuration(gameEntryToCopy.getDurationMinutes());
        descriptor.setLocation(new Location(gameEntryToCopy.getLocation()));
        descriptor.setTags(gameEntryToCopy.getTags());
    }

    /**
     * Sets the {@code GameType} of the {@code GameEntry} that we are building.
     */
    public EditGameEntryDescriptorBuilder withGameType(GameType gameType) {
        descriptor.setGameType(gameType);
        return this;
    }

    /**
     * Sets the start amount of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withStartAmount(Double startAmount) {
        descriptor.setStartAmount(startAmount);
        return this;
    }

    /**
     * Sets the end amount of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withEndAmount(Double endAmount) {
        descriptor.setEndAmount(endAmount);
        return this;
    }

    /**
     * Sets the {@code DatePlayed} of the {@code GameEntry} that we are building.
     *
     * @throws ParseException if the given {@code datePlayed} is invalid.
     */
    public EditGameEntryDescriptorBuilder withDatePlayed (DatePlayed datePlayed) {
        descriptor.setDate(datePlayed);
        return this;
    }

    /**
     * Sets the duration of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withDuration(Integer duration) {
        descriptor.setDuration(duration);
        return this;
    }

    /**
     * Sets the location of the {@code GameEntry} that we are building.
     *
     */
    public EditGameEntryDescriptorBuilder withLocation(Location location) {
        descriptor.setLocation(location);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public EditGameEntryDescriptorBuilder withTags(String ... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }


    public EditCommand.EditGameEntryDescriptor build() {
        return descriptor;
    }
}
