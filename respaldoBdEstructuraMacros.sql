-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: laboratorio_ver2
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

-- =============================================================================== --

-- Empezar a eliminar la base de datos y asignar los privilegios para poderla levantar --

DROP DATABASE IF EXISTS laboratorio_ver2;
CREATE DATABASE laboratorio_ver2
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_0900_ai_ci;

-- Crear al usuario -- IMPORTANTE REVISAR LA CONTRAEÑA, NOMBRE Y LA RUTa (localhosto o una ip)

CREATE USER IF NOT EXISTS 'admin'@'localhost'
IDENTIFIED BY '1234';

-- Otorgarle privilegios

GRANT ALL PRIVILEGES ON laboratorio_ver2.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;

-- USAR LA BASE DE DATOS

USE laboratorio_ver2;
SET FOREIGN_KEY_CHECKS = 0;

-- =============================================================================== --

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
CREATE TABLE `actividad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `horas` int NOT NULL,
  `id_proyecto` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_proyecto` (`id_proyecto`),
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `actividad_chk_1` CHECK ((`horas` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
CREATE TABLE `asistencia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `visita_id` int NOT NULL,
  `equipo_id` int NOT NULL,
  `actividad_id` int NOT NULL,
  `hora_entrada` datetime NOT NULL,
  `hora_salida` datetime DEFAULT NULL,
  `observacion` text,
  PRIMARY KEY (`id`),
  KEY `visita_id` (`visita_id`),
  KEY `equipo_id` (`equipo_id`),
  KEY `actividad_id` (`actividad_id`),
  CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`visita_id`) REFERENCES `visita` (`id_visita`),
  CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`id`),
  CONSTRAINT `asistencia_ibfk_3` FOREIGN KEY (`actividad_id`) REFERENCES `actividad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
CREATE TABLE `carrera` (
  `id_carrera` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) NOT NULL,
  PRIMARY KEY (`id_carrera`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
CREATE TABLE `equipo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tipo` int NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `id_estatus` int NOT NULL,
  `codigo_inventario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_inventario` (`codigo_inventario`),
  KEY `id_tipo` (`id_tipo`),
  KEY `id_estatus` (`id_estatus`),
  CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_equipo` (`id_tipo`),
  CONSTRAINT `equipo_ibfk_2` FOREIGN KEY (`id_estatus`) REFERENCES `estatus` (`id_estatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `estatus`
--

DROP TABLE IF EXISTS `estatus`;
CREATE TABLE `estatus` (
  `id_estatus` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(120) NOT NULL,
  PRIMARY KEY (`id_estatus`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tabla_afectada` varchar(50) NOT NULL,
  `registro_afectado` varchar(50) NOT NULL,
  `accion` varchar(10) NOT NULL,
  `usr_id` int NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cambios` json DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
CREATE TABLE `proyecto` (
  `id_proyecto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text,
  `objetivos` text,
  `clave` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_proyecto`),
  UNIQUE KEY `clave` (`clave`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `proyectos_carreras`
--

DROP TABLE IF EXISTS `proyectos_carreras`;
CREATE TABLE `proyectos_carreras` (
  `proyecto_id` int NOT NULL,
  `carrera_id` int NOT NULL,
  KEY `FK70f2wwsgbey8scfghdu3wqlaf` (`carrera_id`),
  KEY `FKtabdh4i8w81sw2v29wwtr5ed0` (`proyecto_id`),
  CONSTRAINT `FK70f2wwsgbey8scfghdu3wqlaf` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`id_carrera`),
  CONSTRAINT `FKtabdh4i8w81sw2v29wwtr5ed0` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `proyectos_responsables`
--

DROP TABLE IF EXISTS `proyectos_responsables`;
CREATE TABLE `proyectos_responsables` (
  `proyecto_id` int NOT NULL,
  `visita_id` int NOT NULL,
  KEY `FK95vvjojv92nlgfixe5g673ps3` (`visita_id`),
  KEY `FKonfsd99141jl3hgv23t0uyev7` (`proyecto_id`),
  CONSTRAINT `FK95vvjojv92nlgfixe5g673ps3` FOREIGN KEY (`visita_id`) REFERENCES `visita` (`id_visita`),
  CONSTRAINT `FKonfsd99141jl3hgv23t0uyev7` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `tipo_equipo`
--

DROP TABLE IF EXISTS `tipo_equipo`;
CREATE TABLE `tipo_equipo` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(120) NOT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id_usr` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `password` varchar(255) NOT NULL,
  `es_admin` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usr`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
CREATE TABLE `visita` (
  `id_visita` int NOT NULL AUTO_INCREMENT,
  `no_cuenta_rfc` varchar(15) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `apellido_materno` varchar(120) NOT NULL,
  `apellido_paterno` varchar(120) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `id_rol` int NOT NULL,
  `id_carrera` int DEFAULT NULL,
  PRIMARY KEY (`id_visita`),
  UNIQUE KEY `no_cuenta_rfc` (`no_cuenta_rfc`),
  KEY `id_rol` (`id_rol`),
  KEY `id_carrera` (`id_carrera`),
  CONSTRAINT `visita_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `visita_ibfk_2` FOREIGN KEY (`id_carrera`) REFERENCES `carrera` (`id_carrera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


SET FOREIGN_KEY_CHECKS = 1;

-- Dump completed on 2026-02-15 14:25:22

-- =============================================================================== --
-- DATOS BASE DEL SISTEMA, CAMBIAR EN CASO NECESARIO
-- =============================================================================== --


INSERT INTO rol (descripcion) VALUES
('Estudiante'),
('Profesor'),
('Servicio Social'),
('Administrador'),
('Becario'),
('Visitante');

INSERT INTO carrera (nombre) VALUES
('Ingenieria en Computacion'),
('Ingenieria Civil'),
('Arquitectura'),
('Comunicacion y Periodismo'),
('Derecho'),
('Diseño Industrial'),
('Economia'),
('Ingenieria Electrica y Electronica'),
('Ingenieria Industrial'),
('Ingenieria Mecanica'),
('Pedagogia'),
('Planificacion para el Desarrollo Agropecuario'),
('Relaciones Internacionales'),
('Sociologia');

INSERT INTO estatus (descripcion) VALUES
('Disponible'),
('En uso'),
('Reservado'),
('Fuera de servicio'),
('En mantenimiento');

INSERT INTO tipo_equipo (descripcion) VALUES
('Laptop'),
('Computadora de escritorio'),
('Multímetro'),
('Osciloscopio'),
('Impresora 3D'),
('Proyector'),
('Robots educativos'),
('Tablet'),
('Equipo de redes'),
('Herramientas de laboratorio');
