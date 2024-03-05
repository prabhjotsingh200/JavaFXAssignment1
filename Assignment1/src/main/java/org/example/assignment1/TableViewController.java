package org.example.assignment1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableViewController {

    @FXML
    private TableView<DataItem> tableView;

    @FXML
    private TableColumn<DataItem, String> nameColumn;

    @FXML
    private TableColumn<DataItem, Double> valueColumn;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initData(ObservableList<PieChart.Data> data) {
        // clear existing items in the table
        tableView.getItems().clear();

        // populate the TableView with data from PieChart
        for (PieChart.Data pieData : data) {
            String countryName = pieData.getName();
            double gdp2022 = pieData.getPieValue();
            tableView.getItems().add(new DataItem(countryName, gdp2022));
        }

        // set up column cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
    }

    @FXML
    private void goBack() {
        MainController.switchToPieChartScene(stage); // calling the switchToPieChartScene method from MainController
    }

    public static class DataItem {
        private final String name;
        private final double value;

        public DataItem(String name, double value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }
    }
}
