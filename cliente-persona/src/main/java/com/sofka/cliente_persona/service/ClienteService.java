package com.sofka.cliente_persona.service;


import com.sofka.cliente_persona.dto.ClienteRequestDTO;
import com.sofka.cliente_persona.dto.ClienteResponseDTO;
import com.sofka.cliente_persona.dto.ClienteUpdateRequestDTO;
import com.sofka.cliente_persona.exception.ResourceNotFoundException;
import com.sofka.cliente_persona.kafka.KafkaProducer;
import com.sofka.cliente_persona.model.Cliente;
import com.sofka.cliente_persona.repository.ClienteRepository;
import com.sofka.cliente_persona.service.mapper.ClienteMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final KafkaProducer kafkaProducer;

    public ClienteService(ClienteRepository clienteRepository, KafkaProducer kafkaProducer) {
        this.clienteRepository = clienteRepository;
        this.kafkaProducer = kafkaProducer;
    }

    public List<ClienteResponseDTO> getClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteMapper::toDTO).toList();
    }

    public ClienteResponseDTO createCliente(ClienteRequestDTO clienteRequestDTO) {
        Cliente newCliente = clienteRepository.save(ClienteMapper.toModel(clienteRequestDTO));
        log.warn("✅ Cliente creado exitosamente");
        kafkaProducer.sendEvent(newCliente);
        return ClienteMapper.toDTO(newCliente);
    }

    public ClienteResponseDTO updateCliente(Long id, ClienteUpdateRequestDTO clienteRequestDTO) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("⛔️ Cliente con id "+id+" no encontrado"));
        if (clienteRequestDTO.getContrasena() != null) {
            clienteExistente.setContrasena(clienteRequestDTO.getContrasena());
        }
        if (clienteRequestDTO.getEstado() != null) {
            clienteExistente.setEstado(clienteRequestDTO.getEstado());
        }
        if (clienteRequestDTO.getNombre() != null) {
            clienteExistente.setNombre(clienteRequestDTO.getNombre());
        }
        if (clienteRequestDTO.getGenero() != null) {
            clienteExistente.setGenero(clienteRequestDTO.getGenero());
        }
        if (clienteRequestDTO.getEdad() != null) {
            clienteExistente.setEdad(clienteRequestDTO.getEdad());
        }
        if (clienteRequestDTO.getIdentificacion() != null) {
            clienteExistente.setIdentificacion(clienteRequestDTO.getIdentificacion());
        }
        if (clienteRequestDTO.getDireccion() != null) {
            clienteExistente.setDireccion(clienteRequestDTO.getDireccion());
        }
        if (clienteRequestDTO.getTelefono() != null) {
            clienteExistente.setTelefono(clienteRequestDTO.getTelefono());
        }
        Cliente clienteGuardado = clienteRepository.save(clienteExistente);
        log.warn("🔄 Cliente con id {} actualizado exitosamente",id);
        return ClienteMapper.toDTO(clienteGuardado);
    }

    public void deleteCliente(Long id){
        if(!clienteRepository.existsById(id)){
                throw new ResourceNotFoundException("⛔️ Cliente con id "+id+" no encontrado");
        }
        clienteRepository.deleteById(id);
        log.warn("❌ Cliente con id {} eliminado exitosamente",id);
    }
}
