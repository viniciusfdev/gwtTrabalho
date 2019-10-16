-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: corretora
-- ------------------------------------------------------
-- Server version	5.7.26

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
-- Table structure for table `apartamento`
--

DROP TABLE IF EXISTS `apartamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartamento` (
  `idapartamento` int(11) NOT NULL AUTO_INCREMENT,
  `idimovel` int(11) DEFAULT NULL,
  `salas_jantar` int(11) NOT NULL,
  `andar` int(11) NOT NULL,
  `valor_condominio` float DEFAULT NULL,
  `portaria_24h` int(11) NOT NULL,
  PRIMARY KEY (`idapartamento`),
  UNIQUE KEY `idapartamento_UNIQUE` (`idapartamento`),
  KEY `idimovel_idx` (`idimovel`),
  CONSTRAINT `idimovel_fk2` FOREIGN KEY (`idimovel`) REFERENCES `imovel` (`idimovel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartamento`
--

LOCK TABLES `apartamento` WRITE;
/*!40000 ALTER TABLE `apartamento` DISABLE KEYS */;
INSERT INTO `apartamento` VALUES (1,2,1,4,1500,1),(2,5,2,30,5000,1);
/*!40000 ALTER TABLE `apartamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casa`
--

DROP TABLE IF EXISTS `casa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casa` (
  `idcasa` int(11) NOT NULL AUTO_INCREMENT,
  `idimovel` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcasa`),
  UNIQUE KEY `idcasa_UNIQUE` (`idcasa`),
  KEY `idimovel_idx` (`idimovel`),
  CONSTRAINT `idimovel_fk1` FOREIGN KEY (`idimovel`) REFERENCES `imovel` (`idimovel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casa`
--

LOCK TABLES `casa` WRITE;
/*!40000 ALTER TABLE `casa` DISABLE KEYS */;
INSERT INTO `casa` VALUES (1,1),(2,3),(3,4);
/*!40000 ALTER TABLE `casa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `idendereco` int(11) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(100) NOT NULL,
  PRIMARY KEY (`idendereco`),
  UNIQUE KEY `idendereco_UNIQUE` (`idendereco`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'Barreiro'),(2,'Barro Preto');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imovel`
--

DROP TABLE IF EXISTS `imovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imovel` (
  `idimovel` int(11) NOT NULL AUTO_INCREMENT,
  `quartos` int(11) NOT NULL,
  `suites` int(11) NOT NULL,
  `salas_estar` int(11) NOT NULL,
  `vagas_garagem` int(11) DEFAULT NULL,
  `area` int(11) NOT NULL,
  `armario` tinyint(4) NOT NULL,
  `descricao` varchar(150) DEFAULT NULL,
  `idendereco` int(11) DEFAULT NULL,
  PRIMARY KEY (`idimovel`),
  UNIQUE KEY `idimovel_UNIQUE` (`idimovel`),
  KEY `idendereco_fk_idx` (`idendereco`),
  CONSTRAINT `idendereco_fk` FOREIGN KEY (`idendereco`) REFERENCES `endereco` (`idendereco`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imovel`
--

LOCK TABLES `imovel` WRITE;
/*!40000 ALTER TABLE `imovel` DISABLE KEYS */;
INSERT INTO `imovel` VALUES (1,4,1,1,2,50,0,'Casa Comum 1',NULL),(2,2,0,1,1,20,1,'Ocupação MST',NULL),(3,4,1,1,2,75,1,'Casa da Manu',1),(4,4,2,1,3,80,1,'Casa do God',2),(5,8,2,2,4,150,1,'Cafofo do Vi',1);
/*!40000 ALTER TABLE `imovel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-14 21:32:54
