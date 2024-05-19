package com.alquieventos.services;

import com.alquieventos.models.Cliente;
import com.alquieventos.models.Compra;
import com.alquieventos.models.Cupon;
import com.alquieventos.models.Evento;
import com.alquieventos.models.Localidad;
import com.alquieventos.utils.Database;

public class PurchaseService {
    private Database database;

    public PurchaseService() {
        database = Database.getInstance();
    }

    public Cupon getCuponByCodigo(String codigo) {
        return database.findCuponByCodigo(codigo);
    }

    public Compra createCompra(Cliente cliente, Evento evento, Localidad localidad, int cantidad, Cupon cupon) {
        if (localidad.getCapacidad() >= cantidad) {
            Compra compra = new Compra(cliente, evento, localidad, cantidad, cupon);
            database.saveCompra(compra);
            return compra;
        } else {
            return null;
        }
    }
}
