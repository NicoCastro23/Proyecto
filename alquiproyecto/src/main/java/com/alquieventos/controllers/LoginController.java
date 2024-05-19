package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Administrador;
import com.alquieventos.models.Cliente;
import com.alquieventos.services.AuthService;
import com.alquieventos.services.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    private MainApp mainApp;
    private AuthService authService;
    private ClienteService clienteService;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            Object user = authService.login(email, password);
            if (user instanceof Cliente) {
                Cliente cliente = (Cliente) user;
                if (!cliente.isActivado()) {
                    mainApp.showActivationView(cliente);
                } else {
                    // Mostrar vista de cliente
                }
            } else if (user instanceof Administrador) {
                // Mostrar vista de administrador
                mainApp.showAdminView();
            }
        } catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRegister() {
        mainApp.showRegisterView();
    }

    @FXML
    private void handleAdminRegister() {
        mainApp.showAdminRegisterView();
    }
}
