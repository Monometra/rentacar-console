package com.rentacar.util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                ConsolePrinter.imprimirError("Ingrese un numero entero valido.");
            }
        }
    }

    public static int leerEnteroRango(String mensaje, int min, int max) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= min && valor <= max) {
                return valor;
            }
            ConsolePrinter.imprimirError(String.format("Ingrese un numero entre %d y %d.", min, max));
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + ": ");
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                ConsolePrinter.imprimirError("Ingrese un numero decimal valido.");
            }
        }
    }

    public static double leerDoublePositivo(String mensaje) {
        while (true) {
            double valor = leerDouble(mensaje);
            if (valor > 0) {
                return valor;
            }
            ConsolePrinter.imprimirError("El valor debe ser mayor a cero.");
        }
    }
}