package org.example.assignment1;

import javafx.scene.chart.PieChart;

import java.sql.*;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/sexratio";
    private static final String USER = "root";
    private static final String PASS = "prabh123";

    // connecting databse with javaFX project using driver
    public Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }


    // running sql queries to fetch data from database and initiating data to pieChart with try catch to handle exception handaling
    public void fetchData(PieChart pieChart) {
        String query = "SELECT District , Sex_Ratio  FROM RajasthanDistrictSexRatio";
        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String district = resultSet.getString("District");
                double sexRatio = resultSet.getDouble("Sex_Ratio");
                pieChart.getData().add(new PieChart.Data(district, sexRatio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
