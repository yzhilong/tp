package seedu.address.model.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;

public class Average {
    private static final String REGEX_TO_SPLIT_DATE_AND_TIME = "\\s";

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
        TreeMap<String, Double> processedDates = new TreeMap<>(); // after calculating average

        /*
        for each game entry, check if the treemap contains the date - if it does append the profit of the game entry
        to the list stored as the value for the corresponding date
        and if it doesn't contain, then initialise the key and value to be the date and an empty list.
        */
        gameEntryList.forEach(gameEntry -> {
            if (!preprocessedDates.containsKey(gameEntry.getDate())) {
                preprocessedDates.put(gameEntry.getDate(), new ArrayList<>());
            }
            preprocessedDates.get(gameEntry.getDate()).add(gameEntry.getDifference());
        }
        );


        /*
        preprocessedDates now stores the dates as the keys and a list of profits (from each game played on that date)
        for the corresponding date as the values.

        for each element in preprocessedDates, the date is split from the time and the average of the profits stored
        in the list is calculated. The split date and the calculated average are stored in a new treemap
        */

        preprocessedDates.forEach((date, listOfProfits) -> {
            String parsedDate = date.toString().strip().split(REGEX_TO_SPLIT_DATE_AND_TIME, 2)[0];
            Double averageProfit = listOfProfits.stream().mapToDouble(Double::doubleValue).average().orElse(0.00);
            processedDates.put(parsedDate, averageProfit);
        });

        /*
        processedDates now contains the dates as the values in the hashmap and average profit for each date as the
        key
        */
        return processedDates;
    }

    public static Double getOverallAverage(List<GameEntry> gameEntryList) {
        return gameEntryList.stream().mapToDouble(GameEntry::getDifference).average().orElse(0.00);
    }

}
