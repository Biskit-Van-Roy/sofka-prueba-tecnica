package com.sofka.cliente_persona.kafka;


import com.sofka.cliente_persona.model.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class KafkaProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    public KafkaProducer(KafkaTemplate<String,byte[]> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendEvent(Cliente cliente){
        ClienteEvent clienteEvent = ClienteEvent.newBuilder()
                .setClienteId(cliente.getClienteId().toString()).setNombre(cliente.getNombre()).setEventType("INFORMACION_CLIENTE").build();
        try{
            kafkaTemplate.send("cliente-persona", clienteEvent.toByteArray());
        } catch (Exception e){
            log.warn("Error enviando el evento {} ", clienteEvent);
        }
    }

}
