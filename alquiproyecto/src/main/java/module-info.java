module com.alquieventos {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.zxing;
    requires com.google.zxing.javase;

    opens com.alquieventos to javafx.fxml;
    opens com.alquieventos.controllers to javafx.fxml;
    exports com.alquieventos;
    exports com.alquieventos.controllers;
    exports com.alquieventos.models;
    exports com.alquieventos.services;
}
