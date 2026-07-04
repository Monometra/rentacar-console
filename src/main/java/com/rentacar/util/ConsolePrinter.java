package com.rentacar.util;

public class ConsolePrinter {
    public static void imprimirTitulo(String titulo) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("  " + titulo.toUpperCase());
        System.out.println("=".repeat(50));
    }

    public static void imprimirSubtitulo(String subtitulo) {
        System.out.println("\n--- " + subtitulo + " ---");
    }

    public static void imprimirMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void imprimirError(String mensaje) {
        System.err.println("[ERROR] " + mensaje);
    }

    public static void imprimirExito(String mensaje) {
        System.out.println("[OK] " + mensaje);
    }

    public static void imprimirAdvertencia(String mensaje) {
        System.out.println("[AVISO] " + mensaje);
    }

public static void imprimirLinea() {
        System.out.println("-".repeat(50));
    }

    public static void imprimirSeparador() {
        System.out.println("-".repeat(50));
    }

    public static void imprimirMenu(String titulo, String[] opciones) {
        imprimirTitulo(titulo);
        for (int i = 0; i < opciones.length; i++) {
            System.out.printf("%d. %s%n", i + 1, opciones[i]);
        }
        imprimirLinea();
    }
}