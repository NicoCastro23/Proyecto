package com.alquieventos.controllers;

import com.alquieventos.models.UniEventos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminMainController extends GridPane {
    public AdminMainController(UniEventos uniEventos, Stage stage, Runnable onCrearEvento, Runnable onCrearCupon,
            Runnable onVerGraficos) {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        Button btnCrearEvento = new Button("Crear Evento");
        btnCrearEvento.setOnAction(e -> onCrearEvento.run());
        add(btnCrearEvento, 0, 0);

        Button btnCrearCupon = new Button("Crear Cupones");
        btnCrearCupon.setOnAction(e -> onCrearCupon.run());
        add(btnCrearCupon, 1, 0);

        Button btnVerGraficos = new Button("Ver Gráficos");
        btnVerGraficos.setOnAction(e -> showStatisticsWindow(uniEventos));
        add(btnVerGraficos, 2, 0);
    }

    private void showStatisticsWindow(UniEventos uniEventos) {
        Stage stage = new Stage();
        ViewChartsControlller viewChartsPane = new ViewChartsControlller(uniEventos, stage);
        Scene scene = new Scene(viewChartsPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Estadísticas de Eventos");
        stage.show();
    }
}
