package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Compra;
import com.alquieventos.models.Evento;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class PurchasesController {
    @FXML
    private TableView<Compra> purchasesTable;
    @FXML
    private TableColumn<Compra, Evento> eventColumn;
    @FXML
    private TableColumn<Compra, String> dateColumn;
    @FXML
    private TableColumn<Compra, String> totalColumn;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        // Inicializar las columnas de la tabla, asumiendo que Compra tiene métodos getEventName, getDate y getTotal.
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setPurchases(List<Compra> purchases) {
        purchasesTable.getItems().setAll(purchases);
    }

    @FXML
    private void handleBack() {
        mainApp.showCustomerView();
    }
}

