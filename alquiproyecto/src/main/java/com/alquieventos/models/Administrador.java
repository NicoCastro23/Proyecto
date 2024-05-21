package com.alquieventos.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Administrador extends User {
    private static Administrador instance;
    private String password;

    private Administrador(String id, String name, String phoneNumber, String email, String password) {
        this.password = password;
    }
    

    public static Administrador getInstance() {
        if (instance == null) {
            instance = new Administrador("admin", "Administrador", "123456789", "admin@unieventos.com", "admin123");
        }
        return instance;
    }

    public void gestionarEvento(UniEventos uniEventos, String nombre, String ciudad, String descripcion, TipoEvento tipo, String rutaImagen, LocalDate fecha, List<Localidad> localidades) {
        Evento evento = new Evento(nombre, ciudad, descripcion, tipo, rutaImagen, fecha, localidades);
        uniEventos.agregarEvento(evento);
    }   

    public String getPassword() {
        return password;
    }
}
