CREATE DATABASE IF NOT EXISTS tienda_videojuegos;
USE tienda_videojuegos;

CREATE TABLE IF NOT EXISTS videojuegos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    CONSTRAINT chk_videojuegos_stock CHECK (stock >= 0)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    videojuego_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_total DECIMAL(10,2) NOT NULL,
    fecha TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_ventas_cantidad CHECK (cantidad > 0),
    CONSTRAINT fk_ventas_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    CONSTRAINT fk_ventas_videojuego FOREIGN KEY (videojuego_id) REFERENCES videojuegos(id)
) ENGINE=InnoDB;

-- Datos de prueba (ejecutar solo si las tablas están vacías)
INSERT INTO videojuegos (titulo, plataforma, precio, stock)
SELECT 'Minecraft', 'PC', 30.00, 10
WHERE NOT EXISTS (SELECT 1 FROM videojuegos WHERE titulo='Minecraft' AND plataforma='PC');

INSERT INTO videojuegos (titulo, plataforma, precio, stock)
SELECT 'GTA V', 'PS5', 40.00, 15
WHERE NOT EXISTS (SELECT 1 FROM videojuegos WHERE titulo='GTA V' AND plataforma='PS5');

INSERT INTO clientes (nombre, email)
SELECT 'Pedro', 'pedro@gmail.com'
WHERE NOT EXISTS (SELECT 1 FROM clientes WHERE email='pedro@gmail.com');

INSERT INTO clientes (nombre, email)
SELECT 'Maria', 'maria@gmail.com'
WHERE NOT EXISTS (SELECT 1 FROM clientes WHERE email='maria@gmail.com');
