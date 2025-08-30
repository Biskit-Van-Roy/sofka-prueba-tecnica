package com.sofka.cliente_persona.service.mapper;

import com.sofka.cliente_persona.dto.ClienteRequestDTO;
import com.sofka.cliente_persona.dto.ClienteResponseDTO;
import com.sofka.cliente_persona.model.Cliente;


public class ClienteMapper {


    public static ClienteResponseDTO toDTO(Cliente cliente) {
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO();
        clienteResponseDTO.setClienteId(String.valueOf(cliente.getClienteId()));
        clienteResponseDTO.setEstado(cliente.getEstado());
        clienteResponseDTO.setNombre(cliente.getNombre());
        clienteResponseDTO.setGenero(cliente.getGenero());
        clienteResponseDTO.setEdad(cliente.getEdad());
        clienteResponseDTO.setIdentificacion(cliente.getIdentificacion());
        clienteResponseDTO.setDireccion(cliente.getDireccion());
        clienteResponseDTO.setTelefono(cliente.getTelefono());

        return clienteResponseDTO;
    }

    public static Cliente toModel(ClienteRequestDTO clienteRequestDTO) {
        Cliente cliente = new Cliente();
        cliente.setEstado(clienteRequestDTO.getEstado());
        cliente.setContrasena(clienteRequestDTO.getContrasena());
        cliente.setNombre(clienteRequestDTO.getNombre());
        cliente.setIdentificacion(clienteRequestDTO.getIdentificacion());
        cliente.setDireccion(clienteRequestDTO.getDireccion());
        cliente.setEdad(clienteRequestDTO.getEdad());
        cliente.setGenero(clienteRequestDTO.getGenero());
        cliente.setTelefono(clienteRequestDTO.getTelefono());

        return cliente;
    }
}