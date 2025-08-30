package com.sofka.cuenta_movimiento.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovimientoRequestDTO {
    private String tipoMovimiento;
    private Double valor;
    private String numeroCuenta;
}
