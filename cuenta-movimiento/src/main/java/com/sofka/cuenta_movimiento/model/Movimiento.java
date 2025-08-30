package com.sofka.cuenta_movimiento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name="movimientos")
@Data
@NoArgsConstructor
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime fecha;
    @NotNull(message = "El tipo de movimiento es requerido")
    private String tipoMovimiento;
    @NotNull(message = "El valor es requerido")
    private Double valor;
    @NotNull(message = "El saldo es requerido")
    private Double saldo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_cuenta")
    private Cuenta cuenta;

}
