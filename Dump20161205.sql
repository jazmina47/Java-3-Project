-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: healthcare_clinic
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appointment` (
  `appointment_date` varchar(19) NOT NULL,
  `appointment_time` varchar(19) NOT NULL,
  `complaint` varchar(50) NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `requested_doctor` varchar(30) DEFAULT NULL,
  `staff_id` int(10) unsigned DEFAULT NULL,
  `checkin` enum('Y','N','C','NS') NOT NULL DEFAULT 'N',
  `nurse_visit` enum('Y','N','C','NS') DEFAULT NULL,
  `doctor_visit` enum('Y','N','C','NS') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
INSERT INTO `appointment` VALUES ('2016-10-22','9:00 am','My Wings have fallen off',4000,NULL,1000,'Y',NULL,NULL),('2016-10-25','9:00 am','I am very naggy',4000,NULL,1000,'NS','NS','NS'),('2016-10-25','11:00 am','nothing',4000,NULL,1002,'NS','NS','NS'),('2016-10-31','9:00 am','Stomach pains',4001,'1002',1002,'Y','Y','Y'),('2016-11-16','11:00 am','I feel so old.',4002,NULL,NULL,'N',NULL,NULL),('2016-12-05','10:00 am','broken arm',4003,'1002',NULL,'Y','Y','Y'),('2016-12-05','11:00 am','broken arm #2',4003,NULL,NULL,'N',NULL,NULL);
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diagnosis_chart`
--

DROP TABLE IF EXISTS `diagnosis_chart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diagnosis_chart` (
  `diagnosis` varchar(30) NOT NULL,
  `cost` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diagnosis_chart`
--

LOCK TABLES `diagnosis_chart` WRITE;
/*!40000 ALTER TABLE `diagnosis_chart` DISABLE KEYS */;
INSERT INTO `diagnosis_chart` VALUES ('headache','45'),('neck pain','80'),('shoulder pain','80'),('chest pain','80'),('back pain','80'),('stomach pain','80'),('obesity','80'),('high blood pressure','95'),('thyroids','80'),('bad knee','3800'),('bad heart','4000'),('anxiety','140'),('depression','180'),('allergy','145'),('asthma','145'),('arthritis','145'),('painful hip joint','245'),('broken body part','1700'),('tumor removal','2400'),('no show','75'),('canceled','0');
/*!40000 ALTER TABLE `diagnosis_chart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_notes`
--

DROP TABLE IF EXISTS `doctor_notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor_notes` (
  `appointment_date` varchar(20) NOT NULL,
  `appointment_time` varchar(20) NOT NULL,
  `patient_id` varchar(20) NOT NULL,
  `height` varchar(20) NOT NULL,
  `weight` varchar(20) NOT NULL,
  `heart_rate` varchar(20) NOT NULL,
  `blood_pressure` varchar(20) NOT NULL,
  `medication` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_notes`
--

LOCK TABLES `doctor_notes` WRITE;
/*!40000 ALTER TABLE `doctor_notes` DISABLE KEYS */;
INSERT INTO `doctor_notes` VALUES ('2016-10-31','9:00 am','4001','66','150','70','120/80','none'),('2016-12-05','10:00 am','4003','72','157','120','25','none');
/*!40000 ALTER TABLE `doctor_notes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `ssn` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `zip_code` varchar(20) NOT NULL,
  `county` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `date_of_birth` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `staff_position` varchar(20) NOT NULL,
  `staff_speciality` varchar(20) NOT NULL,
  `staff_salary` varchar(20) NOT NULL,
  `staff_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('Kurt','Connors','996582227','352 Kinder Rd','losangeles','58720','Kindert','3598745897','1983-10-24','Male','Doctor','Wing Doctor','851421',1000),('Miles','Levinton','875326498','321 some ave','Elgin','58792','Kendal','9992543650','1984-10-15','Male','Doctor','Weight','663693',1001),('Charlie','Brown','656834789','587 Fantasy Lane','Chicago','56872','Kane','3314875962','1982-02-21','Male','Admin','Heart','900000',1002),('Jane','Roberts','333557727','212 Real Ave','Montgomery','58792','Kane','5793214865','1997-04-23','Female','Receptionist','None','1087008',1003),('John','Smith','999999999','254 Fake Street','Faketown','28795','Fake','2583651479','1996-05-27','Male','Receptionist','None','1159651',1005),('Amy','Rose','199203487','214 Sonic Lane','Montgomery','87954','Kendal','3565874454','1992-04-21','Female','Nurse','Caring','1043197',1006);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `ssn` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `zip_code` varchar(20) NOT NULL,
  `county` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `date_of_birth` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `immunization_status` varchar(20) NOT NULL,
  `emergency_contact` varchar(20) NOT NULL,
  `emergency_contact_relationship` varchar(20) NOT NULL,
  `emergency_contact_number` varchar(20) NOT NULL,
  `insurance` varchar(50) NOT NULL,
  `patient_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4004 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES ('lucifer','morningstar','987654321','231 Heck kitchen','LosAngeles','58794','Grill','6843241597','1994-10-04','Male','Up To Date','Maze','BodyGuard','6843152970','Medicaid  3258467',4000),('Suzie','Brown','888888888','254 Some Street','Chicago','31875','Kane','3210564789','1989-05-15','Female','Up To Date','Charlie','Brother','9876543210','Private Insurance FakeSome 2489584',4001),('Jack','Brown','999325764','325 Fake Lane','Bombshell','65678','Courthouse','9633578421','1997-09-11','Female','Up to date','Francis','Someone','9876523541','Medicaid  3842',4002),('Jack','Lemming','487356521','548 Congrats St','Bearinton','57821','Kane','6302688879','2000-12-12','Male','Up to date','Jane','Friend','6303659654','Medicaid  59812',4003);
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_bill`
--

DROP TABLE IF EXISTS `payment_bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_bill` (
  `diagnosis` varchar(30) NOT NULL,
  `appointment_date` varchar(19) NOT NULL,
  `appointment_time` varchar(19) NOT NULL,
  `payment` varchar(16) NOT NULL,
  `patient_id` int(10) unsigned NOT NULL,
  `paid` enum('Y','N') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_bill`
--

LOCK TABLES `payment_bill` WRITE;
/*!40000 ALTER TABLE `payment_bill` DISABLE KEYS */;
INSERT INTO `payment_bill` VALUES ('neck pain','2016-10-22','9:00 am','80',4000,'N'),('shoulder pain','2016-10-22','9:00 am','80',4000,'N'),('back pain','2016-10-22','9:00 am','80',4000,'N'),('no show','2016-10-25','9:00 am','75',4000,'Y'),('stomach pain','2016-10-31','9:00 am','80',4001,'Y'),('broken body part','2016-12-05','10:00 am','1700',4003,'Y');
/*!40000 ALTER TABLE `payment_bill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-05  9:09:18
