package seedu.address.model.gameentry;

public class StartAmount extends Amount {
    public StartAmount(String startAmount) {
        super(startAmount);
    }

    @Override
    public String toString() {
        return String.format("Start amount: %s", super.toString());
    }
}
