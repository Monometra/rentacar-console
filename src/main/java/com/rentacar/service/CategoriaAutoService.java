package com.rentacar.service;

import com.rentacar.exception.DatoDuplicadoException;
import com.rentacar.exception.EntidadNoEncontradaException;
import com.rentacar.exception.OperacionInvalidaException;
import com.rentacar.model.CategoriaAuto;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;
import com.rentacar.util.Validador;

import java.util.List;

public class CategoriaAutoService {
    private final com.rentacar.model.RentACar rentACar;

    public CategoriaAutoService(com.rentacar.model.RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void registrarCategoria() {
        ConsolePrinter.imprimirSubtitulo("REGISTRAR CATEGORIA DE AUTO");
        try {
            String nombre = InputHelper.leerTexto("Nombre de la categoria");
            Validador.validarNoVacio(nombre, "Nombre");

            if (rentACar.getCategoriasMap().values().stream().anyMatch(c -> c.getNombre().equalsIgnoreCase(nombre))) {
                throw new DatoDuplicadoException("Ya existe una categoria con ese nombre.");
            }

            String descripcion = InputHelper.leerTexto("Descripcion");

            int nuevoId = rentACar.getTotalCategorias() + 1;
            CategoriaAuto categoria = new CategoriaAuto(nuevoId, nombre, descripcion);
            rentACar.agregarCategoria(categoria);

            ConsolePrinter.imprimirExito("Categoria registrada con ID: " + nuevoId);
        } catch (IllegalArgumentException | DatoDuplicadoException e) {
            ConsolePrinter.imprimirError(e.getMessage());
        }
    }

    public void listarCategorias() {
        ConsolePrinter.imprimirSubtitulo("LISTA DE CATEGORIAS");
        List<CategoriaAuto> categorias = rentACar.getCategorias();
        if (categorias.isEmpty()) {
            ConsolePrinter.imprimirAdvertencia("No hay categorias registradas.");
            return;
        }
        categorias.forEach(c -> ConsolePrinter.imprimirMensaje(
                String.format("ID: %d | Nombre: %s | Descripcion: %s | Autos: %d | Disponibles: %d",
                        c.getId(), c.getNombre(), c.getDescripcion(), c.getCantidadAutos(), c.getCantidadDisponibles())));
    }

    public void buscarCategoriaPorId() {
        ConsolePrinter.imprimirSubtitulo("BUSCAR CATEGORIA POR ID");
        int id = InputHelper.leerEntero("ID de la categoria");
        CategoriaAuto categoria = rentACar.getCategoriasMap().get(id);
        if (categoria != null) {
            ConsolePrinter.imprimirMensaje(String.format("ID: %d | Nombre: %s | Descripcion: %s | Autos: %d",
                    categoria.getId(), categoria.getNombre(), categoria.getDescripcion(), categoria.getCantidadAutos()));
        } else {
            ConsolePrinter.imprimirError("Categoria no encontrada con ID: " + id);
        }
    }

    public void eliminarCategoria() {
        ConsolePrinter.imprimirSubtitulo("ELIMINAR CATEGORIA");
        int id = InputHelper.leerEntero("ID de la categoria a eliminar");
        CategoriaAuto categoria = rentACar.getCategoriasMap().get(id);
        if (categoria == null) {
            ConsolePrinter.imprimirError("Categoria no encontrada.");
            return;
        }
        if (categoria.getCantidadAutos() > 0) {
            ConsolePrinter.imprimirError("No se puede eliminar una categoria que tiene autos asignados.");
            return;
        }
        rentACar.eliminarCategoria(id);
        ConsolePrinter.imprimirExito("Categoria eliminada correctamente.");
    }
}