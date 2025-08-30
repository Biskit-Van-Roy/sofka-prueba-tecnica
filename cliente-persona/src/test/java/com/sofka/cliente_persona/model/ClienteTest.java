package com.sofka.cliente_persona.model;

import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {
    @Test
    void testClienteCreation() {
        String nombre = "Michael Hidalgo";
        String genero = "MASCULINO";
        int edad = 30;
        String identificacion = "1002345678";
        String direccion = "Calle 10 # 5-25";
        String telefono = "3104567890";
        String contrasena = "password123";
        boolean estado = true;

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setGenero(genero);
        cliente.setEdad(edad);
        cliente.setIdentificacion(identificacion);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setContrasena(contrasena);
        cliente.setEstado(estado);
        assertNotNull(cliente, "El objeto Cliente no debe ser nulo.");
        assertEquals(nombre, cliente.getNombre(), "El nombre del cliente no coincide.");
        assertEquals(genero, cliente.getGenero(), "El género del cliente no coincide.");
        assertEquals(edad, cliente.getEdad(), "La edad del cliente no coincide.");
        assertEquals(identificacion, cliente.getIdentificacion(), "La identificación del cliente no coincide.");
        assertEquals(direccion, cliente.getDireccion(), "La dirección del cliente no coincide.");
        assertEquals(telefono, cliente.getTelefono(), "El teléfono del cliente no coincide.");
        assertEquals(contrasena, cliente.getContrasena(), "La contraseña del cliente no coincide.");
        assertEquals(estado, cliente.getEstado(), "El estado del cliente no coincide.");
    }
}
