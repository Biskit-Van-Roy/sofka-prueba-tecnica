package com.sofka.cuenta_movimiento.service.mapper;

import com.sofka.cuenta_movimiento.dto.MovimientoRequestDTO;
import com.sofka.cuenta_movimiento.dto.MovimientoResponseDTO;
import com.sofka.cuenta_movimiento.model.Movimiento;

import java.time.LocalDateTime;

public class MovimientoMapper {
    public static MovimientoResponseDTO toDTO(Movimiento movimiento){
        MovimientoResponseDTO movimientoResponseDTO = new MovimientoResponseDTO();
        movimientoResponseDTO.setId(movimiento.getId());
        movimientoResponseDTO.setFecha(movimiento.getFecha());
        movimientoResponseDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoResponseDTO.setValor(movimiento.getValor());
        movimientoResponseDTO.setSaldo(movimiento.getSaldo());
        movimientoResponseDTO.setNumeroCuenta(movimiento.getCuenta().getNumeroCuenta());
        return movimientoResponseDTO;
    }
    public static Movimiento toModel(MovimientoRequestDTO dto) {
        Movimiento movimiento = new Movimiento();
        movimiento.setTipoMovimiento(dto.getTipoMovimiento());
        movimiento.setValor(dto.getValor());
        movimiento.setFecha(LocalDateTime.now());
        return movimiento;
    }
}
