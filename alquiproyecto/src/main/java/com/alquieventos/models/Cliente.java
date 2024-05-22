package com.alquieventos.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cliente extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String password;
    private boolean isVerified;
    private String verficationCode;
    private boolean compra;
    private Map<String, Double> codigosDescuento;

    public Cliente(String id, String name, String phoneNumber, String email, String password) {
        this.id = id;
        this.password = password;
        this.isVerified = false;
        this.verficationCode = GeneradorCodigo.generarCodigo();
        this.compra = false;
        this.codigosDescuento = new HashMap<>();
    }

    public boolean isCompra() {
        return compra;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerficationCode() {
        return verficationCode;
    }

    public void setVerficationCode(String verficationCode) {
        this.verficationCode = verficationCode;
    }

    public void setCompra(boolean compra) {
        this.compra = compra;
    }

    public double obtenerPorcentajeDescuento(String codigo) {
        return codigosDescuento.getOrDefault(codigo, 0.0);
    }

    public void eliminarCodigoDescuento(String codigo) {
        codigosDescuento.remove(codigo);
    }

    public void agregarCodigoDescuento(String codigo, double porcentaje) {
        codigosDescuento.put(codigo, porcentaje);
    }

    public boolean validarCodigoDescuento(String codigo) {
        return codigosDescuento.containsKey(codigo);
    }

}