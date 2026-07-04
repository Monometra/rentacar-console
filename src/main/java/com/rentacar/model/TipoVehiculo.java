package com.rentacar.model;

public enum TipoVehiculo {
    SEDAN("Sedan"),
    SUV("SUV"),
    HATCHBACK("Hatchback"),
    CAMIONETA("Camioneta"),
    DEPORTIVO("Deportivo"),
    VAN("Van"),
    MOTO("Moto");

    private final String descripcion;

    TipoVehiculo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}