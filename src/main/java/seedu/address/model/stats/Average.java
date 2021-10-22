package seedu.address.model.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;

public class Average {
    private static HashMap<DatePlayed, List<Double>> preprocessedDates = new HashMap<>();
    private static HashMap<String, Double> processedDates = new HashMap<>();
    private static List<GameEntry> currList = new ArrayList<>();

    private Average() {
    }

    public static HashMap<String, Double> getStats() {
        currList.forEach(gameEntry -> {
                if (!preprocessedDates.containsKey(gameEntry.getDate())) {
                    preprocessedDates.put(gameEntry.getDate(), new ArrayList<>());
                }
                preprocessedDates.get(gameEntry.getDate()).add(gameEntry.getProfit());
            }
        );
        System.out.println(preprocessedDates);
        preprocessedDates.forEach((date, listOfProfits) -> {
            Double averageProfit = listOfProfits.stream().mapToDouble(Double::doubleValue).average().orElse(0.00);
            processedDates.put(date.toString().strip().split("\\s", 2)[0], averageProfit);
        });
        return processedDates;
    }

    public static void initList(ObservableList<GameEntry> list) {
        currList = list;
    }

    /**
     * Resets the value of the current list to be the new list
     * @param list
     */
    public static void updateList(ObservableList<GameEntry> list) {
        if (!list.equals(currList)) {
            currList = list;
        }
    }
}
