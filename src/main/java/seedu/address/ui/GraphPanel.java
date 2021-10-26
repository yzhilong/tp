package seedu.address.ui;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.ModelManager;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.Average;

public class GraphPanel extends UiPart<Region> {
    private static final String FXML = "GraphPanel.fxml";

    @FXML
    private LineChart<String, Number> lineChart;
    private ObservableList<GameEntry> gameEntryList;
    private XYChart.Series<String, Number> series;
    private TreeMap<String, Double> averageProfits = new TreeMap<>();

    /**
     * Constructor used to initialise the GraphPanel and a given GameEntry List
     *
     * @param gameEntryList
     */
    public GraphPanel(ObservableList<GameEntry> gameEntryList) {
        super(FXML);
        series = new XYChart.Series<>();
        series.setName("Average Profit on Latest 20 Dates");
        lineChart.setAnimated(false);
        this.gameEntryList = gameEntryList;
    }

    /**
     * Resets the series and panel, and draws a graph of the latest k dates using the provided TreeMap
     * @param k The number of dates to be plotted.
     */
    public void drawGraphOfLatestKDates(int k) {
        averageProfits = Average.getAverageData(gameEntryList);
        lineChart.getData().clear();
        lineChart.getData().add(series);
        series.getData().clear();
        int averageProfitsSize = averageProfits.size();
        int startIndex = averageProfitsSize - k + 1;
        int counter = 1;

        for (Map.Entry<String, Double> entry : averageProfits.entrySet()) {
            if (counter < startIndex) {
                counter++;
                continue;
            }
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            counter++;
        }
    }

    /**
     * Resets the value of the gameList after every command and calls draw graph
     *
     * @param gameList
     */
    public void updateGameEntryList(ObservableList<GameEntry> gameList) {
        this.gameEntryList = gameList;
        this.drawGraphOfLatestKDates(ModelManager.NUMBER_OF_DATES_TO_PLOT);
    }

}

