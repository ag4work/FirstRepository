CREATE DATABASE  IF NOT EXISTS `ecareis` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ecareis`;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,9111234567,1,1,0,0,100),(2,9219876543,2,27,1,1,50),(3,9213297654,9,2,0,0,-118),(6,9214512789,2,27,0,0,150),(7,9213578545,1,1,0,0,107),(8,9217985689,2,27,1,1,1),(9,9216487645,1,12,1,1,1),(10,9214975489,1,10,1,1,1),(11,9215976215,1,11,1,1,1),(12,9217321566,1,13,0,0,257),(13,9213497548,2,5,1,1,379),(14,9216288578,2,9,0,0,450),(15,9218218974,7,1,0,0,300),(16,9218321566,1,19,1,1,10),(17,9217427124,1,14,0,0,222),(18,9210832070,9,28,0,0,495);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `contract_chosen_option` WRITE;
/*!40000 ALTER TABLE `contract_chosen_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `contract_chosen_option` ENABLE KEYS */;
UNLOCK TABLES;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `dependent_option` WRITE;
/*!40000 ALTER TABLE `dependent_option` DISABLE KEYS */;
INSERT INTO `dependent_option` VALUES (9,6,1),(10,6,2),(11,8,3),(12,8,4),(13,7,5),(14,1,11);
/*!40000 ALTER TABLE `dependent_option` ENABLE KEYS */;
UNLOCK TABLES;
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `inconsistent_option` WRITE;
/*!40000 ALTER TABLE `inconsistent_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `inconsistent_option` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `options` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `monthly_cost` int(11) DEFAULT NULL,
  `activation_charge` int(11) DEFAULT NULL,
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'SMS 100',10,20),(2,'SMS 200',30,40),(3,'Internet 1gb',100,50),(4,'Internet  5gb',200,100),(5,'Минуты 100',100,50),(6,'SMS пакет',10,10),(7,'минуты - пакет',20,5),(8,'интернет - пакет',15,10),(9,'Ночью скидка 30%',40,28),(10,'Область со скидкой 50%',23,43),(11,'SMS100 Plus',25,20),(12,'Интернет - социальные сети',50,25);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;
DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `tariff_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`tariff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,'Базовый',100),(2,'Мобильный безлимит',2000),(6,'Оптима',200),(7,'СуперТариф',5000),(8,'Коннект',300),(9,'Оптима Плюс',350);
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;
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
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `tariff_possible_option` WRITE;
/*!40000 ALTER TABLE `tariff_possible_option` DISABLE KEYS */;
/*!40000 ALTER TABLE `tariff_possible_option` ENABLE KEYS */;
UNLOCK TABLES;
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Иван','Суслов','1989-12-24','1231131 УВД Петродворца','188537, Спб, пр. Героев 17, дом 11, кв. 12','iv@iv.com','111',0),(2,'Егор','Хохлов','1985-12-24','6234133 УВД Омска','646856, Омск, пр. Мира 8, дом 4, кв. 323','egor@omsk.ru','egor',0),(5,'Семен','Карпов','1979-12-22','1211213, УВД Перми','121211, Пермь, ул. Говорова 3','sema@perm.ru','1111',1),(9,'Игорь','Морозов','1993-04-05','2458459, УВД Москвы','254875, Москва, ул. Нахимова, 8','morozov@iv.com','1111',1),(10,'Федор','Ершов','1988-02-02','3698745, УВД Санкт-Петербурга','548348, Луга, ул. Смирнова 8','fed@luga.ru','11111',0),(11,'Илья','Егоров','1975-06-27','3267849, УВД Санкт-Петербурга','122112, Санкт-Петурбург, Садовая 3','ie@rt.com','111111',1),(12,'Максим','Комаров','1980-03-22','7213234, УВД Перми','121211, Пермь, ул.Растрелли 4','maxbigman@perm.ru','12312312',1),(13,'Андрей','Решетников','1991-10-09','1213233, УВД Новгорода','658435, Новгород, ул. Соборная, 11','dron@nov.ru','novpassw',1),(14,'Николай','Дроздов','1995-02-28','5513234, УВД Перми','121211, Пермь, ул. Говорова 1','adrozd@perm.ru','1111',1),(15,'Елена','Капустина','2000-09-20','3267849, УВД Санкт-Петербурга','122112, Санкт-Петурбург, пр. Большевиков 4','ek@ag.ru','111',1),(19,'Евгения','Войт','1988-11-29','3767122, УВД Санкт-Петербурга','122112, Санкт-Петурбург, пр. Стахановцев 12','evgen@damochka.ru','111',1),(20,'Василий','Белоусов','1964-01-31','5513234, УВД Перми','121211, Пермь, ул. Говорова 3','ag@ag.ru','111',0),(21,'Станислав','Бобровский','1782-01-12','3767122, УВДГатчины','958784, Гатчина, ул. Ленина, 8','stas@gmail.com','111',1),(22,'Арсений','Куприянов','2015-06-28','1211213234, УВД Луги','548348, Луга, ул. Смирнова 8','ars@gmail.com','111',1),(23,'Алексей','Пивоваров','1990-01-01','123456, Тверское УВД','123456, Тверь, улица Правды','leha@tver.ru','123',1),(24,'Андрей','Столяров','1987-12-23','12345645, Выборгкий УВД от 12 марта 2009 года','987854, Выборг, ул. Ленина, дом 3','as@vyborg.ru','111',0),(25,'Борис','Губин','1954-07-14','234324, УВД Тихвина','164975, Тихвин, ул. Пятилеток 4','bob@mail.ru','222',1),(26,'Егор','Нечаев','1974-01-07','5665, серия21 номер12','Витебск, ул. Говорова, дом 7','egor@nechaev.ru','123',1),(27,'Тимофей','Данилов','1985-04-13','9874561, серия 12 номер23','Гатчина, ул. Петрова, дом 12','tima@gmail.com','111',1),(28,'Леша','Гордеев','1982-01-07','1231231223','адрес такой-то','ag@ag.ru','111',1),(29,'Тимур','Угольков','1975-01-17','234324, серия номер','адре1с такой-то1','ag1@ag.ru','123',1);
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

