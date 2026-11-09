CREATE DATABASE IF NOT EXISTS ACT01;
USE ACT01;
CREATE TABLE clientes (
  id int NOT NULL PRIMARY KEY,
  nombre VARCHAR(25),
  persona_contacto int(10),
  tlf_contacto int(10),
  CIF VARCHAR(20)
);
CREATE TABLE departamento (
  id int NOT NULL PRIMARY KEY,
  nombre VARCHAR(25),
  ubicacion VARCHAR(25)
);
CREATE TABLE proyecto (
  id INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(20),
  ubicacion VARCHAR(15),
  id_cliente int,
  CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES clientes (id)
);
CREATE TABLE empleado (
  id INT NOT NULL PRIMARY KEY,
  nombre VARCHAR(20),
  apellidos VARCHAR(25),
  DNI VARCHAR(10),
  salario DOUBLE (10, 2),
  id_departamento int,
  id_proyecto int,
  CONSTRAINT fk_departamento FOREIGN KEY (id_departamento) REFERENCES departamento (id),
  CONSTRAINT fk_proyecto FOREIGN KEY (id_proyecto) REFERENCES proyecto (id)
);
