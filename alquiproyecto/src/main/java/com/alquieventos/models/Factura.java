package com.alquieventos.models;


import java.io.Serializable;
import java.time.LocalDate;



public class Factura implements Serializable{
    private static final long serialVersionUID = 1L;

    private double subtotal;
    private double total;
    private Cliente cliente;
    private Evento evento;
    private Localidad localidad;
    private boolean usoCupon;
    private String codigo;
    private LocalDate fecha;

    public Factura(double subtotal, double total, Cliente cliente, Evento evento, Localidad localidad, boolean usoCupon,
            String codigo) {
        this.subtotal = subtotal;
        this.total = total;
        this.cliente = cliente;
        this.evento = evento;
        this.localidad = localidad;
        this.usoCupon = usoCupon;
        this.codigo = codigo;
        this.fecha = LocalDate.now();
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Evento getEvento() {
        return evento;
    }
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    public Localidad getLocalidad() {
        return localidad;
    }
    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }
    public boolean isUsoCupon() {
        return usoCupon;
    }
    public void setUsoCupon(boolean usoCupon) {
        this.usoCupon = usoCupon;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    

}
