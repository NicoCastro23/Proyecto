package com.alquieventos.services;

import com.alquieventos.models.Cliente;
import com.alquieventos.models.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraService {
    private List<Compra> compraRepository = new ArrayList<>();

    public void registerCompra(Compra compra) {
        compraRepository.add(compra);
    }

    public List<Compra> getPurchasesByCliente(Cliente cliente) {
        List<Compra> result = new ArrayList<>();
        for (Compra compra : compraRepository) {
            if (compra.getCliente().equals(cliente)) {
                result.add(compra);
            }
        }
        return result;
    }
}

