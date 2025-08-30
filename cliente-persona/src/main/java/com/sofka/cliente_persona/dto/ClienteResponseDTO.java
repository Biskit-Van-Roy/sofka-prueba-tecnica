package com.sofka.cliente_persona.dto;

import lombok.Data;

@Data
public class ClienteResponseDTO {

    private String clienteId;
    private boolean estado;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

}