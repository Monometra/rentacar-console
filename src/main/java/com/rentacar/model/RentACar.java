package com.rentacar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RentACar {
    private String nombre;
    private String direccion;
    private String telefono;
    private List<CategoriaAuto> categorias;
    private List<Auto> autos;
    private List<Cliente> clientes;
    private List<AgenteRenta> agentes;
    private List<ContratoRenta> contratos;
    private Map<Integer, CategoriaAuto> categoriasMap;
    private Map<Integer, Auto> autosMap;
    private Map<Integer, Cliente> clientesMap;
    private Map<Integer, AgenteRenta> agentesMap;
    private Map<Integer, ContratoRenta> contratosMap;
    private Set<String> placasRegistradas;
    private Set<String> documentosClientes;
    private Set<String> documentosAgentes;

    public RentACar(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.categorias = new ArrayList<>();
        this.autos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.agentes = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.categoriasMap = new HashMap<>();
        this.autosMap = new HashMap<>();
        this.clientesMap = new HashMap<>();
        this.agentesMap = new HashMap<>();
        this.contratosMap = new HashMap<>();
        this.placasRegistradas = new HashSet<>();
        this.documentosClientes = new HashSet<>();
        this.documentosAgentes = new HashSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<CategoriaAuto> getCategorias() {
        return new ArrayList<>(categorias);
    }

    public List<Auto> getAutos() {
        return new ArrayList<>(autos);
    }

    public List<Cliente> getClientes() {
        return new ArrayList<>(clientes);
    }

    public List<AgenteRenta> getAgentes() {
        return new ArrayList<>(agentes);
    }

    public List<ContratoRenta> getContratos() {
        return new ArrayList<>(contratos);
    }

    public Map<Integer, CategoriaAuto> getCategoriasMap() {
        return new HashMap<>(categoriasMap);
    }

    public Map<Integer, Auto> getAutosMap() {
        return new HashMap<>(autosMap);
    }

    public Map<Integer, Cliente> getClientesMap() {
        return new HashMap<>(clientesMap);
    }

    public Map<Integer, AgenteRenta> getAgentesMap() {
        return new HashMap<>(agentesMap);
    }

    public Map<Integer, ContratoRenta> getContratosMap() {
        return new HashMap<>(contratosMap);
    }

    public Set<String> getPlacasRegistradas() {
        return new HashSet<>(placasRegistradas);
    }

    public Set<String> getDocumentosClientes() {
        return new HashSet<>(documentosClientes);
    }

    public Set<String> getDocumentosAgentes() {
        return new HashSet<>(documentosAgentes);
    }

    public void agregarCategoria(CategoriaAuto categoria) {
        if (categoriasMap.containsKey(categoria.getId())) {
            throw new IllegalArgumentException("Ya existe una categoria con ID: " + categoria.getId());
        }
        categorias.add(categoria);
        categoriasMap.put(categoria.getId(), categoria);
    }

    public void agregarAuto(Auto auto) {
        if (autosMap.containsKey(auto.getId())) {
            throw new IllegalArgumentException("Ya existe un auto con ID: " + auto.getId());
        }
        if (placasRegistradas.contains(auto.getPlaca())) {
            throw new IllegalArgumentException("Ya existe un auto con placa: " + auto.getPlaca());
        }
        autos.add(auto);
        autosMap.put(auto.getId(), auto);
        placasRegistradas.add(auto.getPlaca());
    }

    public void agregarCliente(Cliente cliente) {
        if (clientesMap.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con ID: " + cliente.getId());
        }
        if (documentosClientes.contains(cliente.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un cliente con documento: " + cliente.getDocumento());
        }
        clientes.add(cliente);
        clientesMap.put(cliente.getId(), cliente);
        documentosClientes.add(cliente.getDocumento());
    }

    public void agregarAgente(AgenteRenta agente) {
        if (agentesMap.containsKey(agente.getId())) {
            throw new IllegalArgumentException("Ya existe un agente con ID: " + agente.getId());
        }
        if (documentosAgentes.contains(agente.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un agente con documento: " + agente.getDocumento());
        }
        agentes.add(agente);
        agentesMap.put(agente.getId(), agente);
        documentosAgentes.add(agente.getDocumento());
    }

    public void agregarContrato(ContratoRenta contrato) {
        if (contratosMap.containsKey(contrato.getId())) {
            throw new IllegalArgumentException("Ya existe un contrato con ID: " + contrato.getId());
        }
        contratos.add(contrato);
        contratosMap.put(contrato.getId(), contrato);
    }

    public CategoriaAuto buscarCategoriaPorId(int id) {
        return categoriasMap.get(id);
    }

    public Auto buscarAutoPorId(int id) {
        return autosMap.get(id);
    }

    public Cliente buscarClientePorId(int id) {
        return clientesMap.get(id);
    }

    public AgenteRenta buscarAgentePorId(int id) {
        return agentesMap.get(id);
    }

    public ContratoRenta buscarContratoPorId(int id) {
        return contratosMap.get(id);
    }

    public Auto buscarAutoPorPlaca(String placa) {
        return autos.stream().filter(a -> a.getPlaca().equalsIgnoreCase(placa)).findFirst().orElse(null);
    }

    public Cliente buscarClientePorDocumento(String documento) {
        return clientes.stream().filter(c -> c.getDocumento().equals(documento)).findFirst().orElse(null);
    }

    public List<Auto> getAutosDisponibles() {
        return autos.stream().filter(a -> a.getEstado() == com.rentacar.enums.EstadoAuto.DISPONIBLE).toList();
    }

    public List<Auto> getAutosPorCategoria(int categoriaId) {
        return autos.stream().filter(a -> a.getCategoria() != null && a.getCategoria().getId() == categoriaId).toList();
    }

    public List<ContratoRenta> getContratosActivos() {
        return contratos.stream().filter(c -> c.getEstado() == com.rentacar.enums.EstadoContrato.ACTIVO).toList();
    }

    public List<ContratoRenta> getContratosPorCliente(int clienteId) {
        return contratos.stream().filter(c -> c.getCliente().getId() == clienteId).toList();
    }

    public void eliminarCategoria(int id) {
        CategoriaAuto categoria = categoriasMap.remove(id);
        if (categoria != null) {
            categorias.remove(categoria);
        }
    }

    public void eliminarAuto(int id) {
        Auto auto = autosMap.remove(id);
        if (auto != null) {
            autos.remove(auto);
            placasRegistradas.remove(auto.getPlaca());
            if (auto.getCategoria() != null) {
                auto.getCategoria().removerAuto(auto);
            }
        }
    }

    public void eliminarCliente(int id) {
        Cliente cliente = clientesMap.remove(id);
        if (cliente != null) {
            clientes.remove(cliente);
            documentosClientes.remove(cliente.getDocumento());
        }
    }

    public void eliminarAgente(int id) {
        AgenteRenta agente = agentesMap.remove(id);
        if (agente != null) {
            agentes.remove(agente);
            documentosAgentes.remove(agente.getDocumento());
        }
    }

    public void eliminarContrato(int id) {
        ContratoRenta contrato = contratosMap.remove(id);
        if (contrato != null) {
            contratos.remove(contrato);
        }
    }

    public int getTotalCategorias() { return categorias.size(); }
    public int getTotalAutos() { return autos.size(); }
    public int getTotalClientes() { return clientes.size(); }
    public int getTotalAgentes() { return agentes.size(); }
    public int getTotalContratos() { return contratos.size(); }

    @Override
    public String toString() {
        return String.format("RentACar{nombre='%s', direccion='%s', categorias=%d, autos=%d, clientes=%d, agentes=%d, contratos=%d}", 
                nombre, direccion, categorias.size(), autos.size(), clientes.size(), agentes.size(), contratos.size());
    }
}