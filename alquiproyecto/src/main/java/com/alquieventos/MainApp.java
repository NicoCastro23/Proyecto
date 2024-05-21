package com.alquieventos;

import com.alquieventos.controllers.*;
import com.alquieventos.models.Cliente;
import com.alquieventos.services.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Alquieventos");
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Inicio.fxml"))));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

