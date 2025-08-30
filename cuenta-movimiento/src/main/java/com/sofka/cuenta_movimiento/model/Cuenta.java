package com.sofka.cuenta_movimiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cuentas")
@Data
@NoArgsConstructor
public class Cuenta {
    @Id
    @NotBlank(message = "El numero de cuenta es requerido")
    private String numeroCuenta;
    @NotBlank(message = "El tipo de cuenta es requerido")
    private String tipoCuenta;
    @NotNull(message = "El saldo es requerido")
    private Double saldoInicial;
    @NotNull(message = "El estado es requerido")
    private Boolean estado;
    @NotBlank(message = "El clienteId es requerido")
    private String clienteId; // Asociar con el cliente
}
