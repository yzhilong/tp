package seedu.address.ui;

import static java.util.Objects.requireNonNull;

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
        requireNonNull(gameEntryList);
        series = new XYChart.Series<>();
        series.setName("Average");
        lineChart.setAnimated(false);
        this.gameEntryList = gameEntryList;
    }

    /**
     * Resets the series and panel, and draws a graph using the provided TreeMap
     */
    public void drawGraph() {
        averageProfits = Average.getAverageData(gameEntryList);
        lineChart.getData().clear();
        lineChart.getData().add(series);
        series.getData().clear();
        for (Map.Entry<String, Double> entry : averageProfits.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * Resets the value of the gameList after every command and calls draw graph
     *
     * @param gameList
     */
    public void updateGameEntryList(ObservableList<GameEntry> gameList) {
        requireNonNull(gameList);
        this.gameEntryList = gameList;
        this.drawGraph();
    }

}

