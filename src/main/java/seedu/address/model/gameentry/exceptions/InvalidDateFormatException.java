package seedu.address.model.gameentry.exceptions;

public class InvalidDateFormatException extends RuntimeException {
    private static final String INVALID_DATE_MESSAGE = "Date should be in dd/MM/yy HH:mm or dd/MM/yy format.";
    public InvalidDateFormatException() {
        super(INVALID_DATE_MESSAGE);
    }
}
