package com.sofka.cuenta_movimiento.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReporteEstadoCuentaDTO {
    private String clienteId;
    private List<CuentaReporteDTO> cuentas;

    @Data
    @NoArgsConstructor
    public static class CuentaReporteDTO {
        private String numeroCuenta;
        private String tipoCuenta;
        private Double saldoInicial;
        private List<MovimientoResponseDTO> movimientos;
    }
}
