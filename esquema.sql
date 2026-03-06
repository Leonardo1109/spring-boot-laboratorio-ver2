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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `actividad_aud`
--

DROP TABLE IF EXISTS `actividad_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad_aud` (
  `id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `horas` int DEFAULT NULL,
  `id_proyecto` int DEFAULT NULL,
  PRIMARY KEY (`rev`,`id`),
  CONSTRAINT `FK9ij89vp0k5iouhor53kg33bae` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `asistencia_aud`
--

DROP TABLE IF EXISTS `asistencia_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia_aud` (
  `id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `hora_entrada` datetime(6) DEFAULT NULL,
  `hora_salida` datetime(6) DEFAULT NULL,
  `observacion` text,
  `actividad_id` int DEFAULT NULL,
  `equipo_id` int DEFAULT NULL,
  `visita_id` int DEFAULT NULL,
  PRIMARY KEY (`rev`,`id`),
  CONSTRAINT `FKcufbxcx49dxympg7khl8atekx` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `carrera_aud`
--

DROP TABLE IF EXISTS `carrera_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrera_aud` (
  `id_carrera` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `nombre` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_carrera`),
  CONSTRAINT `FKcyjbg03lxbi47vaiuclcubsgs` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `observaciones` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codigo_inventario` (`codigo_inventario`),
  KEY `id_tipo` (`id_tipo`),
  KEY `id_estatus` (`id_estatus`),
  CONSTRAINT `equipo_ibfk_1` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_equipo` (`id_tipo`),
  CONSTRAINT `equipo_ibfk_2` FOREIGN KEY (`id_estatus`) REFERENCES `estatus` (`id_estatus`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipo_aud`
--

DROP TABLE IF EXISTS `equipo_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipo_aud` (
  `id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `codigo_inventario` varchar(50) DEFAULT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `observaciones` text,
  `ubicacion` varchar(100) DEFAULT NULL,
  `id_estatus` int DEFAULT NULL,
  `id_tipo` int DEFAULT NULL,
  PRIMARY KEY (`rev`,`id`),
  CONSTRAINT `FKhsnn0fuf8gb7jmpp9m3nlda3x` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `estatus_aud`
--

DROP TABLE IF EXISTS `estatus_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estatus_aud` (
  `id_estatus` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `descripcion` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_estatus`),
  CONSTRAINT `FK38g76fne26tjlxwgtip5hpscy` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `log_aud`
--

DROP TABLE IF EXISTS `log_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log_aud` (
  `id` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `accion` varchar(10) DEFAULT NULL,
  `usr_id` int DEFAULT NULL,
  `cambios` json DEFAULT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `registro_afectado` varchar(50) DEFAULT NULL,
  `tabla_afectada` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id`),
  CONSTRAINT `FK31163kgyq6c6hxwpehi5c992c` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proyecto_aud`
--

DROP TABLE IF EXISTS `proyecto_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyecto_aud` (
  `id_proyecto` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `clave` varchar(30) DEFAULT NULL,
  `descripcion` text,
  `nombre` varchar(150) DEFAULT NULL,
  `objetivos` text,
  PRIMARY KEY (`rev`,`id_proyecto`),
  CONSTRAINT `FKmfdbnpplfp3pc73steklfjlde` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `proyectos_carreras_aud`
--

DROP TABLE IF EXISTS `proyectos_carreras_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos_carreras_aud` (
  `rev` int NOT NULL,
  `proyecto_id` int NOT NULL,
  `carrera_id` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  PRIMARY KEY (`proyecto_id`,`rev`,`carrera_id`),
  KEY `FKs9yaxifg7l5h694i0u8132iwc` (`rev`),
  CONSTRAINT `FKs9yaxifg7l5h694i0u8132iwc` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `proyectos_responsables_aud`
--

DROP TABLE IF EXISTS `proyectos_responsables_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proyectos_responsables_aud` (
  `rev` int NOT NULL,
  `proyecto_id` int NOT NULL,
  `visita_id` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  PRIMARY KEY (`proyecto_id`,`rev`,`visita_id`),
  KEY `FKeoygbtpmb7wnwc1s3iir880d` (`rev`),
  CONSTRAINT `FKeoygbtpmb7wnwc1s3iir880d` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `revinfo`
--

DROP TABLE IF EXISTS `revinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime(6) NOT NULL,
  `user_id` int DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `rol_aud`
--

DROP TABLE IF EXISTS `rol_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol_aud` (
  `id_rol` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_rol`),
  CONSTRAINT `FKacthn5yg3v2oy40cs3uoangwl` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipo_cambio`
--

DROP TABLE IF EXISTS `tipo_cambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_cambio` (
  `id` tinyint NOT NULL,
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `tipo_equipo_aud`
--

DROP TABLE IF EXISTS `tipo_equipo_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_equipo_aud` (
  `id_tipo` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `descripcion` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_tipo`),
  CONSTRAINT `FKqjjan233is8u5ssvmh322y6o8` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
-- Table structure for table `usuario_aud`
--

DROP TABLE IF EXISTS `usuario_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_aud` (
  `id_usr` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `es_admin` bit(1) DEFAULT NULL,
  `nombre` varchar(120) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_usr`),
  CONSTRAINT `FK74gdm3bhlqa3diq16ouihfq6e` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  UNIQUE KEY `email` (`email`),
  KEY `id_rol` (`id_rol`),
  KEY `id_carrera` (`id_carrera`),
  CONSTRAINT `visita_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`),
  CONSTRAINT `visita_ibfk_2` FOREIGN KEY (`id_carrera`) REFERENCES `carrera` (`id_carrera`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `visita_aud`
--

DROP TABLE IF EXISTS `visita_aud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visita_aud` (
  `id_visita` int NOT NULL,
  `rev` int NOT NULL,
  `revtype` tinyint DEFAULT NULL,
  `apellido_materno` varchar(120) DEFAULT NULL,
  `apellido_paterno` varchar(120) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `no_cuenta_rfc` varchar(15) DEFAULT NULL,
  `nombre` varchar(120) DEFAULT NULL,
  `id_carrera` int DEFAULT NULL,
  `id_rol` int DEFAULT NULL,
  PRIMARY KEY (`rev`,`id_visita`),
  CONSTRAINT `FK9xpavie4s7m6o9xpycwmsrmjn` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-05 10:17:54
