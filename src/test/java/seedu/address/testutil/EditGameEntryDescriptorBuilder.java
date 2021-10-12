package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.gameentry.GameType;
import seedu.address.model.gameentry.Location;
import seedu.address.model.tag.Tag;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class EditGameEntryDescriptorBuilder {

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
        descriptor.setGameType(gameEntryToCopy.getGameType());
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
    public EditGameEntryDescriptorBuilder withGameType(String gameType) {
        descriptor.setGameType(gameType);
        return this;
    }

    // TODO - change to String and use constructors after 1.2
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
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code GameEntry} that we are building.
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
