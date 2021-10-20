package seedu.address.ui;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.StatsByDate;

public class GraphPanel extends UiPart<Region> {
	private static final String FXML = "GraphPanel.fxml";

	@FXML
	private LineChart<String, Double> lineChart;

	private ObservableList<GameEntry> gameEntryList;
	private XYChart.Series series;
	private List<StatsByDate.Tuple> averageSeries;

	// todo:
	// Bugs:
	// Refreshing axes while updating the list - currently only the series is refreshed and not the axes

	public GraphPanel(ObservableList<GameEntry> gameEntryList) {
		super(FXML);
		this.gameEntryList = gameEntryList;
		series = new XYChart.Series();
		series.setName("Average");
	}

	public void drawGraph() {
		averageSeries = StatsByDate.getStats(gameEntryList);
		Comparator<StatsByDate.Tuple> c = Comparator.comparing(StatsByDate.Tuple::getDate);
		averageSeries.sort(c);
		for (StatsByDate.Tuple entry : averageSeries) {
			System.out.println(entry.getDate());
			System.out.println(entry.getProfit());
			series.getData().add(new XYChart.Data(entry.getDate(), entry.getProfit()));
		}
		lineChart.getData().add(series);
	}

	public void updateList(ObservableList<GameEntry> list) {
		this.gameEntryList = list;
		this.drawGraph();
	}

	public void clear() {
		series.getData().clear();
		averageSeries.clear();
		lineChart.getXAxis().autosize();
	}
}

