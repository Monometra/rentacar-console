package com.rentacar.exception;

public class OperacionInvalidaException extends Exception {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}