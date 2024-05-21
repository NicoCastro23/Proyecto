package com.alquieventos.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrincipalController {

    @FXML
    private void onAgregarEvento(ActionEvent event) {
        // Show the Add Event view
    }

    @FXML
    private void onListarEventos(ActionEvent event) {
        // Show the List Events view
    }

    @FXML
    private void onCerrarSesion(ActionEvent event) {
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Inicio.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

