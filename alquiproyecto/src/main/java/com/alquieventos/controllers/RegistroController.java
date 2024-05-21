package com.alquieventos.controllers;


import java.io.IOException;

import com.alquieventos.models.UniEventos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistroController {

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtContrasena;

    private UniEventos uniEventos = new UniEventos();

    @FXML
    private void onRegister(ActionEvent event) {
        String identificacion = txtIdentificacion.getText();
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String contrasena = txtContrasena.getText();

        uniEventos.registerClient(identificacion, nombre, telefono, email, contrasena);

        // Return to inicio scene
        try {
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/alquieventos/views/Inicio.fxml")));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

