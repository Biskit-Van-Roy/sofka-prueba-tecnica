package com.sofka.cliente_persona.dto;

import lombok.Data;

@Data
public class ClienteUpdateRequestDTO {
    private Boolean estado;
    private String contrasena;
    private String nombre;
    private String genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}
