-- MySQL dump 10.13  Distrib 8.0.41, for Linux (x86_64)
--
-- Host: localhost    Database: laboratorio_ver2
-- ------------------------------------------------------
-- Server version	8.0.41-0ubuntu0.24.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  `horas` int NOT NULL,
  `id_proyecto` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_proyecto` (`id_proyecto`),
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`id_proyecto`) REFERENCES `proyecto` (`id_proyecto`),
  CONSTRAINT `actividad_chk_1` CHECK ((`horas` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (46,'prueba 2',2,8),(47,'Diseñar arquitectura del sistema y modelos de datos.',15,1),(48,'Implementar módulo de control de inventario.',20,1),(49,'Configurar sistema de autenticación y roles.',10,1),(54,'Primer actividad',12,4),(55,'Segunda Actividad',50,4),(56,'Actividad Prueba',46,4),(57,'Prueba JS',100,9),(58,'PRUEBA JS',100,9),(59,'Prueba JS2',10,10),(60,'Prueba JS2',12,10),(61,'Prueba JS3 45',3,11);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES (14,1,4,55,'2026-02-27 20:38:00',NULL,''),(15,1,5,61,'2026-02-28 18:57:00',NULL,'Prueba que no sera eliminada'),(23,4,1,48,'2026-03-07 21:06:00',NULL,''),(24,1,7,54,'2026-03-07 21:18:00',NULL,''),(25,4,13,54,'2026-03-07 21:19:00',NULL,'');
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrera` (
  `id_carrera` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) NOT NULL,
  PRIMARY KEY (`id_carrera`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrera`
--

LOCK TABLES `carrera` WRITE;
/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
INSERT INTO `carrera` VALUES (3,'Arquitectura'),(4,'Comunicacion y Periodismo'),(5,'Derecho'),(6,'Diseño Industrial'),(7,'Economia'),(2,'Ingenieria Civil'),(8,'Ingenieria Electrica y Electronica'),(1,'Ingenieria en Computacion'),(9,'Ingenieria Industrial'),(10,'Ingenieria Mecanica'),(11,'Pedagogia'),(12,'Planificacion para el Desarrollo Agropecuario'),(13,'Relaciones Internacionales'),(14,'Sociologia');
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,6,'Impresora Ender PRUEBA HOY','Laboratorio Z',3,'PRUEBAHOY'),(4,2,'Computadora','Laboratorio A',2,'XYZ987'),(5,5,'Descripcion Prueba 2','Ubicacion Prueba 2',2,'CodigoInventarioPrueba2'),(7,7,'Descripcion Prueba 3','Ubicacion Prueba 3',2,'CodigoInventarioPrueba3'),(9,1,'bbbbb','bbbbbb',5,'aaaaaaaa'),(10,1,'zzzzzzzzzzzzzz','zzzzzzzzzzzz',1,'zzzzzzzzzzzz'),(11,10,'Prueba Asistencia','Asistencia',1,'12345'),(12,7,'Reservado 1','Reservado 1',1,'reservado1'),(13,1,'Fuera de servicio','Fuera de servicio',3,'FDSERV'),(15,1,'Equipo 1','Laboratorio',4,'11111'),(16,9,'Equipo 2','Laboratorio A',5,'11112'),(17,6,'Equipo 3','Laboratorio B',4,'11113'),(18,1,'EQUIPOPRUEBA','EQUIPOPRUEBA',1,'EQUIPOPRUEBA');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estatus`
--

DROP TABLE IF EXISTS `estatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatus` (
  `id_estatus` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(120) NOT NULL,
  PRIMARY KEY (`id_estatus`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estatus`
--

LOCK TABLES `estatus` WRITE;
/*!40000 ALTER TABLE `estatus` DISABLE KEYS */;
INSERT INTO `estatus` VALUES (1,'Disponible'),(5,'En mantenimiento'),(2,'En uso'),(4,'Fuera de servicio'),(3,'Reservado');
/*!40000 ALTER TABLE `estatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `proyecto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto` (
  `id_proyecto` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `descripcion` text,
  `objetivos` text,
  `clave` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_proyecto`),
  UNIQUE KEY `clave` (`clave`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyecto`
--

LOCK TABLES `proyecto` WRITE;
/*!40000 ALTER TABLE `proyecto` DISABLE KEYS */;
INSERT INTO `proyecto` VALUES (1,'Sistema de Gestión de Laboratorio','Proyecto para controlar equipos, visitas y actividades del laboratorio.','Optimizar procesos, mejorar control interno y automatizar reportes.','LAB-2025'),(4,'Proyecto Prueba','Proyecto prueba ','Probar Create del proyecto','PRUEBAPROYECTO'),(8,'Prueba2 Editado','Proyecto prueba 2 Editado','proyecto prueba 2 Editado','prueba2Editado'),(9,'Prueba JS','Prueba JS','Prueba JS','PRUEBAJS'),(10,'Prueba JS2','Prueba JS2','Prueba JS2','Prueba JS2'),(11,'Prueba JS3 PUT','Prueba JS3 Proyecto PAPIME','Prueba JS3 PUT','Prueba JS3');
/*!40000 ALTER TABLE `proyecto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos_carreras`
--

DROP TABLE IF EXISTS `proyectos_carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos_carreras` (
  `proyecto_id` int NOT NULL,
  `carrera_id` int NOT NULL,
  KEY `FK70f2wwsgbey8scfghdu3wqlaf` (`carrera_id`),
  KEY `FKtabdh4i8w81sw2v29wwtr5ed0` (`proyecto_id`),
  CONSTRAINT `FK70f2wwsgbey8scfghdu3wqlaf` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`id_carrera`),
  CONSTRAINT `FKtabdh4i8w81sw2v29wwtr5ed0` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos_carreras`
--

LOCK TABLES `proyectos_carreras` WRITE;
/*!40000 ALTER TABLE `proyectos_carreras` DISABLE KEYS */;
INSERT INTO `proyectos_carreras` VALUES (8,3),(8,4),(8,5),(1,1),(1,2),(1,7),(4,1),(9,3),(9,6),(9,8),(9,10),(10,1),(10,3),(10,7),(10,13),(10,14),(11,1),(11,3);
/*!40000 ALTER TABLE `proyectos_carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proyectos_responsables`
--

DROP TABLE IF EXISTS `proyectos_responsables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos_responsables` (
  `proyecto_id` int NOT NULL,
  `visita_id` int NOT NULL,
  KEY `FK95vvjojv92nlgfixe5g673ps3` (`visita_id`),
  KEY `FKonfsd99141jl3hgv23t0uyev7` (`proyecto_id`),
  CONSTRAINT `FK95vvjojv92nlgfixe5g673ps3` FOREIGN KEY (`visita_id`) REFERENCES `visita` (`id_visita`),
  CONSTRAINT `FKonfsd99141jl3hgv23t0uyev7` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id_proyecto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proyectos_responsables`
--

LOCK TABLES `proyectos_responsables` WRITE;
/*!40000 ALTER TABLE `proyectos_responsables` DISABLE KEYS */;
INSERT INTO `proyectos_responsables` VALUES (8,6),(8,7),(1,6),(1,7),(4,1),(4,3),(9,4),(9,7),(10,1),(10,4),(10,6),(10,7),(11,3);
/*!40000 ALTER TABLE `proyectos_responsables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`id_rol`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (4,'Administrador'),(5,'Becario'),(1,'Estudiante'),(2,'Profesor'),(3,'Servicio Social'),(6,'Visitante');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_equipo`
--

DROP TABLE IF EXISTS `tipo_equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_equipo` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(120) NOT NULL,
  PRIMARY KEY (`id_tipo`),
  UNIQUE KEY `descripcion` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_equipo`
--

LOCK TABLES `tipo_equipo` WRITE;
/*!40000 ALTER TABLE `tipo_equipo` DISABLE KEYS */;
INSERT INTO `tipo_equipo` VALUES (2,'Computadora de escritorio'),(9,'Equipo de redes'),(10,'Herramientas de laboratorio'),(5,'Impresora 3D'),(1,'Laptop'),(3,'Multímetro'),(4,'Osciloscopio'),(6,'Proyector'),(7,'Robots educativos'),(8,'Tablet');
/*!40000 ALTER TABLE `tipo_equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usr` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `nombre` varchar(120) NOT NULL,
  `password` varchar(255) NOT NULL,
  `es_admin` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usr`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','Administrador','$2a$10$K0Qk6bzsg8P7.mYVrsP7vOkQjqAxeDfLN2oRCEQEr.pAqNnQjuLNW',1),(2,'admin2','Administrador Secundario','$2a$10$WmUHOxUmPnQQuo6eb6UgKuhG9BeY2Emmt756f3bfyM9/mF04j1YdO',1),(3,'juan','Juan Perez','$2a$10$OBBVSNtQpLSolMWLQGGPju398OcPNzFSxHRopNo1Nui0zjASIjMRK',0),(6,'usuarioPrueba','Usuario Prueba','$2a$10$muj7gsGom6YqZ63kjKkAOuvksOdT3ulk80naEguiObPgLakGGShKG',0),(7,'leonardo','Leonardo Olvera','$2a$10$EggwVw5b2L03TPEZQh6WLOY1gXjBTGYSIx2GMAMcrZg4r5xEenob6',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita`
--

DROP TABLE IF EXISTS `visita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita`
--

LOCK TABLES `visita` WRITE;
/*!40000 ALTER TABLE `visita` DISABLE KEYS */;
INSERT INTO `visita` VALUES (1,'319241329','Leonardo','Martinez','Olvera','leonardoolveram11@gmail.com',3,1),(3,'NOCUENTARFC','Juan','Perez','Martinez','email_2@email.com',2,5),(4,'123456789','NombrePrueba1','ApellidoMaternoPrueba1','ApellidoPaternoPrueba1','email@email.com',1,3),(6,'987654321','Prueba1','Prueba1','Prueba1','Prueba1@Prueba1.com',1,14),(7,'123454321','Prueba2','Prueba2','Prue','lewkweqk@mkfwjiweq.com',4,1);
/*!40000 ALTER TABLE `visita` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-02 20:27:19
