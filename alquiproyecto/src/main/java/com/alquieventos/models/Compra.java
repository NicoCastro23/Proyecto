package com.alquieventos.models;

public class Compra {
    private Cliente cliente;
    private Evento evento;
    private Localidad localidad;
    private int cantidad;
    private Cupon cupon;
    private Factura factura;
    private Descuento descuento;

    public Compra(Cliente cliente, Evento evento, Localidad localidad, int cantidad, Cupon cupon) {
        this.cliente = cliente;
        this.evento = evento;
        this.localidad = localidad;
        this.cantidad = cantidad;
        this.cupon = cupon;
        if (cupon != null) {
            if (cupon.getPorcentajeDescuento() == 15) {
                this.descuento = new Descuento15();
            } else if (cupon.getPorcentajeDescuento() == 10) {
                this.descuento = new Descuento10();
            }
        }
        this.factura = new Factura(cliente, evento, localidad, cantidad, this.cupon);
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cupon getCupon() {
        return cupon;
    }

    public void setCupon(Cupon cupon) {
        this.cupon = cupon;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public double getTotal() {
        return factura.getTotal();
    }

    
}
