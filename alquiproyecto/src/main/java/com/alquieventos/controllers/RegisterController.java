package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Cliente;
import com.alquieventos.services.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {
    private MainApp mainApp;
    private ClienteService clienteService;

    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField idField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void initialize() {
        clienteService = new ClienteService();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleRegister() {
        String name = nameField.getText();
        String email = emailField.getText();
        String id = idField.getText();
        String phoneNumber = phoneField.getText();
        String password = passwordField.getText();
        


        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Please fill in all fields.", ButtonType.OK);
            alert.showAndWait();
        } else {
            Cliente newCliente = new Cliente(id, name, phoneNumber, email, password, false);
            clienteService.registerCliente(newCliente);
            Alert alert = new Alert(AlertType.INFORMATION, "Registration successful! Please check your email for the activation code.", ButtonType.OK);
            alert.showAndWait();
            mainApp.showLoginView();
        }
    }

    @FXML
    private void handleBack() {
        mainApp.showLoginView();
    }
}
