package com.alquieventos.models;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import com.google.zxing.WriterException;


public class Factura {
    private String codigo;
    private double subtotal;
    private double total;
    private Date fecha;
    private Cliente cliente;
    private Evento evento;
    private Localidad localidad;
    private int cantidad;
    private Cupon cupon;

    public Factura(Cliente cliente, Evento evento, Localidad localidad, int cantidad, Cupon cupon) {
        this.codigo = UUID.randomUUID().toString();
        this.subtotal = localidad.getPrecio() * cantidad;
        if (cupon != null) {
            if (cupon.getPorcentajeDescuento() == 15) {
                this.total = subtotal * 0.85;
            } else if (cupon.getPorcentajeDescuento() == 10) {
                this.total = subtotal * 0.90;
            }
        } else {
            this.total = subtotal;
        }
        this.fecha = new Date();
        this.cliente = cliente;
        this.evento = evento;
        this.localidad = localidad;
        this.cantidad = cantidad;
        this.cupon = cupon;
    }

    public void generarCodigoQR() throws WriterException {
        String rutaArchivo = "qrcodes/" + codigo + ".png";
        try {
            QrCodeGenerator.generarQRCode(codigo, rutaArchivo, 350, 350);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public String getCodigo() { 
        return codigo; 
    }

    public void setCodigo(String codigo) { 
        this.codigo = codigo; 
    }

    public double getSubtotal() { 
        return subtotal; 
    }

    public void setSubtotal(double subtotal) { 
        this.subtotal = subtotal; 
    }

    public double getTotal() { return total; }

    public void setTotal(double total) { this.total = total; }

    public Date getFecha() { return fecha; }
    
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public Localidad getLocalidad() { return localidad; }
    public void setLocalidad(Localidad localidad) { this.localidad = localidad; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Cupon getCupon() { return cupon; }
    public void setCupon(Cupon cupon) { this.cupon = cupon; }
}
