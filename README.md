# RentACar - Console Application

Sistema de gestión de alquiler de vehículos desarrollado en Java 21 como proyecto de taller/tarea.

## Descripción

Aplicación de consola para gestionar una empresa de alquiler de autos. Permite administrar categorías, vehículos, clientes, agentes de renta y contratos de alquiler a través de un menú interactivo.

## Estructura del Proyecto

```
src/main/java/com/rentacar/
├── App.java                      # Punto de entrada con menú principal
├── model/                        # Entidades del dominio
│   ├── RentACar.java            # Contenedor principal (colecciones + HashMaps)
│   ├── CategoriaAuto.java       # Agrupa vehículos por categoría
│   ├── Auto.java                # Vehículo con estado, combustible, precio/día
│   ├── Cliente.java             # Cliente con documento, licencia, contacto
│   ├── AgenteRenta.java         # Agente que registra alquileres
│   ├── ContratoRenta.java       # Contrato vincula cliente + auto + agente + fechas
│   └── TipoVehiculo.java        # Enum: SEDAN, SUV, HATCHBACK, etc.
├── enums/
│   ├── EstadoAuto.java          # DISPONIBLE, ALQUILADO, MANTENIMIENTO, FUERA_DE_SERVICIO
│   ├── TipoCombustible.java     # GASOLINA, DIESEL, HIBRIDO, ELECTRICO
│   └── EstadoContrato.java      # ACTIVO, FINALIZADO, CANCELADO, VENCIDO
├── service/                     # Lógica de negocio (CRUD + operaciones)
│   ├── RentACarService.java
│   ├── CategoriaAutoService.java
│   ├── AutoService.java
│   ├── ClienteService.java
│   ├── AgenteRentaService.java
│   └── ContratoRentaService.java
├── exception/
│   ├── DatoDuplicadoException.java
│   ├── EntidadNoEncontradaException.java
│   └── OperacionInvalidaException.java
└── util/
    ├── InputHelper.java         # Lectura de consola con validación
    ├── Validador.java           # Validaciones comunes
    └── ConsolePrinter.java      # Salida formateada
```

## Requisitos Implementados

- **App.java** como punto de entrada con menú interactivo
- **Colecciones**: ArrayList, HashMap, HashSet
- **OOP**: Herencia, polimorfismo, encapsulamiento (getters/setters/constructores)
- **Relaciones entre clases**: RentACar contiene todo, Auto pertenece a CategoriaAuto, ContratoRenta vincula Cliente + Auto + AgenteRenta
- **Enums** para estados y tipos
- **Excepciones** personalizadas para duplicados, no encontrado, operaciones inválidas
- **Principios SOLID** básicos (separación de responsabilidades en services)
- **Generics** en collections y maps

## Cómo Ejecutar

```bash
# Compilar
mvn compile

# Empaquetar JAR
mvn package

# Ejecutar
java -jar target/rentacar-console-1.0.0.jar
```

## Menú Principal

1. **Gestionar Categorías** - CRUD categorías de autos
2. **Gestionar Autos** - CRUD autos, buscar por ID/placa, cambiar estado, listar por categoría
3. **Gestionar Clientes** - CRUD clientes, buscar por ID/documento
4. **Gestionar Agentes** - CRUD agentes de renta
5. **Gestionar Contratos** - Crear contrato, listar, finalizar, cancelar
6. **Ver Información General** - Estadísticas del sistema
7. **Configurar Empresa** - Cambiar nombre, dirección, teléfono
8. **Salir**

## Datos de Prueba

Al iniciar se cargan automáticamente:
- 3 categorías: Económico, SUV, Lujo
- 3 autos (uno por categoría)
- 2 clientes
- 2 agentes
- 1 contrato activo (auto alquilado)

## Tecnologías

- Java 21
- Maven 3.x
- Sin dependencias externas (solo JDK)