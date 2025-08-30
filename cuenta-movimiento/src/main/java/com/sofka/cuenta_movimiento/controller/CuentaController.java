package com.sofka.cuenta_movimiento.controller;
import com.sofka.cuenta_movimiento.dto.CuentaRequestDTO;
import com.sofka.cuenta_movimiento.dto.CuentaResponseDTO;
import com.sofka.cuenta_movimiento.dto.CuentaUpdateRequestDTO;
import com.sofka.cuenta_movimiento.dto.ReporteEstadoCuentaDTO;
import com.sofka.cuenta_movimiento.dto.validators.CreateCuentaValidationGroup;
import com.sofka.cuenta_movimiento.service.CuentaService;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;
    @GetMapping
    public ResponseEntity<List<CuentaResponseDTO>> getCuentas() {
        List<CuentaResponseDTO> cuentas = cuentaService.getCuentas();
        return ResponseEntity.ok().body(cuentas);
    }

    @PostMapping
    public ResponseEntity<CuentaResponseDTO> createCuenta(
            @Validated({Default.class, CreateCuentaValidationGroup.class}) @RequestBody CuentaRequestDTO cuentaRequestDTO) {
        CuentaResponseDTO newCuenta = cuentaService.createCuenta(cuentaRequestDTO);
        return ResponseEntity.ok(newCuenta);
    }

    @PutMapping("/{numeroCuenta}")
    public ResponseEntity<CuentaResponseDTO> updateCuenta(
            @PathVariable String numeroCuenta,
            @RequestBody CuentaUpdateRequestDTO cuentaUpdateRequestDTO) {
        CuentaResponseDTO updatedCuenta = cuentaService.updateCuenta(numeroCuenta, cuentaUpdateRequestDTO);
        return ResponseEntity.ok(updatedCuenta);
    }

    @DeleteMapping("/{numeroCuenta}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable String numeroCuenta) {
        cuentaService.deleteCuenta(numeroCuenta);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/reportes")
    public ResponseEntity<ReporteEstadoCuentaDTO> obtenerReporte(
            @RequestParam("clienteId") String clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {
        ReporteEstadoCuentaDTO reporte = cuentaService.generarReporte(clienteId, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }
}
