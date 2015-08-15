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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,9111234567,7,1,0,0,490),(2,9219876543,2,27,1,1,800),(3,9213297654,1,2,0,0,900),(6,9214512789,1,27,0,0,800),(7,9213578545,2,1,0,0,700),(8,9217985689,1,27,1,1,1000),(9,9216487645,1,12,1,1,800),(10,9214975489,2,10,1,1,1000),(11,9215976215,6,11,1,1,800),(12,9217321566,1,13,0,0,600),(13,9213497548,2,5,1,1,500),(14,9216288578,6,9,1,1,700),(15,9218218974,1,1,0,0,400),(16,9212122923,1,19,0,0,600),(17,9217427124,1,14,0,0,500),(19,9219970479,6,5,1,1,700),(20,9216698792,1,14,0,0,600),(21,9212360185,2,1,0,0,800),(22,9216282726,6,26,0,0,500),(23,9212251232,1,28,0,0,2000);
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
  PRIMARY KEY (`id`),
  KEY `fk_contract_option_idx` (`contract_id`),
  KEY `fk_options_idx` (`chosen_option_id`),
  CONSTRAINT `fk_contract` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_options` FOREIGN KEY (`chosen_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract_chosen_option`
--

LOCK TABLES `contract_chosen_option` WRITE;
/*!40000 ALTER TABLE `contract_chosen_option` DISABLE KEYS */;
INSERT INTO `contract_chosen_option` VALUES (26,7,28),(31,11,2),(32,11,6),(33,14,2),(34,14,6),(35,19,2),(36,19,6),(38,22,1),(39,22,6),(40,13,5),(41,13,7),(52,2,6),(53,2,7),(54,2,26),(74,21,6),(75,21,7),(76,1,8),(77,1,4);
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
  PRIMARY KEY (`id`),
  KEY `fk1_idx` (`src_option_id`),
  KEY `fk2_idx` (`dependent_option_id`),
  CONSTRAINT `fk1` FOREIGN KEY (`src_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk2` FOREIGN KEY (`dependent_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dependent_option`
--

LOCK TABLES `dependent_option` WRITE;
/*!40000 ALTER TABLE `dependent_option` DISABLE KEYS */;
INSERT INTO `dependent_option` VALUES (9,6,1),(10,6,2),(11,8,3),(12,8,4),(13,7,5),(22,28,29),(24,29,30),(25,1,11);
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
  PRIMARY KEY (`id`),
  KEY `fk1_idx` (`src_option_id`),
  KEY `inc_fk2_idx` (`inconsistent_option_id`),
  CONSTRAINT `inc_fk1` FOREIGN KEY (`src_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inc_fk2` FOREIGN KEY (`inconsistent_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inconsistent_option`
--

LOCK TABLES `inconsistent_option` WRITE;
/*!40000 ALTER TABLE `inconsistent_option` DISABLE KEYS */;
INSERT INTO `inconsistent_option` VALUES (12,4,3),(13,3,4),(16,2,1),(17,1,2),(18,28,8),(19,8,28);
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'SMS 100',10,20),(2,'SMS 200',30,40),(3,'Internet 1gb',100,50),(4,'Internet  5gb',200,100),(5,'Минуты 100',100,50),(6,'SMS пакет',10,10),(7,'минуты - пакет',20,5),(8,'интернет - пакет',15,10),(9,'Ночью скидка 30%',40,28),(10,'Область со скидкой 50%',23,43),(11,'SMS100 Plus',25,20),(12,'Интернет - социальные сети',50,25),(26,'Любимый номер',10,10),(28,'Gold Internet',700,200),(29,'Gold 10GB',600,300),(30,'Gold 10GB HD TV',100,100);
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'Базовый',100),(2,'Мобильный безлимит',2000),(6,'Оптима',200),(7,'СуперТариф',5000),(8,'Коннект',300);
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
  KEY `fk_tarif_idx` (`tariff_id`),
  KEY `fk_option_idx` (`possible_option_id`),
  CONSTRAINT `fk_option` FOREIGN KEY (`possible_option_id`) REFERENCES `options` (`option_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tariff` FOREIGN KEY (`tariff_id`) REFERENCES `tariff` (`tariff_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff_possible_option`
--

LOCK TABLES `tariff_possible_option` WRITE;
/*!40000 ALTER TABLE `tariff_possible_option` DISABLE KEYS */;
INSERT INTO `tariff_possible_option` VALUES (146,2,6),(149,2,5),(150,2,7),(158,8,6),(159,8,28),(160,8,29),(161,8,30),(162,8,8),(163,8,4),(167,2,26),(170,2,28),(171,6,2),(172,6,6),(173,6,1),(174,6,11),(175,6,28),(176,6,29),(177,6,5),(178,6,7),(179,6,30),(180,6,26),(181,7,2),(182,7,6),(183,7,1),(184,7,11),(185,7,28),(186,7,29),(187,7,5),(188,7,7),(189,7,30),(190,7,26),(191,7,8),(192,7,4),(193,7,12),(194,7,9),(195,7,3),(196,7,10),(197,8,12),(198,8,3),(199,6,8),(200,6,4),(201,6,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Иван','Суслов','1989-12-24','1231131 УВД Петродворца','188537, Спб, пр. Героев 17, дом 11, кв. 12','iv@iv.com','111',0),(2,'Егор','Хохлов','1985-12-24','6234133 УВД Омска','646856, Омск, пр. Мира 8, дом 4, кв. 323','egor@omsk.ru','111',1),(5,'Семён','Карпов','1979-12-22','1211213, УВД Перми','121211, Пермь, ул. Говорова 3','sema@perm.ru','111',1),(9,'Игорь','Морозов','1993-04-05','2458459, УВД Москвы','254875, Москва, ул. Нахимова, 8','morozov@iv.com','111',1),(10,'Федор','Ершов','1988-02-02','3698745, УВД Санкт-Петербурга','548348, Луга, ул. Смирнова 8','fed@luga.ru','111',1),(11,'Илья','Егоров','1975-06-27','3267849, УВД Санкт-Петербурга','122112, Санкт-Петурбург, Садовая 3','ie@rt.com','111',1),(12,'Максим','Комаров','1980-03-22','7213234, УВД Перми','121211, Пермь, ул.Растрелли 4','maxbigman@perm.ru','111',1),(13,'Андрей','Решетников','1991-10-09','1213233, УВД Новгорода','658435, Новгород, ул. Соборная, 11','dron@nov.ru','111',0),(14,'Николай','Дроздов','1995-02-28','5513234, УВД Перми','121211, Пермь, ул. Говорова 1','adrozd@perm.ru','111',1),(15,'Елена','Капустина','2000-09-20','3267849, УВД Санкт-Петербурга','122112, Санкт-Петурбург, пр. Большевиков 4','ek@ag.ru','111',1),(19,'Евгения','Войт','1988-11-29','3767122, УВД Санкт-Петербурга','122112, Санкт-Петурбург, пр. Стахановцев 12','evgen@damochka.ru','111',1),(20,'Артем','Громов','1964-01-31','5513234, УВД Гатчины','121211, Сочи, ул. Верхняя, 3','ag@ag.ru','111',0),(21,'Станислав','Бобровский','1782-01-12','3767122, УВДГатчины','958784, Гатчина, ул. Ленина, 8','stas@gmail.com','111',1),(22,'Арсений','Куприянов','2015-06-28','1211213234, УВД Луги','548348, Луга, ул. Смирнова 8','ars@gmail.com','111',1),(23,'Алексей','Пивоваров','1990-01-01','123456, Тверское УВД','123456, Тверь, улица Правды','leha@tver.ru','111',1),(24,'Андрей','Столяров','1987-12-23','12345645, Выборгкий УВД от 12 марта 2009 года','987854, Выборг, ул. Ленина, дом 3','as@vyborg.ru','111',0),(25,'Борис','Губин','1954-07-14','234324, УВД Тихвина','164975, Тихвин, ул. Пятилеток 4','bob@mail.ru','111',1),(26,'Егор','Нечаев','1974-01-07','5665, серия21 номер12','Витебск, ул. Говорова, дом 7','egor@nechaev.ru','111',1),(27,'Тимофей','Данилов','1985-04-13','9874561, серия 12 номер23','Гатчина, ул. Петрова, дом 12','tima@gmail.com','111',1),(28,'Юрий','Тимофеев','1974-01-07','5665, серия21 номер12','адре1с такой-то1','ut@ut.ru','111',1);
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

-- Dump completed on 2015-08-16  1:53:24
