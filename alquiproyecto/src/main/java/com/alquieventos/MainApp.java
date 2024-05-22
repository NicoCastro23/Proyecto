package com.alquieventos;

import com.alquieventos.controllers.AdminController;
import com.alquieventos.controllers.AdminMainController;
import com.alquieventos.controllers.CouponController;
import com.alquieventos.controllers.LoginController;
import com.alquieventos.controllers.PostLoginController;
import com.alquieventos.controllers.RegistroController;
import com.alquieventos.controllers.VerificationController;
import com.alquieventos.controllers.ViewChartsControlller;
import com.alquieventos.models.UniEventos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private UniEventos uniEventos;

    @Override
    public void start(Stage primaryStage) {
        uniEventos = new UniEventos();

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        Button btnRegistrarse = new Button("Registrarse");
        btnRegistrarse.setOnAction(e -> showRegisterWindow(primaryStage));
        gridPane.add(btnRegistrarse, 1, 1);

        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setOnAction(e -> showLoginWindow(primaryStage));
        gridPane.add(btnLogin, 0, 1);

        Button btnVerificarse = new Button("Verificarse");
        btnVerificarse.setOnAction(e -> showVerificationWindow(primaryStage));
        gridPane.add(btnVerificarse, 1, 2);

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> primaryStage.close());
        gridPane.add(btnSalir, 0, 2);

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inicio");
        primaryStage.show();
    }

    private void showRegisterWindow(Stage primaryStage) {
        Stage stage = new Stage();
        RegistroController registerPane = new RegistroController(uniEventos, stage);
        Scene scene = new Scene(registerPane, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Registrarse");
        stage.show();
    }

    private void showLoginWindow(Stage primaryStage) {
        Stage stage = new Stage();
        LoginController loginPane = new LoginController(uniEventos, stage, () -> showPostLoginPage(primaryStage),
                () -> showAdminMainPage(primaryStage));
        Scene scene = new Scene(loginPane, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Iniciar Sesión");
        stage.show();
    }

    private void showPostLoginPage(Stage primaryStage) {
        Stage stage = new Stage();
        PostLoginController postLoginPage = new PostLoginController(uniEventos, stage);
        Scene scene = new Scene(postLoginPage, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Bienvenido");
        stage.show();
    }

    private void showAdminMainPage(Stage primaryStage) {
        Stage stage = new Stage();
        AdminMainController adminMainPane = new AdminMainController(uniEventos, stage, () -> showAdminPane(stage),
                () -> showCreateCouponPane(stage), () -> showViewChartsPane(stage));
        Scene scene = new Scene(adminMainPane, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Panel del Administrador");
        stage.show();
    }

    private void showAdminPane(Stage primaryStage) {
        Stage stage = new Stage();
        AdminController adminPane = new AdminController(uniEventos, stage, () -> showAdminMainPage(primaryStage));
        Scene scene = new Scene(adminPane, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Crear Evento");
        stage.show();
    }

    private void showCreateCouponPane(Stage primaryStage) {
        Stage stage = new Stage();
        CouponController createCouponPane = new CouponController(uniEventos, stage);
        Scene scene = new Scene(createCouponPane, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Crear Cupón");
        stage.show();
    }

    private void showVerificationWindow(Stage primaryStage) {
        Stage stage = new Stage();
        VerificationController verificationPane = new VerificationController(uniEventos, stage);
        Scene scene = new Scene(verificationPane, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Verificarse");
        stage.show();
    }

    private void showViewChartsPane(Stage primaryStage) {
        Stage stage = new Stage();
        ViewChartsControlller viewChartsPane = new ViewChartsControlller(uniEventos, stage);
        Scene scene = new Scene(viewChartsPane, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Estadísticas de Eventos");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
