package com.sofka.cliente_persona.dto;

import com.sofka.cliente_persona.dto.validators.CreateClienteValidationGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteRequestDTO {


    @NotNull(message = "El estado es requerido")
    private Boolean estado;

    @NotBlank(message = "La contrasena es requerida")
    private String contrasena;

    // Campos heredados de Persona
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El genero es requerido")
    private String genero;

    @NotNull(message = "La edad es requerida")
    private Integer edad;

    @NotBlank(message = "La identificacion es requerida", groups = CreateClienteValidationGroup.class)
    private String identificacion;

    @NotBlank(message = "La direccion es requerida")
    private String direccion;

    @NotBlank(message = "El telefono es requerido")
    private String telefono;
}