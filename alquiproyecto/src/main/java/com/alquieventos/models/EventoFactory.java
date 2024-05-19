package com.alquieventos.models;

import java.time.LocalDate;

public interface EventoFactory {
    Evento crearEvento(String nombre, String ciudad, String descripcion, String tipo, String imagen, LocalDate fecha, String direccion);
}
