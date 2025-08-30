CREATE TABLE IF NOT EXISTS clientes (
    cliente_id BIGINT PRIMARY KEY,
    contrasena VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50),
    edad INT,
    identificacion VARCHAR(255) UNIQUE NOT NULL,
    direccion VARCHAR(255),
    telefono VARCHAR(20)
);
