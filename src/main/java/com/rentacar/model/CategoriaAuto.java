package com.rentacar.model;

import java.util.ArrayList;
import java.util.List;

public class CategoriaAuto {
    private int id;
    private String nombre;
    private String descripcion;
    private List<Auto> autos;

    public CategoriaAuto(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.autos = new ArrayList<>();
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Auto> getAutos() {
        return new ArrayList<>(autos);
    }

    public void agregarAuto(Auto auto) {
        if (!autos.contains(auto)) {
            autos.add(auto);
            auto.setCategoria(this);
        }
    }

    public void removerAuto(Auto auto) {
        autos.remove(auto);
        if (auto.getCategoria() == this) {
            auto.setCategoria(null);
        }
    }

    public int getCantidadAutos() {
        return autos.size();
    }

    public int getCantidadDisponibles() {
        return (int) autos.stream().filter(a -> a.getEstado() == com.rentacar.enums.EstadoAuto.DISPONIBLE).count();
    }

    @Override
    public String toString() {
        return String.format("CategoriaAuto{id=%d, nombre='%s', descripcion='%s', autos=%d}", 
                id, nombre, descripcion, autos.size());
    }
}