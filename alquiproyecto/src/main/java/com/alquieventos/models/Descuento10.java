package com.alquieventos.models;

public class Descuento10 implements Descuento {
    
    @Override
    public double aplicarDescuento(double monto) {
        return monto * 0.90;
    }
}
