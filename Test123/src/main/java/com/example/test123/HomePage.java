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


























/*

    @FXML
    private ListView<Expense> expenseListView;

    public void initialize(){
        loadRecentExpenses();
    }
    private void loadRecentExpenses(){
        List<Expense> lastFiveExpenses = getRecentExpenses();
        expenseListView.setItems(FXCollections.observableArrayList(lastFiveExpenses));

        expenseListView.setCellFactory(lv -> new ListCell<Expense>(){
            @Override
            protected void updateItem(Expense expense, boolean empty){
                super.updateItem(expense,empty);
                if (empty){
                    setText(null);
                }
                else {
                    String text = String.format("%s - $%.2f - %s", expense.getCategory(), expense.getAmount(), expense.getDate().toString());
                    setText(text);
                }
            }
        });
    }
    private List<Expense> getRecentExpenses(){
        List<Expense> expenses =new ArrayList<>();
        String sql = "SELECT * FROM spend ORDER BY date DESC LIMIT 5";

        try (Connection con = DBUtils.connect();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while (rs.next()){
                Expense expense = new Expense();
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setDate(rs.getDate("Date").toLocalDate());
                expenses.add(expense);
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return expenses;

    }
    public void populationPieChart(){
        List<PieChart.Data> pieChartData = getChartData();
    }
    private List<PieChart.Data> getChartData(){
        List<PieChart.Data> data = new ArrayList<>();
       String sql = "SELECT category, SUM(amount) as total FROM expenses GROUP BY category";
       try (Connection con = DBUtils.connect();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()){
           while (rs.next()){
               String category = rs.getString("category");
               double total = rs.getDouble("total");
               data.add(new PieChart.Data(category,total));
           }

       }catch (SQLException s){
           s.printStackTrace();
       }
    return data;
    }
 */