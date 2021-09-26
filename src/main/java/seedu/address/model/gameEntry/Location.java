package seedu.address.model.gameEntry;

public class Location {
    private final String location;

    public Location(String location) {
        this.location = location.substring(0,1).toUpperCase() + location.substring(1);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Location) {
            Location tmp = (Location) other;
            return this.location.equals(tmp.location);
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
