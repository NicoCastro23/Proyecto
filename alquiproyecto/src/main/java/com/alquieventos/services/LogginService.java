package com.alquieventos.services;

import com.alquieventos.models.Cliente;
import com.alquieventos.models.UniEventos;

public class LogginService {
    private UniEventos uniEventos;

    public LogginService(UniEventos uniEventos) {
        this.uniEventos = uniEventos;
    }

    public boolean login(String identificacion, String contrasena) {
        Cliente cliente = uniEventos.buscarClientePorIdentificacion(identificacion);
        if (cliente != null && cliente.getPassword().equals(contrasena)) {
            if (!cliente.isVerified()) {
                System.out.println("Debe verificar su cuenta ingresando el código de verificación.");
                return false;
            }
            uniEventos.setUsuarioActual(cliente);
            System.out.println("Login exitoso. Bienvenido, " + cliente.getName() + "!");
            return true;
        }
        System.out.println("Identificación o contraseña incorrecta.");
        return false;
    }
}
