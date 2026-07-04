package com.rentacar.service;

import com.rentacar.exception.DatoDuplicadoException;
import com.rentacar.exception.EntidadNoEncontradaException;
import com.rentacar.exception.OperacionInvalidaException;
import com.rentacar.model.Auto;
import com.rentacar.model.CategoriaAuto;
import com.rentacar.enums.TipoCombustible;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;
import com.rentacar.util.Validador;

import java.util.List;
import java.util.Set;
import java.time.Year;

public class AutoService {
    private final com.rentacar.model.RentACar rentACar;

    public AutoService(com.rentacar.model.RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void registrarAuto() {
        ConsolePrinter.imprimirSubtitulo("REGISTRAR AUTO");
        try {
            CategoriaAuto categoria = seleccionarCategoria();
            if (categoria == null) {
                ConsolePrinter.imprimirError("Debe seleccionar una categoria valida.");
                return;
            }

            String placa = InputHelper.leerTexto("Placa").toUpperCase();
            Validador.validarNoVacio(placa, "Placa");
            if (rentACar.getPlacasRegistradas().contains(placa)) {
                throw new DatoDuplicadoException("Ya existe un auto con esa placa.");
            }

            String marca = InputHelper.leerTexto("Marca");
            Validador.validarNoVacio(marca, "Marca");

            String modelo = InputHelper.leerTexto("Modelo");
            Validador.validarNoVacio(modelo, "Modelo");

            int anio = InputHelper.leerEnteroRango("Anio", 1990, java.time.Year.now().getValue() + 1);

            String color = InputHelper.leerTexto("Color");
            Validador.validarNoVacio(color, "Color");

            ConsolePrinter.imprimirMensaje("Tipos de combustible disponibles:");
            for (TipoCombustible t : TipoCombustible.values()) {
                ConsolePrinter.imprimirMensaje("  " + t.ordinal() + ". " + t);
            }
            int tipoIdx = InputHelper.leerEnteroRango("Seleccione tipo de combustible", 0, TipoCombustible.values().length - 1);
            TipoCombustible combustible = TipoCombustible.values()[tipoIdx];

            double precioPorDia = InputHelper.leerDoublePositivo("Precio por dia");

            int nuevoId = rentACar.getTotalAutos() + 1;
            Auto auto = new Auto(nuevoId, placa, marca, modelo, anio, color, combustible, precioPorDia, categoria);
            rentACar.agregarAuto(auto);
            categoria.agregarAuto(auto);

            ConsolePrinter.imprimirExito("Auto registrado con ID: " + nuevoId);
        } catch (IllegalArgumentException | DatoDuplicadoException e) {
            ConsolePrinter.imprimirError(e.getMessage());
        }
    }

    public void listarAutos() {
        ConsolePrinter.imprimirSubtitulo("LISTA DE AUTOS");
        List<Auto> autos = rentACar.getAutos();
        if (autos.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay autos registrados.");
            return;
        }
        autos.forEach(a -> ConsolePrinter.imprimirMensaje(a.toString()));
    }

    public void listarAutosPorCategoria() {
        ConsolePrinter.imprimirSubtitulo("AUTOS POR CATEGORIA");
        CategoriaAuto categoria = seleccionarCategoria();
        if (categoria == null) return;

        List<Auto> autos = categoria.getAutos();
        if (autos.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay autos en esta categoria.");
            return;
        }
        autos.forEach(a -> ConsolePrinter.imprimirMensaje(a.toString()));
    }

    public void buscarAutoPorPlaca() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR AUTO POR PLACA");
        String placa = InputHelper.leerTexto("Placa").toUpperCase();
        Auto auto = rentACar.getAutosMap().values().stream()
                .filter(a -> a.getPlaca().equals(placa))
                .findFirst()
                .orElse(null);
        if (auto != null) {
            ConsolePrinter.imprimirMensaje(auto.toString());
        } else {
            ConsolePrinter.imprimirError("Auto no encontrado con placa: " + placa);
        }
    }

    public void buscarAutoPorId() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR AUTO POR ID");
        int id = InputHelper.leerEntero("ID del auto");
        Auto auto = rentACar.getAutosMap().get(id);
        if (auto != null) {
            ConsolePrinter.imprimirMensaje(auto.toString());
        } else {
            ConsolePrinter.imprimirError("Auto no encontrado con ID: " + id);
        }
    }

    public void cambiarEstadoAuto() {
        ConsolePrinter.imprimirSubtitulo("CAMBIAR ESTADO DEL AUTO");
        int id = InputHelper.leerEntero("ID del auto");
        Auto auto = rentACar.getAutosMap().get(id);
        if (auto == null) {
            ConsolePrinter.imprimirError("Auto no encontrado.");
            return;
        }
        ConsolePrinter.imprimirMensaje("Estado actual: " + auto.getEstado());
        ConsolePrinter.imprimirMensaje("Estados disponibles:");
        com.rentacar.enums.EstadoAuto[] estados = com.rentacar.enums.EstadoAuto.values();
        for (int i = 0; i < estados.length; i++) {
            ConsolePrinter.imprimirMensaje("  " + i + ". " + estados[i]);
        }
        int idx = InputHelper.leerEnteroRango("Seleccione nuevo estado", 0, estados.length - 1);
        auto.setEstado(estados[idx]);
        ConsolePrinter.imprimirExito("Estado actualizado a: " + auto.getEstado());
    }

    public void eliminarAuto() {
        ConsolePrinter.imprimirSubtitulo("ELIMINAR AUTO");
        int id = InputHelper.leerEntero("ID del auto a eliminar");
        Auto auto = rentACar.getAutosMap().get(id);
        if (auto == null) {
            ConsolePrinter.imprimirError("Auto no encontrado.");
            return;
        }
        if (auto.getEstado() == com.rentacar.enums.EstadoAuto.ALQUILADO) {
            ConsolePrinter.imprimirError("No se puede eliminar un auto que esta alquilado.");
            return;
        }
        rentACar.eliminarAuto(id);
        if (auto.getCategoria() != null) {
            auto.getCategoria().removerAuto(auto);
        }
        ConsolePrinter.imprimirExito("Auto eliminado correctamente.");
    }

    private CategoriaAuto seleccionarCategoria() {
        List<CategoriaAuto> categorias = rentACar.getCategorias();
        if (categorias.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay categorias registradas. Registre una primero.");
            return null;
        }
        ConsolePrinter.imprimirMensaje("Categorias disponibles:");
        for (int i = 0; i < categorias.size(); i++) {
            ConsolePrinter.imprimirMensaje((i + 1) + ". " + categorias.get(i).getNombre());
        }
        int idx = InputHelper.leerEnteroRango("Seleccione categoria", 1, categorias.size()) - 1;
        return categorias.get(idx);
    }
}