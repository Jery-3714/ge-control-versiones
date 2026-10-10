CREATE DATABASE IF NOT EXISTS gestion_empleados;

USE gestion_empleados;

CREATE TABLE IF NOT EXISTS empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_completo VARCHAR(100) NOT NULL,
    departamento VARCHAR(100) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    fecha_contratacion DATE NOT NULL,
    activo TINYINT(1) NOT NULL DEFAULT 1
);