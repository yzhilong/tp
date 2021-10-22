package seedu.address.model.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;

public class Average {

    private Average() {
    }

    /**
     * Puts all the Dates and corresponding profits into a TreeMap from a GameEntry List
     *
     * @param gameEntryList
     * @return a treemap with the dates as keys and the average profit as values
     */
    public static TreeMap<String, Double> getAverageData(List<GameEntry> gameEntryList) {
        TreeMap<DatePlayed, List<Double>> preprocessedDates = new TreeMap<>(); //before calculating average
        TreeMap<String, Double> processedDates = new TreeMap<>(); //after calculating average

        gameEntryList.forEach(gameEntry -> {
            if (!preprocessedDates.containsKey(gameEntry.getDate())) {
                preprocessedDates.put(gameEntry.getDate(), new ArrayList<>());
            }
            preprocessedDates.get(gameEntry.getDate()).add(gameEntry.getProfit());
        }
        );
        preprocessedDates.forEach((date, listOfProfits) -> {
            Double averageProfit = listOfProfits.stream().mapToDouble(Double::doubleValue).average().orElse(0.00);
            processedDates.put(date.toString().strip().split("\\s", 2)[0], averageProfit);
        });

        return processedDates;
    }


}
