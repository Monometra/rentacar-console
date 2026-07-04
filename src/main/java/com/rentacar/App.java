package com.rentacar;

import com.rentacar.model.*;
import com.rentacar.service.*;
import com.rentacar.util.ConsolePrinter;
import com.rentacar.util.InputHelper;

public class App {
    private static RentACar rentACar;
    private static CategoriaAutoService categoriaService;
    private static AutoService autoService;
    private static ClienteService clienteService;
    private static AgenteRentaService agenteService;
    private static ContratoRentaService contratoService;
    private static RentACarService rentACarService;

    public static void main(String[] args) {
        inicializarSistema();
        mostrarMenuPrincipal();
    }

    private static void inicializarSistema() {
        rentACar = new RentACar("RentACar Demo", "Av. Principal 123", "555-1234");
        
        categoriaService = new CategoriaAutoService(rentACar);
        autoService = new AutoService(rentACar);
        clienteService = new ClienteService(rentACar);
        agenteService = new AgenteRentaService(rentACar);
        contratoService = new ContratoRentaService(rentACar);
        rentACarService = new RentACarService(rentACar);

        cargarDatosDemo();
        
        ConsolePrinter.imprimirTitulo("BIENVENIDO A " + rentACar.getNombre().toUpperCase());
    }

    private static void cargarDatosDemo() {
        // Categorias demo
        CategoriaAuto cat1 = new CategoriaAuto(1, "Economico", "Vehiculos economicos para ciudad");
        CategoriaAuto cat2 = new CategoriaAuto(2, "SUV", "Vehiculos espaciosos todo terreno");
        CategoriaAuto cat3 = new CategoriaAuto(3, "Lujo", "Vehiculos premium y deportivos");
        rentACar.agregarCategoria(cat1);
        rentACar.agregarCategoria(cat2);
        rentACar.agregarCategoria(cat3);

        // Autos demo
        Auto a1 = new Auto(1, "ABC-123", "Toyota", "Yaris", 2022, "Blanco", 
                com.rentacar.enums.TipoCombustible.GASOLINA, 45.0, cat1);
        Auto a2 = new Auto(2, "XYZ-789", "Honda", "CR-V", 2023, "Negro", 
                com.rentacar.enums.TipoCombustible.HIBRIDO, 85.0, cat2);
        Auto a3 = new Auto(3, "DEF-456", "BMW", "X5", 2023, "Azul", 
                com.rentacar.enums.TipoCombustible.DIESEL, 150.0, cat3);
        rentACar.agregarAuto(a1);
        rentACar.agregarAuto(a2);
        rentACar.agregarAuto(a3);
        cat1.agregarAuto(a1);
        cat2.agregarAuto(a2);
        cat3.agregarAuto(a3);

        // Clientes demo
        Cliente c1 = new Cliente(1, "Juan Perez", "12345678", "555-1111", "juan@email.com", "Calle 1 #123", "LIC-001");
        Cliente c2 = new Cliente(2, "Maria Garcia", "87654321", "555-2222", "maria@email.com", "Calle 2 #456", "LIC-002");
        rentACar.agregarCliente(c1);
        rentACar.agregarCliente(c2);

        // Agentes demo
        AgenteRenta ag1 = new AgenteRenta(1, "Carlos Lopez", "11111111", "555-3333", "carlos@rentacar.com", "Sucursal Centro");
        AgenteRenta ag2 = new AgenteRenta(2, "Ana Martinez", "22222222", "555-4444", "ana@rentacar.com", "Sucursal Norte");
        rentACar.agregarAgente(ag1);
        rentACar.agregarAgente(ag2);

        // Contrato demo
        java.time.LocalDate inicio = java.time.LocalDate.now();
        java.time.LocalDate fin = java.time.LocalDate.now().plusDays(3);
        ContratoRenta cont1 = new ContratoRenta(1, c1, a1, ag1, inicio, fin);
        rentACar.agregarContrato(cont1);
        a1.setEstado(com.rentacar.enums.EstadoAuto.ALQUILADO);
    }

    private static void mostrarMenuPrincipal() {
        String[] opciones = {
            "Gestionar Categorias",
            "Gestionar Autos",
            "Gestionar Clientes",
            "Gestionar Agentes",
            "Gestionar Contratos",
            "Ver Informacion General",
            "Configurar Empresa",
            "Salir"
        };

        boolean salir = false;
        while (!salir) {
            ConsolePrinter.imprimirMenu("MENU PRINCIPAL", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);

            switch (opcion) {
                case 1 -> menuCategorias();
                case 2 -> menuAutos();
                case 3 -> menuClientes();
                case 4 -> menuAgentes();
                case 5 -> menuContratos();
                case 6 -> rentACarService.mostrarInformacionGeneral();
                case 7 -> rentACarService.configurarEmpresa();
                case 8 -> {
                    ConsolePrinter.imprimirExito("Gracias por usar RentACar. ¡Hasta pronto!");
                    salir = true;
                }
            }
        }
    }

    private static void menuCategorias() {
        String[] opciones = {
            "Registrar Categoria",
            "Listar Categorias",
            "Buscar Categoria por ID",
            "Eliminar Categoria",
            "Volver"
        };
        boolean volver = false;
        while (!volver) {
            ConsolePrinter.imprimirMenu("CATEGORIAS DE AUTOS", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);
            switch (opcion) {
                case 1 -> categoriaService.registrarCategoria();
                case 2 -> categoriaService.listarCategorias();
                case 3 -> categoriaService.buscarCategoriaPorId();
                case 4 -> categoriaService.eliminarCategoria();
                case 5 -> volver = true;
            }
        }
    }

    private static void menuAutos() {
        String[] opciones = {
            "Registrar Auto",
            "Listar Todos los Autos",
            "Listar Autos por Categoria",
            "Buscar Auto por ID",
            "Buscar Auto por Placa",
            "Cambiar Estado de Auto",
            "Eliminar Auto",
            "Volver"
        };
        boolean volver = false;
        while (!volver) {
            ConsolePrinter.imprimirMenu("GESTION DE AUTOS", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);
            switch (opcion) {
                case 1 -> autoService.registrarAuto();
                case 2 -> autoService.listarAutos();
                case 3 -> autoService.listarAutosPorCategoria();
                case 4 -> autoService.buscarAutoPorId();
                case 5 -> autoService.buscarAutoPorPlaca();
                case 6 -> autoService.cambiarEstadoAuto();
                case 7 -> autoService.eliminarAuto();
                case 8 -> volver = true;
            }
        }
    }

    private static void menuClientes() {
        String[] opciones = {
            "Registrar Cliente",
            "Listar Clientes",
            "Buscar Cliente por ID",
            "Buscar Cliente por Documento",
            "Eliminar Cliente",
            "Volver"
        };
        boolean volver = false;
        while (!volver) {
            ConsolePrinter.imprimirMenu("GESTION DE CLIENTES", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);
            switch (opcion) {
                case 1 -> clienteService.registrarCliente();
                case 2 -> clienteService.listarClientes();
                case 3 -> clienteService.buscarClientePorId();
                case 4 -> clienteService.buscarClientePorDocumento();
                case 5 -> clienteService.eliminarCliente();
                case 6 -> volver = true;
            }
        }
    }

    private static void menuAgentes() {
        String[] opciones = {
            "Registrar Agente",
            "Listar Agentes",
            "Buscar Agente por ID",
            "Eliminar Agente",
            "Volver"
        };
        boolean volver = false;
        while (!volver) {
            ConsolePrinter.imprimirMenu("GESTION DE AGENTES", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);
            switch (opcion) {
                case 1 -> agenteService.registrarAgente();
                case 2 -> agenteService.listarAgentes();
                case 3 -> agenteService.buscarAgentePorId();
                case 4 -> agenteService.eliminarAgente();
                case 5 -> volver = true;
            }
        }
    }

    private static void menuContratos() {
        String[] opciones = {
            "Crear Contrato de Renta",
            "Listar Todos los Contratos",
            "Listar Contratos Activos",
            "Buscar Contrato por ID",
            "Finalizar Contrato",
            "Cancelar Contrato",
            "Volver"
        };
        boolean volver = false;
        while (!volver) {
            ConsolePrinter.imprimirMenu("GESTION DE CONTRATOS", opciones);
            int opcion = InputHelper.leerEnteroRango("Seleccione una opcion", 1, opciones.length);
            switch (opcion) {
                case 1 -> contratoService.crearContrato();
                case 2 -> contratoService.listarContratos();
                case 3 -> contratoService.listarContratosActivos();
                case 4 -> contratoService.buscarContratoPorId();
                case 5 -> contratoService.finalizarContrato();
                case 6 -> contratoService.cancelarContrato();
                case 7 -> volver = true;
            }
        }
    }
}