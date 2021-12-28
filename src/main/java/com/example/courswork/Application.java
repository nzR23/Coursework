package com.example.courswork;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("loginMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("TrainManager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void SwitchScene(String str, Button but){
        but.getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(str));

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("TrainManager");
        stage.setScene(new Scene(fxmlLoader.getRoot()));
        stage.setResizable(false);
        stage.show();
    }
}