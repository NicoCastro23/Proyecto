package com.alquieventos;

import com.alquieventos.controllers.LoginController;
import com.alquieventos.controllers.RegisterController;
import com.alquieventos.models.Cliente;
import com.alquieventos.models.Compra;
import com.alquieventos.models.Evento;
import com.alquieventos.models.Usuario;
import com.alquieventos.services.AdministradorService;
import com.alquieventos.services.AuthService;
import com.alquieventos.services.ClienteService;
import com.alquieventos.services.CompraService;

import java.io.IOException;
import java.util.List;

import com.alquieventos.controllers.ActivationController;
import com.alquieventos.controllers.AdminController;
import com.alquieventos.controllers.CustomerController;
import com.alquieventos.controllers.EventController;
import com.alquieventos.controllers.PurchaseController;
import com.alquieventos.controllers.PurchasesController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    private Stage primaryStage;

    private ClienteService clienteService = new ClienteService();
    private AdministradorService administradorService = new AdministradorService();
    private CompraService compraService = new CompraService();
    private AuthService authService = new AuthService();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UniEventos");

        showLoginView();
    }

    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/LoginView.fxml"));
            GridPane loginLayout = loader.load();

            Scene scene = new Scene(loginLayout);
            primaryStage.setScene(scene);

            LoginController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showRegisterView() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/RegisterView.fxml"));
            AnchorPane registerLayout = loader.load();

            Scene scene = new Scene(registerLayout);
            primaryStage.setScene(scene);

            RegisterController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error al cargar la vista de registro", e.getMessage());
        }
    }

    public void showEventView() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/alquieventos/views/EventView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();

            EventController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error al cargar la vista de eventos", e.getMessage());
        }
    }

    public void showPurchaseView( ) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/alquieventos/views/PurchaseView.fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();

            PurchaseController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error al cargar la vista de compra", e.getMessage());
        }
    }

    public void showAdminView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/alquieventos/views/AdminView.fxml"));
            AnchorPane adminLayout = loader.load();

            Scene scene = new Scene(adminLayout);
            primaryStage.setScene(scene);

            AdminController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/com/alquieventos/views/CustomerView.fxml"));
            AnchorPane customerLayout = loader.load();

            Scene scene = new Scene(customerLayout);
            primaryStage.setScene(scene);

            CustomerController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showActivationView(Usuario cliente) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/ActivationView.fxml"));
            AnchorPane activationView = (AnchorPane) loader.load();

            Scene scene = new Scene(activationView);
            primaryStage.setScene(scene);
            primaryStage.show();

            ActivationController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Error al cargar la vista de activación", e.getMessage());
        }
    }

    private void showErrorDialog(String title, String message) {
        // Mostrar una alerta de error usando JavaFX
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showPurchasesView(List<Compra> purchases) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("views/PurchasesView.fxml"));
            AnchorPane purchasesLayout = loader.load();

            Scene scene = new Scene(purchasesLayout);
            primaryStage.setScene(scene);

            PurchasesController controller = loader.getController();
            controller.setMainApp(this);
            controller.setPurchases(purchases);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}