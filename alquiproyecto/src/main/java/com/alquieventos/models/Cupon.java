package com.alquieventos.models;

import java.time.LocalDate;

public class Cupon {
    private String codigo;
    private double porcentajeDescuento;
    private LocalDate fechaExpiracion;
    private Boolean redimido = false;

    public Cupon(String codigo, double porcentajeDescuento, LocalDate fechaExpiracion) {
        this.codigo = codigo;
        this.porcentajeDescuento = porcentajeDescuento;
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDate fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public void setRedimido(Boolean redimido) {
        this.redimido = redimido;
    }

    
}
