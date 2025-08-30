package com.sofka.cliente_persona.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Persona  {

    @NotNull
    @Column(name= "nombre")
    private String nombre;

    @NotNull
    @Column(name = "genero")
    private  String genero;

    @NotNull
    @Column(name= "edad")
    private Integer edad;

    @NotNull
    @Column(name="identificacion", unique = true)
    private String identificacion;

    @NotNull
    @Column(name = "direccion")
    private String direccion;

    @NotNull
    @Column(name="telefono")
    private String telefono;
}
