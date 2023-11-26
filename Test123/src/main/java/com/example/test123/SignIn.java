package com.example.test123;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignIn {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/userInformation";
    private static final String DATABASE_USER = "";
    private static final String DATABASE_PASSWORD = " ";
    static int id;
    @FXML
    private Label refmsgLable;
    @FXML
    private Button loginCancel;
    @FXML
    private TextField  l_usernameField;
    @FXML
    private PasswordField l_passwordField;
    @FXML
    private Button signinCancel;
    @FXML
    private TextField s_usernameField;
    @FXML
    private PasswordField s_passwordField;

    Stage stage;
    Scene scene;
    Parent root;

    public void logincancelOnAction(ActionEvent e){
        System.exit(0);
    }
    public void regcancelOnAction(ActionEvent e){
        System.exit(0);
    }

    public void r_ButtonOnAction(ActionEvent e) throws IOException {
        try {
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println("Can't load");
        }
    }

    public void regButtonOnAction(ActionEvent e) {
        if (!s_usernameField.getText().trim().isEmpty() && !s_passwordField.getText().trim().isEmpty())
        {
            registerUser(e, s_passwordField.getText(), s_passwordField.getText());
        }
        else {
            System.out.println("Please fill in all information!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill all information to register");
            alert.show();
        }
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


    public void registerUser(ActionEvent e, String username, String password) {
        connection = connection();
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUser = null; // For checking if already this user exists or not
        ResultSet resultSet = null; // For storing query's result

        try {
            psCheckUser = connection.prepareStatement("SELECT * FROM expense_tracker.register WHERE username = ?");
            psCheckUser.setString(1, username);
            resultSet = psCheckUser.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this usename");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO expense_tracker.register (username,password) VALUES (?,?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("You register successfully!!");
                alert.show();
                try {
                    root = FXMLLoader.load(getClass().getResource("Sign-in.fxml"));
                    stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println("Can't load");
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }




        // this method login the user
        public void loginButtonOnAction(ActionEvent e) throws Exception {
            loginUser(e, l_usernameField.getText(), l_passwordField.getText());
        }
        public void loginUser(ActionEvent e, String username, String password) throws IOException{
            connection = connection();
            PreparedStatement psCheckUser = null;
            ResultSet resultSet = null;

            try {
                psCheckUser = connection.prepareStatement("SELECT * FROM expense_tracker.register WHERE username = ? and password = ?");
                psCheckUser.setString(1, username);
                psCheckUser.setString(2, password);
                resultSet = psCheckUser.executeQuery();

                if (resultSet.next()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("You logged in successfully!!");
                    alert.show();
                    SignIn.id = resultSet.getInt("userID");
                    //Changing scene to main page
                    try {
                        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    catch (Exception ex) {
                        System.out.println("Can't load");
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid username or password");
                    alert.show();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }





