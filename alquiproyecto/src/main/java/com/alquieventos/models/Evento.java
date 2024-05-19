package com.alquieventos.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Evento implements Serializable {
    private String nombre;
    private String ciudad;
    private String descripcion;
    private String tipo;
    private String imagen;
    private Date fecha;
    private String direccion;
    private List<Localidad> localidades;
    private double totalRecaudado;

    public Evento(String nombre, String ciudad, String descripcion, String tipo, String imagen, Date fecha, String direccion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.imagen = imagen;
        this.fecha = fecha;
        this.direccion = direccion;
        this.localidades = new LinkedList<>();
        this.totalRecaudado = 0;
    }

    public void agregarLocalidad(Localidad localidad) {
        localidades.add(localidad);
    }

    public int getCapacidadDisponible() {
        return localidades.stream().mapToInt(Localidad::getCapacidad).sum();
    }
    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public void aumentarRecaudado(double cantidad) {
        totalRecaudado += cantidad;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List< Localidad> getLocalidades() {
        return localidades;
    }

    public Localidad getLocalidadByName(String nombre) {
        for (Localidad localidad : localidades) {
            if (localidad.getNombre().equals(nombre)) {
                return localidad;
            }
        }
        return null;
    }
    
} 
