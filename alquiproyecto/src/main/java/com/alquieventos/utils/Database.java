package com.alquieventos.utils;

import com.alquieventos.models.Cliente;
import com.alquieventos.models.Cupon;
import com.alquieventos.models.Evento;
import com.alquieventos.models.Compra;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static Database instance;
    private List<Cliente> clientes;
    private List<Evento> eventos;
    private List<Cupon> cupones;
    private List<Compra> compras;

    private Database() {
        clientes = new ArrayList<>();
        eventos = new ArrayList<>();
        cupones = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void saveCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void addEvento(Evento evento){
        eventos.add(evento);
    }

    public void removeEvento(Evento evento){
        eventos.remove(evento);
    }

    public Evento findEventoByName (String name){
        return eventos.stream()
                .filter(evento -> evento.getNombre().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Evento> findEventosByCity (String city){
        return eventos.stream()
                .filter(evento -> evento.getCiudad().equals(city))
                .toList();
    }

    public List<Evento> findEventosByType (String type){
        return eventos.stream()
                .filter(evento -> evento.getTipo().equals(type))
                .toList();
    }

    public Cliente findClienteByEmailAndPassword(String email, String password) {
        return clientes.stream()
                .filter(cliente -> cliente.getEmail().equals(email) && cliente.getContrasena().equals(password))
                .findFirst()
                .orElse(null);
    }

    public Cliente findClienteByEmail(String email) {
        return clientes.stream()
            .filter(cliente -> cliente.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    } 
    
    public List<Evento> getEventos() {
        return eventos;
    }

    public Evento findEventoByNombre(String nombre) {
        return eventos.stream()
                .filter(evento -> evento.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    public void saveCompra(Compra compra) {
        compras.add(compra);
    }

    public Cupon findCuponByCodigo(String codigo) {
        return cupones.stream()
                .filter(cupon -> cupon.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    
}