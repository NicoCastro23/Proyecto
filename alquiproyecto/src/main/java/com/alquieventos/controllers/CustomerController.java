package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Cliente;
import com.alquieventos.models.Compra;
import com.alquieventos.services.CompraService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.List;

public class CustomerController {
    private MainApp mainApp;
    private CompraService compraService;
    private Cliente cliente;

    @FXML
    private void initialize() {
        compraService = new CompraService();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void handleLogout() {
        mainApp.showLoginView();
    }

    @FXML
    private void handleViewPurchases() {
        List<Compra> purchases = compraService.getPurchasesByCliente(cliente);
        if (purchases != null && !purchases.isEmpty()) {
            mainApp.showPurchasesView(purchases);
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "No purchases found.", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleBuyTickets() {
        
    }
}

