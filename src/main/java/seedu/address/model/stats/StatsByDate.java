package seedu.address.model.stats;

import javafx.collections.ObservableList;
import seedu.address.model.GameBook;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;

import java.util.*;

public class StatsByDate {
	private static List<Tuple> averageSeries = new ArrayList<>();

	private StatsByDate() {
	}

	public static List<Tuple> getStats(ObservableList<GameEntry> gameEntries) {
		gameEntries.forEach(gameEntry -> averageSeries.add( new Tuple(gameEntry.getDate(), gameEntry.getProfit())));
		return averageSeries;
	}

	public static class Tuple
	{
		DatePlayed datePlayed;
		Double profit;
		public Tuple(DatePlayed datePlayed, Double profit) {
			this.datePlayed = datePlayed;
			this.profit = profit;
		}

		public String getDate() {
			return this.datePlayed.toString().strip().split("\\s", 2)[0];
		}

		public Double getProfit() {
			return this.profit;
		}

	}


}
