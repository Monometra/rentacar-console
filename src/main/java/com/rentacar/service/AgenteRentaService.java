package com.rentacar.service;

import com.rentacar.exception.DatoDuplicadoException;
import com.rentacar.exception.EntidadNoEncontradaException;
import com.rentacar.exception.OperacionInvalidaException;
import com.rentacar.model.AgenteRenta;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;
import com.rentacar.util.Validador;

import java.util.List;

public class AgenteRentaService {
    private final com.rentacar.model.RentACar rentACar;

    public AgenteRentaService(com.rentacar.model.RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void registrarAgente() {
        ConsolePrinter.imprimirSubtitulo("REGISTRAR AGENTE DE RENTA");
        try {
            String nombre = InputHelper.leerTexto("Nombre completo");
            Validador.validarNoVacio(nombre, "Nombre");

            String documento = InputHelper.leerTexto("Documento de identidad");
            Validador.validarNoVacio(documento, "Documento");

            if (rentACar.getAgentesMap().values().stream().anyMatch(a -> a.getDocumento().equals(documento))) {
                throw new DatoDuplicadoException("Ya existe un agente con ese documento.");
            }

            String telefono = InputHelper.leerTexto("Telefono");
            String email = InputHelper.leerTexto("Email");
            Validador.validarEmail(email);
            String sucursal = InputHelper.leerTexto("Sucursal");
            Validador.validarNoVacio(sucursal, "Sucursal");

            int nuevoId = rentACar.getTotalAgentes() + 1;
            AgenteRenta agente = new AgenteRenta(nuevoId, nombre, documento, telefono, email, sucursal);
            rentACar.agregarAgente(agente);

            ConsolePrinter.imprimirExito("Agente registrado con ID: " + nuevoId);
        } catch (IllegalArgumentException | DatoDuplicadoException e) {
            ConsolePrinter.imprimirError(e.getMessage());
        }
    }

    public void listarAgentes() {
        ConsolePrinter.imprimirSubtitulo("LISTA DE AGENTES");
        List<AgenteRenta> agentes = rentACar.getAgentes();
        if (agentes.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay agentes registrados.");
            return;
        }
        agentes.forEach(a -> ConsolePrinter.imprimirMensaje(a.toString()));
    }

    public void buscarAgentePorId() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR AGENTE POR ID");
        int id = InputHelper.leerEntero("ID del agente");
        AgenteRenta agente = rentACar.getAgentesMap().get(id);
        if (agente != null) {
            ConsolePrinter.imprimirMensaje(agente.toString());
        } else {
            ConsolePrinter.imprimirError("Agente no encontrado con ID: " + id);
        }
    }

    public void eliminarAgente() {
        ConsolePrinter.imprimirSubtitulo("ELIMINAR AGENTE");
        int id = InputHelper.leerEntero("ID del agente a eliminar");
        AgenteRenta agente = rentACar.getAgentesMap().get(id);
        if (agente == null) {
            ConsolePrinter.imprimirError("Agente no encontrado.");
            return;
        }
        rentACar.eliminarAgente(id);
        ConsolePrinter.imprimirExito("Agente eliminado correctamente.");
    }
}