package seedu.address.model.gameentry.exceptions;

public class DuplicateGameEntryException extends RuntimeException {
    public DuplicateGameEntryException() {
        super("Operation would result in duplicate game entries");
    }
}
