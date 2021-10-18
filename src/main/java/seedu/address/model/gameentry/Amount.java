package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;

import java.text.DecimalFormat;

public class Amount {
    private static final String CASH_VALUE_FORMAT = "-{0,1}[0-9]{1,}(.[0-9]{0,2})";
    private static DecimalFormat df = new DecimalFormat("0.00");
    private double amount;

    public Amount() {
        amount = 0;
    }

    /**
     * Constructs Amount.
     *
     * @param amount Should be a string representing a double up to 2 decimal places.
     */
    public Amount(String amount) {
        requireNonNull(amount);
        if (!isValidAmount(amount)) {
            throw new IllegalArgumentException();
        }
        this.amount = Double.parseDouble(amount);
    }

    public boolean isValidAmount(String cashAmount) {
        return cashAmount.matches(CASH_VALUE_FORMAT);
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%.2f", amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Amount) {
            Amount tmp = (Amount) obj;
            return amount == tmp.amount;
        }
        return false;
    }

}
