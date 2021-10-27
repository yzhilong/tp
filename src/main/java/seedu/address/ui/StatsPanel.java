package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.Average;
import seedu.address.model.stats.Median;

public class StatsPanel extends UiPart<Region> {
    private static final String FXML = "StatsPanel.fxml";
    private static final String MEAN = "Mean";
    private static final String MEDIAN = "Median";
    private ObservableList<GameEntry> gameEntries;


    @FXML
    private TableView<Pair<String, String>> table;

    @FXML
    private TableColumn<Pair<String, String>, String> statName;

    @FXML
    private TableColumn<Pair<String, String>, String> value;

    private ObservableList<Pair<String, String>> gameEntryList = FXCollections.observableArrayList();

    /**
     * Constructor used to initialise a StatsPanel using a given GameEntry list
     * @param gameEntries
     */
    public StatsPanel(ObservableList<GameEntry> gameEntries) {
        super(FXML);
        this.gameEntries = gameEntries;
    }

    public void getStats() {
        String overallAverage = String.format("%.2f", Average.getOverallAverage(gameEntries));
        String overallMedian = String.format("%.2f", Median.getOverallMedian(gameEntries));

        gameEntryList.add(new Pair<>(MEAN, overallAverage));
        gameEntryList.add(new Pair<>(MEDIAN, overallMedian));

        statName.setCellValueFactory(new PropertyValueFactory<>("key"));
        value.setCellValueFactory(new PropertyValueFactory<>("value"));

        table.setItems(gameEntryList);
    }

    /**
     * Clears the existing values for the statistics, re-initialises the GameEntry list and recalculates the statistics
     * @param gameEntries
     */
    public void updateStats(ObservableList<GameEntry> gameEntries) {
        gameEntryList.clear();
        this.gameEntries = gameEntries;
        this.getStats();
    }


}

