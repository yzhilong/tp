package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;

public class Duration {
    private static final String[] VALID_FORMATS = new String[] {
        "[0-9]*",
        "[0-9]{1,}:[0-5][0-9]",
        "[0-9]{1,}h",
        "[0-9]{1,}h [0-5][0-9]m",
        "[1-9][0-9]*m"
    };
    private final int durationMinutes;

    /**
     * Constructs Duration.
     *
     * @param durationString
     */
    public Duration(String durationString) {
        requireNonNull(durationString);
        if (durationString.matches("-2147483648")) {
            this.durationMinutes = -2147483648;
        } else {
            this.durationMinutes = parseDurationString(durationString);
        }
    }

    /**
     * Checks whether input string is valid duration.
     *
     * @param durationString
     * @return Whether input string is valid duration.
     */
    public static boolean isValidDuration(String durationString) {
        for (String format : VALID_FORMATS) {
            if (durationString.matches(format)) {
                return true;
            }
        }
        return durationString.equals("-2147483648");
    }

    private static int parseDurationString(String durationString) {
        if (durationString.matches(VALID_FORMATS[0])) {
            return Integer.valueOf(durationString);
        } else if (durationString.matches(VALID_FORMATS[1])) {
            String[] vals = durationString.split(":");
            return Integer.valueOf(vals[0]) * 60 + Integer.valueOf(vals[1]);
        } else if (durationString.matches(VALID_FORMATS[2])) {
            return Integer.valueOf(durationString.substring(0, durationString.length() - 2)) * 60;
        } else if (durationString.matches(VALID_FORMATS[3])) {
            String[] vals = durationString.split(" ");
            int hours = Integer.valueOf(vals[0].substring(0, vals[0].length() - 2));
            int minutes = Integer.valueOf(vals[1].substring(0, vals[1].length() - 2));
            return 60 * hours + minutes;
        } else if (durationString.matches(VALID_FORMATS[4])) {
            return Integer.valueOf(durationString.substring(0, durationString.length() - 2));
        } else {
            throw new IllegalArgumentException(
                    "Input format should be \"12:34\", \"12h\", \"34m\", \"12h 34m\" or \"1234\"");
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
        return String.format("Game duration: %d", durationMinutes);
    }

}
