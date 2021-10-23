package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

public class Amount {
    public static final String MESSAGE_CONSTRAINTS =
            "Cash values should be floating point numbers with at most 2 decimal places";
    private static final String CASH_VALUE_FORMAT = "-{0,1}(([0-9]{1,}(.[0-9]{0,2}))|[0-9]{1,})";
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
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = Double.parseDouble(amount.strip());
    }

    /**
     * Constructs Amount.
     *
     * @param amount
     */
    public Amount(Double amount) {
        requireNonNull(amount);
        this.amount = amount;
    }

    /**
     * Checks whether input string is valid amount.
     *
     * @param cashAmount
     * @return Whether input string is valid amount.
     */
    public static boolean isValidAmount(String cashAmount) {
        return cashAmount.strip().matches(CASH_VALUE_FORMAT);
    }

    public double getAmount() {
        return amount;
    }

    /**
     * Calculates the difference between this and another {@code Amount}.
     *
     * @param otherAmount
     * @return Difference between this and other {@code Amount}.
     */
    public Amount difference(Amount otherAmount) {
        requireNonNull(otherAmount);
        return new Amount(amount - otherAmount.amount);
    }

    /**
     * Creates a string representing this with added prefix.
     *
     * @param prefix
     * @return
     */
    public String addCurrencySymbol(String prefix) {
        requireNonNull(prefix);
        String sign = amount >= 0 ? "+" : "-";
        String value = String.format("%.2f", amount >= 0 ? amount : -amount);
        return String.format("%s %s%s", sign, prefix, value);
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
