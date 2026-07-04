package com.rentacar.model;

import com.rentacar.enums.EstadoContrato;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ContratoRenta {
    private int id;
    private Cliente cliente;
    private Auto auto;
    private AgenteRenta agente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double precioTotal;
    private EstadoContrato estado;

    public ContratoRenta(int id, Cliente cliente, Auto auto, AgenteRenta agente, 
                         LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.cliente = cliente;
        this.auto = auto;
        this.agente = agente;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = EstadoContrato.ACTIVO;
        calcularPrecioTotal();
    }

    private void calcularPrecioTotal() {
        long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
        this.precioTotal = dias * auto.getPrecioPorDia();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public AgenteRenta getAgente() {
        return agente;
    }

    public void setAgente(AgenteRenta agente) {
        this.agente = agente;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoContrato getEstado() {
        return estado;
    }

    public void setEstado(EstadoContrato estado) {
        this.estado = estado;
    }

    public long getDiasRenta() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin) + 1;
    }

    @Override
    public String toString() {
        return String.format("ContratoRenta{id=%d, cliente=%s, auto=%s, agente=%s, inicio=%s, fin=%s, dias=%d, total=%.2f, estado=%s}", 
                id, cliente.getNombre(), auto.getPlaca(), agente.getNombre(), 
                fechaInicio, fechaFin, getDiasRenta(), precioTotal, estado);
    }
}