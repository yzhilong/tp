package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.GameBook;
import seedu.address.model.ReadOnlyGameBook;
import seedu.address.model.gameentry.GameEntry;

/**
 * An Immutable GameBook that is serializable to JSON format.
 */
@JsonRootName(value = "gamebook")
class JsonSerializableGameBook {

    public static final String MESSAGE_DUPLICATE_GAME_ENTRY = "Game Entry list contains duplicate game entries(s).";

    private final List<JsonAdaptedGameEntry> gameEntries = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableGameBook} with the given game entries.
     */
    @JsonCreator
    public JsonSerializableGameBook(@JsonProperty("gameEntries") List<JsonAdaptedGameEntry> gameEntries) {
        this.gameEntries.addAll(gameEntries);
    }

    /**
     * Converts a given {@code ReadOnlyGameBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableGameBook}.
     */
    public JsonSerializableGameBook(ReadOnlyGameBook source) {
        gameEntries.addAll(source.getGameEntryList().stream().map(JsonAdaptedGameEntry::new).collect(Collectors.toList()));
    }

    /**
     * Converts this game book into the model's {@code GameBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public GameBook toModelType() throws IllegalValueException {
        GameBook gameBook = new GameBook();
        for (JsonAdaptedGameEntry jsonAdaptedGameEntry : gameEntries) {
            GameEntry gameEntry = jsonAdaptedGameEntry.toModelType();
            if (gameBook.hasGameEntry(gameEntry)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_GAME_ENTRY);
            }
            gameBook.addGameEntry(gameEntry);
        }
        return gameBook;
    }

}
