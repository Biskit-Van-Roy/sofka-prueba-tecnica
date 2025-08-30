package com.sofka.cuenta_movimiento.service;

import com.sofka.cuenta_movimiento.repository.CuentaRepository;
import com.sofka.cuenta_movimiento.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {
//    @Autowired
//    private ClienteRepository clienteRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;
//    public EstadoCuentaReporteDTO createEstadoCuenta(String identificacion, LocalDateTime fechaInicio, LocalDateTime fechaFin){
//        Cliente cliente = clienteRepository.findByIdentificacion(identificacion).orElseThrow(()->new ResourceNotFoundException("Cliente no encontrado"))
//    }
}
