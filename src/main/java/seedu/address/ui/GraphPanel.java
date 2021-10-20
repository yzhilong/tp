package seedu.address.ui;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.gameentry.DatePlayed;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.StatsByDate;

public class GraphPanel extends UiPart<Region> {
	private static final String FXML = "GraphPanel.fxml";

	@FXML
	private LineChart<String, Double> lineChart;

	private ObservableList<GameEntry> gameEntryList;
	private XYChart.Series series;
	private HashMap<String, Double> averageProfits;

	// todo:
	// Bugs:
	// Refreshing axes while updating the list - currently only the series is refreshed and not the axes
	// Duplicate handling in StatsByDate

	public GraphPanel(ObservableList<GameEntry> gameEntryList) {
		super(FXML);
		this.gameEntryList = gameEntryList;
		StatsByDate.initList(gameEntryList);
		series = new XYChart.Series();
		series.setName("Average");
	}

	public void drawGraph() {
		averageProfits = StatsByDate.getStats();
		Map<String, Double> sortedAverageProfits = new TreeMap<>(averageProfits); //sorts the hashmap
		for (Map.Entry<String, Double> entry : sortedAverageProfits.entrySet()) {
				series.getData().add(new XYChart.Data(entry.getKey().toString(), entry.getValue()));
		}
		lineChart.getData().add(series);
	}

	public void updateList(ObservableList<GameEntry> list) {
		this.gameEntryList = list;
		StatsByDate.initList(list);
		this.drawGraph();
	}

	public void clear() {
		series.getData().clear();
		averageProfits.clear();
	}
}

