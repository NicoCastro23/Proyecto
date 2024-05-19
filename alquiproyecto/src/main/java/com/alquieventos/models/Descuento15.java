package com.alquieventos.models;

public class Descuento15 implements Descuento{
    
    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.85;
    }
}
