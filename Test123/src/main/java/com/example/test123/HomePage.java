package com.example.test123;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    private Label HomePageWelcome;
    @FXML
    private Button buttonLogout;

    public void initialize(URL location, ResourceBundle resource){

        buttonLogout.setActionCommand(new EventHandler<ActionEvent> {

        }

    }




}
