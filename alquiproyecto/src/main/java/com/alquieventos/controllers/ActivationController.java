package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Cliente;
import com.alquieventos.services.ClienteService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ActivationController {
    private MainApp mainApp;
    private ClienteService clienteService;
    private Cliente cliente;

    @FXML
    private TextField activationCodeField;

    @FXML
    private void initialize() {
        clienteService = new ClienteService();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void handleActivate() {
        String activationCode = activationCodeField.getText();
        if (cliente.getCodigoActivacion().equals(activationCode)) {
            cliente.setActivado(true);
            clienteService.updateCliente(cliente);
            Alert alert = new Alert(AlertType.INFORMATION, "Account activated successfully!", ButtonType.OK);
            alert.showAndWait();
            mainApp.showLoginView();
        } else {
            Alert alert = new Alert(AlertType.ERROR, "Invalid activation code. Please try again.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}

