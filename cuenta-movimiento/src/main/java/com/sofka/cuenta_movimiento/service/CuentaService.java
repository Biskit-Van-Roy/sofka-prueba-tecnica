package com.sofka.cuenta_movimiento.service;

import com.sofka.cuenta_movimiento.dto.*;
import com.sofka.cuenta_movimiento.exception.ResourceNotFoundException;
import com.sofka.cuenta_movimiento.model.Cuenta;
import com.sofka.cuenta_movimiento.model.Movimiento;
import com.sofka.cuenta_movimiento.repository.CuentaRepository;
import com.sofka.cuenta_movimiento.repository.MovimientoRepository;
import com.sofka.cuenta_movimiento.service.mapper.CuentaMapper;
import com.sofka.cuenta_movimiento.service.mapper.MovimientoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<CuentaResponseDTO> getCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        return cuentas.stream().map(CuentaMapper::toDTO).toList();
    }

    public CuentaResponseDTO createCuenta(CuentaRequestDTO cuentaRequestDTO) {
        Cuenta newCuenta = cuentaRepository.save(CuentaMapper.toModel(cuentaRequestDTO));
        log.info("✅ Cuenta creada exitosamente");
        return CuentaMapper.toDTO(newCuenta);
    }

    public CuentaResponseDTO updateCuenta(String numeroCuenta, CuentaUpdateRequestDTO cuentaUpdateRequestDTO) {
        Cuenta cuentaExistente = cuentaRepository.findById(numeroCuenta)
                .orElseThrow(() -> new ResourceNotFoundException("⛔\uFE0F Cuenta con numeroCuenta " + numeroCuenta + " no encontrada"));
        if (cuentaUpdateRequestDTO.getTipoCuenta() != null) {
            cuentaExistente.setTipoCuenta(cuentaUpdateRequestDTO.getTipoCuenta());
        }
        if (cuentaUpdateRequestDTO.getSaldoInicial() != null) {
            cuentaExistente.setSaldoInicial(cuentaUpdateRequestDTO.getSaldoInicial());
        }
        if (cuentaUpdateRequestDTO.getEstado() != null) {
            cuentaExistente.setEstado(cuentaUpdateRequestDTO.getEstado());
        }
        Cuenta cuentaGuardada = cuentaRepository.save(cuentaExistente);
        log.info("🔄 Cuenta con numeroCuenta {} actualizada exitosamente", numeroCuenta);
        return CuentaMapper.toDTO(cuentaGuardada);
    }

    public void deleteCuenta(String numeroCuenta) {
        if (!cuentaRepository.existsById(numeroCuenta)) {
            throw new ResourceNotFoundException("⛔\uFE0F Cuenta con numeroCuenta " + numeroCuenta + " no encontrada");
        }
        cuentaRepository.deleteById(numeroCuenta);
        log.info("❌ Cuenta con numeroCuenta {} eliminada exitosamente", numeroCuenta);
    }

    public ReporteEstadoCuentaDTO generarReporte(String clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        // Validar fechas
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
        List<Cuenta> cuentas = cuentaRepository.findAll().stream()
                .filter(cuenta -> cuenta.getClienteId().equals(clienteId))
                .collect(Collectors.toList());
        ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
        reporte.setClienteId(clienteId);

        List<ReporteEstadoCuentaDTO.CuentaReporteDTO> cuentasDTO = cuentas.stream().map(cuenta -> {
            ReporteEstadoCuentaDTO.CuentaReporteDTO cuentaDTO = new ReporteEstadoCuentaDTO.CuentaReporteDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());

            // Buscar movimientos en el rango de fechas
            List<Movimiento> movimientos = movimientoRepository.findByCuentaNumeroCuentaAndFechaBetween(
                    cuenta.getNumeroCuenta(), fechaInicio, fechaFin);

            // Mapear movimientos a MovimientoResponseDTO
            List<MovimientoResponseDTO> movimientosDTO = movimientos.stream()
                    .map(MovimientoMapper::toDTO)
                    .collect(Collectors.toList());

            cuentaDTO.setMovimientos(movimientosDTO);
            return cuentaDTO;
        }).collect(Collectors.toList());

        reporte.setCuentas(cuentasDTO);
        return reporte;
    }
}
