package com.sofka.cuenta_movimiento.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimientoUpdateRequestDTO {
    private String tipoMovimiento;
    private Double valor;
    private Double saldo;
}
