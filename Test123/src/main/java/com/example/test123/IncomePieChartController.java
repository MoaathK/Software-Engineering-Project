package com.example.test123;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class IncomePieChartController  implements Initializable {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/WhereItGoesDB";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "m=0552564107";
    @FXML
    public PieChart pieChart;
    Stage stage;
    Scene scene;
    Parent root;


    Connection connection = null;
    public Connection connection(){
        try {
            connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return connection;
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ObservableList<PieChart.Data> pieChartData = fetchDataFromDatabase();
            pieChart.setData(pieChartData);
        }
        catch (Exception ex){
            System.out.println("Can't load");
        }

    }




    private ObservableList<PieChart.Data> fetchDataFromDatabase() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        connection = connection();
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT \"userID\",category,SUM(amount) as amount FROM public.data \n" +
                    "where type=2 and (category='Food' or category='Grocery store' or category='Education' or category='Maintenance' or category='Rent' or  category='Entertainment' or category='Subscription' or category='Loan' or category='Travel' )\n" +
                    "GROUP BY \"userID\",category;");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                double amount = resultSet.getDouble("amount");
                int userID = resultSet.getInt("userID");
                if(userID == SignIn.id)
                {
                    pieChartData.add(new PieChart.Data(category, amount));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pieChartData;
    }

    public void incomeChartBackOnAction(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("showIncome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }
    }
}
