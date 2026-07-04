package com.rentacar.util;

public class Validador {
    public static boolean validarNoVacio(String valor, String nombreCampo) throws IllegalArgumentException {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(nombreCampo + " no puede estar vacio.");
        }
        return true;
    }

    public static boolean validarPositivo(double valor, String nombreCampo) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException(nombreCampo + " debe ser mayor a cero.");
        }
        return true;
    }

    public static boolean validarPositivo(int valor, String nombreCampo) throws IllegalArgumentException {
        if (valor <= 0) {
            throw new IllegalArgumentException(nombreCampo + " debe ser mayor a cero.");
        }
        return true;
    }

    public static boolean validarLongitud(String valor, int min, int max, String nombreCampo) throws IllegalArgumentException {
        if (valor == null || valor.length() < min || valor.length() > max) {
            throw new IllegalArgumentException(String.format("%s debe tener entre %d y %d caracteres.", nombreCampo, min, max));
        }
        return true;
    }

    public static boolean validarEmail(String email) throws IllegalArgumentException {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
            throw new IllegalArgumentException("Formato de email invalido.");
        }
        return true;
    }
}