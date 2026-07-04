package com.rentacar.model;

public class AgenteRenta {
    private int id;
    private String nombre;
    private String documento;
    private String telefono;
    private String email;
    private String sucursal;

    public AgenteRenta(int id, String nombre, String documento, String telefono, String email, String sucursal) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
        this.sucursal = sucursal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public String toString() {
        return String.format("AgenteRenta{id=%d, nombre='%s', documento='%s', sucursal='%s'}", 
                id, nombre, documento, sucursal);
    }
}