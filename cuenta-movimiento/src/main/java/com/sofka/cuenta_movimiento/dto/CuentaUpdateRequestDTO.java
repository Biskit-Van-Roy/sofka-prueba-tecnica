package com.sofka.cuenta_movimiento.dto;

import lombok.Data;

@Data
public class CuentaUpdateRequestDTO {
    private String tipoCuenta;
    private Double saldoInicial;
    private Boolean estado;
}
