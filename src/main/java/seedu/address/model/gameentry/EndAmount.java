package seedu.address.model.gameentry;

public class EndAmount extends Amount {
    public EndAmount(String endAmount) {
        super(endAmount);
    }

    @Override
    public String toString() {
        return String.format("End amount: %s", super.toString());
    }
}
