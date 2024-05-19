package com.alquieventos.services;

import com.alquieventos.models.Cliente;

public class EmailService {
    public static void sendActivationCode(Cliente cliente) {
        // Aquí se implementaría la lógica para enviar el correo electrónico con el código de activación.
        System.out.println("Enviando código de activación a " + cliente.getEmail());
    }
}
