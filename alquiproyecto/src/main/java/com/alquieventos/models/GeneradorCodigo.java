package com.alquieventos.models;

import java.util.Random;

public class GeneradorCodigo {
    public static String generarCodigo() {
        int tamaño = 7;
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(tamaño);
        for (int i = 0; i < tamaño; i++) {
            code.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        return code.toString();
    }
}
