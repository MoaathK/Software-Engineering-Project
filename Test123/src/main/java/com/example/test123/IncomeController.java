package com.example.test123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class IncomeController implements Initializable {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/userInformation";
    private static final String DATABASE_USER = "";
    private static final String DATABASE_PASSWORD = " ";
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

    @FXML
    private ScrollPane historyScroll;
    @FXML
    private RadioButton incomeTodayRadio;
    @FXML
    private RadioButton incomeWeekRadio;
    @FXML
    private RadioButton incomeMonthRadio;
    @FXML
    private RadioButton incomeAllRadio;
    @FXML
    private Button incomeBackButton;
    @FXML
    private Label incomeTotal;

    Stage stage;
    Scene scene;
    Parent root;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ToggleGroup tGroup=new ToggleGroup();
            incomeTodayRadio.setToggleGroup(tGroup);
            incomeWeekRadio.setToggleGroup(tGroup);
            incomeMonthRadio.setToggleGroup(tGroup);
            incomeAllRadio.setToggleGroup(tGroup);
            incomeAllRadio.setSelected(true);
            showIncome();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }



    public void showIncome() throws Exception{
        VBox mainBox = new VBox();
        Font text = new Font("Verdana", 16);
        mainBox.setPrefWidth(598);
        mainBox.setSpacing(10);
        connection = connection();
        ResultSet historySet;
        int temp=0;

        if(incomeTodayRadio.isSelected())
        {
            PreparedStatement getHistory = connection.prepareStatement("SELECT * from expense_tracker.data where userID = ? and type = 2 and date = CURRENT_DATE()");
            getHistory.setInt(1, SignIn.id);
            historySet = getHistory.executeQuery();
        }
        else if(incomeWeekRadio.isSelected())
        {
            PreparedStatement getHistory = connection.prepareStatement("SELECT * from expense_tracker.data where userID = ? and type = 2 and date> CURRENT_DATE() - INTERVAL 7 day order by date desc");
            getHistory.setInt(1, SignIn.id);
            historySet = getHistory.executeQuery();
        }
        else if(incomeMonthRadio.isSelected())
        {
            PreparedStatement getHistory = connection.prepareStatement("SELECT * from expense_tracker.data where userID = ? and type = 2 and date> CURRENT_DATE() - INTERVAL 30 day order by date desc");
            getHistory.setInt(1, SignIn.id);
            historySet = getHistory.executeQuery();
        }
        else
        {
            PreparedStatement getHistory = connection.prepareStatement("SELECT * from expense_tracker.data where userID = ? and type = 2 order by date desc");
            getHistory.setInt(1, SignIn.id);
            historySet = getHistory.executeQuery();
        }

        while(historySet.next())
        {
            AnchorPane gp = new AnchorPane();
            Label dateLabel = new Label(historySet.getDate("date") + "");
            Label categoryLabel = new Label(historySet.getString("category"));
            Label amountLabel = new Label(historySet.getString("amount"));
            dateLabel.setFont(text);
            categoryLabel.setFont(text);
            amountLabel.setFont(text);
            gp.getChildren().add(dateLabel);
            gp.getChildren().add(categoryLabel);
            gp.getChildren().add(amountLabel);
            dateLabel.setLayoutX(10);
            amountLabel.setLayoutX(430);
            categoryLabel.setLayoutX(160);
            categoryLabel.setMinWidth(200);
            categoryLabel.setAlignment(Pos.CENTER);
            dateLabel.setMinHeight(40);
            categoryLabel.setMinHeight(40);
            amountLabel.setMinHeight(40);
            temp = temp + Integer.parseInt(amountLabel.getText());

            dateLabel.setStyle("-fx-text-fill: green");
            categoryLabel.setStyle("-fx-text-fill: green");
            amountLabel.setStyle("-fx-text-fill: green");
            gp.setStyle("-fx-background-color: #cbdcf2");
            mainBox.getChildren().add(gp);
        }

        incomeTotal.setText(temp+"");
        historyScroll.setContent(mainBox);
    }


    public void incomeBackButton(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }
    }

    public void chartButton(ActionEvent e){
        try {
            root = FXMLLoader.load(getClass().getResource("incomeChart.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }
    }
}
