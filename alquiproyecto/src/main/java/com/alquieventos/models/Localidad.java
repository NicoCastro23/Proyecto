package com.alquieventos.models;

import java.io.Serializable;

public class Localidad implements Serializable {
    private String nombre;
    private double precio;
    private int capacidad;
    private int capacidadInicial;

    public Localidad(String nombre, double precio, int capacidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.capacidad = capacidad;
        this.capacidadInicial = capacidad;
    }

    public void reducirCapacidad(int cantidad) {
        if (capacidad >= cantidad) {
            capacidad -= cantidad;
        }
    }

    public void incrementarCapacidad(int cantidad) {
        capacidad += cantidad;
    }

    // Getters y Setters
    public String getNombre() { 
        return nombre;
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public double getPrecio() { 
        return precio; 
    }

    public void setPrecio(double precio) { 
        this.precio = precio; 
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidadInicial() {
        return capacidadInicial;
    }

    
}
