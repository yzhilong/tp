package seedu.address.model.gameEntry;

public class GameType {
    private final String gameType;

    public GameType(String gameType) {
        this.gameType = gameType.substring(0,1).toUpperCase() + gameType.substring(1);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof GameType) {
            GameType tmp = (GameType) other;
            return this.gameType.equals(tmp.gameType);
        }
        return false;
    }

    @Override
    public String toString() {
        return gameType;
    }

    @Override
    public int hashCode() {
        return gameType.hashCode();
    }
}
