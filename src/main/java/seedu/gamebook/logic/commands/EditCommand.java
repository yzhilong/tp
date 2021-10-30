package seedu.gamebook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_GAMETYPE;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_PROFIT;
import static seedu.gamebook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.gamebook.model.Model.PREDICATE_SHOW_ALL_GAME_ENTRIES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.gamebook.commons.core.Messages;
import seedu.gamebook.commons.core.index.Index;
import seedu.gamebook.commons.util.CollectionUtil;
import seedu.gamebook.logic.commands.exceptions.CommandException;
import seedu.gamebook.model.Model;
import seedu.gamebook.model.gameentry.DatePlayed;
import seedu.gamebook.model.gameentry.Duration;
import seedu.gamebook.model.gameentry.EndAmount;
import seedu.gamebook.model.gameentry.GameEntry;
import seedu.gamebook.model.gameentry.GameType;
import seedu.gamebook.model.gameentry.Location;
import seedu.gamebook.model.gameentry.StartAmount;
import seedu.gamebook.model.tag.Tag;

/**
 * Edits the details of an existing game entry in the game book.
 */
public class EditCommand extends Command {

    public static final EditCommand DUMMY = new EditCommand();

    public static final String COMMAND_WORD = "edit";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " INDEX "
        + "[" + PREFIX_GAMETYPE + "GAME_NAME] "
        + "[" + PREFIX_PROFIT + "PROFIT_AMOUNT]"
        + "[" + PREFIX_DATE + "DATE] "
        + "[" + PREFIX_DURATION + "DURATION] "
        + "[" + PREFIX_LOCATION + "LOCATION] "
        + "[" + PREFIX_TAG + "TAGS ... ]";
    public static final String COMMAND_SPECIFICATION = "INDEX must be a positive integer and cannot be bigger than the "
        + "number of entries in your game list.";
    public static final String COMMAND_EXAMPLE = "Assume that there is at least one game entry in GameBook now.\n"
        + COMMAND_WORD + " 1 "
        + PREFIX_GAMETYPE + "poker "
        + PREFIX_PROFIT + "150";
    public static final String COMMAND_SUMMARY = "Edits the details of the game entry identified "
        + "by the given index number. (Index number is obtained from the displayed games list.) "
        + "Existing values will be overwritten by the input values.\n\n"
        + "Format:\n" + COMMAND_FORMAT + "\n\n"
        + "Example:\n" + COMMAND_EXAMPLE;

    public static final String MESSAGE_USAGE = COMMAND_FORMAT + "\n" + COMMAND_SPECIFICATION;
    public static final String MESSAGE_EDIT_GAME_SUCCESS = "Edited game entry: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_GAME = "This game already exists in the game book.";

    private final Index index;
    private final EditGameEntryDescriptor editGameEntryDescriptor;

    private EditCommand() {
        index = null;
        editGameEntryDescriptor = null;
    }

    /**
     * @param index              of the game in the filtered game list to edit
     * @param editGameDescriptor details to edit the game with
     */
    public EditCommand(Index index, EditGameEntryDescriptor editGameDescriptor) {
        requireNonNull(index);
        requireNonNull(editGameDescriptor);

        this.index = index;
        this.editGameEntryDescriptor = new EditGameEntryDescriptor(editGameDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<GameEntry> lastShownList = model.getFilteredGameEntryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_GAMEENTRY_DISPLAYED_INDEX);
        }

        GameEntry gameEntryToEdit = lastShownList.get(index.getZeroBased());
        GameEntry editedGameEntry = createEditedGameEntry(gameEntryToEdit, editGameEntryDescriptor);

        // TODO - might remove if we are not checking for identical game entries
        if (!gameEntryToEdit.isSameGameEntry(editedGameEntry) && model.hasGameEntry(editedGameEntry)) {
            throw new CommandException(MESSAGE_DUPLICATE_GAME);
        }

        model.setGameEntry(gameEntryToEdit, editedGameEntry);


        model.updateFilteredGameEntryList(PREDICATE_SHOW_ALL_GAME_ENTRIES);
        return new CommandResult(String.format(MESSAGE_EDIT_GAME_SUCCESS, editedGameEntry));
    }

    /**
     * Creates and returns a {@code GameEntry} with the details of {@code gameEntryToEdit}
     * edited with {@code editGameEntryDescriptor}.
     */
    private static GameEntry createEditedGameEntry(GameEntry gameEntryToEdit,
                                                   EditGameEntryDescriptor editGameEntryDescriptor) {
        assert gameEntryToEdit != null;

        GameType updatedGameType = editGameEntryDescriptor.getGameType()
                .orElse(gameEntryToEdit.getGameType());
        StartAmount updatedStartAmount = editGameEntryDescriptor.getStartAmount()
                .orElse(gameEntryToEdit.getStartAmount());
        EndAmount updatedEndAmount = editGameEntryDescriptor.getEndAmount().orElse(gameEntryToEdit.getEndAmount());
        DatePlayed date = editGameEntryDescriptor.getDate().orElse(gameEntryToEdit.getDate());
        Duration updatedDuration = editGameEntryDescriptor.getDuration().orElse(gameEntryToEdit.getDuration());
        Location updatedLocation = editGameEntryDescriptor.getLocation()
                .orElse(gameEntryToEdit.getLocation());
        Set<Tag> updatedTags = editGameEntryDescriptor.getTags().orElse(gameEntryToEdit.getTags());

        return new GameEntry(updatedGameType, updatedStartAmount, updatedEndAmount, date,
                updatedDuration, updatedLocation, updatedTags);
    }

    @Override
    public String getCommandWord() {
        return EditCommand.COMMAND_WORD;
    }

    @Override
    public String getCommandSummary() {
        return EditCommand.COMMAND_SUMMARY;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editGameEntryDescriptor.equals(e.editGameEntryDescriptor);
    }

    /**
     * Stores the details to edit the game entry with. Each non-empty field value will replace the
     * corresponding field value of the game entry.
     */
    public static class EditGameEntryDescriptor {
        private GameType gameType;
        private StartAmount startAmount;
        private EndAmount endAmount;
        private DatePlayed date;
        private Duration durationMinutes;
        private Location location;
        private Set<Tag> tags;

        public EditGameEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGameEntryDescriptor(EditGameEntryDescriptor toCopy) {
            setGameType(toCopy.gameType);
            setStartAmount(toCopy.startAmount);
            setEndAmount(toCopy.endAmount);
            setDate(toCopy.date);
            setDuration(toCopy.durationMinutes);
            setLocation(toCopy.location);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(gameType, startAmount, endAmount, date, durationMinutes, location, tags);
        }

        public void setGameType(GameType gameType) {
            this.gameType = gameType;
        }

        public Optional<GameType> getGameType() {
            return Optional.ofNullable(gameType);
        }

        public void setStartAmount(StartAmount startAmount) {
            this.startAmount = startAmount;
        }

        public Optional<StartAmount> getStartAmount() {
            return Optional.ofNullable(startAmount);
        }

        public void setEndAmount(EndAmount endAmount) {
            this.endAmount = endAmount;
        }

        public Optional<EndAmount> getEndAmount() {
            return Optional.ofNullable(endAmount);
        }

        public void setDate(DatePlayed date) {
            this.date = date;
        }

        public Optional<DatePlayed> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDuration(Duration durationMinutes) {
            this.durationMinutes = durationMinutes;
        }

        public Optional<Duration> getDuration() {
            return Optional.ofNullable(durationMinutes);
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public Optional<Location> getLocation() {
            return Optional.ofNullable(location);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGameEntryDescriptor)) {
                return false;
            }

            // state check
            EditGameEntryDescriptor e = (EditGameEntryDescriptor) other;

            // assume different game entries must be unique in their fields
            return getGameType().equals(e.getGameType())
                    && getStartAmount().equals(e.getStartAmount())
                    && getEndAmount().equals(e.getEndAmount())
                    && getDate().equals(e.getDate())
                    && getDuration().equals(e.getDuration())
                    && getLocation().equals(e.getLocation())
                    && getTags().equals(e.getTags());
        }
    }
}