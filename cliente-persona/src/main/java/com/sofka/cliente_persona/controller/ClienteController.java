package com.sofka.cliente_persona.controller;

import com.sofka.cliente_persona.dto.ClienteRequestDTO;
import com.sofka.cliente_persona.dto.ClienteResponseDTO;
import com.sofka.cliente_persona.dto.ClienteUpdateRequestDTO;
import com.sofka.cliente_persona.dto.validators.CreateClienteValidationGroup;
import com.sofka.cliente_persona.service.ClienteService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }
    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getClientes(){
        List<ClienteResponseDTO> clientes = clienteService.getClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> createCliente(@Validated({Default.class, CreateClienteValidationGroup.class}) @RequestBody ClienteRequestDTO clienteRequestDTO) {
        ClienteResponseDTO newCliente = clienteService.createCliente(clienteRequestDTO);
        return ResponseEntity.ok(newCliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteUpdateRequestDTO clienteRequestDTO) {
        ClienteResponseDTO updatedCliente = clienteService.updateCliente(id, clienteRequestDTO);
        if (updatedCliente != null) {
            return ResponseEntity.ok(updatedCliente);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }



}



