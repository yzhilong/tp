package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.Average;

public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";

    @FXML
    private LineChart<String, Double> lineChart;
    private ObservableList<GameEntry> gameEntryList;
    private final XYChart.Series<String, Double> series;
    private HashMap<String, Double> averageProfits;

    /**
     * Constructor for initialising a GraphPanel
     * @param gameEntryList
     */
    public GraphPanel(ObservableList<GameEntry> gameEntryList) {
        super(FXML);
        this.gameEntryList = gameEntryList;
        Average.initList(gameEntryList);
        series = new XYChart.Series<>();
        series.setName("Average");
    }

    /**
     * Draws a graph using a Hashmap of Date and Average profit by first sorting it then adding each entry to the
     * series
     */
    public void drawGraph() {
        averageProfits = Average.getStats();
        Map<String, Double> sortedAverageProfits = new TreeMap<>(averageProfits); //sorts the hashmap
        for (Map.Entry<String, Double> entry : sortedAverageProfits.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        if (!lineChart.getData().contains(series)) {
            lineChart.getData().add(series);
        }
    }

    /**
     * Updates the current list of Game Entries to a new list of game entries from logic
     * @param list
     */
    public void updateList(ObservableList<GameEntry> list) {
        this.gameEntryList = list;
        Average.updateList(list);
        this.drawGraph();
    }

    /**
     * Clears all the data points in the series and resets the averageProfits HashMap
     */
    public void clear() {
        series.getData().clear();
        averageProfits.clear();
    }
}

