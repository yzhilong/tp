package seedu.address.model.stats;

import javafx.collections.ObservableList;
import seedu.address.model.GameBook;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Average {
	private static Set<Tuple> averageSeries = new HashSet<>();

	private Average() {
	}

	public static Set<Tuple> getAverage(ObservableList<GameEntry> gameEntries) {
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

		public DatePlayed getDate() {
			return this.datePlayed;
		}

		public Double getProfit() {
			return this.profit;
		}

	}


}
