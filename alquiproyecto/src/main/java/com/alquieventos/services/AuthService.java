package com.alquieventos.services;

import com.alquieventos.models.Administrador;
import com.alquieventos.models.Cliente;
import com.alquieventos.models.Usuario;
import com.alquieventos.utils.Database;

public class AuthService {
    private Database database;
    private ClienteService clienteService;
    private AdministradorService administradorService;

    public AuthService() {
        database = Database.getInstance();
    }

    public void register(Cliente cliente) {
        database.saveCliente(cliente);
        EmailService.sendActivationCode(cliente);
    }

    public Usuario login(String email, String password) {
        Cliente cliente = clienteService.getClienteByEmail(email);
        if (cliente != null) {
            if (!cliente.isActivado()) {
                throw new IllegalArgumentException("La cuenta no ha sido verificada.");
            }
            if (cliente.getContrasena().equals(password)) {
                return cliente;
            }
        }
        Administrador admin = administradorService.getAdministradorByEmail(email);
        if (admin != null) {
            if (admin.getContrasena().equals(password)) {
                return admin;
            }
        }
        throw new IllegalArgumentException("Email o contraseña incorrectos.");
    }
    

    public boolean activateAccount(String email , String activationCode) {
        Cliente cliente = Database.getInstance().findClienteByEmail(email);
        if (cliente != null && cliente.getCodigoActivacion().equals(activationCode)) {
            cliente.setActivado(true);
            Database.getInstance().saveCliente(cliente);
            return true;
        }
        return false;
    }

    // Método para verificar si el administrador está registrado
    public boolean isAdministratorRegistered() {
        // Lógica para verificar si el administrador está registrado
        // Aquí deberías acceder a la base de datos o archivo de persistencia para comprobarlo
        Cliente admin = Database.getInstance().findClienteByEmail("admin@unieventos.com");
        return admin != null;
    }

    // Método para registrar al administrador
    public void registerAdministrator(String email, String password) {
        Cliente admin = new Cliente("0000000000", "Administrador", "0000000000", email, password, true);
        Database.getInstance().saveCliente(admin);
    }
}
