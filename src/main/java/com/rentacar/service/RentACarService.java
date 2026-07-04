package com.rentacar.service;

import com.rentacar.model.RentACar;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;

public class RentACarService {
    private final RentACar rentACar;

    public RentACarService(RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public void mostrarInformacionGeneral() {
        ConsolePrinter.imprimirTitulo("INFORMACION GENERAL DEL SISTEMA");
        ConsolePrinter.imprimirMensaje("Empresa: " + rentACar.getNombre());
        ConsolePrinter.imprimirMensaje("Direccion: " + rentACar.getDireccion());
        ConsolePrinter.imprimirMensaje("Telefono: " + rentACar.getTelefono());
        ConsolePrinter.imprimirSeparador();
        ConsolePrinter.imprimirMensaje("Total categorias: " + rentACar.getTotalCategorias());
        ConsolePrinter.imprimirMensaje("Total autos: " + rentACar.getTotalAutos());
        ConsolePrinter.imprimirMensaje("Total clientes: " + rentACar.getTotalClientes());
        ConsolePrinter.imprimirMensaje("Total agentes: " + rentACar.getTotalAgentes());
        ConsolePrinter.imprimirMensaje("Total contratos: " + rentACar.getTotalContratos());
        ConsolePrinter.imprimirMensaje("Contratos activos: " + rentACar.getContratosActivos().size());
        ConsolePrinter.imprimirMensaje("Autos disponibles: " + rentACar.getAutosDisponibles().size());
    }

    public void configurarEmpresa() {
        ConsolePrinter.imprimirSubtitulo("CONFIGURAR EMPRESA");
        String nombre = InputHelper.leerTexto("Nombre de la empresa [" + rentACar.getNombre() + "]");
        if (!nombre.isEmpty()) rentACar.setNombre(nombre);
        String direccion = InputHelper.leerTexto("Direccion [" + rentACar.getDireccion() + "]");
        if (!direccion.isEmpty()) rentACar.setDireccion(direccion);
        String telefono = InputHelper.leerTexto("Telefono [" + rentACar.getTelefono() + "]");
        if (!telefono.isEmpty()) rentACar.setTelefono(telefono);
        ConsolePrinter.imprimirExito("Configuracion actualizada.");
    }
}