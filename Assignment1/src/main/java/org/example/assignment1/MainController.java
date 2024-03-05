package org.example.assignment1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private PieChart pieChart;

    @FXML
    private Button tableViewButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        DatabaseConnector dbConnector = new DatabaseConnector();
        dbConnector.fetchData(pieChart);

        tableViewButton.setOnAction(event -> switchToTableViewScene());
    }

    // this is main controller to control all the trasitions betweeen
    // two scenes of chart and table using event listners
    private void switchToTableViewScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tableView.fxml"));
            Parent tableViewRoot = loader.load();
            TableViewController controller = loader.getController();
            controller.setStage(stage); // Set the stage here
            controller.initData(pieChart.getData()); // Pass data to TableView

            Scene tableViewScene = new Scene(tableViewRoot);
            stage.setScene(tableViewScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToPieChartScene(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pieChart.fxml"));
        try {
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Rajasthan's Sex Ratio");
            stage.setScene(scene);

            MainController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
