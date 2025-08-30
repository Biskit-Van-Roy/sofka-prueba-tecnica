package com.sofka.cuenta_movimiento.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import com.sofka.cliente_persona.kafka.ClienteEvent;
import com.sofka.cuenta_movimiento.model.Cuenta;
import com.sofka.cuenta_movimiento.repository.CuentaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {
    private final CuentaRepository cuentaRepository;

    public KafkaConsumer(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }
    @KafkaListener(topics = "cliente-persona", groupId= "cuenta-movimiento")
    public void consumirClienteCreador(byte[] mensaje){
        try{
            ClienteEvent event = ClienteEvent.parseFrom(mensaje);
            log.info("Informacion de la cuenta {}",event);
            String clienteId = event.getClienteId();
            log.info("el cliente id es {}",clienteId);
            if(cuentaRepository.findByClienteId(clienteId).isPresent()){
                log.info("Ya existe cuenta para el cliente: {}",clienteId);
                return;
            }
            Cuenta nuevaCuenta = new Cuenta();
            nuevaCuenta.setNumeroCuenta(generarNumeroCuenta());
            nuevaCuenta.setTipoCuenta("AHORROS"); // Ajusta según tu lógica
            nuevaCuenta.setSaldoInicial(0.0);
            nuevaCuenta.setEstado(true);
            nuevaCuenta.setClienteId(clienteId);

            cuentaRepository.save(nuevaCuenta);
            log.info("✅Cuenta creada para clienteId: {}", clienteId);

        } catch (InvalidProtocolBufferException e) {
            log.info("⛔️  Error deserializando el mensaje de Kafka");
            throw new RuntimeException(e);
        }


    }
    private String generarNumeroCuenta() {
        return "" + System.currentTimeMillis();
    }

}
