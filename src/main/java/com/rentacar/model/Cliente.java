package com.rentacar.model;

public class Cliente {
    private int id;
    private String nombre;
    private String documento;
    private String telefono;
    private String email;
    private String direccion;
    private String licencia;

    public Cliente(int id, String nombre, String documento, String telefono, String email, String direccion, String licencia) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.licencia = licencia;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    @Override
    public String toString() {
        return String.format("Cliente{id=%d, nombre='%s', documento='%s', telefono='%s', email='%s', licencia='%s'}", 
                id, nombre, documento, telefono, email, licencia);
    }
}