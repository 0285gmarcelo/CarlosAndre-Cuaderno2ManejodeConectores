CREATE DATABASE IF NOT EXISTS academia;
USE academia;
CREATE TABLE profesor (
    dni VARCHAR(10) NOT NULL PRIMARY KEY,
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    direccion VARCHAR(100),
    fecha_nacimiento DATE,
    codigo_postal VARCHAR(5),
    telefono VARCHAR(15)
);

CREATE TABLE alumno (
    dni VARCHAR(10) NOT NULL PRIMARY KEY,
    nombre VARCHAR(50),
    apellidos VARCHAR(100),
    direccion VARCHAR(100),
    fecha_nacimiento DATE,
    codigo_postal VARCHAR(5),
    telefono VARCHAR(15)
);

CREATE TABLE curso (
    cod_curso INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(50)
);

CREATE TABLE aula (
    cod_aula INT NOT NULL PRIMARY KEY,
    num_piso INT,
    num_pupitres INT
);

CREATE TABLE asignatura (
    cod_asignatura INT NOT NULL PRIMARY KEY,
    nombre VARCHAR(50),
    num_horas INT,
    dni_profesor VARCHAR(10) NOT NULL,
    cod_curso INT NOT NULL,

    CONSTRAINT fk_profesor
        FOREIGN KEY (dni_profesor)
        REFERENCES profesor(dni),

    CONSTRAINT fk_curso
        FOREIGN KEY (cod_curso)
        REFERENCES curso(cod_curso)
);

CREATE TABLE matricula (
    dni_alumno VARCHAR(10) NOT NULL,
    cod_asignatura INT NOT NULL,
    calificacion DECIMAL(4,2),
    incidencias VARCHAR(255),

    PRIMARY KEY (dni_alumno, cod_asignatura),

    CONSTRAINT fk_alumno
        FOREIGN KEY (dni_alumno)
        REFERENCES alumno(dni),

    CONSTRAINT fk_matricula_asignatura
        FOREIGN KEY (cod_asignatura)
        REFERENCES asignatura(cod_asignatura)
);

CREATE TABLE horario (
    cod_asignatura INT NOT NULL,
    cod_aula INT NOT NULL,
    mes INT NOT NULL,
    dia INT NOT NULL,
    hora TIME NOT NULL,

    PRIMARY KEY (cod_asignatura, cod_aula, mes, dia, hora),

    CONSTRAINT fk_horario_asignatura
        FOREIGN KEY (cod_asignatura)
        REFERENCES asignatura(cod_asignatura),

    CONSTRAINT fk_aula
        FOREIGN KEY (cod_aula)
        REFERENCES aula(cod_aula)
);

INSERT INTO profesor VALUES (
    '11111111A', 'Carlos', 'Garcia Lopez',
    'Calle Mayor 10', '1980-05-15',
    '50001', '611111111'
);

INSERT INTO alumno VALUES (
    '22222222B', 'Laura', 'Martin Perez',
    'Calle San Miguel 20', '2005-03-12',
    '50002', '622222222'
);

INSERT INTO alumno VALUES (
    '33333333C', 'Pablo', 'Gomez Ruiz',
    'Calle Alfonso 15', '2004-07-20',
    '50003', '633333333'
);

INSERT INTO curso VALUES (1, '1 DAM');
INSERT INTO curso VALUES (2, '2 DAM');

INSERT INTO aula VALUES (101, 1, 25);
INSERT INTO aula VALUES (201, 2, 30);

INSERT INTO asignatura VALUES (
    1, 'Programacion', 8, '11111111A', 1
);

INSERT INTO asignatura VALUES (
    2, 'Acceso a datos', 6, '11111111A', 2
);

INSERT INTO matricula VALUES (
    '22222222B', 1, 8.50, 'Ninguna'
);

INSERT INTO matricula VALUES (
    '33333333C', 2, 7.00, 'Ninguna'
);

INSERT INTO horario VALUES (
    1, 101, 11, 24, '09:00:00'
);

INSERT INTO horario VALUES (
    2, 201, 11, 24, '12:00:00'
);
