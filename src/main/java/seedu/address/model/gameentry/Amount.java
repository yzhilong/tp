package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

public class Amount {
    private static final String CASH_VALUE_FORMAT = "-{0,1}[0-9]{1,}(.[0-9]{0,2})";
    private static final String INVALID_AMOUNT_MESSAGE =
            "Cash values should be floating point numbers with at most 2 decimal places";
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
        checkArgument(isValidAmount(amount), INVALID_AMOUNT_MESSAGE);
        this.amount = Double.parseDouble(amount);
    }

    /**
     * Checks whether input string is valid amount.
     *
     * @param cashAmount
     * @return Whether input string is valid amount.
     */
    public static boolean isValidAmount(String cashAmount) {
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
