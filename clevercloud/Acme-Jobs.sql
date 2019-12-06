-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: acme-jobs
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `_request`
--

DROP TABLE IF EXISTS `_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `_request` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `reward_amount` double DEFAULT NULL,
  `reward_currency` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cojbnsth7eki2kphtwc27slb5` (`ticker`),
  KEY `IDX6chpf38fstvkblv6t12t0qs91` (`deadline`),
  KEY `IDXq82g0jb2mlplkxoma94rvovh8` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `_request`
--

LOCK TABLES `_request` WRITE;
/*!40000 ALTER TABLE `_request` DISABLE KEYS */;
INSERT INTO `_request` VALUES (11,0,'2021-09-09 08:00:00.000000','2019-09-09 08:00:00.000000',1000,'EUR','Want some money?','RABCD-12345','Job Request 001'),(12,0,'2022-09-09 08:00:00.000000','2019-09-09 08:00:00.000000',1000000,'EUR','Want EVEN more money?','RABCD-12340','Job Request 002'),(13,0,'2018-09-09 08:00:00.000000','2017-05-09 08:00:00.000000',100,'EUR','I\'m invisible, spooky huh?','RABCD-12349','Job Request 003'),(14,0,'2018-09-09 08:00:00.000000','2017-05-09 08:00:00.000000',100,'EUR','I\'m invisible, spooky huh?','RZZZZ-99999','Job Request 004'),(15,0,'2018-09-09 08:00:00.000000','2017-05-09 08:00:00.000000',100,'EUR','I\'m invisible, spooky huh?','RGKDP-76567','Job Request 005');
/*!40000 ALTER TABLE `_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2a5vcjo3stlfcwadosjfq49l1` (`user_account_id`),
  CONSTRAINT `FK_2a5vcjo3stlfcwadosjfq49l1` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (4,0,3);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXnhikaa2dj3la6o2o7e9vo01y0` (`moment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (6,0,'2019-09-09 08:00:00.000000','http://google.es','Announcement test 01','Announcement 01'),(7,0,'2019-11-03 09:00:00.000000','http://google.es','A not so old announcement','Announcement 02'),(8,0,'2019-11-01 09:00:00.000000','http://example.es','Normal announcement','Announcement 03'),(9,0,'2019-05-03 08:00:00.000000','http://example.es','Normal announcement','Announcement 04'),(10,0,'2019-10-30 09:00:00.000000',NULL,'Non optional link to more info','Announcement 05');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `anonymous`
--

DROP TABLE IF EXISTS `anonymous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anonymous` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6lnbc6fo3om54vugoh8icg78m` (`user_account_id`),
  CONSTRAINT `FK_6lnbc6fo3om54vugoh8icg78m` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anonymous`
--

LOCK TABLES `anonymous` WRITE;
/*!40000 ALTER TABLE `anonymous` DISABLE KEYS */;
INSERT INTO `anonymous` VALUES (2,0,1);
/*!40000 ALTER TABLE `anonymous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_moment` datetime(6) DEFAULT NULL,
  `qualifications` varchar(1024) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `skills` varchar(1024) DEFAULT NULL,
  `statement` varchar(1024) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `job_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ct7r18vvxl5g4c4k7aefpa4do` (`reference`),
  KEY `FKoa6p4s2oyy7tf80xwc4r04vh6` (`job_id`),
  KEY `FKmbjdoxi3o93agxosoate4sxbt` (`worker_id`),
  CONSTRAINT `FKmbjdoxi3o93agxosoate4sxbt` FOREIGN KEY (`worker_id`) REFERENCES `worker` (`id`),
  CONSTRAINT `FKoa6p4s2oyy7tf80xwc4r04vh6` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (92,0,'2019-11-03 09:00:00.000000','JAVA, Python','EMP1-APPL01','Good programmer','I want to work','Accepted',60,87),(93,0,'2018-11-03 09:00:00.000000','Not being a Leslie','EMP1-APPL02','Great work in a team.','Can you hire me?','Accepted',60,87),(94,0,'2017-01-03 09:00:00.000000','Great talker','EMP1-APPL03','Sleeping','Bruh, r u 4 real?','Pending',70,90),(95,0,'2015-12-03 09:00:00.000000','Whatever...','EMP1-APPL04','Worst skills.','Hire me!','Rejected',67,90),(96,0,'2016-05-03 08:00:00.000000','Awesome jokes.','EMP1-APPL05','Best buddy.','I don\'t see the point of this.','Rejected',73,90);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audit_record`
--

DROP TABLE IF EXISTS `audit_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audit_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(1024) DEFAULT NULL,
  `creation_moment` datetime(6) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `auditor_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXof878cqun8l1ynh0ao94bw3au` (`status`),
  KEY `FKdcrrgv6rkfw2ruvdja56un4ji` (`auditor_id`),
  KEY `FKlbvbyimxf6pxvbhkdd4vfhlnd` (`job_id`),
  CONSTRAINT `FKdcrrgv6rkfw2ruvdja56un4ji` FOREIGN KEY (`auditor_id`) REFERENCES `auditor` (`id`),
  CONSTRAINT `FKlbvbyimxf6pxvbhkdd4vfhlnd` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audit_record`
--

LOCK TABLES `audit_record` WRITE;
/*!40000 ALTER TABLE `audit_record` DISABLE KEYS */;
INSERT INTO `audit_record` VALUES (81,0,'A great job!','2019-11-11 09:00:00.000000',_binary '','Audit Record 1',76,60),(82,0,'A great job! Keep up.','2019-11-10 09:00:00.000000',_binary '\0','Audit Record 2',79,60),(83,0,'A terrible job!','2019-10-10 08:00:00.000000',_binary '','Audit Record 3',79,70),(84,0,'I don\'t know what to comment on that.','2019-09-10 08:00:00.000000',_binary '\0','Audit Record 4',79,73),(85,0,'Really?!','2019-08-10 08:00:00.000000',_binary '','Audit Record 5',76,70);
/*!40000 ALTER TABLE `audit_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditor`
--

DROP TABLE IF EXISTS `auditor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `firm` varchar(255) DEFAULT NULL,
  `responsibility_statement` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clqcq9lyspxdxcp6o4f3vkelj` (`user_account_id`),
  CONSTRAINT `FK_clqcq9lyspxdxcp6o4f3vkelj` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditor`
--

LOCK TABLES `auditor` WRITE;
/*!40000 ALTER TABLE `auditor` DISABLE KEYS */;
INSERT INTO `auditor` VALUES (76,0,75,'Auditor 1 Inc.','I agree to all conditions.'),(79,0,78,'Auditor 2 Inc.','I don\'t agree to all conditions.');
/*!40000 ALTER TABLE `auditor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authenticated`
--

DROP TABLE IF EXISTS `authenticated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authenticated` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h52w0f3wjoi68b63wv9vwon57` (`user_account_id`),
  CONSTRAINT `FK_h52w0f3wjoi68b63wv9vwon57` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authenticated`
--

LOCK TABLES `authenticated` WRITE;
/*!40000 ALTER TABLE `authenticated` DISABLE KEYS */;
INSERT INTO `authenticated` VALUES (5,0,3),(38,0,36),(41,0,39),(55,0,53),(58,0,56),(77,0,75),(80,0,78),(88,0,86),(91,0,89);
/*!40000 ALTER TABLE `authenticated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjoxwdnjr54soq3j89kt3fgrtj` (`sponsor_id`),
  CONSTRAINT `FKjoxwdnjr54soq3j89kt3fgrtj` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `bronze_goal` varchar(255) DEFAULT NULL,
  `bronze_reward_amount` double DEFAULT NULL,
  `bronze_reward_currency` varchar(255) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `gold_goal` varchar(255) DEFAULT NULL,
  `gold_reward_amount` double DEFAULT NULL,
  `gold_reward_currency` varchar(255) DEFAULT NULL,
  `silver_goal` varchar(255) DEFAULT NULL,
  `silver_reward_amount` double DEFAULT NULL,
  `silver_reward_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXnr284tes3x8hnd3h716tmb3fr` (`deadline`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (31,0,'A bronze goal description.',100,'EUR','2019-10-07 06:00:00.000000','A description for the challenge.','A gold goal description.',300,'EUR','A silver goal description.',200,'EUR','Sample challenge 01'),(32,0,'An active bronze goal description.',200,'EUR','2020-10-07 06:00:00.000000','A description for the active challenge','An active gold goal description.',400,'EUR','An active silver goal description.',300,'EUR','Sample challenge 02'),(33,0,'Bronze goal: Item C.',5,'EUR','2020-10-07 06:00:00.000000','A description for the active challenge','Gold goal: Item A.',10,'EUR','Silver goal:Item B.',8,'EUR','Sample challenge 03'),(34,0,'An active bronze goal description.',1,'EUR','2050-10-07 06:00:00.000000','A description for the active challenge','An active gold goal description.',3,'EUR','An active silver goal description.',2,'EUR','Sample challenge 04'),(35,0,'An active bronze goal description.',20000,'EUR','2020-10-07 06:00:00.000000','A description for the active challenge','An active gold goal description.',40000,'EUR','An active silver goal description.',30000,'EUR','Sample challenge 05');
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comercial_banner`
--

DROP TABLE IF EXISTS `comercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `expiration_date` varchar(255) DEFAULT NULL,
  `holder` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2uqsobmmc3lje3k58op7dsyvw` (`sponsor_id`),
  CONSTRAINT `FK_2uqsobmmc3lje3k58op7dsyvw` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comercial_banner`
--

LOCK TABLES `comercial_banner` WRITE;
/*!40000 ALTER TABLE `comercial_banner` DISABLE KEYS */;
INSERT INTO `comercial_banner` VALUES (42,0,'Banner test 01','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'MasterCard','4617307392039794','10/2050','sponsor1'),(43,0,'Banner test 02','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'MasterCard','4617307392039794','10/2040','sponsor1'),(44,0,'Banner test 03','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'MasterCard','4617307392039794','10/2030','sponsor1'),(45,0,'Banner test 04','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'VISA','4617307392039794','10/2020','sponsor1'),(46,0,'Banner test 05','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'VISA','4617307392039794','11/2050','sponsor1');
/*!40000 ALTER TABLE `comercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_record`
--

DROP TABLE IF EXISTS `company_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activities_description` varchar(255) DEFAULT NULL,
  `ceo_name` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `contact_email` varchar(255) DEFAULT NULL,
  `contact_phone` varchar(255) DEFAULT NULL,
  `is_incorporated` bit(1) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `web_site` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX9pkce3d1y6w47wadap5s5xptc` (`stars`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_record`
--

LOCK TABLES `company_record` WRITE;
/*!40000 ALTER TABLE `company_record` DISABLE KEYS */;
INSERT INTO `company_record` VALUES (21,0,'A simple activities description.','Average Director','Average Company','averagecompany@gmail.com','+123 (4567) 890123',_binary '','Sector Z2',3,'https://averagecompany.com/'),(22,0,'A simple activities description.','Average Director','Acme Jobs, Inc','averagecompany@gmail.com','+123 890123',_binary '\0','Sector Z1',NULL,'https://averagecompany.com/'),(23,0,'A simple activities description.','Average Director','Average Company','averagecompany@gmail.com','+999 (9999) 999999',_binary '','Sector Z2',3,'https://averagecompany.com/'),(24,0,'A simple activities description.','Average Director','Cookibite','averagecompany@gmail.com','+001 (9999) 1234567890',_binary '','Sector Z3',5,'https://averagecompany.com/'),(25,0,'Company with stars null','Average Director','Company number 01','averagecompany@gmail.com','+123 1234567890',_binary '\0','Sector Z4',NULL,'https://averagecompany.com/');
/*!40000 ALTER TABLE `company_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `spam_threshold` double NOT NULL,
  `spam_words_listing` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (52,0,1,'Sex,Sexo,Hard core,Viagra,Cialis,Nigeria,you\'ve won,has ganado,million dollar,millon de dolares');
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6cyha9f1wpj0dpbxrrjddrqed` (`user_account_id`),
  CONSTRAINT `FK_6cyha9f1wpj0dpbxrrjddrqed` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `descriptor`
--

DROP TABLE IF EXISTS `descriptor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `descriptor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `descriptor`
--

LOCK TABLES `descriptor` WRITE;
/*!40000 ALTER TABLE `descriptor` DISABLE KEYS */;
INSERT INTO `descriptor` VALUES (59,0,'Lorem ipsum sit dolor amet.'),(63,0,'Lorem ipsum sit dolor amet.'),(66,0,'Lorem ipsum sit dolor amet.'),(69,0,'Lorem ipsum sit dolor amet.'),(72,0,'Lorem ipsum sit dolor amet.');
/*!40000 ALTER TABLE `descriptor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `duty`
--

DROP TABLE IF EXISTS `duty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `duty` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `percentage` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `descriptor_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3cc3garl37bl7gswreqwr7pj4` (`descriptor_id`),
  CONSTRAINT `FK3cc3garl37bl7gswreqwr7pj4` FOREIGN KEY (`descriptor_id`) REFERENCES `descriptor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `duty`
--

LOCK TABLES `duty` WRITE;
/*!40000 ALTER TABLE `duty` DISABLE KEYS */;
INSERT INTO `duty` VALUES (61,0,'Description of Duty 1',40,'Duty 1',59),(62,0,'Description of Duty 2',60,'Duty 2',59),(65,0,'Description of Duty 3',100,'Duty 3',63),(68,0,'Description of Duty 4',100,'Duty 4',66),(71,0,'Description of Duty 5',100,'Duty 5',69),(74,0,'Description of Duty 6',100,'Duty 6',72);
/*!40000 ALTER TABLE `duty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_na4dfobmeuxkwf6p75abmb2tr` (`user_account_id`),
  CONSTRAINT `FK_na4dfobmeuxkwf6p75abmb2tr` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (54,0,53,'Employer 1 Inc.','Sector 1'),(57,0,56,'Employer 2 Inc.','Sector 2');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (107);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `investor_record`
--

DROP TABLE IF EXISTS `investor_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `investor_record` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  `stars` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDXk2t3uthe649ao1jllcuks0gv4` (`stars`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `investor_record`
--

LOCK TABLES `investor_record` WRITE;
/*!40000 ALTER TABLE `investor_record` DISABLE KEYS */;
INSERT INTO `investor_record` VALUES (26,0,'Investor 001','Sample Sector',NULL,'Sample text'),(27,0,'Investor 002','Another Sample Sector',5,'Another Sample text'),(28,0,'Investor 003','The very best sector',0,'Worst text ever!'),(29,0,'Investor 004','The worst sector',NULL,'Stars null:('),(30,0,'Investor 005','The best sector',3,'Best text ever!');
/*!40000 ALTER TABLE `investor_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `final_mode` bit(1) NOT NULL,
  `more_info` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `salary_amount` double DEFAULT NULL,
  `salary_currency` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `descriptor_id` int(11) NOT NULL,
  `employer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qpodqtu8nvqkof3olnqnqcv2l` (`descriptor_id`),
  UNIQUE KEY `UK_7jmfdvs0b0jx7i33qxgv22h7b` (`reference`),
  KEY `IDXfdmpnr8o4phmk81sqsano16r` (`deadline`),
  KEY `IDXt84ibbldao4ngscmvo7ja0es` (`final_mode`),
  KEY `FK3rxjf8uh6fh2u990pe8i2at0e` (`employer_id`),
  CONSTRAINT `FK3rxjf8uh6fh2u990pe8i2at0e` FOREIGN KEY (`employer_id`) REFERENCES `employer` (`id`),
  CONSTRAINT `FKfqwyynnbcsq0htxho3vchpd2u` FOREIGN KEY (`descriptor_id`) REFERENCES `descriptor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (60,0,'2030-12-11 17:59:00.000000',_binary '','http://www.example.com','EMP1-JOB1',15000.98,'€','Title 1',59,54),(64,0,'2030-12-11 18:59:00.000000',_binary '\0','http://www.example.com','EMP1-JOB2',20000,'€','Title 2',63,54),(67,0,'2019-11-11 19:59:00.000000',_binary '\0',NULL,'EMP2-JOB3',30000,'€','Title 3',66,57),(70,0,'2030-12-11 20:59:00.000000',_binary '',NULL,'EMP2-JOB4',40000,'€','Title 4',69,57),(73,0,'2030-12-11 21:59:00.000000',_binary '','http://www.example.com','EMP1-JOB5',50000,'€','Title 5',72,54);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `authenticated_id` int(11) NOT NULL,
  `message_thread_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3ny0h1379q528toyokq81noiu` (`authenticated_id`),
  KEY `FKn5adlx3oqjna7aupm8gwg3fuj` (`message_thread_id`),
  CONSTRAINT `FK3ny0h1379q528toyokq81noiu` FOREIGN KEY (`authenticated_id`) REFERENCES `authenticated` (`id`),
  CONSTRAINT `FKn5adlx3oqjna7aupm8gwg3fuj` FOREIGN KEY (`message_thread_id`) REFERENCES `message_thread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (102,0,'Hi!','2015-12-03 09:00:00.000000',NULL,'Message1',55,97),(103,0,'Hello there! How r u?','2015-12-04 09:00:00.000000','Presentation, Offtopic','Message2',88,97),(104,0,'Buuuh','2017-12-03 09:00:00.000000','Scary, Offtopic','Message3',55,98),(105,0,'Can we schedule a meeting?','2018-12-04 09:00:00.000000','Meeting','Message4',91,100),(106,0,'Sure! When?','2018-12-04 09:00:00.000000',NULL,'Message5',58,100);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_thread`
--

DROP TABLE IF EXISTS `message_thread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message_thread` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `moment` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_thread`
--

LOCK TABLES `message_thread` WRITE;
/*!40000 ALTER TABLE `message_thread` DISABLE KEYS */;
INSERT INTO `message_thread` VALUES (97,0,'2015-12-03 09:00:00.000000','MessageThread1'),(98,0,'2016-12-03 09:00:00.000000','MessageThread2'),(99,0,'2017-12-03 09:00:00.000000','MessageThread3'),(100,0,'2018-12-03 09:00:00.000000','MessageThread4'),(101,0,'2019-10-03 08:00:00.000000','MessageThread5');
/*!40000 ALTER TABLE `message_thread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `non_comercial_banner`
--

DROP TABLE IF EXISTS `non_comercial_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_comercial_banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `slogan` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `sponsor_id` int(11) NOT NULL,
  `jingle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_h7gdwb5bu1dvickx9h13sl2tj` (`sponsor_id`),
  CONSTRAINT `FK_h7gdwb5bu1dvickx9h13sl2tj` FOREIGN KEY (`sponsor_id`) REFERENCES `sponsor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_comercial_banner`
--

LOCK TABLES `non_comercial_banner` WRITE;
/*!40000 ALTER TABLE `non_comercial_banner` DISABLE KEYS */;
INSERT INTO `non_comercial_banner` VALUES (47,0,'Non comercial Banner test 01','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',40,'https://drive.google.com/file/d/10__w8yVtwR3LujGGMYsBjIvIOBz8_DT_/view?usp=sharing'),(48,0,'Non comercial Banner test 02','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',40,'https://drive.google.com/file/d/10__w8yVtwR3LujGGMYsBjIvIOBz8_DT_/view?usp=sharing'),(49,0,'Non comercial Banner test 03','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',40,'https://drive.google.com/file/d/10__w8yVtwR3LujGGMYsBjIvIOBz8_DT_/view?usp=sharing'),(50,0,'Non comercial Banner test 04','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'https://drive.google.com/file/d/10__w8yVtwR3LujGGMYsBjIvIOBz8_DT_/view?usp=sharing'),(51,0,'Non comercial Banner test 05','https://drive.google.com/file/d/1GLE9rD9Rlt4iMz2ywFs5EEtbg_rjTuEm/view?usp=sharing',37,'https://drive.google.com/file/d/10__w8yVtwR3LujGGMYsBjIvIOBz8_DT_/view?usp=sharing');
/*!40000 ALTER TABLE `non_comercial_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offer`
--

DROP TABLE IF EXISTS `offer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_moment` datetime(6) DEFAULT NULL,
  `deadline` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_money_amount` double DEFAULT NULL,
  `max_money_currency` varchar(255) DEFAULT NULL,
  `min_money_amount` double DEFAULT NULL,
  `min_money_currency` varchar(255) DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_iex7e8fs0fh89yxpcnm1orjkm` (`ticker`),
  KEY `IDXq2o9psuqfuqmq59f0sq57x9uf` (`deadline`),
  KEY `IDXcp4664f36sgqsd0ihmirt0w0` (`ticker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer`
--

LOCK TABLES `offer` WRITE;
/*!40000 ALTER TABLE `offer` DISABLE KEYS */;
INSERT INTO `offer` VALUES (16,0,'2019-09-09 08:00:00.000000','2019-10-09 08:00:00.000000','This is a description of the offer',65000,'EUR',45000,'EUR','OABCD-12345','Sample offer 01'),(17,0,'2019-09-09 08:00:00.000000','2020-10-09 08:00:00.000000','This is a description of the active offer',75000,'EUR',65000,'EUR','OABCD-12346','Sample offer 02'),(18,0,'2019-09-09 08:00:00.000000','2020-10-09 08:00:00.000000','This is a description of the active offer',20000,'EUR',15000,'EUR','ODCBA-64321','Sample offer 03'),(19,0,'2018-09-09 08:00:00.000000','2030-10-09 08:00:00.000000','This is a description of the active offer',2000.02,'EUR',1000.01,'EUR','OBBBB-99999','Sample offer 04'),(20,0,'2019-01-01 09:00:00.000000','2050-10-09 08:00:00.000000','This is a description of the active offer',55555.55,'EUR',44444.44,'EUR','OQWER-12321','Sample offer 05');
/*!40000 ALTER TABLE `offer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `provider`
--

DROP TABLE IF EXISTS `provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `provider` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` (`user_account_id`),
  CONSTRAINT `FK_b1gwnjqm6ggy9yuiqm0o4rlmd` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `provider`
--

LOCK TABLES `provider` WRITE;
/*!40000 ALTER TABLE `provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `provider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sponsor`
--

DROP TABLE IF EXISTS `sponsor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sponsor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `credit_card_number` varchar(255) DEFAULT NULL,
  `organization_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_20xk0ev32hlg96kqynl6laie2` (`user_account_id`),
  CONSTRAINT `FK_20xk0ev32hlg96kqynl6laie2` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sponsor`
--

LOCK TABLES `sponsor` WRITE;
/*!40000 ALTER TABLE `sponsor` DISABLE KEYS */;
INSERT INTO `sponsor` VALUES (37,0,36,'4617307392039794','Organization 1'),(40,0,39,NULL,'Organization 2');
/*!40000 ALTER TABLE `sponsor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `identity_email` varchar(255) DEFAULT NULL,
  `identity_name` varchar(255) DEFAULT NULL,
  `identity_surname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (1,0,_binary '\0','john.doe@acme.com','John','Doe','$2a$05$1.aJ4sk.I0aNxOMLqWgg0erb4Io2ghK4/uvvlf12WY8roCUnk4kUi','anonymous'),(3,0,_binary '','administrator@acme.com','Administrator','Acme.com','$2a$05$lasyBKRxKxTk.Y/YnCXWD.IPquT2.4E8zAuonrxdy0U0FxEGGIAQC','administrator'),(36,0,_binary '','sponsor1@acme.com','Sponsor','One','$2a$05$Pk1nBZCN73CF/Yxfzxq8M.lOS7DbdrqbZJtEMglmstHxD/pP24nLq','sponsor1'),(39,0,_binary '','sponsor2@acme.com','Sponsor','Two','$2a$05$ANxy.mj4yL8AzG4XNvangex6LjyB5VERcaAJ2kfXGrcm4gdBREDKu','sponsor2'),(53,0,_binary '','employer1@acme.com','Employer','One','$2a$05$ADWM.GPe0.QBM6ZU9d1mJOzA4zwRwjiD3.Luxk/JFq5QaXQnL3iPO','employer1'),(56,0,_binary '','employer2@acme.com','Employer','Two','$2a$05$48addu/1HLaUfN5HF/ev1et.3ggCL6.TL6yz76KvDVFozLqHfOQ5y','employer2'),(75,0,_binary '','auditor1@acme.com','Auditor','One','$2a$05$6ICrvNSs44bVb79kYECzFOE2HooGEZG8RLDe8mqFUAwR/1YqPR7a2','auditor1'),(78,0,_binary '','auditor2@acme.com','Auditor','Two','$2a$05$ET1OUzJAfgM5FbG3lLmoIOc6KykMlk8Gh3CaZSj4OqSjE3EBprJrG','auditor2'),(86,0,_binary '','worker1@acme.com','Worker','One','$2a$05$/1qTxyr/gnaTFcg6RbrFSO7kizQYLW5yhRhPqasSVaH13vxLXToXu','worker1'),(89,0,_binary '','worker2@acme.com','Worker','Two','$2a$05$JFuZ/zsRfU9kX7iDgrIH2.Uqsib9xknf78QQ51gxbHqf1cMqEF6DG','worker2');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worker`
--

DROP TABLE IF EXISTS `worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `user_account_id` int(11) DEFAULT NULL,
  `qualification_record` varchar(1024) DEFAULT NULL,
  `skill_record` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l5q1f33vs2drypmbdhpdgwfv3` (`user_account_id`),
  CONSTRAINT `FK_l5q1f33vs2drypmbdhpdgwfv3` FOREIGN KEY (`user_account_id`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worker`
--

LOCK TABLES `worker` WRITE;
/*!40000 ALTER TABLE `worker` DISABLE KEYS */;
INSERT INTO `worker` VALUES (87,0,86,'Qualifications worker 1','Skills worker 1'),(90,0,89,'Qualifications worker 2','Skills worker 2');
/*!40000 ALTER TABLE `worker` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-04 20:49:48
