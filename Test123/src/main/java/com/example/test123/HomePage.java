package com.example.test123;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HomePage {

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
}
