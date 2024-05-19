package com.alquieventos.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends Usuario {
    private static Administrador instance;
    private List<Observer> observers = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();

    private Administrador(String cedula, String nombreCompleto, String telefono, String email, String contrasena, boolean activado) {
        super(cedula, nombreCompleto, telefono, email, contrasena, activado);
        this.observers = new ArrayList<>();
        this.eventos = new ArrayList<>();
        this.activado = activado;
    }

    public static Administrador getInstance() {
        if (instance == null) {
            instance = new Administrador("admin", "Administrador", "123456789", "admin@unieventos.com", "admin123", true);
        }
        return instance;
    }

    // Método para gestionar eventos
    public void gestionarEventos(Evento evento, String accion) {
        switch (accion.toLowerCase()) {
            case "crear":
                eventos.add(evento);
                notificarObservers("Nuevo evento creado: " + evento.getNombre());
                break;
            case "modificar":
                // Lógica para modificar el evento
                break;
            case "buscar":
                // Lógica para buscar el evento
                break;
            case "listar":
                // Lógica para listar eventos
                break;
            case "eliminar":
                eventos.remove(evento);
                notificarObservers("Evento eliminado: " + evento.getNombre());
                break;
        }
    }

    // Método para crear cupones
    public Cupon crearCupon(String codigo, double porcentajeDescuento,  LocalDate fechaExpiracion) {
        Cupon cupon = new Cupon(codigo, porcentajeDescuento, fechaExpiracion);
        return cupon;
    }

    // Método para obtener datos estadísticos de un evento
    public void obtenerDatosEstadisticos(Evento evento) {
        double totalRecaudado = evento.getTotalRecaudado();
        for (Localidad localidad : evento.getLocalidades()) {
            double porcentajeVendido = (1 - (localidad.getCapacidad() / localidad.getCapacidadInicial())) * 100;
            System.out.println("Localidad: " + localidad.getNombre() + ", Porcentaje vendido: " + porcentajeVendido + "%");
        }
        System.out.println("Total recaudado por el evento: " + totalRecaudado);
    }

    // Método para listar eventos por acogida
    public List<Evento> listarEventosPorAcogida() {
        eventos.sort((e1, e2) -> Double.compare(e2.getTotalRecaudado(), e1.getTotalRecaudado()));
        return eventos;
    }

    // Métodos para gestionar observers
    public void agregarObserver(Observer observer) {
        observers.add(observer);
    }

    public void eliminarObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notificarObservers(String mensaje) {
        for (Observer observer : observers) {
            observer.actualizar(mensaje);
        }
    }
}
