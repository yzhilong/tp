package seedu.address.ui;

import java.awt.event.ActionEvent;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.gameentry.GameEntry;
import seedu.address.model.stats.Average;

public class GraphPanel extends UiPart<Region> {
	private static final String FXML = "GraphPanel.fxml";

	@FXML
	private LineChart<String, Double> lineChart;

	private ObservableList<GameEntry> gameEntryList;

	public GraphPanel(ObservableList<GameEntry> gameEntryList) {
		super(FXML);
		this.gameEntryList = gameEntryList;
	}


	public void drawGraph() {
		lineChart.getXAxis();
		lineChart.getYAxis();
		Set<Average.Tuple> averageSeries = Average.getAverage(gameEntryList);
		XYChart.Series series = new XYChart.Series();

		for (Average.Tuple entry : averageSeries) {
			System.out.println(entry.getDate());
			System.out.println(entry.getProfit());
			series.getData().add(new XYChart.Data(entry.getDate().toString(), entry.getProfit()));
		}

		lineChart.getData().add(series);
	}
}

