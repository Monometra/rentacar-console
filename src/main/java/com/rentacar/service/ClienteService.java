package com.rentacar.service;

import com.rentacar.exception.DatoDuplicadoException;
import com.rentacar.exception.EntidadNoEncontradaException;
import com.rentacar.exception.OperacionInvalidaException;
import com.rentacar.model.Cliente;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;
import com.rentacar.util.Validador;

import java.util.List;

public class ClienteService {
    private final com.rentacar.model.RentACar rentACar;

    public ClienteService(com.rentacar.model.RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void registrarCliente() {
        ConsolePrinter.imprimirSubtitulo("REGISTRAR CLIENTE");
        try {
            String nombre = InputHelper.leerTexto("Nombre completo");
            Validador.validarNoVacio(nombre, "Nombre");

            String documento = InputHelper.leerTexto("Documento de identidad");
            Validador.validarNoVacio(documento, "Documento");

            if (rentACar.getClientesMap().values().stream().anyMatch(c -> c.getDocumento().equals(documento))) {
                throw new DatoDuplicadoException("Ya existe un cliente con ese documento.");
            }

            String telefono = InputHelper.leerTexto("Telefono");
            String email = InputHelper.leerTexto("Email");
            Validador.validarEmail(email);

            String direccion = InputHelper.leerTexto("Direccion");
            Validador.validarNoVacio(direccion, "Direccion");

            String licencia = InputHelper.leerTexto("Numero de licencia de conducir");
            Validador.validarNoVacio(licencia, "Licencia");

            int nuevoId = rentACar.getTotalClientes() + 1;
            Cliente cliente = new Cliente(nuevoId, nombre, documento, telefono, email, direccion, licencia);
            rentACar.agregarCliente(cliente);

            ConsolePrinter.imprimirExito("Cliente registrado con ID: " + nuevoId);
        } catch (IllegalArgumentException | DatoDuplicadoException e) {
            ConsolePrinter.imprimirError(e.getMessage());
        }
    }

    public void listarClientes() {
        ConsolePrinter.imprimirSubtitulo("LISTA DE CLIENTES");
        List<Cliente> clientes = rentACar.getClientes();
        if (clientes.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay clientes registrados.");
            return;
        }
        clientes.forEach(c -> ConsolePrinter.imprimirMensaje(c.toString()));
    }

    public void buscarClientePorId() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR CLIENTE POR ID");
        int id = InputHelper.leerEntero("ID del cliente");
        Cliente cliente = rentACar.getClientesMap().get(id);
        if (cliente != null) {
            ConsolePrinter.imprimirMensaje(cliente.toString());
        } else {
            ConsolePrinter.imprimirError("Cliente no encontrado con ID: " + id);
        }
    }

    public void buscarClientePorDocumento() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR CLIENTE POR DOCUMENTO");
        String documento = InputHelper.leerTexto("Documento");
        Cliente cliente = rentACar.getClientesMap().values().stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst()
                .orElse(null);
        if (cliente != null) {
            ConsolePrinter.imprimirMensaje(cliente.toString());
        } else {
            ConsolePrinter.imprimirError("Cliente no encontrado con documento: " + documento);
        }
    }

    public void eliminarCliente() {
        ConsolePrinter.imprimirSubtitulo("ELIMINAR CLIENTE");
        int id = InputHelper.leerEntero("ID del cliente a eliminar");
        Cliente cliente = rentACar.getClientesMap().get(id);
        if (cliente == null) {
            ConsolePrinter.imprimirError("Cliente no encontrado.");
            return;
        }
        boolean tieneContratosActivos = rentACar.getContratos().stream()
                .anyMatch(c -> c.getCliente().getId() == id && c.getEstado() == com.rentacar.enums.EstadoContrato.ACTIVO);
        if (tieneContratosActivos) {
            ConsolePrinter.imprimirError("No se puede eliminar un cliente con contratos activos.");
            return;
        }
        rentACar.eliminarCliente(id);
        ConsolePrinter.imprimirExito("Cliente eliminado correctamente.");
    }
}