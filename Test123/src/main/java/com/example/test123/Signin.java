package com.example.test123;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Signin {


    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button signIn;
    @FXML
    private Label wringMessage;


    public void getData(ActionEvent event){
        String user = username.getText();
        if ( JavaPostgreSql.getFromDatabase(username.getText(),password.getText())){
            try {

                Parent root = FXMLLoader.load(getClass().getResource("homePage.fxml"));
                Stage stage = (Stage) ((Node) event.getSource() ).getScene().getWindow();
                Scene s = new Scene(root);
                stage.setScene(s);
                stage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        else {
            wringMessage.setText("Wrong username or password");
        }
    }



}
