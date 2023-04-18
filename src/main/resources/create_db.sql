CREATE DATABASE  IF NOT EXISTS `employee_ms_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employee_ms_db`;
-- MySQL dump 10.13  Distrib 8.0.25, for macos11 (x86_64)
--
-- Host: 127.0.0.1    Database: employee_ms_db
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `start_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (56,'ADMIN','ADMIN','ADMIN','2023-04-18 15:28:55','admin','$2a$10$jLCdEs.fBZBEN51tM9f8xeV0Npf7acrpgkAlno0qDhyZbLXj6K4ve'),(57,'Test1','Test1','Test1','2001-01-01 00:00:00','test1','$2a$10$Hos3YZE/gh2N26gBgR4hueLOIy5qDT/LzpwAFFFSIjRQUe.m6U82e'),(58,'Test2','Test2','Test2','2003-03-03 00:00:00','test2','$2a$10$NNJeX/Mg6cqyqiMb06R5qeAPGqDi.yoOUMGqMWwm2pTo80FIxBNdm'),(59,'Test3','Test3','Test3','2005-05-05 00:00:00','test3','$2a$10$7XWbJ0z.U3vGUCCU7yYfeOvy5cYmXPG/2/kxYetgpjLYNO7lii2MK'),(60,'Ronan','Garrett','Autem corporis volup','1992-01-08 00:00:00','test4','$2a$10$g3CBMOgIvjJsfZBHXoBfhOgJqnoU9fCOSoTnJ6o2SsPC1m1/SpcLi'),(61,'Sonya','Stone','Fugiat impedit ea ','1998-01-29 00:00:00','test5','$2a$10$zaJ5R96zy7ZXiw2yJvEHgOL8qxBvoOvHy5FVPNiHohW5EatnwZZk2');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee_role` (
  `employee_id` int NOT NULL,
  `role_id` int NOT NULL,
  KEY `fk_employee_role_employee_idx` (`employee_id`),
  KEY `fk_employee_role_role_idx` (`role_id`),
  CONSTRAINT `fk_employee_role_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `fk_employee_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES (56,1),(56,2),(57,2),(58,2),(59,2),(60,2),(61,2);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `holiday`
--

DROP TABLE IF EXISTS `holiday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `holiday` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `employee_id` int NOT NULL,
  `is_approved` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_holiday_employee_idx` (`employee_id`),
  CONSTRAINT `fk_holiday_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holiday`
--

LOCK TABLES `holiday` WRITE;
/*!40000 ALTER TABLE `holiday` DISABLE KEYS */;
INSERT INTO `holiday` VALUES (1,'2023-04-12 00:00:00','2023-04-19 00:00:00','Few days off',57,0),(2,'2023-03-03 00:00:00','2023-03-05 00:00:00','Reason 2',59,1);
/*!40000 ALTER TABLE `holiday` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `past_experience`
--

DROP TABLE IF EXISTS `past_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `past_experience` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `date_from` datetime NOT NULL,
  `date_to` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_past_experience_employee_idx` (`employee_id`),
  CONSTRAINT `fk_past_experience_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `past_experience`
--

LOCK TABLES `past_experience` WRITE;
/*!40000 ALTER TABLE `past_experience` DISABLE KEYS */;
INSERT INTO `past_experience` VALUES (1,'Test2','2005-01-01 00:00:00','2009-01-01 00:00:00',57),(2,'Test1','2001-01-01 00:00:00','2004-01-01 00:00:00',57),(3,'Test2','1999-03-03 00:00:00','2011-03-03 00:00:00',58),(4,'Test3-2','2022-02-02 00:00:00','2023-02-02 00:00:00',59),(5,'Test3-1','2002-02-02 00:00:00','2003-02-02 00:00:00',59),(6,'Test3-3','2006-06-06 00:00:00','2010-02-02 00:00:00',59),(7,'Campos Gillespie Associates','1981-12-02 00:00:00','1985-10-14 00:00:00',60),(8,'Dunn and Hale LLC','1988-01-06 00:00:00','1999-12-24 00:00:00',61);
/*!40000 ALTER TABLE `past_experience` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-18 15:50:00
