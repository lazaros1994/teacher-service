-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: teachers-db
-- ------------------------------------------------------
-- Server version	8.0.13


 SET NAMES utf8 ;
--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `teacher_has_lesson`
--

DROP TABLE IF EXISTS `teacher_has_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher_has_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher` int(11) NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `student_surname` varchar(45) NOT NULL,
  `day` varchar(45) NOT NULL,
  `start_hour` varchar(45) NOT NULL,
  `start_minute` varchar(45) NOT NULL,
  `end_hour` varchar(45) NOT NULL,
  `end_minute` varchar(45) NOT NULL,
  `course` varchar(45) NOT NULL,
  `euro_per_hour` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teach_fk_idx` (`teacher`),
  CONSTRAINT `teach_fk` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
--
-- Table structure for table `teacher_has_extra_lesson`
--

DROP TABLE IF EXISTS `teacher_has_extra_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher_has_extra_lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher` int(11) NOT NULL,
  `student_name` varchar(45) NOT NULL,
  `student_surname` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `start_hour` varchar(45) NOT NULL,
  `start_minute` varchar(45) NOT NULL,
  `end_hour` varchar(45) NOT NULL,
  `end_minute` varchar(45) NOT NULL,
  `course` varchar(45) NOT NULL,
  `euro_per_hour` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `teacher_fk_extra_idx` (`teacher`),
  CONSTRAINT `teacher_fk_extra` FOREIGN KEY (`teacher`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `lesson_is_cancelled`
--

DROP TABLE IF EXISTS `lesson_is_cancelled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lesson_is_cancelled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson` int(11) NOT NULL,
  `year` int(11) NOT NULL,
  `month` varchar(45) NOT NULL,
  `day` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cl_fk_idx` (`lesson`),
  CONSTRAINT `cl_fk` FOREIGN KEY (`lesson`) REFERENCES `teacher_has_lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


--
-- Table structure for table `extra_lesson_is_cancelled`
--

DROP TABLE IF EXISTS `extra_lesson_is_cancelled`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `extra_lesson_is_cancelled` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `extra_lesson` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `elc_fk_idx` (`extra_lesson`),
  CONSTRAINT `elc_fk` FOREIGN KEY (`extra_lesson`) REFERENCES `teacher_has_extra_lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
