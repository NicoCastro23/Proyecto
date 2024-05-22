package com.alquieventos.models;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import java.util.List;

import javafx.scene.image.Image;

public class Evento implements Serializable {
    private String nombre;
    private String ciudad;
    private String descripcion;
    private TipoEvento tipo;
    private String imagenLocation;
    private LocalDate fecha;
    private List<Localidad> localidades;

    public Evento(String nombre, String ciudad, String descripcion, TipoEvento tipo, String imagenLocation,
            LocalDate fecha,
            List<Localidad> localidades) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagenLocation = imagenLocation;
        this.fecha = fecha;
        this.localidades = localidades;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEvento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Localidad> getLocalidades() {
        return localidades;
    }

    public void setLocalidades(List<Localidad> localidades) {
        this.localidades = localidades;
    }

    public Image getImagen() {
        if (imagenLocation != null && !imagenLocation.isEmpty()) {
            File file = new File(imagenLocation);
            if (file.exists()) {
                return new Image(file.toURI().toString());
            }
        }
        return null;
    }

    public String getImagenLocation() {
        return imagenLocation;
    }

}
