-- MySQL dump 10.13  Distrib 5.7.44, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Current Database: `quiz`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `quiz` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `quiz`;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questionText` varchar(255) NOT NULL,
  `answer1Text` varchar(255) NOT NULL,
  `answer1Correct` tinyint(1) NOT NULL DEFAULT '0',
  `answer2Text` varchar(255) NOT NULL,
  `answer2Correct` tinyint(1) NOT NULL DEFAULT '0',
  `answer3Text` varchar(255) NOT NULL,
  `answer3Correct` tinyint(1) NOT NULL DEFAULT '0',
  `answer4Text` varchar(255) NOT NULL,
  `answer4Correct` tinyint(1) NOT NULL DEFAULT '0',
  `isDelete` tinyint(4) DEFAULT NULL,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What is the capital of France2?','Paris',1,'London',0,'Berlin',0,'Madrid',0,0,'2025-11-14 11:34:46','2025-11-14 11:34:46'),(2,'which of the following is not a programming language?','Java',0,'Apple',1,'Python',0,'Javascript',0,1,'2025-11-14 13:53:32','2025-11-14 13:53:32'),(3,'Which of the following is not an operating system?','Windows',0,'Linux',0,'Python',1,'macOS',0,0,'2025-11-21 10:05:54','2025-11-21 10:05:54'),(4,'Which of the following is not a database management system?','MySQL',0,'Oracle',0,'Java',1,'PostgreSQL',0,0,'2025-11-21 10:06:24','2025-11-21 10:06:24'),(5,'Which of the following is not a web browser?','Chrome',0,'Firefox',0,'Safari',0,'HTML',1,0,'2025-11-21 10:06:59','2025-11-21 10:06:59'),(6,'Which of the following is not a version control system?','Git',0,'SVN',0,'Mercurial',0,'PHP',1,0,'2025-11-21 10:07:27','2025-11-21 10:07:27'),(7,'Which of the following is not a cloud computing service?','AWS',0,'Azure',0,'Google Cloud',0,'Ruby',1,0,'2025-11-21 10:07:54','2025-11-21 10:07:54'),(8,'test1','aaa',1,'b',0,'c',0,'d',0,0,'2025-11-21 10:54:04','2025-11-21 11:43:45'),(9,'test2','a',0,'b',0,'c',1,'d',0,0,'2025-11-21 11:00:48','2025-11-21 11:00:48'),(10,'3','a',0,'b',1,'c',0,'d',0,1,'2025-11-21 11:02:02','2025-11-21 11:02:02'),(11,'What is the capital of France2?','Paris',1,'London',0,'Berlin',0,'Madrid',0,0,'2025-11-29 17:58:23','2025-11-29 17:58:23'),(12,'What is the capital of France2?','Paris',1,'London',0,'Berlin',0,'Madrid',0,0,'2025-11-29 18:12:26','2025-11-29 18:12:26');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(256) DEFAULT NULL COMMENT '用户名',
  `userPassword` varchar(512) DEFAULT NULL COMMENT '密码',
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `createTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `isDelete` tinyint(4) DEFAULT NULL,
  `userRole` int(11) DEFAULT '0' COMMENT '表示用户角色， 0 普通用户， 1 管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'fromapifox','abb283b0200e38c5676e51f4ee23f28b','2025-11-29 17:58:22','2025-11-11 21:05:00',1,0),(2,'forafterservice','abb283b0200e38c5676e51f4ee23f28b','2025-11-12 09:16:59','2025-11-12 09:16:59',0,0),(3,'toms','14c0cfb979f4fd6994f57af3f997e2b1','2025-11-29 17:58:22','2025-11-12 09:28:00',1,0),(4,'test1','1e44dc7f976ab5abb93eb857edf403b5','2025-11-26 09:50:01','2025-11-26 09:45:36',1,0),(5,'test1','1e44dc7f976ab5abb93eb857edf403b5','2025-11-28 09:55:55','2025-11-28 09:55:27',1,1),(6,'test1','1e44dc7f976ab5abb93eb857edf403b5','2025-11-28 10:11:29','2025-11-28 09:56:01',1,1),(7,'test1','1e44dc7f976ab5abb93eb857edf403b5','2025-11-28 10:49:59','2025-11-28 10:11:33',0,1),(8,'user','abb283b0200e38c5676e51f4ee23f28b','2025-11-28 10:49:33','2025-11-28 10:49:33',0,1),(9,'testuser','password123','2025-11-29 17:58:22','2025-11-29 17:58:22',0,0),(10,'testuser','password123','2025-11-29 18:12:26','2025-11-29 18:12:26',0,0);
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

-- Dump completed on 2025-11-29 19:32:22
