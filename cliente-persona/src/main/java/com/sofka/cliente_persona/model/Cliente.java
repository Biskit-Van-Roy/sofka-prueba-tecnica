package com.sofka.cliente_persona.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Table(name ="clientes")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona{


    @Id
    @Column(name = "cliente_id", unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clienteId;


    @NotNull
    @Column(name="contrasena")
    private String contrasena;

    @NotNull
    @Column(name="estado")
    private Boolean estado;

}
