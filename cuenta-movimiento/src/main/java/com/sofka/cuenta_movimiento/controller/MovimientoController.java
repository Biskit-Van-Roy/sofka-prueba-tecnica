package com.sofka.cuenta_movimiento.controller;

import com.sofka.cuenta_movimiento.dto.MovimientoRequestDTO;
import com.sofka.cuenta_movimiento.dto.MovimientoResponseDTO;
import com.sofka.cuenta_movimiento.dto.MovimientoUpdateRequestDTO;
import com.sofka.cuenta_movimiento.dto.validators.CreateMovimientoValidationGroup;
import com.sofka.cuenta_movimiento.service.CuentaService;
import com.sofka.cuenta_movimiento.service.MovimientoService;
import jakarta.validation.groups.Default;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<MovimientoResponseDTO>> getMovimientos(){
        List<MovimientoResponseDTO> movimientos = movimientoService.getMovimientos();
        return ResponseEntity.ok().body(movimientos);
    }
    @PostMapping("/{numeroCuenta}")
    public ResponseEntity<MovimientoResponseDTO> createMovimiento(@PathVariable String numeroCuenta, @Validated({Default.class, CreateMovimientoValidationGroup.class}) @RequestBody MovimientoRequestDTO movimientoRequestDTO){
        MovimientoResponseDTO nuevoMovimiento = movimientoService.createMovimiento(numeroCuenta, movimientoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMovimiento);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MovimientoResponseDTO> updateMovimiento(@PathVariable Long id, @RequestBody MovimientoUpdateRequestDTO movimientoUpdateRequestDTO){
        MovimientoResponseDTO updateadMovimiento = movimientoService.updateMovimiento(id, movimientoUpdateRequestDTO);
        if(updateadMovimiento != null){
            return ResponseEntity.ok(updateadMovimiento);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id){
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }

}
