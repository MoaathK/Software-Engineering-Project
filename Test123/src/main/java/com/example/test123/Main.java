package com.example.test123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setTitle("WhereItGoes");
            primaryStage.setScene(new Scene(root, 520, 400));
            primaryStage.show();
            primaryStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        launch();
    }
}