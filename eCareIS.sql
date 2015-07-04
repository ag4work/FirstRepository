CREATE DATABASE  IF NOT EXISTS `ecareis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecareis`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: ecareis
-- ------------------------------------------------------
-- Server version	5.6.24-log

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
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` bigint(20) DEFAULT NULL,
  `tariff_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `blocked` tinyint(1) DEFAULT NULL,
  `blocked_by_staff` tinyint(1) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  PRIMARY KEY (`contract_id`),
  KEY `fk_user_idx` (`client_id`),
  KEY `fk_tarif_idx` (`tariff_id`),
  CONSTRAINT `fk_tarif` FOREIGN KEY (`tariff_id`) REFERENCES `tariff` (`tariff_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `fk_user` FOREIGN KEY (`client_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,9111234567,1,1,0,0,100),(2,9219876543,1,1,1,1,50),(3,9311234568,1,2,0,0,100),(4,9218971598,1,NULL,0,0,200),(5,9216321566,1,NULL,0,0,300),(6,9217412536,1,5,0,0,150);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract_chosen_option`
--

DROP TABLE IF EXISTS `contract_chosen_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract_chosen_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `chosen_option_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_chosen_option`
--

LOCK TABLES `contract_chosen_option` WRITE;
/*!40000 ALTER TABLE `contract_chosen_option` DISABLE KEYS */;
INSERT INTO `contract_chosen_option` VALUES (1,1,3),(2,1,1);
/*!40000 ALTER TABLE `contract_chosen_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dependent_option`
--

DROP TABLE IF EXISTS `dependent_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dependent_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src_option_id` int(11) DEFAULT NULL,
  `dependent_option_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependent_option`
--

LOCK TABLES `dependent_option` WRITE;
/*!40000 ALTER TABLE `dependent_option` DISABLE KEYS */;
INSERT INTO `dependent_option` VALUES (1,1,6),(3,5,7),(4,2,6),(5,3,8),(6,4,8);
/*!40000 ALTER TABLE `dependent_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inconsistent_option`
--

DROP TABLE IF EXISTS `inconsistent_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inconsistent_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `src_option_id` int(11) DEFAULT NULL,
  `inconsistent_option_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inconsistent_option`
--

LOCK TABLES `inconsistent_option` WRITE;
/*!40000 ALTER TABLE `inconsistent_option` DISABLE KEYS */;
INSERT INTO `inconsistent_option` VALUES (1,1,2),(2,2,1),(3,3,4),(4,4,3);
/*!40000 ALTER TABLE `inconsistent_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `monthly_cost` int(11) DEFAULT NULL,
  `activation_charge` int(11) DEFAULT NULL,
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'sms100',10,20),(2,'sms200',30,40),(3,'inet500',100,50),(4,'inet1000',200,100),(5,'минуты100',100,50),(6,'SMS пакет',10,10),(7,'минуты - пакет',20,5),(8,'инет - пакет',15,10);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `tariff_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`tariff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'коннект',100),(2,'безлимит звонки',2000),(3,'оптима',500),(4,'all inclusive',10000);
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff_possible_option`
--

DROP TABLE IF EXISTS `tariff_possible_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff_possible_option` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tariff_id` int(11) DEFAULT NULL,
  `possible_option_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tarif_idx` (`tariff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_possible_option`
--

LOCK TABLES `tariff_possible_option` WRITE;
/*!40000 ALTER TABLE `tariff_possible_option` DISABLE KEYS */;
INSERT INTO `tariff_possible_option` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,4),(5,3,1),(6,3,2),(7,3,3),(8,3,4);
/*!40000 ALTER TABLE `tariff_possible_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `lastname` varchar(40) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `passport` varchar(120) DEFAULT NULL,
  `address` varchar(120) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Иван','Суслов','1989-12-24','1231131 УВД Петродворца','188537, Спб, пр. Героев 17, дом 11, кв. 12','iv@iv.com','111',0),(2,'Егор','Хохлов','1985-12-24','6234133 УВД Омска','646856, Омск, пр. Мира 8, дом 4, кв. 323','egor@omsk.ru','egor',0),(5,'Семен','Карпов','2000-01-24','1211213234, УВД Перми','121211, Пермь, ул. Говорова 3','sema@perm.ru','1111',1),(9,'фыв','Карпов','2010-01-24','1211213234, УВД Перми','121211, Пермь, ул. Говорова 3','iv@iv.com','1111',1),(10,'Федор','Ершов','2010-01-01','1211213234, УВД Луги','548348, Луга, ул. Смирнова 8','fed@luga.ru','11111',0),(11,'fsdf1','asdfas1','2000-01-11','23231','asdfds1','sdfsaf@dasfds.ad1','111111',1),(12,'вап2','asd2','2010-01-22','1211213234, УВД Перми2','sdfs2','adfad@sdfdsf.com2','12312312',1),(13,'Семен','go','2010-01-03','1211213233, УВД Новгорода','Новгород дом','nov@nov.ru','novpassw',1),(14,'Семен1','Карпов1','2010-01-24','1211213234, УВД Перми1','121211, Пермь, ул. Говорова 1','ag1@ag1.ru','1111',1),(15,'Таня','Г','2010-01-24','2323','Piter Bolsh 6','ag@ag.ru','111',1),(19,'вап','',NULL,'','','ag@ag.ru','111',1),(20,'werwe','werwer','1964-01-31','sdfsdfsd','121211, Пермь, ул. Говорова 3','ag@ag.ru','111',1),(21,'sdfds','dsfdsf','1782-01-12','sdfsdfdsf','sdfdsf','ag@ag.ru','111',1),(22,'ket','kete','2015-06-28','1211213234, УВД Луги','548348, Луга, ул. Смирнова 8','ag@ag.ru','111',1),(23,'Коля','Вахрапов','1990-01-01','123456, Тверское УВД','123456, Тверь, улица Правды','kolya@tver.ru','123',1),(24,'Андрей','Столяров','1987-01-02','12345645, Выборгкий УВД от 12 марта 2009 года','987854, Выборг, ул. Ленина, дом 3','as@v.ru','111',0),(25,'Борис','Коло','1954-01-14','234324, серия номер','адрес такой-то','b@b.ru','222',1),(26,'Егор','Котов','1974-01-07','5665, серия21 номер12','Витебск, ул. Говорова, дом 7','egor@egor.ru','123',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-07-02 14:51:19
