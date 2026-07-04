# RentACar - Main Idea

This project consists of building a console-based car rental management system in Java 21+ where the user interacts through a menu in `App.java` to manage categories, cars, customers, rental agents, and rental contracts. This file defines the general concept of the project and how the main entities relate to each other based on the current requirements and structure.

[[Requirements & Structure]]
[[Project Managment]]

---

## Objective

- Practice object-oriented programming concepts such as inheritance and polymorphism.
- Apply Java collections such as `ArrayList`, `Set`, and `HashMap`.
- Use arrays, lists, and generics as part of the system design.
- Represent relationships between classes in a simple and understandable way.
- Include enumerations, exception handling, and SOLID principles in the project.
- Include the use of getters, setters, and method calls between classes.

---

## Core idea

- `RentACar` acts as the main container of the system.
- `CategoriaAuto` is used to group or classify cars.
- `Auto` represents a vehicle that belongs to a category.
- `AgenteRenta` manages or registers rental operations.
- `Cliente` represents the person renting a vehicle.
- `ContratoRenta` stores the main rental record that connects the customer, agent, car, and rental details.

---

## General system flow

- The program starts from `App.java`.
- A menu is displayed so the user can choose actions.
- The user can register and manage categories, cars, customers, agents, and contracts.
- The system validates input and handles invalid operations with exceptions.
- Information is stored using collections during program execution.

---

## Relationships between classes

- `RentACar` contains and manages the main collections of the system.
- `CategoriaAuto` groups vehicles according to their category.
- `Auto` belongs to one `CategoriaAuto`.
- `AgenteRenta` is responsible for processing or registering rentals.
- `Cliente` is linked to rental contracts.
- `ContratoRenta` connects the customer, the rental agent, and the selected car.

---

## Data structures to use

- `ArrayList` for storing lists of customers, agents, cars, categories, or contracts.
- `Set` for controlling unique values such as codes, documents, or plates.
- `HashMap` for searching and organizing data more efficiently.
- Generics for reusable collection handling.

---

## Development approach

- Keep the project simple because it is a workshop or homework project.
- Focus on implementing the required Java topics correctly.
- Organize the code clearly using the current folder structure.
- Prioritize readability and functionality over advanced architecture.
