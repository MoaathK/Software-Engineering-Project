package com.example.test123;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SignIn {

    @FXML
    private TextField username;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Button signInButton;
    @FXML
    private Button SignUpButton;
    @FXML
    private Label wrongEnter;

    public void signInOnAction(ActionEvent event){
        if (DBUtils.checkIfUserExist(username.getText(),userPassword.getText())){
            Main m = new Main();
            m.showHomeScene();
        }
        else {
            wrongEnter.setText("Username or password is wrong");
        }


    }
    public void signUpOnAction(ActionEvent event){
        Main m = new Main();
        m.showSignUpScene();
    }

}
