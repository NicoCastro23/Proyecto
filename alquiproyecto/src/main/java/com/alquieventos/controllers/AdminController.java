package com.alquieventos.controllers;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.alquieventos.models.Administrador;
import com.alquieventos.models.Evento;
import com.alquieventos.models.Localidad;
import com.alquieventos.models.TipoEvento;
import com.alquieventos.models.UniEventos;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminController extends GridPane {
    public AdminController(UniEventos uniEventos, Stage stage, Runnable onSuccess) {
        setPadding(new Insets(10));
        setHgap(10);
        setVgap(10);
        setAlignment(Pos.CENTER);

        Label lblNombre = new Label("Nombre:");
        TextField txtNombre = new TextField();
        add(lblNombre, 0, 0);
        add(txtNombre, 1, 0);

        Label lblCiudad = new Label("Ciudad:");
        TextField txtCiudad = new TextField();
        add(lblCiudad, 0, 1);
        add(txtCiudad, 1, 1);

        Label lblDescripcion = new Label("Descripción:");
        TextField txtDescripcion = new TextField();
        add(lblDescripcion, 0, 2);
        add(txtDescripcion, 1, 2);

        Label lblTipo = new Label("Tipo:");
        ComboBox<TipoEvento> cbTipo = new ComboBox<>();
        cbTipo.getItems().setAll(TipoEvento.values());
        add(lblTipo, 0, 3);
        add(cbTipo, 1, 3);

        Label lblFecha = new Label("Fecha:");
        DatePicker datePicker = new DatePicker();
        add(lblFecha, 0, 4);
        add(datePicker, 1, 4);

        Label lblImagen = new Label("Imagen:");
        Button btnSeleccionarImagen = new Button("Seleccionar Imagen");
        FileChooser fileChooser = new FileChooser();
        final File[] imagenFile = new File[1];
        btnSeleccionarImagen.setOnAction(e -> {
            imagenFile[0] = fileChooser.showOpenDialog(stage);
        });
        add(lblImagen, 0, 6);
        add(btnSeleccionarImagen, 1, 6);

        Button btnCrear = new Button("Crear Evento");
        btnCrear.setOnAction(e -> {
            try {
                String nombre = txtNombre.getText();
                String ciudad = txtCiudad.getText();
                String descripcion = txtDescripcion.getText();
                TipoEvento tipo = cbTipo.getValue();
                LocalDate fecha = datePicker.getValue();

                String rutaImagen = imagenFile[0].getAbsolutePath();

                Administrador admin = (Administrador) uniEventos.getUsuarioActual();
                admin.gestionarEvento(uniEventos, nombre, ciudad, descripcion, tipo, rutaImagen, fecha,
                        null);

                showLocalidadesWindow(uniEventos, stage, nombre, ciudad, fecha, onSuccess);
            } catch (Exception ex) {
                ex.printStackTrace();
                showError("Error al crear el evento. Verifique los datos ingresados.");
            }
        });
        add(btnCrear, 1, 7);
    }

    private void showLocalidadesWindow(UniEventos uniEventos, Stage stage, String nombre, String ciudad,
            LocalDate fecha, Runnable onSuccess) {
        Stage localidadStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label lblNumLocalidades = new Label("Número de Localidades (1-5):");
        TextField txtNumLocalidades = new TextField();
        gridPane.add(lblNumLocalidades, 0, 0);
        gridPane.add(txtNumLocalidades, 1, 0);

        Button btnSiguiente = new Button("Siguiente");
        btnSiguiente.setOnAction(e -> {
            int numLocalidades = Integer.parseInt(txtNumLocalidades.getText());
            showLocalidadesDetailWindow(uniEventos, localidadStage, nombre, ciudad, fecha, numLocalidades, onSuccess);
        });
        gridPane.add(btnSiguiente, 1, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        localidadStage.setScene(scene);
        localidadStage.setTitle("Seleccionar Localidades");
        localidadStage.show();
    }

    private void showLocalidadesDetailWindow(UniEventos uniEventos, Stage stage, String nombre, String ciudad,
            LocalDate fecha, int numLocalidades, Runnable onSuccess) {
        Stage detailStage = new Stage();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        List<TextField> nombres = new ArrayList<>();
        List<TextField> precios = new ArrayList<>();
        List<TextField> capacidades = new ArrayList<>();

        for (int i = 0; i < numLocalidades; i++) {
            Label lblNombre = new Label("Nombre Localidad " + (i + 1) + ":");
            TextField txtNombre = new TextField();
            gridPane.add(lblNombre, 0, i * 3);
            gridPane.add(txtNombre, 1, i * 3);
            nombres.add(txtNombre);

            Label lblPrecio = new Label("Precio Localidad " + (i + 1) + ":");
            TextField txtPrecio = new TextField();
            gridPane.add(lblPrecio, 0, i * 3 + 1);
            gridPane.add(txtPrecio, 1, i * 3 + 1);
            precios.add(txtPrecio);

            Label lblCapacidad = new Label("Capacidad Localidad " + (i + 1) + ":");
            TextField txtCapacidad = new TextField();
            gridPane.add(lblCapacidad, 0, i * 3 + 2);
            gridPane.add(txtCapacidad, 1, i * 3 + 2);
            capacidades.add(txtCapacidad);
        }

        Button btnGuardar = new Button("Guardar Localidades");
        btnGuardar.setOnAction(e -> {
            List<Localidad> localidades = new ArrayList<>();
            for (int i = 0; i < numLocalidades; i++) {
                String nombreLocalidad = nombres.get(i).getText();
                double precio = Double.parseDouble(precios.get(i).getText());
                int capacidadMaxima = Integer.parseInt(capacidades.get(i).getText());
                localidades.add(new Localidad(nombreLocalidad, precio, capacidadMaxima));
            }

            Evento evento = uniEventos.buscarEventoPorNombreCiudadFecha(nombre, ciudad, fecha);
            if (evento != null) {
                evento.setLocalidades(localidades);
            } else {
                showError("No se encontró el evento.");
            }

            stage.close();
            detailStage.close();
            onSuccess.run();
        });
        gridPane.add(btnGuardar, 1, numLocalidades * 3);

        Scene scene = new Scene(gridPane, 600, 400);
        detailStage.setScene(scene);
        detailStage.setTitle("Detalles de Localidades");
        detailStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
