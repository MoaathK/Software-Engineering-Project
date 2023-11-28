package com.example.test123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class History implements Initializable {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/WhereItGoesDB";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "m=0552564107";
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
    private Button historyBackButton;
    Stage stage;
    Scene scene;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            showHistory();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    public void showHistory() throws Exception{
        var mainBox = new VBox();
        Font text = new Font("Verdana", 16);
        mainBox.setPrefWidth(598);
        mainBox.setSpacing(10);
        connection = connection();
        PreparedStatement getHistory = connection.prepareStatement("SELECT * from public.data where \"userID\" = ? order by date desc");
        getHistory.setInt(1, SignIn.id);
        ResultSet historySet = getHistory.executeQuery();
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
            if(historySet.getInt("type")==1){
                dateLabel.setStyle("-fx-text-fill: red");
                categoryLabel.setStyle("-fx-text-fill: red");
                amountLabel.setStyle("-fx-text-fill: red");
            }
            else{
                dateLabel.setStyle("-fx-text-fill: green");
                categoryLabel.setStyle("-fx-text-fill: green");
                amountLabel.setStyle("-fx-text-fill: green");
            }

            gp.setStyle("-fx-background-color: #cbdcf2");
            mainBox.getChildren().add(gp);
        }


        historyScroll.setContent(mainBox);
    }

    public void setHistoryBackButtonOnAction(ActionEvent e){
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
}
