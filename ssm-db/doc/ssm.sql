-- MySQL dump 10.13  Distrib 5.1.57, for Win32 (ia32)
--
-- Host: localhost    Database: ssm
-- ------------------------------------------------------
-- Server version	5.1.57-community

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
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `C` int(11) NOT NULL DEFAULT '0',
  `Cname` varchar(10) DEFAULT NULL,
  `T` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`C`,`T`),
  KEY `T` (`T`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`T`) REFERENCES `teacher` (`T`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'数学',1),(2,'物理',2),(3,'地理',3),(4,'数据结构',4),(5,'数据库',4);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sc`
--

DROP TABLE IF EXISTS `sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sc` (
  `S` int(11) DEFAULT NULL,
  `C` int(11) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  KEY `S` (`S`),
  KEY `C` (`C`),
  CONSTRAINT `sc_ibfk_1` FOREIGN KEY (`S`) REFERENCES `student` (`S`),
  CONSTRAINT `sc_ibfk_2` FOREIGN KEY (`C`) REFERENCES `course` (`C`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc`
--

LOCK TABLES `sc` WRITE;
/*!40000 ALTER TABLE `sc` DISABLE KEYS */;
INSERT INTO `sc` VALUES (1,1,75),(2,1,53),(3,1,98),(4,1,40),(5,1,67),(6,1,76),(7,1,79),(8,1,85),(9,1,86),(10,1,92),(1,2,34),(2,2,53),(3,2,90),(4,2,44),(5,2,57),(6,2,76),(7,2,73),(8,2,82),(9,2,56),(10,2,82),(2,3,53),(3,3,94),(4,3,55),(5,3,69),(6,3,78),(7,3,89),(8,3,65),(9,3,86),(10,3,92),(7,4,53),(8,4,65),(9,4,86),(10,4,59),(2,5,53),(3,5,94),(7,5,53),(8,5,65);
/*!40000 ALTER TABLE `sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_menu`
--

DROP TABLE IF EXISTS `sm_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_menu` (
  `i_id` int(11) NOT NULL,
  `s_name` varchar(100) DEFAULT NULL,
  `s_href` varchar(200) DEFAULT NULL,
  `s_icon` varchar(200) DEFAULT NULL,
  `s_target` varchar(100) DEFAULT NULL,
  `i_sort` int(11) DEFAULT NULL,
  `s_show` char(1) DEFAULT NULL,
  `i_parent` int(11) DEFAULT NULL,
  `s_permissioncode` varchar(100) DEFAULT NULL,
  `ts_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ts_lastupdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_menu`
--

LOCK TABLES `sm_menu` WRITE;
/*!40000 ALTER TABLE `sm_menu` DISABLE KEYS */;
INSERT INTO `sm_menu` VALUES (1,'root','#','','',1,'',NULL,'','2015-08-27 04:41:38','2015-08-27 04:41:40');
/*!40000 ALTER TABLE `sm_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_role`
--

DROP TABLE IF EXISTS `sm_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_role` (
  `int_id` int(11) DEFAULT NULL,
  `vcr_rolecode` varchar(100) DEFAULT NULL,
  `vcr_name` varchar(100) DEFAULT NULL,
  `vcr_desc` char(10) DEFAULT NULL,
  `tms_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tms_lastupdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_role`
--

LOCK TABLES `sm_role` WRITE;
/*!40000 ALTER TABLE `sm_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `sm_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_role_menu`
--

DROP TABLE IF EXISTS `sm_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_role_menu` (
  `int_roleid` int(11) NOT NULL,
  `int_menuid` int(11) NOT NULL,
  `tms_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`int_roleid`,`int_menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_role_menu`
--

LOCK TABLES `sm_role_menu` WRITE;
/*!40000 ALTER TABLE `sm_role_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `sm_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_user_auth`
--

DROP TABLE IF EXISTS `sm_user_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_user_auth` (
  `i_id` int(11) NOT NULL,
  `i_userid` int(11) DEFAULT NULL,
  `s_authcode` varchar(32) DEFAULT NULL,
  `s_authpwd` varchar(64) DEFAULT NULL,
  `s_encrypttype` varchar(10) DEFAULT NULL,
  `ts_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ts_lastupdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ts_lastlogin` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户认证信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_user_auth`
--

LOCK TABLES `sm_user_auth` WRITE;
/*!40000 ALTER TABLE `sm_user_auth` DISABLE KEYS */;
INSERT INTO `sm_user_auth` VALUES (1,1,'admin','1234',NULL,'2015-09-01 08:38:48','2015-09-15 08:29:33','2015-09-01 08:29:36');
/*!40000 ALTER TABLE `sm_user_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_userbaseinfo`
--

DROP TABLE IF EXISTS `sm_userbaseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_userbaseinfo` (
  `i_id` int(11) NOT NULL,
  `s_NAME` varchar(30) DEFAULT NULL,
  `D_BIRTH_DAY` date DEFAULT NULL,
  `i_DEPT_ID` int(11) DEFAULT NULL,
  `s_GENDER` char(1) DEFAULT NULL,
  `i_age` int(11) DEFAULT NULL,
  `s_status` char(1) DEFAULT NULL,
  `ts_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ts_lastupdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_userbaseinfo`
--

LOCK TABLES `sm_userbaseinfo` WRITE;
/*!40000 ALTER TABLE `sm_userbaseinfo` DISABLE KEYS */;
INSERT INTO `sm_userbaseinfo` VALUES (1,'管理员','2015-08-31',NULL,'1',108,'1','2015-08-31 11:17:40','2015-08-31 11:17:43');
/*!40000 ALTER TABLE `sm_userbaseinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sm_userrole`
--

DROP TABLE IF EXISTS `sm_userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sm_userrole` (
  `int_userid` int(11) NOT NULL,
  `int_roleid` int(11) NOT NULL,
  `tms_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`int_userid`,`int_roleid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sm_userrole`
--

LOCK TABLES `sm_userrole` WRITE;
/*!40000 ALTER TABLE `sm_userrole` DISABLE KEYS */;
/*!40000 ALTER TABLE `sm_userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `S` int(11) NOT NULL DEFAULT '0',
  `Sname` varchar(10) DEFAULT NULL,
  `Sage` int(11) DEFAULT NULL,
  `Ssex` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`S`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'李明东',18,'男'),(2,'潘岳',17,'男'),(3,'司马良',17,'男'),(4,'马超',17,'男'),(5,'孙策',17,'男'),(6,'临渊羡鱼',17,'女'),(7,'公孙明月',17,'女'),(8,'萧艳艳',17,'女'),(9,'李清照',17,'女'),(10,'冷静',17,'女');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student1`
--

DROP TABLE IF EXISTS `student1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student1` (
  `S` int(11) DEFAULT NULL,
  `Sname` varchar(10) DEFAULT NULL,
  `Sage` int(11) DEFAULT NULL,
  `Ssex` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student1`
--

LOCK TABLES `student1` WRITE;
/*!40000 ALTER TABLE `student1` DISABLE KEYS */;
INSERT INTO `student1` VALUES (1,'李明东',18,'男'),(2,'潘小婷',27,'女'),(3,'司马良',17,'女'),(4,'马超',17,'男');
/*!40000 ALTER TABLE `student1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `T` int(11) NOT NULL DEFAULT '0',
  `Tname` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`T`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'李老师'),(2,'王老师'),(3,'孙老师'),(4,'刘勇');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) DEFAULT NULL,
  `AGE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'悟空',520),(2,'八戒',460);
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

-- Dump completed on 2015-10-24  8:20:14
