package com.alquieventos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.alquieventos.models.Administrador;
import com.alquieventos.models.Localidad;
import com.alquieventos.models.TipoEvento;
import com.alquieventos.models.UniEventos;

public class AdninistradorApp {

    public static void main(String[] args) {
        UniEventos uniEventos = new UniEventos();
        Scanner scanner = new Scanner(System.in);

        // Pedir al administrador que inicie sesión
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Ingrese sus credenciales de administrador para iniciar sesión:");
            System.out.print("Email: ");
            String email = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            if (uniEventos.login(email, password) && uniEventos.getUsuarioActual() instanceof Administrador) {
                loggedIn = true;
                System.out.println("Login exitoso. Bienvenido, Administrador!");
            } else {
                System.out.println("Email o contraseña incorrecta. Intente nuevamente.");
            }
        }

        while (true) {
            System.out.println("1. Crear evento");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            if (opcion == 1) {
                System.out.println("Ingrese los siguientes datos para crear un evento:");
                System.out.print("Nombre: ");
                String nombreEvento = scanner.nextLine();
                System.out.print("Ciudad: ");
                String ciudad = scanner.nextLine();
                System.out.print("Descripción: ");
                String descripcion = scanner.nextLine();
                System.out.print(
                        "Tipo (FESTIVAL, TEATRO, POLITICO, UNIVERSITARIO, CULTURAL, DEPORTIVO, RELIGIOSO, OTRO): ");
                TipoEvento tipo = TipoEvento.valueOf(scanner.nextLine().toUpperCase());
                System.out.print("Ruta de la imagen: ");
                String rutaImagen = scanner.nextLine();
                System.out.print("Fecha (AAAA-MM-DD): ");
                String fechaStr = scanner.nextLine();
                LocalDate fecha = LocalDate.parse(fechaStr);

                List<Localidad> localidades = new ArrayList<>();
                while (true) {
                    System.out.println("Ingrese los datos de la localidad:");
                    System.out.print("Nombre: ");
                    String nombreLocalidad = scanner.nextLine();
                    System.out.print("Capacidad: ");
                    int capacidadMaxima = scanner.nextInt();
                    System.out.print("Precio: ");
                    double precio = scanner.nextDouble();
                    scanner.nextLine(); // Consumir el salto de línea

                    localidades.add(new Localidad(nombreLocalidad, precio, capacidadMaxima));

                    System.out.print("¿Desea agregar otra localidad? (si/no): ");
                    String respuesta = scanner.nextLine();
                    if (!respuesta.equalsIgnoreCase("si")) {
                        break;
                    }
                }

                Administrador admin = (Administrador) uniEventos.getUsuarioActual();
                admin.gestionarEvento(uniEventos, nombreEvento, ciudad, descripcion, tipo, rutaImagen, fecha,
                        localidades);
            } else if (opcion == 2) {
                System.out.println("Saliendo del programa...");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
