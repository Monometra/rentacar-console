package com.rentacar.model;

import com.rentacar.enums.EstadoAuto;
import com.rentacar.enums.TipoCombustible;

public class Auto {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private TipoCombustible tipoCombustible;
    private double precioPorDia;
    private EstadoAuto estado;
    private CategoriaAuto categoria;

    public Auto(int id, String placa, String marca, String modelo, int anio, String color, 
                TipoCombustible tipoCombustible, double precioPorDia, CategoriaAuto categoria) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precioPorDia = precioPorDia;
        this.estado = EstadoAuto.DISPONIBLE;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TipoCombustible getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(TipoCombustible tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(double precioPorDia) {
        this.precioPorDia = precioPorDia;
    }

    public EstadoAuto getEstado() {
        return estado;
    }

    public void setEstado(EstadoAuto estado) {
        this.estado = estado;
    }

    public CategoriaAuto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAuto categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("Auto{id=%d, placa='%s', marca='%s', modelo='%s', anio=%d, color='%s', combustible=%s, precio/dia=%.2f, estado=%s, categoria=%s}", 
                id, placa, marca, modelo, anio, color, tipoCombustible, precioPorDia, estado, 
                categoria != null ? categoria.getNombre() : "Sin categoria");
    }
}