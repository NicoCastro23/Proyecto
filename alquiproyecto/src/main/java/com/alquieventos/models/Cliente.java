package com.alquieventos.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cliente extends Usuario implements Observer {
    private List<Compra> compras;
    
    private String codigoActivacion;

    public Cliente(String cedula, String nombreCompleto, String telefono, String email, String contrasena, boolean activado) {
        super(cedula, nombreCompleto, telefono, email, contrasena, activado);
        this.compras = new ArrayList<>();
        this.activado = false;
        this.codigoActivacion = generarCodigoActivacion();
        enviarCodigoActivacion();
    }

    // Método para registrar un cliente
    public void registrarse() {
        // Lógica para registrar el cliente
        // Enviar código de activación por email
        enviarCodigoActivacion();
    }

    // Método para activar cuenta
    public boolean activarCuenta(String codigo) {
        if (this.codigoActivacion.equals(codigo)) {
            this.activado = true;
            return true;
        }
        return false;
    }

    // Método para generar un código de activación
    private String generarCodigoActivacion() {
        return UUID.randomUUID().toString();
    }

    // Método para enviar el código de activación por email
    private void enviarCodigoActivacion() {
        // Lógica para enviar el código de activación por correo electrónico
        System.out.println("Código de activación enviado a " + this.email + ": " + this.codigoActivacion);
    }

    // Método para realizar una compra
    public void realizarCompra(Evento evento, Localidad localidad, int cantidad, Cupon cupon) {
        if (localidad.getCapacidad() >= cantidad) {
            Compra compra = new Compra(this, evento, localidad, cantidad, cupon);
            compras.add(compra);
            localidad.reducirCapacidad(cantidad);
            evento.aumentarRecaudado(compra.getTotal());
            enviarConfirmacionCompra(compra);
        } else {
            System.out.println("No hay suficiente capacidad en la localidad seleccionada.");
        }
    }

    // Método para cancelar una compra
    public void cancelarCompra(Compra compra) {
        if (compras.contains(compra)) {
            compras.remove(compra);
            compra.getLocalidad().incrementarCapacidad(compra.getCantidad());
            // Lógica adicional para manejar la cancelación
        }
    }

    // Método para listar compras
    public List<Compra> listarCompras() {
        return compras;
    }

    // Método para redimir cupones
    public void redimirCupon(Cupon cupon) {
        // Lógica para redimir cupon
        cupon.setRedimido(true);
    }

    @Override
    public void actualizar(String mensaje) {
        // Lógica para manejar la notificación
        System.out.println("Notificación para " + nombreCompleto + ": " + mensaje);
    }

    // Método para enviar confirmación de compra por email
    private void enviarConfirmacionCompra(Compra compra) {
        // Lógica para enviar confirmación por correo electrónico
        System.out.println("Confirmación de compra enviada a " + this.email);
    }

    public boolean isActivado() {
        return activado;
    }

    public String getCodigoActivacion() {
        return codigoActivacion;
    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }
    
    
    
}