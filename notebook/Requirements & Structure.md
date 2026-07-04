
This file contains the functional and structural requirements for the RentACar project, including the main topics to implement in Java and the base class distribution to follow during development.

---

# REQUIREMENTS

- [ ] App.java as the main file with menu  
- [ ] ArrayList and collections  
- [ ] OOP: inheritance and polymorphism  
- [ ] Arrays, lists, and generics  
- [ ] Relationships between classes  
- [ ] Sets (Set)  
- [ ] Maps (HashMap)  
- [ ] Enumerations  
- [ ] Exceptions (I/O, HashMap, etc.)  
- [ ] SOLID principles

---

# CANVAS 

| Contenedor General | Unidad Intermedia | Entidad Asignada | Usuario | Registro Fuerte |
| ------------------ | ----------------- | ---------------- | ------- | --------------- |
| RentACar           | CategoriaAuto     | AgenteRenta      | Cliente | ContratoRenta   |

---

# STRUCTURE

```
src/
├── App.java
├── model/
│   ├── RentACar.java
│   ├── CategoriaAuto.java
│   ├── Auto.java
│   ├── Cliente.java
│   ├── AgenteRenta.java
│   └── ContratoRenta.java
├── enums/
│   ├── EstadoAuto.java
│   ├── TipoCombustible.java
│   └── EstadoContrato.java
├── service/
│   ├── RentACarService.java
│   ├── ClienteService.java
│   ├── AgenteRentaService.java
│   ├── AutoService.java
│   ├── CategoriaAutoService.java
│   └── ContratoRentaService.java
├── exception/
│   ├── DatoDuplicadoException.java
│   ├── EntidadNoEncontradaException.java
│   └── OperacionInvalidaException.java
└── util/
    ├── InputHelper.java
    ├── Validador.java
    └── ConsolePrinter.java
```