package com.sofka.cuenta_movimiento.service;

import com.sofka.cuenta_movimiento.dto.MovimientoRequestDTO;
import com.sofka.cuenta_movimiento.dto.MovimientoResponseDTO;
import com.sofka.cuenta_movimiento.dto.MovimientoUpdateRequestDTO;
import com.sofka.cuenta_movimiento.exception.ResourceNotFoundException;
import com.sofka.cuenta_movimiento.exception.SaldoNoDisponibleException;
import com.sofka.cuenta_movimiento.model.Cuenta;
import com.sofka.cuenta_movimiento.model.Movimiento;
import com.sofka.cuenta_movimiento.repository.CuentaRepository;
import com.sofka.cuenta_movimiento.repository.MovimientoRepository;
import com.sofka.cuenta_movimiento.service.mapper.MovimientoMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<MovimientoResponseDTO> getMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        return movimientos.stream().map(MovimientoMapper::toDTO).toList();
    }

    @Transactional
    public MovimientoResponseDTO createMovimiento(String numeroCuenta, MovimientoRequestDTO movimientoRequestDTO) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta).orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada"));
        Double saldoActual = cuenta.getSaldoInicial();
        Double valorMovimiento = movimientoRequestDTO.getValor();
        Double nuevoSaldo = saldoActual + valorMovimiento;
        if(nuevoSaldo < 0){
          throw  new SaldoNoDisponibleException("Saldo no disponible");
        }
        Movimiento movimiento = new Movimiento();
        movimiento.setFecha(LocalDateTime.now());
        movimiento.setTipoMovimiento(movimientoRequestDTO.getTipoMovimiento());
        movimiento.setValor(valorMovimiento);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);
        movimientoRepository.save(movimiento);
        log.warn("✅ Movimiento creado exitosamente");
        return MovimientoMapper.toDTO(movimiento);
    }

    public MovimientoResponseDTO updateMovimiento(Long id, MovimientoUpdateRequestDTO movimientoUpdateRequestDTO) {
        Movimiento movimientoExistente = movimientoRepository.findById(String.valueOf(id)).orElseThrow(() -> new ResourceNotFoundException("⛔\\\\uFE0F Movimiento con id \"+id+\" no encontrado"));
        if (movimientoUpdateRequestDTO.getTipoMovimiento() != null) {
            movimientoExistente.setTipoMovimiento(movimientoUpdateRequestDTO.getTipoMovimiento());
        }
        if (movimientoUpdateRequestDTO.getSaldo() != null) {
            movimientoExistente.setSaldo(movimientoUpdateRequestDTO.getSaldo());
        }
        if (movimientoUpdateRequestDTO.getValor() != null) {
            movimientoExistente.setValor(movimientoUpdateRequestDTO.getValor());
        }
        Movimiento movimientoGuardado = movimientoRepository.save(movimientoExistente);
        log.warn("🔄 Movimiento con id {} actualizada exitosamente", id);
        return MovimientoMapper.toDTO(movimientoGuardado);
    }

    public void deleteMovimiento(Long id) {
        if (!movimientoRepository.existsById(String.valueOf(id))) {
            throw new ResourceNotFoundException("⛔\uFE0F Movimiento con id " + id + " no encontrado");
        }
        movimientoRepository.deleteById(String.valueOf(id));
        log.warn("❌ Movimiento con id {} eliminado exitosamente",id);
    }
}
