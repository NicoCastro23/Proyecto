package com.alquieventos.models;

import java.io.*;

public class Serializador {
    public static void guardarObjeto(String ruta, Object objeto) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(objeto);
        }
    }

    public static Object cargarObjeto(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            return in.readObject();
        }
    }
}
