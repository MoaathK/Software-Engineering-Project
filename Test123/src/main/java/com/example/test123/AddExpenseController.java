package com.example.test123;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddExpenseController implements Initializable {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/WhereItGoesDB";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "m=0552564107";

    @FXML
    private TextField e_amount;
    @FXML
    private ComboBox<String> e_category;
    @FXML
    private DatePicker e_date;

    Stage stage;
    Scene scene;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Need to change this below
        e_category.setItems(FXCollections.observableArrayList("Food","Grocery store","Education","Maintenance","Rent","Entertainment","Subscription","Loan","Travel"));// need to change the Items
    }
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

    public void addData(ActionEvent e, int type, int amount, String category, LocalDate date, int userID)
    {
        connection = connection();
        PreparedStatement psInsert=null;

        try {
            psInsert = connection.prepareStatement("INSERT INTO public.data (type,amount,category,date) VALUES (?,?,?,?)");
            psInsert.setInt(1, type);
            psInsert.setInt(2, amount);
            psInsert.setString(3, category);
            psInsert.setString(4, date+"");
            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your data added successfully");
            alert.show();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public void e_saveButtonOnAction(ActionEvent e)
    {
        if(!e_amount.getText().trim().isEmpty())
        {
            int amount = Integer.parseInt(e_amount.getText());
            if(amount <= 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Amount can't be less than 0");
                alert.show();
            }
            else{
                // type = 1 mean it is an expense
                addData(e, 1, Integer.parseInt(e_amount.getText()), e_category.getValue(), e_date.getValue(), SignIn.id);
                try {
                    root = FXMLLoader.load(getClass().getResource("HomaPage.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println("Can't load");
                }
            }

        }
        else
        {
            System.out.println("Please fill in all information!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all information to register");
            alert.show();
        }
    }


    public void onDateClicked()
    {
        e_date.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });
    }


    public void e_cancelOnAction(ActionEvent e) throws Exception {
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
