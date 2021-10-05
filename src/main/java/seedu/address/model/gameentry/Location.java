package seedu.address.model.gameentry;

import static java.util.Objects.requireNonNull;

public class Location {
    private static final String EMPTY_LOCATION = "";
    private final String location;

    public Location(String location) {
        requireNonNull(location);
        String[] tmp = location.strip().split(" ");
        if (tmp.length == 0) {
            throw new IllegalArgumentException("Input cannot only contain whitespaces");
        }

        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = tmp[i].substring(0,1).toUpperCase() + tmp[i].substring(1).toLowerCase();
        }
        this.location = String.join(" ", tmp);
    }

    public Location() {
        this.location = EMPTY_LOCATION;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Location) {
            Location tmp = (Location) other;
            return location.equals(tmp.location);
        }
        return false;
    }

    @Override
    public String toString() {
        return location;
    }

    @Override
    public int hashCode() {
        return location.hashCode();
    }
}
