package com.rentacar.service;

import com.rentacar.exception.DatoDuplicadoException;
import com.rentacar.exception.OperacionInvalidaException;
import com.rentacar.model.AgenteRenta;
import com.rentacar.model.Auto;
import com.rentacar.model.Cliente;
import com.rentacar.model.ContratoRenta;
import com.rentacar.enums.EstadoAuto;
import com.rentacar.enums.EstadoContrato;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;
import com.rentacar.util.Validador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ContratoRentaService {
    private final com.rentacar.model.RentACar rentACar;

    public ContratoRentaService(com.rentacar.model.RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void crearContrato() {
        ConsolePrinter.imprimirSubtitulo("CREAR CONTRATO DE RENTA");
        try {
            Cliente cliente = seleccionarCliente();
            if (cliente == null) return;

            Auto auto = seleccionarAutoDisponible();
            if (auto == null) return;

            AgenteRenta agente = seleccionarAgente();
            if (agente == null) return;

            LocalDate fechaInicio = leerFecha("Fecha de inicio (yyyy-mm-dd)");
            LocalDate fechaFin = leerFecha("Fecha de fin (yyyy-mm-dd)");

            if (fechaFin.isBefore(fechaInicio) || fechaFin.isEqual(fechaInicio)) {
                throw new OperacionInvalidaException("La fecha de fin debe ser posterior a la de inicio.");
            }

            long dias = ChronoUnit.DAYS.between(fechaInicio, fechaFin);
            double total = dias * auto.getPrecioPorDia();

            int nuevoId = rentACar.getTotalContratos() + 1;
            ContratoRenta contrato = new ContratoRenta(nuevoId, cliente, auto, agente, fechaInicio, fechaFin);
            rentACar.agregarContrato(contrato);

            auto.setEstado(EstadoAuto.ALQUILADO);
            ConsolePrinter.imprimirExito("Contrato creado con ID: " + nuevoId);
            ConsolePrinter.imprimirMensaje(String.format("Total a pagar: $%.2f (%d dias)", total, dias));
        } catch (Exception e) {
            ConsolePrinter.imprimirError(e.getMessage());
        }
    }

    public void listarContratos() {
        ConsolePrinter.imprimirSubtitulo("LISTA DE CONTRATOS");
        List<ContratoRenta> contratos = rentACar.getContratos();
        if (contratos.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay contratos registrados.");
            return;
        }
        contratos.forEach(c -> ConsolePrinter.imprimirMensaje(c.toString()));
    }

    public void listarContratosActivos() {
        ConsolePrinter.imprimirSubtitulo("CONTRATOS ACTIVOS");
        List<ContratoRenta> contratos = rentACar.getContratosActivos();
        if (contratos.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay contratos activos.");
            return;
        }
        contratos.forEach(c -> ConsolePrinter.imprimirMensaje(c.toString()));
    }

    public void buscarContratoPorId() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR CONTRATO POR ID");
        int id = InputHelper.leerEntero("ID del contrato");
        ContratoRenta contrato = rentACar.getContratosMap().get(id);
        if (contrato != null) {
            ConsolePrinter.imprimirMensaje(contrato.toString());
        } else {
            ConsolePrinter.imprimirError("Contrato no encontrado con ID: " + id);
        }
    }

    public void finalizarContrato() {
        ConsolePrinter.imprimirSubtitulo("FINALIZAR CONTRATO");
        int id = InputHelper.leerEntero("ID del contrato a finalizar");
        ContratoRenta contrato = rentACar.getContratosMap().get(id);
        if (contrato == null) {
            ConsolePrinter.imprimirError("Contrato no encontrado.");
            return;
        }
        if (contrato.getEstado() != EstadoContrato.ACTIVO) {
            ConsolePrinter.imprimirError("El contrato no esta activo.");
            return;
        }
        contrato.setEstado(EstadoContrato.FINALIZADO);
        contrato.getAuto().setEstado(EstadoAuto.DISPONIBLE);
        ConsolePrinter.imprimirExito("Contrato finalizado. Auto devuelto a estado DISPONIBLE.");
    }

    public void cancelarContrato() {
        ConsolePrinter.imprimirSubtitulo("CANCELAR CONTRATO");
        int id = InputHelper.leerEntero("ID del contrato a cancelar");
        ContratoRenta contrato = rentACar.getContratosMap().get(id);
        if (contrato == null) {
            ConsolePrinter.imprimirError("Contrato no encontrado.");
            return;
        }
        if (contrato.getEstado() != EstadoContrato.ACTIVO) {
            ConsolePrinter.imprimirError("Solo se pueden cancelar contratos activos.");
            return;
        }
        contrato.setEstado(EstadoContrato.CANCELADO);
        contrato.getAuto().setEstado(EstadoAuto.DISPONIBLE);
        ConsolePrinter.imprimirExito("Contrato cancelado. Auto devuelto a estado DISPONIBLE.");
    }

    private LocalDate leerFecha(String mensaje) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                String input = InputHelper.leerTexto(mensaje);
                Validador.validarNoVacio(input, "Fecha");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                ConsolePrinter.imprimirError("Formato de fecha invalido. Use yyyy-mm-dd.");
            }
        }
    }

    private Cliente seleccionarCliente() {
        List<Cliente> clientes = rentACar.getClientes();
        if (clientes.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay clientes registrados.");
            return null;
        }
        ConsolePrinter.imprimirMensaje("Clientes disponibles:");
        for (int i = 0; i < clientes.size(); i++) {
            ConsolePrinter.imprimirMensaje((i + 1) + ". " + clientes.get(i).getNombre() + " - " + clientes.get(i).getDocumento());
        }
        int idx = InputHelper.leerEnteroRango("Seleccione cliente", 1, clientes.size()) - 1;
        return clientes.get(idx);
    }

    private Auto seleccionarAutoDisponible() {
        List<Auto> autos = rentACar.getAutosDisponibles();
        if (autos.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay autos disponibles.");
            return null;
        }
        ConsolePrinter.imprimirMensaje("Autos disponibles:");
        for (int i = 0; i < autos.size(); i++) {
            Auto a = autos.get(i);
            ConsolePrinter.imprimirMensaje((i + 1) + ". " + a.getMarca() + " " + a.getModelo() + " - " + a.getPlaca() + " - $" + a.getPrecioPorDia() + "/dia");
        }
        int idx = InputHelper.leerEnteroRango("Seleccione auto", 1, autos.size()) - 1;
        return autos.get(idx);
    }

    private AgenteRenta seleccionarAgente() {
        List<AgenteRenta> agentes = rentACar.getAgentes();
        if (agentes.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay agentes registrados.");
            return null;
        }
        ConsolePrinter.imprimirMensaje("Agentes disponibles:");
        for (int i = 0; i < agentes.size(); i++) {
            ConsolePrinter.imprimirMensaje((i + 1) + ". " + agentes.get(i).getNombre());
        }
        int idx = InputHelper.leerEnteroRango("Seleccione agente", 1, agentes.size()) - 1;
        return agentes.get(idx);
    }
}