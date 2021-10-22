package seedu.address.model.gameentry.exceptions;

public class DuplicateGameEntryException extends RuntimeException {
    private static final String DUPLICATE_GAME_MESSAGE = "Operation would result in duplicate game entries";
    public DuplicateGameEntryException() {
        super(DUPLICATE_GAME_MESSAGE);
    }
}
