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

public class AddIncomeController implements Initializable {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/WhereItGoesDB";
    private static final String DATABASE_USER = "postgres";
    private static final String DATABASE_PASSWORD = "m=0552564107";

    @FXML
    private TextField incomeAmount;
    @FXML
    private ComboBox<String> incomeCategory;
    @FXML
    private DatePicker incomeDate;
    @FXML
    private Button incomeSave;
    @FXML
    private Button incomeCancel;


    Stage stage;
    Scene scene;
    Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        incomeCategory.setItems(FXCollections.observableArrayList("Salary","Loan"));// the category will be put here

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
    public void i_saveButtonOnAction(ActionEvent e)
    {
        // amount and category field should not be empty
        if(!incomeAmount.getText().trim().isEmpty()) {
            int amount = Integer.parseInt(incomeAmount.getText());   //To convert getText's text into int
            if(amount <= 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Amount can't be less than 0");
                alert.show();
            }
            else{
                // Type =2 mean it is an Income
                addData(e, 2, Integer.parseInt(incomeAmount.getText()), incomeCategory.getValue(), incomeDate.getValue(), SignIn.id);
                // For changing scene to the main page
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
        else
        {
            // If all fields are not filled
            System.out.println("Please fill in all information!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all information to register");
            alert.show();
        }
    }
    public void addData(ActionEvent e, int type, int amount, String category, LocalDate date, int userID)
    {
        connection = connection();
        PreparedStatement psInsert=null;

        try {
            psInsert = connection.prepareStatement("INSERT INTO public.data (type,amount,category,date,\"userID\") VALUES (?,?,?,?,?)");
            psInsert.setInt(1, type);
            psInsert.setInt(2, amount);
            psInsert.setString(3, category);
            psInsert.setString(4, date+"");
            psInsert.setInt(5, userID);
            psInsert.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Your data added successfully");
            alert.show();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    public void i_cancelOnAction(ActionEvent e) throws Exception {
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

    @FXML
    public void onIDateClicked()
    {
        incomeDate.setDayCellFactory(param -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.compareTo(LocalDate.now()) > 0 );
            }
        });
    }
}
