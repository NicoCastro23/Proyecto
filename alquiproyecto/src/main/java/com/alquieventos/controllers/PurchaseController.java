package com.alquieventos.controllers;

import java.io.IOException;
import java.util.List;

import com.alquieventos.MainApp;
import com.alquieventos.models.Cliente;
import com.alquieventos.models.Compra;
import com.alquieventos.models.Cupon;
import com.alquieventos.models.Evento;
import com.alquieventos.models.Localidad;
import com.alquieventos.services.PurchaseService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PurchaseController {
    private Cliente cliente;
    private MainApp mainApp;
    private PurchaseService purchaseService;
    private Evento selectedEvent;

    @FXML
    private ComboBox<String> localidadComboBox;
    @FXML
    private TextField cantidadField;
    @FXML
    private TextField cuponField;

    public PurchaseController() {
        purchaseService = new PurchaseService();
    }

    @FXML
    private void initialize() {
    }

    public void setEvento(Evento evento) {
        this.selectedEvent = evento;
        List<Localidad> localidades = evento.getLocalidades();
        for (Localidad localidad : localidades) {
            localidadComboBox.getItems().add(localidad.getNombre());
        }
    }

    @FXML
    private void handlePurchase() throws IOException {
        String localidadNombre = localidadComboBox.getSelectionModel().getSelectedItem();
        String cantidadStr = cantidadField.getText();
        String cuponCodigo = cuponField.getText();

        if (localidadNombre != null && !cantidadStr.isEmpty()) {
            int cantidad = Integer.parseInt(cantidadStr);
            Localidad localidad = selectedEvent.getLocalidadByName(localidadNombre);
            Cupon cupon = null;

            if (!cuponCodigo.isEmpty()) {
                cupon = purchaseService.getCuponByCodigo(cuponCodigo);
                if (cupon == null) {
                    showAlert("Cupón inválido", "El código de cupón ingresado no es válido.");
                    return;
                }
            }

            Compra compra = purchaseService.createCompra(cliente, selectedEvent, localidad, cantidad, cupon);
            if (compra != null) {
                showAlert("Compra exitosa", "La compra ha sido realizada exitosamente.");
                mainApp.showEventView();
            } else {
                showAlert("Error de compra", "No hay suficientes entradas disponibles.");
            }
        } else {
            showAlert("Campos incompletos", "Por favor complete todos los campos.");
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
