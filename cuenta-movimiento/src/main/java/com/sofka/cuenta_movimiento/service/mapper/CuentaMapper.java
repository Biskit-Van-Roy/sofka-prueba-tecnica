package com.sofka.cuenta_movimiento.service.mapper;

import com.sofka.cuenta_movimiento.dto.CuentaRequestDTO;
import com.sofka.cuenta_movimiento.dto.CuentaResponseDTO;
import com.sofka.cuenta_movimiento.model.Cuenta;

public class CuentaMapper {
    public static CuentaResponseDTO toDTO(Cuenta cuenta){
        CuentaResponseDTO cuentaDto = new CuentaResponseDTO();
        cuentaDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDto.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDto.setClienteId(cuenta.getClienteId());
        cuentaDto.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDto.setEstado(cuenta.getEstado());
        return cuentaDto;
    }
    public static Cuenta toModel(CuentaRequestDTO cuentaRequestDTO){
        Cuenta cuenta = new Cuenta();
        cuenta.setNumeroCuenta(cuentaRequestDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaRequestDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaRequestDTO.getSaldoInicial());
        cuenta.setClienteId(cuentaRequestDTO.getClienteId());
        cuenta.setEstado(cuentaRequestDTO.getEstado());
        return cuenta;
    }
}