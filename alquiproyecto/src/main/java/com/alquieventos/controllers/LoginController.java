package com.alquieventos.controllers;

import java.io.IOException;

import com.alquieventos.models.UniEventos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class LoginController {

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtContrasena;

    private UniEventos uniEventos = new UniEventos();

    @FXML
    private void onLogin(ActionEvent event) {
        String identificacion = txtIdentificacion.getText();
        String contrasena = txtContrasena.getText();

        if (uniEventos.login(identificacion, contrasena)) {
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Principal.fxml")));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Show error message
        }
    }
}

