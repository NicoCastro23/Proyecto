package com.alquieventos.services;

import java.util.List;
import com.alquieventos.utils.Database;
import com.alquieventos.models.Evento;

public class EventService {
    private Database database;

    public EventService() {
        this.database = Database.getInstance();
    }

    public List<Evento> getAllEvents() {
        return database.getEventos();
    }

    public void createEvent(Evento event) {
        database.addEvento(event);
    }

    public void deleteEvent(Evento event) {
        database.removeEvento(event);
    }

    public Evento findEventByName(String name) {
        return database.findEventoByName(name);
    }

    public List<Evento> findEventsByCity(String city) {
        return database.findEventosByCity(city);
    }

    public List<Evento> findEventsByType(String type) {
        return database.findEventosByType(type);
    }
}

    
