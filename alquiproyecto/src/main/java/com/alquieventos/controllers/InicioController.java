package com.alquieventos.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private void onCrearCuenta(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Registro.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogin(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Login.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
