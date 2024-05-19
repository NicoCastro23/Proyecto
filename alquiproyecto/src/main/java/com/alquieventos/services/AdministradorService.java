package com.alquieventos.services;

import com.alquieventos.models.Administrador;

import java.util.HashMap;
import java.util.Map;

public class AdministradorService {
    private Map<String, Administrador> administradores = new HashMap<>();

    public AdministradorService() {
        // Añadir un administrador por defecto
        administradores.put("admin@alquieventos.com", Administrador.getInstance());
    }

    public Administrador getAdministradorByEmail(String email) {
        return administradores.get(email);
    }

    public void registerAdministrador(Administrador administrador) {
        administradores.put(administrador.getEmail(), administrador);
    }
}

