package com.example.test123;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUp {

    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button SignUpButton;

    public void SignUpButtonOnAction(ActionEvent event){
        DBUtils.writeToDatabase(username.getText(),name.getText(),userPassword.getText());
        Main m = new Main();
        m.showLoginScene();
    }
}
