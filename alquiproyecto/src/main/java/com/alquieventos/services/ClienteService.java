package com.alquieventos.services;

import com.alquieventos.models.Cliente;

import java.util.HashMap;
import java.util.Map;

public class ClienteService {
    private Map<String, Cliente> clienteRepository = new HashMap<>();

    public void registerCliente(Cliente cliente) {
        clienteRepository.put(cliente.getEmail(), cliente);
        // Simula el envío del código de activación
        sendActivationCode(cliente);
    }

    public Cliente getClienteByEmail(String email) {
        return clienteRepository.get(email);
    }

    public void updateCliente(Cliente cliente) {
        clienteRepository.put(cliente.getEmail(), cliente);
    }

    private void sendActivationCode(Cliente cliente) {
        // Lógica para enviar el código de activación por correo electrónico
        System.out.println("Sending activation code to " + cliente.getEmail() + ": " + cliente.getCodigoActivacion());
    }
}

