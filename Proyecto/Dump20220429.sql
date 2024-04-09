CREATE DATABASE  IF NOT EXISTS `proyectoprograiv` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `proyectoprograiv`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: proyectoprograiv
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `facturas`
--

DROP TABLE IF EXISTS `facturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facturas` (
  `idFactura` int NOT NULL AUTO_INCREMENT,
  `nombreCompleto` varchar(500) NOT NULL,
  `cantidad` int NOT NULL,
  `iva` decimal(10,2) NOT NULL,
  `subTotal` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idFactura`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facturas`
--

LOCK TABLES `facturas` WRITE;
/*!40000 ALTER TABLE `facturas` DISABLE KEYS */;
INSERT INTO `facturas` VALUES (3,'',0,0.00,0.00,0.00),(4,'',0,0.00,0.00,0.00),(5,'',0,0.00,0.00,0.00),(6,'',0,0.00,0.00,0.00),(7,'',3,135850.00,1045000.00,1180850.00),(8,'',2,20150.00,155000.00,175150.00),(9,'',2,85800.00,660000.00,745800.00),(10,'',2,49465.00,380500.00,429965.00),(11,'Administrador',2,46865.00,360500.00,407365.00),(12,'Administrador',2,80665.00,620500.00,701165.00),(13,'Administrador',1,30615.00,235500.00,266115.00),(14,'Administrador',1,30615.00,235500.00,266115.00),(15,'Administrador',1,30615.00,235500.00,266115.00),(16,'Administrador',1,30615.00,235500.00,266115.00),(17,'Administrador',2,31915.00,245500.00,277415.00),(18,'Administrador',2,85800.00,660000.00,745800.00),(19,'Administrador',2,159972.41,1230557.00,1390529.41),(20,'Administrador',2,159972.41,1230557.00,1390529.41),(21,'Administrador',1,18850.00,145000.00,163850.00),(22,'Administrador',2,91722.41,705557.00,797279.41),(23,'Administrador',1,50050.00,385000.00,435050.00),(24,'Administrador',2,125522.41,965557.00,1091079.41),(25,'Administrador',1,16250.00,125000.00,141250.00),(26,'Administrador',1,30615.00,235500.00,266115.00),(27,'Maria José Tames',2,20150.00,155000.00,175150.00),(28,'Administrador',1,30615.00,235500.00,266115.00),(29,'Administrador',1,1300.00,10000.00,11300.00),(30,'Administrador',1,30615.00,235500.00,266115.00),(31,'Administrador',1,84500.00,650000.00,734500.00),(32,'Administrador',1,1300.00,10000.00,11300.00),(33,'Administrador',1,1300.00,10000.00,11300.00),(34,'Administrador',1,1300.00,10000.00,11300.00),(35,'Administrador',1,16250.00,125000.00,141250.00),(36,'Administrador',1,30615.00,235500.00,266115.00),(37,'Karleny Pérez',4,8450.00,65000.00,73450.00),(38,'Luis Felipe',2,109850.00,845000.00,954850.00),(39,'Administrador',2,3445.00,26500.00,29945.00),(40,'Administrador',2,7150.00,55000.00,62150.00),(41,'Administrador',2,18200.00,140000.00,158200.00),(42,'Administrador',2,105300.00,810000.00,915300.00),(43,'Administrador',1,2145.00,16500.00,18645.00),(44,'Administrador',1,5850.00,45000.00,50850.00),(45,'Administrador',1,5850.00,45000.00,50850.00),(46,'Administrador',1,2145.00,16500.00,18645.00),(47,'Administrador',1,5850.00,45000.00,50850.00),(48,'Administrador',2,7995.00,61500.00,69495.00),(49,'Administrador',4,113295.00,871500.00,984795.00),(50,'Administrador',3,19500.00,150000.00,169500.00),(51,'Administrador',3,119600.00,920000.00,1039600.00),(52,'Administrador',2,3445.00,26500.00,29945.00),(53,'Administrador',2,3445.00,26500.00,29945.00),(54,'Administrador',3,122200.00,940000.00,1062200.00),(55,'Administrador',3,9295.00,71500.00,80795.00),(56,'Administrador',3,116350.00,895000.00,1011350.00),(57,'Administrador',2,106600.00,820000.00,926600.00),(58,'Administrador',3,9295.00,71500.00,80795.00);
/*!40000 ALTER TABLE `facturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros`
--

DROP TABLE IF EXISTS `parametros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parametros` (
  `idparametros` int NOT NULL AUTO_INCREMENT,
  `codgeneral` varchar(300) NOT NULL,
  `valor` varchar(300) NOT NULL,
  PRIMARY KEY (`idparametros`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros`
--

LOCK TABLES `parametros` WRITE;
/*!40000 ALTER TABLE `parametros` DISABLE KEYS */;
INSERT INTO `parametros` VALUES (1,'path_image','C:\\Users\\Anthony Fernández\\OneDrive\\Documentos\\NetBeansProjects\\Proyecto_PrograIV'),(2,'url_image','http://localhost:8080/WebImagenes/imagenes/');
/*!40000 ALTER TABLE `parametros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idProducto` int NOT NULL AUTO_INCREMENT,
  `nombre_producto` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `inventario` int DEFAULT NULL,
  `imagen` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Blusa','Ropa',10000.00,8,'http://localhost:8080/WebImagenes/imagenes/blusa.jpg'),(4,'Camisa','Ropa',16500.00,7,'http://localhost:8080/WebImagenes/imagenes/camisa.jpg'),(9,'Control PS5','Electronicos',45000.00,12,'http://localhost:8080/WebImagenes/imagenes/controlps5.jpg'),(10,'iPhone 13','Electronicos',800000.00,13,'http://localhost:8080/WebImagenes/imagenes/iphone13.jpg'),(12,'Lentes','Accesorios',95000.00,11,'http://localhost:8080/WebImagenes/imagenes/lentes.jpg'),(17,'Pantalon Hombre','Ropa',25000.00,9,'http://localhost:8080/WebImagenes/imagenes/pantalonhombre.jpg'),(34,'PC Gamer','Electronicos',850000.00,19,'http://localhost:8080/WebImagenes/imagenes/pcgamer.jpg'),(35,'Play Station 5','Electronicos',800000.00,19,'http://localhost:8080/WebImagenes/imagenes/ps5.jpg'),(36,'Reloj Hombre','Accesorios',20000.00,18,'http://localhost:8080/WebImagenes/imagenes/relojhombre.jpg'),(37,'Reloj Mujer','Accesorios',25000.00,19,'http://localhost:8080/WebImagenes/imagenes/relojmujer.jpg'),(40,'Pantalon Mujer','Ropa',25000.00,4,'http://localhost:8080/WebImagenes/imagenes/pantalonmujer.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos_x_factura`
--

DROP TABLE IF EXISTS `productos_x_factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos_x_factura` (
  `idproductos_x_carrito` int NOT NULL AUTO_INCREMENT,
  `idFactura` int NOT NULL,
  `idProducto` int NOT NULL,
  PRIMARY KEY (`idproductos_x_carrito`),
  KEY `producto_idx` (`idProducto`),
  KEY `factura_idx` (`idFactura`),
  CONSTRAINT `factura` FOREIGN KEY (`idFactura`) REFERENCES `facturas` (`idFactura`),
  CONSTRAINT `producto` FOREIGN KEY (`idProducto`) REFERENCES `productos` (`idProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos_x_factura`
--

LOCK TABLES `productos_x_factura` WRITE;
/*!40000 ALTER TABLE `productos_x_factura` DISABLE KEYS */;
INSERT INTO `productos_x_factura` VALUES (1,6,1),(2,6,17),(3,7,17),(5,7,10),(7,8,12),(8,9,17),(10,10,1),(11,10,12),(12,11,1),(13,11,9),(14,12,1),(15,12,10),(16,13,1),(17,14,1),(18,15,1),(19,16,1),(20,17,1),(23,18,17),(24,19,17),(25,19,9),(26,20,17),(27,20,9),(28,21,12),(29,22,4),(30,22,9),(31,23,10),(32,24,10),(33,24,4),(34,25,9),(35,26,1),(37,27,12),(38,28,1),(40,30,1),(41,31,17),(45,35,9),(46,36,1),(47,37,1),(48,37,1),(50,37,37),(51,38,9),(52,38,35),(53,39,1),(54,39,4),(55,40,1),(56,40,9),(57,41,9),(58,41,12),(59,42,1),(60,42,10),(61,43,4),(62,44,9),(63,45,9),(64,46,4),(65,47,9),(66,48,9),(67,48,4),(68,49,1),(69,49,4),(70,49,9),(71,49,10),(72,50,1),(73,50,9),(74,50,12),(75,51,10),(76,51,12),(77,51,17),(78,52,1),(79,52,4),(80,53,1),(81,53,4),(82,54,9),(83,54,10),(84,54,12),(85,55,1),(86,55,4),(87,55,9),(88,56,34),(89,56,36),(90,56,40),(91,57,35),(92,57,36),(93,58,1),(94,58,4),(95,58,9);
/*!40000 ALTER TABLE `productos_x_factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(45) NOT NULL,
  `contrasena` varchar(45) NOT NULL,
  `tipo_usuario` varchar(45) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nombreCompleto` varchar(500) NOT NULL,
  PRIMARY KEY (`idUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Admin','1234','Administrador','Activo','afernandez0196@gmail.com','Administrador'),(5,'afernandez','1234','Regular','Activo','afernandez0196@gmail.com','Anthony Fernández'),(6,'karleny','1234','Regular','Activo','karlenypc99@gmail.com','Karleny Pérez'),(7,'Luis','111','Regular','Activo','luisfeugalde@gmail.com','Luis Felipe'),(9,'mtames','1234','Regular','Activo','mtames2000@gmail.com','Maria José Tames');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'proyectoprograiv'
--

--
-- Dumping routines for database 'proyectoprograiv'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-29 13:01:22
