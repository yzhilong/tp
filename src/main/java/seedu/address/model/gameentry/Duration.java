package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Duration {
    public static final String MESSAGE_CONSTRAINTS =
            "Input format should be \"12:34\", \"12h\", \"34m\", \"12h 34m\" or \"12345\"";
    private static final String[] VALID_FORMATS = new String[] {
        "[0-9]*",
        "[0-9]{1,}:[0-5][0-9]",
        "[0-9]{1,}h",
        "[0-9]{1,}h [0-5][0-9]m",
        "[1-9][0-9]*m"
    };
    private static final Duration EMPTY = new Duration();
    private final int durationMinutes;

    /**
     * Constructs Duration.
     *
     * @param durationString
     */
    public Duration(String durationString) {
        requireNonNull(durationString);
        checkArgument(isValidDuration(durationString), MESSAGE_CONSTRAINTS);
        if (durationString.matches("-2147483648")) {
            durationMinutes = -2147483648;
        } else {
            durationMinutes = parseDurationString(durationString);
        }
    }

    /**
     * Constructs Duration.
     *
     * @param duration
     */
    public Duration(Integer duration) {
        requireNonNull(duration);
        checkArgument(isValidDuration(duration), MESSAGE_CONSTRAINTS);
        durationMinutes = duration;
    }

    /**
     * Private constructor for empty duration.
     */
    private Duration() {
        durationMinutes = 0;
    }

    public static Duration empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    /**
     * Checks whether input string is valid duration.
     *
     * @param durationString
     * @return Whether input string is valid duration.
     */
    public static boolean isValidDuration(String durationString) {
        durationString = durationString.strip();
        for (String format : VALID_FORMATS) {
            if (durationString.matches(format)) {
                return true;
            }
        }
        return durationString.equals("-2147483648") || durationString.equals("");
    }

    /**
     * Checks whether input integer is valid duration.
     *
     * @param duration
     * @return Whether input integer is valid duration.
     */
    public static boolean isValidDuration(Integer duration) {
        return duration != null
                && (duration.equals(Integer.MIN_VALUE)
                || duration >= 0);
    }

    private static int parseDurationString(String durationString) {
        durationString = durationString.strip();
        if (durationString.equals("")) {
            return Integer.MIN_VALUE;
        } else if (durationString.matches(VALID_FORMATS[0])) {
            return Integer.valueOf(durationString);
        } else if (durationString.matches(VALID_FORMATS[1])) {
            String[] vals = durationString.split(":");
            return Integer.valueOf(vals[0]) * 60 + Integer.valueOf(vals[1]);
        } else if (durationString.matches(VALID_FORMATS[2])) {
            return Integer.valueOf(durationString.substring(0, durationString.length() - 1)) * 60;
        } else if (durationString.matches(VALID_FORMATS[3])) {
            String[] vals = durationString.split(" ");
            int hours = Integer.valueOf(vals[0].substring(0, vals[0].length() - 1));
            int minutes = Integer.valueOf(vals[1].substring(0, vals[1].length() - 1));
            return 60 * hours + minutes;
        } else if (durationString.matches(VALID_FORMATS[4])) {
            return Integer.valueOf(durationString.substring(0, durationString.length() - 1));
        } else {
            // Will never happen
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Duration) {
            Duration tmp = (Duration) obj;
            return durationMinutes == tmp.durationMinutes;
        }
        return false;
    }

    @Override
    public String toString() {
        // TODO -> Change to show hh:mm if durationMinutes >= 60
        return String.format("%d", durationMinutes);
    }

}
