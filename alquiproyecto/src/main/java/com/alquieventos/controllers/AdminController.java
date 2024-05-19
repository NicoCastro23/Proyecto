package com.alquieventos.controllers;

import com.alquieventos.MainApp;
import com.alquieventos.models.Evento;
import com.alquieventos.services.EventService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.List;

public class AdminController {
    private MainApp mainApp;
    private EventService eventService;

    @FXML
    private void initialize() {
        eventService = new EventService();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleLogout() {
        mainApp.showLoginView();
    }



    @FXML
    private void handleDeleteEvent() {
        List<Evento> selectedEvents = eventService.getAllEvents();
        if (selectedEvents != null && !selectedEvents.isEmpty()) {
            Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete the selected events?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    for (Evento event : selectedEvents) {
                        eventService.deleteEvent(event);
                    }
                }
            });
        }
    }
}

