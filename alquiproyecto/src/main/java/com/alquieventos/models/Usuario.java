package com.alquieventos.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String cedula;
    protected String nombreCompleto;
    protected String telefono;
    protected String email;
    protected String contrasena;
    protected List<Cupon> cupones;
    protected boolean activado;

    public Usuario(String cedula, String nombreCompleto, String telefono, String email, String contrasena, boolean activado) {
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
        this.activado = activado;
        this.cupones = new ArrayList<>();
    }

    public boolean isActivado() {
        return activado;
    }


    public void setActivado(boolean activado) {
        this.activado = activado;
    }


    public String getCedula() {
        return cedula;
    }


    public void setCedula(String cedula) {
        this.cedula = cedula;
    }


    public String getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getContrasena() {
        return contrasena;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public boolean iniciarSesion(String email, String contrasena) {
        return this.email.equals(email) && this.contrasena.equals(contrasena);
    }
    
}
