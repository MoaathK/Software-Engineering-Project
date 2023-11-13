package com.example.test123;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.test123.JavaPostgreSql.writeToDatabase;

public class Controller {

    @FXML
    private Button submitButton;

    @FXML
    private TextField  email;
    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    public void getData(ActionEvent event) {
        System.out.println(name.getText());
        System.out.println(email.getText());
        System.out.println(password.getText());
        writeToDatabase( name.getText(),email.getText(),password.getText());

    }




}
