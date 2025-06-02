-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: webapp
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.24.04.1

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
-- Table structure for table `viaggio1`
--

DROP TABLE IF EXISTS `viaggio1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaggio1` (
  `id_viaggio` binary(16) NOT NULL,
  `arrivo` varchar(255) DEFAULT NULL,
  `ora_arrivo` varchar(255) DEFAULT NULL,
  `ora_partenza` varchar(255) DEFAULT NULL,
  `partenza` varchar(255) DEFAULT NULL,
  `classe` binary(16) NOT NULL,
  `targa` binary(16) NOT NULL,
  `carrello_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id_viaggio`),
  KEY `FK83a5qf6lhwgs3q69k7w69cvsc` (`carrello_id`),
  KEY `FK184whmmnos97ax83bfh1uxfmy` (`classe`),
  KEY `FK8e78tqtbkb100c1fw02luo8u8` (`targa`),
  CONSTRAINT `FK184whmmnos97ax83bfh1uxfmy` FOREIGN KEY (`classe`) REFERENCES `viaggio2` (`classe`),
  CONSTRAINT `FK8e78tqtbkb100c1fw02luo8u8` FOREIGN KEY (`targa`) REFERENCES `bus` (`targa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaggio1`
--

LOCK TABLES `viaggio1` WRITE;
/*!40000 ALTER TABLE `viaggio1` DISABLE KEYS */;
INSERT INTO `viaggio1` VALUES (_binary 'V001\0\0\0\0\0\0\0\0\0\0\0\0','Milano','10:30','08:00','Torino',_binary 'Economy\0\0\0\0\0\0\0\0\0',_binary 'AB123CD\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V002\0\0\0\0\0\0\0\0\0\0\0\0','Milano','10:30','09:00','Torino',_binary 'Economy\0\0\0\0\0\0\0\0\0',_binary 'AB123CD\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V003\0\0\0\0\0\0\0\0\0\0\0\0','Milano','13:30','12:00','Torino',_binary 'Business\0\0\0\0\0\0\0\0',_binary 'AB123CD\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V004\0\0\0\0\0\0\0\0\0\0\0\0','Roma','14:00','10:00','Firenze',_binary 'Business\0\0\0\0\0\0\0\0',_binary 'EF456GH\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V005\0\0\0\0\0\0\0\0\0\0\0\0','Roma','14:00','12:00','Napoli',_binary 'Business\0\0\0\0\0\0\0\0',_binary 'EF456GH\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V006\0\0\0\0\0\0\0\0\0\0\0\0','Roma','14:00','10:00','Genova',_binary 'Luxury\0\0\0\0\0\0\0\0\0\0',_binary 'EF456GH\0\0\0\0\0\0\0\0\0',NULL),(_binary 'V007\0\0\0\0\0\0\0\0\0\0\0\0','Napoli','18:30','15:00','Roma',_binary 'Luxury\0\0\0\0\0\0\0\0\0\0',_binary 'IJ789KL\0\0\0\0\0\0\0\0\0',NULL);
/*!40000 ALTER TABLE `viaggio1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-02 18:20:41
