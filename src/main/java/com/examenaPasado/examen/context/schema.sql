CREATE DATABASE portfolio;
USE portfolio;

CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL
);

CREATE TABLE especialidades (
    codigo VARCHAR(6) PRIMARY KEY,
    nombre VARCHAR(255)
);

CREATE TABLE especialistas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    especialidad VARCHAR(6),
    FOREIGN KEY (especialidad) REFERENCES especialidades(codigo) ON DELETE SET NULL
);

CREATE TABLE tareas (
    codigo VARCHAR(5) PRIMARY KEY,
    proyecto INT,
    nombre VARCHAR(255) NOT NULL,
    especialidad VARCHAR(6) NOT NULL,
    especialista INT,
    FOREIGN KEY (proyecto) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (especialidad) REFERENCES especialidades(codigo) ON DELETE CASCADE,
    FOREIGN KEY (especialista) REFERENCES especialistas(id) ON DELETE SET NULL
);
