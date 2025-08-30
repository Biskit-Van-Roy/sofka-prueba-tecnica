package com.sofka.cuenta_movimiento.repository;

import com.sofka.cuenta_movimiento.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, String> {
    List<Movimiento> findByCuentaNumeroCuentaAndFechaBetween(String numeroCuenta, LocalDateTime start, LocalDateTime end);
}
