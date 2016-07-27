CREATE DATABASE  IF NOT EXISTS `ted` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ted`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: ted
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) CHARACTER SET utf8 NOT NULL,
  `currently` decimal(20,2) NOT NULL,
  `buy_price` decimal(20,2) DEFAULT NULL,
  `first_bid` decimal(20,2) NOT NULL,
  `country` varchar(45) CHARACTER SET utf8 NOT NULL,
  `location` varchar(45) CHARACTER SET utf8 NOT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `creation` datetime NOT NULL,
  `starts` datetime DEFAULT NULL,
  `ends` datetime NOT NULL,
  `seller` varchar(45) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `username_idx` (`seller`),
  CONSTRAINT `username` FOREIGN KEY (`seller`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'ÎÎ±ÏÎ­ÎºÎ»Î± Î³ÏÎ±ÏÎµÎ¯Î¿Ï Î¼Îµ ÏÎ¿Î´Î¬ÎºÎ¹Î±',4.01,1.01,4.01,'ÎÎ»Î»Î¬Î´Î±','ÎÎ¸Î®Î½Î±',NULL,NULL,'2016-07-27 20:36:23',NULL,'2016-07-29 11:20:00','admin','ÏÎ¿ ÎºÎ±Î»ÏÏÎµÏÎ¿ Î±Î½ÏÎ¹ÎºÎµÎ¹Î¼ÎµÎ½Î¿'),(2,'ÎÎ±ÏÎ­ÎºÎ»Î± Î³ÏÎ±ÏÎµÎ¯Î¿Ï Î¼Îµ ÏÎ¿Î´Î¬ÎºÎ¹Î±',0.01,5.01,0.01,'ÏÎ³Î·ÏÎ³Î·Î³Ï','ÏÏÎ²ÏÎ³Î·',0.550000,0.010000,'2016-07-27 20:43:21',NULL,'2016-07-28 23:59:00','admin','ÏÎ´Î²Î³ÏÎ´Î³ÏÎ³Î´'),(3,'Καρέκλα γραφείου με ροδάκια',0.01,1.01,0.01,'Ελλάδα','Αθήνα',0.550000,0.015500,'2016-07-27 20:46:52',NULL,'2016-07-29 11:20:00','admin','ασδασδασδσαδασ'),(4,'τραπεζι',0.01,1.01,0.01,'Ελλάδα','Αθήνα',NULL,NULL,'2016-07-27 21:05:06',NULL,'2016-07-29 23:59:00','admin','αΣΔΣΔΑΣΔΑΣΔΑΣΔΑΣ'),(5,'test15',0.01,0.01,0.01,'Ελλάδα','Αθήνα',NULL,NULL,'2016-07-27 21:07:54',NULL,'2016-07-30 11:20:00','admin','test15');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-27 21:18:51
