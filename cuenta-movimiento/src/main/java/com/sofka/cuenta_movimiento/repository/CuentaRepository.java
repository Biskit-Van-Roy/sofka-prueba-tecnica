package com.sofka.cuenta_movimiento.repository;

import com.sofka.cuenta_movimiento.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    Optional<Cuenta> findByClienteId(String clienteId);
    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
