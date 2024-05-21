package com.alquieventos.models;

import java.io.Serializable;

public class Localidad implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public void setCapacidadInicial(int capacidadInicial) {
        this.capacidadInicial = capacidadInicial;
    }

    

    
}
