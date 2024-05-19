package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Evento;
import com.alquieventos.services.EventService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.List;


public class EventController {
    private MainApp mainApp;
    private EventService eventService;

    @FXML
    private ListView<String> eventListView;

    public EventController() {
        eventService = new EventService();
    }

    @FXML
    private void initialize() {
        List<Evento> eventos = eventService.getAllEvents();
        for (Evento evento : eventos) {
            eventListView.getItems().add(evento.getNombre());
        }
    }

    @FXML
    private void handleEventSelection(MouseEvent event) throws IOException {
        String selectedEvent = eventListView.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            Evento evento = eventService.findEventByName(selectedEvent);
            mainApp.showPurchaseView();
        } else {
            showAlert("Selección inválida", "Por favor seleccione un evento.");
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
