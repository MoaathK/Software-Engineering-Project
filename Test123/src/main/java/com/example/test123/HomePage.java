package com.example.test123;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    Stage stage;
    Scene scene;
    Parent root;
    @FXML
    private Button logoutButton;

    // methods for changing the scenes

    @FXML
    public void addExpenseOnAction(ActionEvent e) {
        // Changing scene to the add expense
        try{
            root = FXMLLoader.load(getClass().getResource("addExpense.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception ex)
        {
            System.out.println("Can't load");
        }
    }

    @FXML
    public void addIncomeOnAction(ActionEvent e) {
        try{
            root = FXMLLoader.load(getClass().getResource("addIncome.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception ex)
        {
            System.out.println("Can't load");
        }
    }

    public void historyButtonOnAction(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("History.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }
    }

    public void showExpenseOnAction(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("showExpense.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }

    }

    public void showIncomeOnAction(ActionEvent e){
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

    public void logoutButton(ActionEvent e){
        System.exit(0);
    }

}

