package com.example.test123;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primaryStage) {
        this.stg = primaryStage;

    }
    public void showLoginScene(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Sign-in.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stg.setTitle("NewApp");
            stg.setScene(scene);
            stg.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void showSignUpScene() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stg.setTitle("NewApp");
            stg.setScene(scene);
            stg.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showHomeScene(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Sign-in.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HomePage home = new HomePage();
            home.populationPieChart();
            stg.setTitle("NewApp");
            stg.setScene(scene);
            stg.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        launch();
    }
}