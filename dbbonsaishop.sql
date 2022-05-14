-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: shopbonsai
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `is_default` bit(1) NOT NULL,
  `province` varchar(255) DEFAULT NULL,
  `specific_address` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `type_address` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda8tuywtf0gb6sedwk7la1pgi` (`user_id`),
  CONSTRAINT `FKda8tuywtf0gb6sedwk7la1pgi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,NULL,NULL,_binary '\0','Administrator','2022-05-05 16:40:41.844000','369852177','huyện Thanh Thủy','Nguyễn Thị Huệ',_binary '\0','tỉnh Phú Thọ','Khu 2','Xã Đào Xá','Nhà bạn',2),(2,NULL,NULL,_binary '\0',NULL,NULL,'0868776094','huyện Thanh Thủy','Nguyễn Thị Hà',_binary '','tỉnh Phú Thọ','Khu 3','xã Xuân Lộc','Công ty',2),(3,NULL,NULL,_binary '','Administrator','2022-05-05 16:54:57.935000','0868776094','Lục Ngạn','Nguyễn Văn Đạt',_binary '\0','Bắc Ninh','Khu 7','Bắc Giang','Nhà',2),(9,'datdeptrai','2022-05-04 00:48:37.658000',_binary '\0',NULL,NULL,'0868776094','Tam Nông','Nguyễn Văn Đạt',_binary '\0','Bắc Ninh','Khu 13','Phú Thọ','Nhà',1),(10,'datnv','2022-05-04 16:15:45.417000',_binary '\0',NULL,NULL,'0256398523','Tam Đảo','Nguyen Thi Ha',_binary '','Bắc Ninh','ngách 80/21','Hoàng Xá','Công ty',3),(11,'fck ui','2022-05-04 21:59:15.952000',_binary '\0',NULL,NULL,'0369852159','Nam Từ Liêm','Nguyen Z',_binary '\0','Bắc Ninh','Khu 13','Hoàng Xá','Nhà',7),(12,'fck ui','2022-05-05 17:15:51.726000',_binary '\0',NULL,NULL,'0256398523','Tam Nông','Nguyen Thi Ha',_binary '','Bắc Ninh','ngách 80/21','Hoàng Xá','Nhà bạn',7),(13,'datdeptrai','2022-05-06 15:59:08.905000',_binary '\0',NULL,NULL,'0256398476','Tam Đảo','Nguyen Ha',_binary '','Bắc Ninh','ngách 80/21','Bắc Giang','Nhà',1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `quantity` int NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3d704slv66tw6x5hmbm6p2x3u` (`product_id`),
  KEY `FKl70asp4l4w0jmbm1tqyofho4o` (`user_id`),
  CONSTRAINT `FK3d704slv66tw6x5hmbm6p2x3u` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (13,'datdeptrai','2022-05-11 14:01:42.440000',_binary '\0',NULL,NULL,1,44,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,NULL,NULL,_binary '\0',NULL,NULL,'Cây để bàn',NULL),(2,NULL,NULL,_binary '\0',NULL,NULL,'Cây nội thất',NULL),(3,NULL,NULL,_binary '\0',NULL,NULL,'Cây phong thủy',NULL),(4,NULL,NULL,_binary '\0',NULL,NULL,'Cây văn phòng',NULL),(5,NULL,NULL,_binary '\0',NULL,NULL,'Cây dây leo',NULL),(6,NULL,NULL,_binary '','Administrator','2022-05-04 21:24:22.010000','Trống',NULL),(7,NULL,NULL,_binary '','Administrator','2022-05-04 21:24:18.867000','Cây Trong Chậu Để Ngoài ',NULL),(8,NULL,NULL,_binary '','Administrator','2022-05-03 22:57:47.126000','Cây Cảnh Trưng Bày cc',NULL),(9,NULL,NULL,_binary '','Administrator','2022-05-04 21:24:16.393000','Cây Hồng Xiêm Đột BIến',NULL),(10,NULL,NULL,_binary '','fck ','2022-05-05 21:04:34.233000','Cây Hồng ',NULL),(11,NULL,NULL,_binary '','Administrator','2022-05-12 01:10:08.461000','Nguyễn Văn Đạtghtyu',NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `discount` bigint NOT NULL,
  `price` bigint NOT NULL,
  `quantity` int DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbjrnrx2banl5xt33fjh8sutre` (`order_id`),
  KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
  CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKbjrnrx2banl5xt33fjh8sutre` FOREIGN KEY (`order_id`) REFERENCES `ordered` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (6,NULL,NULL,_binary '\0',NULL,NULL,3000,30000,2,4,1),(7,NULL,NULL,_binary '\0',NULL,NULL,6000,60000,4,3,1),(8,NULL,NULL,_binary '\0',NULL,NULL,3000,30000,3,3,2),(9,NULL,NULL,_binary '\0',NULL,NULL,5000,55000,5,2,3),(10,NULL,NULL,_binary '','Administrator','2022-05-12 01:30:23.046000',1500,15000,1,1,4),(23,'datdeptrai','2022-05-04 15:36:19.260000',_binary '\0','Administrator','2022-05-04 21:17:58.827000',27900,558000,2,24,44),(24,'datdeptrai','2022-05-04 15:36:19.278000',_binary '\0','Administrator','2022-05-04 21:17:58.839000',22350,447000,3,24,41),(25,'datdeptrai','2022-05-04 15:36:19.283000',_binary '\0','Administrator','2022-05-04 21:17:58.845000',27800,556000,4,24,43),(26,'Administrator','2022-05-04 15:56:39.914000',_binary '\0',NULL,NULL,1500,30000,2,25,11),(27,'Administrator','2022-05-04 15:56:39.928000',_binary '\0',NULL,NULL,20250,405000,3,25,3),(28,'datdeptrai','2022-05-04 16:07:59.374000',_binary '\0',NULL,NULL,1500,30000,2,26,15),(29,'datdeptrai','2022-05-04 16:07:59.392000',_binary '\0',NULL,NULL,19500,390000,3,26,5),(30,'datnv','2022-05-04 16:16:09.104000',_binary '\0',NULL,NULL,37500,750000,3,27,40),(31,'datnv','2022-05-04 16:16:09.120000',_binary '\0',NULL,NULL,55800,1116000,4,27,44),(32,'datnv','2022-05-04 16:22:07.898000',_binary '\0',NULL,NULL,1500,30000,2,28,11),(33,'datnv','2022-05-04 16:22:07.908000',_binary '\0',NULL,NULL,20850,417000,3,28,43),(34,'datdeptrai','2022-05-04 21:43:29.133000',_binary '\0',NULL,NULL,39900,798000,2,29,38),(35,'datdeptrai','2022-05-04 21:50:24.673000',_binary '\0',NULL,NULL,38950,779000,1,30,45),(36,'datdeptrai','2022-05-04 21:56:12.974000',_binary '\0',NULL,NULL,52150,1043000,7,31,41),(37,'fck ui','2022-05-04 21:59:33.178000',_binary '\0',NULL,NULL,54900,1098000,2,32,42),(38,'fck ui','2022-05-04 21:59:58.329000',_binary '\0',NULL,NULL,54900,1098000,2,33,42),(39,'datdeptrai','2022-05-04 22:34:47.296000',_binary '\0',NULL,NULL,34900,698000,2,34,32),(40,'datdeptrai','2022-05-04 22:42:10.397000',_binary '\0',NULL,NULL,14900,298000,2,35,41),(41,'datdeptrai','2022-05-04 22:51:45.099000',_binary '\0',NULL,NULL,27900,558000,2,36,44),(42,'fck ui','2022-05-04 23:19:36.061000',_binary '\0',NULL,NULL,20850,417000,3,37,43),(43,'fck ui','2022-05-04 23:19:36.076000',_binary '\0',NULL,NULL,7450,149000,1,37,41),(44,'datdeptrai','2022-05-04 23:52:02.797000',_binary '\0',NULL,NULL,13500,270000,2,38,3),(45,'datdeptrai','2022-05-04 23:52:02.810000',_binary '\0',NULL,NULL,4350,87000,3,38,18),(46,'datdeptrai','2022-05-04 23:52:02.816000',_binary '\0',NULL,NULL,44900,898000,2,38,39),(47,'fck ui','2022-05-05 01:49:59.382000',_binary '\0',NULL,NULL,44900,898000,2,39,39),(48,'fck ui','2022-05-05 01:49:59.399000',_binary '\0',NULL,NULL,12500,250000,1,39,40),(49,'datdeptrai','2022-05-05 14:52:12.851000',_binary '\0',NULL,NULL,14900,298000,2,40,41),(50,'fck ui','2022-05-05 17:21:54.487000',_binary '\0',NULL,NULL,5250,105000,7,41,9),(51,'datdeptrai','2022-05-06 15:47:39.801000',_binary '\0',NULL,NULL,77900,1558000,2,42,45),(52,'datdeptrai','2022-05-06 15:47:39.810000',_binary '\0',NULL,NULL,6950,139000,1,42,43),(53,'datdeptrai','2022-05-06 15:47:39.815000',_binary '\0',NULL,NULL,19950,399000,1,42,38),(54,'datdeptrai','2022-05-06 15:50:46.364000',_binary '\0',NULL,NULL,17900,358000,2,43,37),(55,'datdeptrai','2022-05-06 15:50:46.373000',_binary '\0',NULL,NULL,21000,420000,2,43,33),(56,'datdeptrai','2022-05-08 00:07:25.040000',_binary '\0',NULL,NULL,77900,1558000,2,44,45),(57,'datdeptrai','2022-05-08 00:07:25.052000',_binary '\0',NULL,NULL,13950,279000,1,44,44),(58,'datdeptrai','2022-05-10 04:36:04.190000',_binary '\0',NULL,NULL,77900,1558000,2,45,45),(59,'datdeptrai','2022-05-10 04:36:04.202000',_binary '\0',NULL,NULL,5500,110000,1,45,31),(60,'fckkkk z','2022-05-11 14:02:31.987000',_binary '\0',NULL,NULL,750,15000,1,46,15),(61,'fckkkk z','2022-05-11 14:02:31.997000',_binary '\0',NULL,NULL,12900,258000,2,46,1),(62,'datnv','2022-05-12 03:27:36.701000',_binary '\0',NULL,NULL,21000,420000,2,47,33),(63,'datnv','2022-05-12 03:27:36.715000',_binary '\0',NULL,NULL,19350,387000,3,47,35),(64,'datnv','2022-05-12 07:25:56.321000',_binary '\0',NULL,NULL,25000,500000,2,48,40),(65,'datnv','2022-05-12 07:25:56.337000',_binary '\0',NULL,NULL,14900,298000,2,48,41),(66,'datnv','2022-05-12 07:29:33.977000',_binary '\0',NULL,NULL,77900,1558000,2,49,45);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordered`
--

DROP TABLE IF EXISTS `ordered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordered` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `order_code` varchar(255) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41kb30ru4dhyoiuadqhp7232r` (`address_id`),
  KEY `FKjqbh5jbj1olkur6fpmle5r9ev` (`user_id`),
  KEY `FKr3oqsy8q6r4fqu4ycwo9n2nsv` (`voucher_id`),
  CONSTRAINT `FK41kb30ru4dhyoiuadqhp7232r` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKjqbh5jbj1olkur6fpmle5r9ev` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr3oqsy8q6r4fqu4ycwo9n2nsv` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered`
--

LOCK TABLES `ordered` WRITE;
/*!40000 ALTER TABLE `ordered` DISABLE KEYS */;
INSERT INTO `ordered` VALUES (1,'Administrator','2022-05-04 15:56:39.882000',_binary '','Administrator','2022-05-12 01:30:23.062000','Chờ',2,'#ABC123',1,NULL),(2,'Administrator','2022-05-04 15:56:39.882000',_binary '\0','Administrator','2022-05-12 02:12:10.915000','Đã_giao',2,'#DEF456',2,2),(3,'Administrator','2022-05-04 15:56:39.882000',_binary '\0',NULL,NULL,'Đã_giao',2,'#GHI789',3,NULL),(4,'Administrator','2022-05-04 15:56:39.882000',_binary '\0',NULL,NULL,'Đã_hủy',2,'#JKL147',1,NULL),(24,'datdeptrai','2022-05-04 15:36:19.216000',_binary '\0','Administrator','2022-05-04 21:17:58.754000','Đã_hủy',1,'Dwq7Mv',9,1),(25,'Administrator','2022-05-04 15:56:39.882000',_binary '\0',NULL,NULL,'Đã_giao',2,'K9tZMC',1,NULL),(26,'datdeptrai','2022-05-04 16:07:59.345000',_binary '\0','Administrator','2022-05-04 21:12:26.274000','Đã_hủy',1,'tNdKa6',9,NULL),(27,'datnv','2022-05-04 16:16:09.074000',_binary '\0','Administrator','2022-05-04 20:54:46.631000','Đã_hủy',3,'HGDWKJ',10,NULL),(28,'datnv','2022-05-04 16:22:07.878000',_binary '\0','Administrator','2022-05-05 22:52:45.194000','Đã_giao',3,'mSWlPT',10,1),(29,'datdeptrai','2022-05-04 21:43:29.106000',_binary '\0',NULL,NULL,'Chờ',1,'6woyui',9,1),(30,'datdeptrai','2022-05-04 21:50:24.659000',_binary '\0',NULL,NULL,'Chờ',1,'O8tZkY',9,NULL),(31,'datdeptrai','2022-05-04 21:56:12.964000',_binary '\0',NULL,NULL,'Đang_giao_hàng',1,'1twZUx',9,NULL),(32,'fck ui','2022-05-04 21:59:33.151000',_binary '\0',NULL,NULL,'Chờ',7,'LnBEMM',11,NULL),(33,'fck ui','2022-05-04 21:59:58.321000',_binary '\0',NULL,NULL,'Đang_giao_hàng',7,'qKlbqL',11,NULL),(34,'datdeptrai','2022-05-04 22:34:47.263000',_binary '\0',NULL,NULL,'Chờ',1,'YykiTx',9,NULL),(35,'datdeptrai','2022-05-04 22:42:10.374000',_binary '\0',NULL,NULL,'Chờ',1,'Jm2sNm',9,NULL),(36,'datdeptrai','2022-05-04 22:51:45.070000',_binary '\0',NULL,NULL,'Đang_giao_hàng',1,'FIdQcD',9,NULL),(37,'fck ui','2022-05-04 23:19:36.045000',_binary '\0',NULL,NULL,'Chờ',7,'gBGmHh',11,NULL),(38,'datdeptrai','2022-05-04 23:52:02.768000',_binary '\0',NULL,NULL,'Đã_giao',1,'vI9mck',9,2),(39,'fck ui','2022-05-05 01:49:59.335000',_binary '\0',NULL,NULL,'Đã_giao',7,'KsNZNv',11,2),(40,'datdeptrai','2022-05-05 14:52:12.790000',_binary '\0',NULL,NULL,'Chờ',1,'Sc2KZ5',9,NULL),(41,'fck ui','2022-05-05 17:21:54.450000',_binary '\0','fck ui','2022-05-05 17:22:51.109000','Đã_hủy',7,'g6Ja9x',12,1),(42,'datdeptrai','2022-05-06 15:47:39.768000',_binary '\0',NULL,NULL,'Chờ',1,'4WHPiC',9,2),(43,'datdeptrai','2022-05-06 15:50:46.343000',_binary '\0',NULL,NULL,'Chờ',1,'92tosM',9,NULL),(44,'datdeptrai','2022-05-08 00:07:25.016000',_binary '\0',NULL,NULL,'Chờ',1,'h23QAN',13,1),(45,'datdeptrai','2022-05-10 04:36:04.165000',_binary '\0',NULL,NULL,'Chờ',1,'ZeVqH1',9,NULL),(46,'fckkkk z','2022-05-11 14:02:31.962000',_binary '\0',NULL,NULL,'Đã_giao',7,'RwmPLf',12,NULL),(47,'datnv','2022-05-12 03:27:36.669000',_binary '\0',NULL,NULL,'Chờ',7,'rIeLvt',12,1),(48,'datnv','2022-05-12 07:25:56.291000',_binary '\0',NULL,NULL,'Chờ',7,'LPEeDe',11,1),(49,'datnv','2022-05-12 07:29:33.969000',_binary '\0',NULL,NULL,'Chờ',7,'oEYIVp',12,NULL);
/*!40000 ALTER TABLE `ordered` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `payment_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,NULL,NULL,_binary '\0',NULL,NULL,'Truc Tiep'),(2,NULL,NULL,_binary '\0',NULL,NULL,'VN PAY');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_order`
--

DROP TABLE IF EXISTS `payment_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_order` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `total_paid` bigint NOT NULL,
  `order_id` bigint DEFAULT NULL,
  `payment_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2j00ualwvpsk6bwjbg6aavqy8` (`order_id`),
  KEY `FKdgex8e70h0449byuurkdmoo79` (`payment_id`),
  CONSTRAINT `FK2j00ualwvpsk6bwjbg6aavqy8` FOREIGN KEY (`order_id`) REFERENCES `ordered` (`id`),
  CONSTRAINT `FKdgex8e70h0449byuurkdmoo79` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_order`
--

LOCK TABLES `payment_order` WRITE;
/*!40000 ALTER TABLE `payment_order` DISABLE KEYS */;
INSERT INTO `payment_order` VALUES (16,'Administrator','2022-03-04 15:56:39.899000',_binary '','Administrator','2022-05-12 01:30:23.032000','Đã thanh toán',100000,1,1),(17,'Administrator','2022-03-04 15:56:39.899000',_binary '\0','Administrator','2022-05-12 02:12:10.932000','Đã thanh toán',100000,2,1),(18,'Administrator','2022-03-04 15:56:39.899000',_binary '\0',NULL,NULL,'Đã thanh toán',100000,3,1),(19,'Administrator','2022-03-04 15:56:39.899000',_binary '\0',NULL,NULL,'Chưa thanh toán',100000,4,1),(20,'datdeptrai','2022-03-04 15:36:19.242000',_binary '','Administrator','2022-05-04 21:17:58.767000','Chưa thanh toán',1791000,24,1),(21,'Administrator','2022-03-04 15:56:39.899000',_binary '\0',NULL,NULL,'Đã thanh toán',525250,25,1),(22,'datdeptrai','2022-03-04 16:07:59.358000',_binary '\0','Administrator','2022-05-04 21:12:26.337000','Đã thanh toán',533000,26,1),(23,'datnv','2022-04-04 16:16:09.089000',_binary '\0','Administrator','2022-05-04 20:54:46.698000','Đã thanh toán',2195900,27,1),(24,'datnv','2022-04-04 16:22:07.886000',_binary '\0','Administrator','2022-05-05 22:52:45.243000','Đã thanh toán',519050,28,1),(25,'datdeptrai','2022-04-04 21:43:29.118000',_binary '\0',NULL,NULL,'Đã thanh toán',922700,29,2),(26,'datdeptrai','2022-04-04 21:50:24.663000',_binary '\0',NULL,NULL,'Đã thanh toán',920850,30,2),(27,'datdeptrai','2022-05-04 21:56:12.968000',_binary '\0',NULL,NULL,'Đã thanh toán',1199450,31,2),(28,'fck ui','2022-05-04 21:59:33.160000',_binary '\0',NULL,NULL,'Chưa thanh toán',1262700,32,1),(29,'fck ui','2022-05-04 21:59:58.325000',_binary '\0',NULL,NULL,'Chưa thanh toán',1262700,33,2),(30,'datdeptrai','2022-05-04 22:34:47.273000',_binary '\0',NULL,NULL,'Chưa thanh toán',802700,34,2),(31,'datdeptrai','2022-05-04 22:42:10.383000',_binary '\0',NULL,NULL,'Chưa thanh toán',342700,35,2),(32,'datdeptrai','2022-05-04 22:51:45.083000',_binary '\0',NULL,NULL,'Đã thanh toán',641700,36,2),(33,'fck ui','2022-05-04 23:19:36.045000',_binary '\0',NULL,NULL,'Đã thanh toán',650900,37,2),(34,'datdeptrai','2022-05-04 23:52:02.782000',_binary '\0',NULL,NULL,'Đã thanh toán',1443250,38,2),(35,'fck ui','2022-05-05 01:49:59.351000',_binary '\0',NULL,NULL,'Đã thanh toán',1320200,39,2),(36,'datdeptrai','2022-05-05 14:52:12.823000',_binary '\0',NULL,NULL,'Đã thanh toán',342700,40,2),(37,'fck ui','2022-05-05 17:21:54.463000',_binary '\0',NULL,NULL,'Đã thanh toán',135250,41,2),(38,'datdeptrai','2022-05-06 15:47:39.777000',_binary '\0',NULL,NULL,'Chưa thanh toán',2410400,42,1),(39,'datdeptrai','2022-05-06 15:50:46.347000',_binary '\0',NULL,NULL,'Đã thanh toán',944700,43,2),(40,'datdeptrai','2022-05-08 00:07:25.023000',_binary '\0',NULL,NULL,'Đã thanh toán',2142550,44,2),(41,'datdeptrai','2022-05-10 04:36:04.177000',_binary '\0',NULL,NULL,'Chưa thanh toán',1918200,45,1),(42,'fckkkk z','2022-05-11 14:02:31.972000',_binary '\0',NULL,NULL,'Đã thanh toán',338950,46,2),(43,'datnv','2022-05-12 03:27:36.684000',_binary '\0',NULL,NULL,'Đã thanh toán',933050,47,2),(44,'datnv','2022-05-12 07:25:56.301000',_binary '\0',NULL,NULL,'Đã thanh toán',922700,48,2),(45,'datnv','2022-05-12 07:29:33.972000',_binary '\0',NULL,NULL,'Chưa thanh toán',1791700,49,1);
/*!40000 ALTER TABLE `payment_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `name_product` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `product_search` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `price_sale` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'admin','2022-05-05 20:58:00.971000',_binary '\0','Administrator','2022-05-12 02:03:08.054000','Chậu sen đá cặp đôi hoàn hảo gồm 2 sản phẩm, quý khách có thể tự lựa chọn cây và chậu, bên cây cảnh sẽ trang trí một cách hợp lý và đẹp nhất cho quý khách. Cây phù hợp để bàn làm việc, bàn học, của sổ, trang trí quán cà phê...','Cặp Đôi Hoàn Hảo','VN',129000,'cap-doi-hoan-hao',98,'Còn_hàng',129000),(2,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Kim Ngân hay còn gọi là cây thắt bím, cây bím tóc phù hợp với gia chủ muốn mua cây đặt ở phòng khách, phòng hội họp, văn phòng công sở, nhà hàng, khách sạn, hoặc dùng làm quà tặng trong những dịp mừng lễ, tết, thăng chức, khai trương. Các cây nhỏ có thể để bàn ngoài tác dụng đem lại tài lộc nó lại còn có thể đuổi muỗi.','Cây Kim Ngân Để Bàn','VN',135000,'cay-kim-ngan-de-ban',125,'Còn_hàng',135000),(3,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Ngọc Ngân hay còn có tên là cây Valentine trong tình cảm nó được đại diện cho tình yêu, nó sẽ là một món quà ý nghĩa đối với các cặp đôi. Nếu đặt ở văn phòng, bàn làm việc, trong nhà thì Ngọc Ngân sẽ mang đến sự may mắn và bổng lộc cho gia chủ.','Cây Ngọc Ngân','VN',155000,'cay-ngoc-ngan',99,'Còn_hàng',155000),(4,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây phát tài hay còn gọi là cây thiết mộc lan, phát tài khúc với ý nghĩa mang lại may mắn cho gia chủ trong cuộc sống và công việc. Sản phẩm phù hợp với gia chủ muốn mua cây phòng khách, phòng hội họp, văn phòng công sở, nhà hàng, khách sạn, hoặc dùng làm quà tặng trong những dịp mừng lễ, tết, thăng chức, khai trương...Hoa của cây phát tài rất thơm, một hương thơm dịu','Cây Phát Tài','VN',149000,'cay-phat-tai',100,'Còn_hàng',120000),(5,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Phú Quý có ý nghĩa phong thủy mang đến cho gia chủ sự may mắn, giàu sang và phú quý. Cây phù hợp để trang trí quán cà phê, góc nhỏ, sảnh, khách sạn...','Cây Phú Quý','VN',155000,'cay-phu-quy',97,'Còn_hàng',130000),(6,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Vạn Lộc hay còn gọi là cây Thiên Phú cây có ý nghĩa phong thủy mang đến tài lộc và may mắn cho gia chủ. Cây phù hợp làm cây phong thủy, cây để bàn, trang trí quán cà phê, để bàn uống nước, góc nhỏ trong nhà...','Cây Vạn Lộc','VN',179000,'cay-van-loc',100,'Còn_hàng',179000),(7,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá cánh bướm là một trong những loại sen đá lớn lá phát triển tối đa có thể từ 30 -40 cm một lá. Cây sen cánh bướm thường dùng để trang trí cách bàn làm việc lớn, khách sạn, nhà hàng, cây nhỏ có thể là tiểu cảnh hoặc trang trí bàn làm việc góc học tập. Cây mang ý nghĩa người bạn tri kỷ của bạn sẽ không trăng hoa óc và om bướm.','Sen Đá Cánh Bướm','VN',30000,'sen-da-canh-buom',100,'Còn_hàng',30000),(8,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá chuỗi ngọc bi mang đến sự đầy đủ, lúc nào cũng căng tròn và mũn mĩn. Cây phù hợp để ở cửa sổ, hiên trước cửa nhà, sảnh khách sạn...','Sen Đá Chuỗi Ngọc Bi','VN',30000,'sen-da-chuoi-ngoc-bi',100,'Còn_hàng',30000),(9,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây sen đá lá tim có lá dày, khi mọc lên tường mọc 2 lá một giống hệt hình trái tim có màu xanh xẫm nhìn cây rất cứng cáp và có sức sống.','Sen Đá Lá Tim','VN',30000,'sen-da-la-tim',72,'Còn_hàng',15000),(10,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá móng rồng viền trắng hay còn gọi là sen ngựa vằn, lá dài và nhọn ở đầu, mọc xung quanh trục, trên lá còn có các viền trắng nhỏ giống như chú ngựa vằn. Cây phù hợp để bàn làm viêc, bàn học, trang trí bàn cà phê...','Sen Đá Móng Rồng Viền Trắng','VN',35000,'sen-da-mong-rong-vien-trang',100,'Còn_hàng',35000),(11,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá mỏ vịt là loại cây có hình dáng lạ phù hợp với những bạn cá tính và yêu thích những thứ độc đáo. Cây phù hợp để bàn làm việc và bàn học...','Sen Đá Mỏ Vịt','VN',20000,'sen-da-mo-vit',96,'Còn_hàng',15000),(12,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá sao xếp là một trong những loại sen đá có hình ngôi sao bốn cánh xếp chồng nên nhau. Cây mang ý nghĩa cho sự may mắn. Phù hợp và thích hợp để bàn, góc làm việc, tiểu cảnh...','Sen Đá Sao Xếp','VN',45000,'sen-da-sao-xep',100,'Còn_hàng',45000),(13,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá sedum dạ quang phù để bàn, trang trí quá cà phê, góc học tập, trồng với tiểu cảnh, terrarium...Cây mang ý nghĩa cho một tình yêu, tình bạn vĩnh cửu không bao giờ phai nhòa dù có khó khăn như màn đêm bao phủ không còn thấy đường thì vẫn có người dẫn lỗi để có thể dễ dàng vượt qua.','Sedum Dạ Quang','VN',50000,'sedum-da-quang',100,'Còn_hàng',50000),(14,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây cảnh sen đá thạch ngọc được lựa chọn để trang trí những nơi sang trọng và gắn liều nhiều với tiền bạc. Cây có lá căng mọng, lá được phủ một lớn phấn nhẹ, đầu lá có thể có viền tím, đỏ, hoặc xanh. Sen đá thạch ngọc mang ý nghĩa đầy đủ, và nhiều tiền bạc mang đến cho chủ sở hữu.','Sen Đá Thạch Ngọc','VN',30000,'sen-da-thach-ngoc',100,'Còn_hàng',30000),(15,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sen đá tứ phương mang ý nghĩa dù bạn có ở bốn phương trời, hay ở đi đâu cũng sẽ được may mắn, cây phù hợp để bàn làm việc, quán cà phê, bàn học...Hoặc để làm tiểu cảnh','Sen Đá Tứ Phương','VN',20000,'sen-da-tu-phuong',97,'Còn_hàng',15000),(16,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây sen đá xanh mang ý nghĩa cho một tình yêu, tình bạn vĩnh cửu theo thời gian. Cây nhỏ thường từ 10 - 20 cm, phù hợp dùng để trang trí bàn làm việc, góc học tập hoặc góc nhỏ riêng của bạn.','Sen Đá Xanh','VN',20000,'sen-da-xanh',100,'Còn_hàng',20000),(17,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Xương rồng kim hổ thủy sinh có ý nghĩa phong thủy giúp trừ tà, phù hợp làm quà tặng, trang trí bàn làm việc, bàn học, để cạnh máy tính giúp hút bức xạ.','Xương Rồng Kim Hổ Thủy Sinh','VN',110000,'xuong-rong-kim-ho-thuy-sinh',100,'Còn_hàng',110000),(18,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Xương rồng tai thỏ có tác dụng hút bức xạ máy tính, nên cây phù hợp để bàn máy tính, bàn làm việc...Ngoài ra cây nhỏ nhắn và đáng yêu dùng để làm món quà nhỏ tặng bạn cũng rất hợp lý','Xương Rồng Tai Thỏ','VN',29000,'xuong-rong-tai-tho',97,'Còn_hàng',29000),(19,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Xương rồng Thanh Sơn được nhiều bạn trẻ ưa thích, mua về trang trí bàn học, bàn làm việc, tặng nhau trong các dịp lễ như mong muốn dù có khó khăn thế nào cũng vẫn có thể vượt qua.','Xương rồng Thanh Sơn','VN',45000,'xuong-rong-thanh-son',100,'Còn_hàng',35000),(20,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Xương rồng trứng chim mang biểu tượng cho sự kiên cường, cây giúp hút bức xạ máy tính. Phù hợp để bàn làm việc, bàn học, trang trí...','Xương Rồng Trứng Chim','VN',35000,'xuong-rong-trung-chim',99,'Còn_hàng',20000),(21,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cành đào nhỏ phù hợp cắm lọ để trưng ban thờ, để bàn uống nước, kệ tivi, quầy lễ tân. ','Cành đào nhỏ','VN',99000,'canh-dao-nho',100,'Còn_hàng',99000),(22,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Với chiều cao khoảng 70cm, lá có hình trái tim xanh viền lá và mặt lá phía sau có màu vàng kem. Cây thích hợp để trang trí nhà, decor quán cà phê, các góc nhỏ để chụp hình thư giãn.','Cây Bàng Cẩm Thạch Lá Tim','VN',239000,'cay-bang-cam-thach-la-tim',100,'Còn_hàng',200000),(23,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Bình An loại cây treo có ý nghĩa phong thủy mang đến sự bình an và may mắn, thường được treo trước cửa nhà để các thành viên trong nhà luôn được may mắn và bình an.','Cây Bình An','VN',149000,'cay-binh-an',100,'Còn_hàng',149000),(24,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây phù hợp để trước hiên, cạnh cửa sổ, ban công, nơi có nhiều ánh sáng.','Cây Cau Lụa Vàng','VN',480000,'cay-cau-lua-vang',100,'Còn_hàng',480000),(25,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây hạnh có chiều cao cả chậu là 1m, chiều cao chậu 43, độ rộng 28. Phù hợp để trang trí nhà với không gian không quá rộng, văn phòng có diện tích vừa phải. Cây hợp tặng khai trương, tân gia, cây để trong nhà, văn phòng','Cây Hạnh Phúc cao 1m','VN',390000,'cay-hanh-phuc-cao-1m',100,'Còn_hàng',390000),(26,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Để thêm phần ấn tượng cho quà tặng thì không thể bỏ qua Kim Ngân chậu con voi mix với cẩm nhung và phụ kiện. Cả cây vào chậu cao khoảng 35cm độ dài của chậu là 20cm','Cây Kim Ngân Chậu Con Voi','VN',269000,'cay-kim-ngan-chau-con-voi',100,'Còn_hàng',230000),(27,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây có chiều cao cả chậu là 80cm, kê thêm đôn gỗ khoảng 1m cực kỳ phù hợp với các không gian không quá lớn nhưng cây sự sang trọng','Cây Kim Ngân Đơn Thân To','VN',349000,'cay-kim-ngan-don-than-to',100,'Còn_hàng',349000),(28,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Mini Monstera khá nổi bật với những chiếc lá xanh đậm và khuyết. Cây không cần chậu quá to chính vì thế cây rất phù hợp để trang trí những góc nhỏ trong nhà, quán cà phê, quà tặng...','Cây Mini Monstera','VN',249000,'cay-mini-monstera',100,'Còn_hàng',200000),(29,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Với lá hình trái tim có nhiều vết xẻ lạ mắt, sống và phát triển tốt ở văn phòng và trong nhà. Chính vì thế cây đang rất được ưa thích làm cây nội thất, cây quà tặng','Cây Monstera','VN',450000,'cay-monstera',100,'Còn_hàng',450000),(30,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây có độ cao khoảng 70cm phù hợp để bàn, kệ tivi, để quần thu ngân, shop nhỏ...','Cây Quất Cảnh nhỏ','VN',149000,'cay-quat-canh-nho',100,'Còn_hàng',149000),(31,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Thủy Tùng trong tự nhiên là loại cây mọc và sống trung gian giữa đất và nước nên bản thân nó mang ý nghĩa của sự hài hòa rất tốt trong ngũ hành phong thủy. Cây phù hợp làm cây để bàn, quầy lễ tân, trang trí nhà, quán cà phê...','Cây Thủy Tùng','VN',125000,'cay-thuy-tung',99,'Còn_hàng',110000),(32,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Trúc Nhật với thân hình mềm mại. Cây mang ý nghĩa phong thủy gặp dữ hóa lành, thăng tiến trong công việc và sự nghiệp nên được tặng trong các dịp như khai trương, sinh nhật hay thăng quan tiến chức.','Cây Trúc Nhật','VN',349000,'cay-truc-nhat',98,'Còn_hàng',349000),(33,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh của bàn cờ tiên bao gồm các loại sen đá và mỗi loại lại có một ý nghĩa khác nhau, nó đen đến cho gia chủ tiền bạc và sự thoải mái. Tiểu cảnh phù hợp để làm quà biếu sếp, để  bàn làm việc của lãnh đão, phòng khách, sản khách sạn....','Tiểu cảnh bàn cờ tiên','VN',249000,'tieu-canh-ban-co-tien',96,'Còn_hàng',210000),(34,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh bộ 3 phù hợp để bàn làm việc, quà tặng, trang trí quầy bán hàng, lễ tân...','Tiểu Cảnh Bộ 3','VN',129000,'tieu-canh-bo-3',98,'Còn_hàng',129000),(35,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Sản phẩm phù hợp để bàn ở phòng khách, bàn trang điểm, trang trí khách sạn, quán cà phê, bàn làm việc...Ý nghĩa luôn được vui vẻ và tươi cười','Tiểu Cảnh Một Vợ Hai Con','VN',129000,'tieu-canh-mot-vo-hai-con',97,'Còn_hàng',129000),(36,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh Sen Đá Ngôi Nhà Nhỏ Trên Thảo Nguyên sẽ giúp bạn thư thái và thoải mái, trong những lúc làm việc căng thẳng. Chậu bao gồm đô lá trắng, sen tứ phương, chuỗi ngọc đứng, bông hồng trắng, đều là những loại sen đá có ý nghĩa phong thủy rất tốt. Cây phù hợp để bàn tiếp khách, sảng khách sạn, bàn lễ tân, bàn sếp...','Tiểu Cảnh Ngôi Nhà Nhỏ Trên TN','VN',249000,'tieu-canh-ngoi-nha-nho-tren-thao-nguyen',97,'Còn_hàng',249000),(37,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh bát nhỏ phù hợp để bàn uống nước, bàn làm việc của sếp, trang trí quán cà phê, bàn lễ tân...','Tiểu Cảnh Sen Đá Bát Nhỏ','VN',179000,'tieu-canh-sen-da-bat-nho',97,'Còn_hàng',179000),(38,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh sen đá điền viên mang ý nghĩa một cuộc sống yên bình, thư thái và nhàn nhã. Tiểu cảnh phù hợp để trong bàn làm việc của sếp, quà biếu, phòng khách, sản khách sạn, quán cà phê...','Tiểu Cảnh Sen Đá Điền Viên','VN',399000,'tieu-canh-sen-da-dien-vien',96,'Còn_hàng',399000),(39,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Tiểu cảnh sen đá đình làng mang đến cho bạn sự thoái mái và thư thái. Tiểu cảnh có ý nghĩa một cuộc sống an nhàn và thoải mái. Phù hợp làm quà tặng sếp, sinh nhật, để bàn lễ tân, khách sạn, trang trí quán cà phê, nhà hàng...','Tiểu Cảnh Sen Đá Đình Làng','VN',449000,'tieu-canh-sen-da-dinh-lang',96,'Còn_hàng',449000),(40,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây cúc mâm xôi với tán rộng 70cm chiều cao 60. Rất hợp chọn làm cây trưng tết đặt ở trước cửa nhà để đón khách. Với ý nghĩa trường thọ và may mắn.','Cây Cúc Mâm Xôi','VN',279000,'cay-cuc-mam-xoi',89,'Còn_hàng',250000),(41,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Cây Thường Xuân mang ý nghĩa phong thủy xua đuổi tà ma, xóa tan âm khí, vượng dương khí mang đến bình an và may mắn cho gia chủ. Cây phù hợp để trang trí quán cà phê, nhà hàng, khách sạn, phòng họp...','Cây Thường Xuân','VN',149000,'cay-thuong-xuan',82,'Còn_hàng',149000),(42,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Terrarium gồm 10 cây sen đá các loại được trồng vào trong bình thủy tinh với ý nghĩa công việc, tình cảm, tiền bạc đều được như mong muốn, toàn vẹn và như ý. Terrarium phù hợp để bàn làm việc, trang trí quán cà phê, khách sạn...','Terrarium 10 Cây Sen Đá','VN',549000,'terrarium-10-cay-sen-da',96,'Còn_hàng',549000),(43,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Terrarium Bách Niên Giai Lão mang ý nghĩa cùng nhau sống đến trăm tuổi, trăm năm hạnh phúc. Bình rất phù hợp để trang trí bàn làm việc, quán cà phê, bàn học, sảng khách sạn...Và là món quà ấn tượng dành cho cặp đôi','Terrarium Bách Niên Giai Lão','VN',159000,'terrarium-bach-nien-giai-lao',87,'Còn_hàng',139000),(44,'admin','2022-05-05 20:58:00.971000',_binary '\0','Administrator','2022-05-08 00:16:06.380000','Terrarium ngôi nhà nhỏ trên thảo nguyên khắc họa lại hình ảnh thảo nguyên thu nhỏ, mang đến cho bạn cảm giác bình yên. Bình phù hợp để làm quà tặng, trang trí quán cà phê, phòng khách, bàn làm việc, lễ tân...','Terrarium Ngôi Nhà Nhỏ Trên TN','VN',279000,'terrarium-ngoi-nha-nho-tren-thao-nguyen',88,'Còn_hàng',279000),(45,'admin','2022-05-05 20:58:00.971000',_binary '\0',NULL,NULL,'Combo bao gồm lướt sắt sơn tĩnh điện, 3 chậu cây cảnh treo, miễn phí thi công, phù hợp để làm tường ban công, khoảng tường trống trong văn phòng, nhà','Tường Cây Xanh','VN',779000,'tuong-cay-xanh',91,'Còn_hàng',779000),(59,'admin','2022-05-05 20:58:00.971000',_binary '','fck ','2022-05-05 20:59:44.876000','Đẹp, nhỏ, giá cả phải chăng, đáng túi tiền.','Cây Câyfgfg','Thái Lan',150000,'cay-cayfgfg',100,'Còn_hàng',120000),(60,'admin','2022-05-11 23:22:16.367000',_binary '','Administrator','2022-05-12 01:09:21.208000','Hàng đột biến giá vài tỉ giờ sale còn 500k.','Cây Chuối Đột Biến','Thái Lan',150000,'cay-chuoi-dot-bien',100,'Còn_hàng',150000);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  KEY `FK2k3smhbruedlcrvu6clued06x` (`product_id`),
  CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,NULL,NULL,_binary '\0','Administrator','2022-05-12 02:03:08.049000',1,1),(2,NULL,NULL,_binary '\0',NULL,NULL,1,2),(3,NULL,NULL,_binary '\0',NULL,NULL,1,3),(4,NULL,NULL,_binary '\0',NULL,NULL,1,4),(5,NULL,NULL,_binary '\0',NULL,NULL,1,5),(6,NULL,NULL,_binary '\0',NULL,NULL,1,6),(7,NULL,NULL,_binary '\0',NULL,NULL,1,7),(8,NULL,NULL,_binary '\0',NULL,NULL,1,8),(9,NULL,NULL,_binary '\0',NULL,NULL,1,9),(10,NULL,NULL,_binary '\0',NULL,NULL,1,10),(11,NULL,NULL,_binary '\0',NULL,NULL,1,11),(12,NULL,NULL,_binary '\0',NULL,NULL,1,12),(13,NULL,NULL,_binary '\0',NULL,NULL,1,13),(14,NULL,NULL,_binary '\0',NULL,NULL,1,14),(15,NULL,NULL,_binary '\0',NULL,NULL,1,15),(16,NULL,NULL,_binary '\0',NULL,NULL,1,16),(17,NULL,NULL,_binary '\0',NULL,NULL,1,17),(18,NULL,NULL,_binary '\0',NULL,NULL,1,18),(19,NULL,NULL,_binary '\0',NULL,NULL,1,19),(20,NULL,NULL,_binary '\0',NULL,NULL,1,20),(21,NULL,NULL,_binary '\0',NULL,NULL,2,21),(22,NULL,NULL,_binary '\0',NULL,NULL,2,22),(23,NULL,NULL,_binary '\0',NULL,NULL,2,23),(24,NULL,NULL,_binary '\0',NULL,NULL,2,24),(25,NULL,NULL,_binary '\0',NULL,NULL,2,25),(26,NULL,NULL,_binary '\0',NULL,NULL,2,26),(27,NULL,NULL,_binary '\0',NULL,NULL,2,27),(28,NULL,NULL,_binary '\0',NULL,NULL,2,28),(29,NULL,NULL,_binary '\0',NULL,NULL,2,29),(30,NULL,NULL,_binary '\0',NULL,NULL,2,30),(31,NULL,NULL,_binary '\0',NULL,NULL,2,31),(32,NULL,NULL,_binary '\0',NULL,NULL,2,32),(33,NULL,NULL,_binary '\0',NULL,NULL,3,33),(34,NULL,NULL,_binary '\0',NULL,NULL,3,34),(35,NULL,NULL,_binary '\0',NULL,NULL,3,35),(36,NULL,NULL,_binary '\0',NULL,NULL,3,36),(37,NULL,NULL,_binary '\0',NULL,NULL,3,37),(38,NULL,NULL,_binary '\0',NULL,NULL,3,38),(39,NULL,NULL,_binary '\0',NULL,NULL,3,39),(40,NULL,NULL,_binary '\0',NULL,NULL,4,40),(41,NULL,NULL,_binary '\0',NULL,NULL,4,41),(42,NULL,NULL,_binary '\0',NULL,NULL,4,42),(43,NULL,NULL,_binary '\0',NULL,NULL,4,43),(44,NULL,NULL,_binary '\0','Administrator','2022-05-08 00:16:06.375000',3,44),(45,NULL,NULL,_binary '\0',NULL,NULL,4,45),(52,NULL,NULL,_binary '\0',NULL,NULL,1,21);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_comment`
--

DROP TABLE IF EXISTS `product_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `content` text,
  `parent_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf0dvop3xf5orf5j8icdwnq5ak` (`product_id`),
  KEY `FKt2atr31lv07ajadvdrmv3ojx2` (`user_id`),
  CONSTRAINT `FKf0dvop3xf5orf5j8icdwnq5ak` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKt2atr31lv07ajadvdrmv3ojx2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comment`
--

LOCK TABLES `product_comment` WRITE;
/*!40000 ALTER TABLE `product_comment` DISABLE KEYS */;
INSERT INTO `product_comment` VALUES (1,'datdeptrai','2022-05-08 15:14:15.045000',_binary '\0',NULL,NULL,'Sản phẩm chất lượng',NULL,3,1),(2,'datdeptrai','2022-05-08 15:14:32.553000',_binary '\0',NULL,NULL,'Xin chào, commetn đầu',NULL,18,1),(3,'datnv','2022-05-12 07:24:15.373000',_binary '\0',NULL,NULL,'Xin chào, commetn đầu',NULL,15,7),(4,'datnv','2022-05-12 12:28:42.253000',_binary '\0',NULL,NULL,'Sản phẩm chất lượng, vừa với túi tiền.',NULL,1,7),(5,'datnv','2022-05-12 12:29:21.267000',_binary '\0',NULL,NULL,'Sản phẩm được vẫn chuyển rất cẩn thận.',NULL,1,7),(6,'datnv','2022-05-12 12:29:48.853000',_binary '\0',NULL,NULL,'Sản phẩm chất lượng, vừa với túi tiền.',NULL,40,7),(7,'datnv','2022-05-12 12:30:57.191000',_binary '\0',NULL,NULL,'Sản phẩm được vẫn chuyển rất cẩn thận, giá cả phải chăng.',NULL,39,7),(8,'datdeptrai','2022-05-12 12:32:51.869000',_binary '\0',NULL,NULL,'Sản phẩm đẹp, phù hợp với túi tiền :V',NULL,39,1),(9,'namnv','2022-05-12 12:35:55.766000',_binary '\0',NULL,NULL,'Sản phẩm được vẫn chuyển rất cẩn thận, giá cả phải chăng.',NULL,43,3),(10,'namnv','2022-05-12 12:36:07.870000',_binary '\0',NULL,NULL,'Sản phẩm đẹp, phù hợp với túi tiền :V',NULL,11,3);
/*!40000 ALTER TABLE `product_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `path` text,
  `product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oo0cvcdtb6qmwsga468uuukk` (`product_id`),
  CONSTRAINT `FK6oo0cvcdtb6qmwsga468uuukk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,'Administrator','2022-05-12 02:03:08.031000',_binary '\0',NULL,NULL,'cap-doi-hoan-hao-1.png',1),(2,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-de-ban-1.png',2),(3,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-de-ban-2.png',2),(4,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-de-ban-3.png',2),(5,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-de-ban-4.png',2),(6,NULL,NULL,_binary '\0',NULL,NULL,'cay-ngoc-ngan-1.png',3),(7,NULL,NULL,_binary '\0',NULL,NULL,'cay-ngoc-ngan-2.png',3),(8,NULL,NULL,_binary '\0',NULL,NULL,'cay-ngoc-ngan-3.png',3),(9,NULL,NULL,_binary '\0',NULL,NULL,'cay-ngoc-ngan-4.png',3),(10,NULL,NULL,_binary '\0',NULL,NULL,'cay-phat-tai-1.png',4),(11,NULL,NULL,_binary '\0',NULL,NULL,'cay-phat-tai-2.png',4),(12,NULL,NULL,_binary '\0',NULL,NULL,'cay-phat-tai-3.png',4),(14,'Administrator','2022-05-12 02:03:08.031000',_binary '\0',NULL,NULL,'cap-doi-hoan-hao-2.png',1),(15,'Administrator','2022-05-12 02:03:08.031000',_binary '\0',NULL,NULL,'cap-doi-hoan-hao-1.png',1),(16,'Administrator','2022-05-12 02:03:08.031000',_binary '\0',NULL,NULL,'cap-doi-hoan-hao-2.png',1),(17,NULL,NULL,_binary '\0',NULL,NULL,'cay-phat-tai-4.png',4),(18,NULL,NULL,_binary '\0',NULL,NULL,'cay-phu-quy-1.png',5),(19,NULL,NULL,_binary '\0',NULL,NULL,'cay-phu-quy-2.png',5),(20,NULL,NULL,_binary '\0',NULL,NULL,'cay-phu-quy-3.png',5),(21,NULL,NULL,_binary '\0',NULL,NULL,'cay-phu-quy-4.png',5),(22,NULL,NULL,_binary '\0',NULL,NULL,'cay-van-loc-1.png',6),(23,NULL,NULL,_binary '\0',NULL,NULL,'cay-van-loc-2.png',6),(24,NULL,NULL,_binary '\0',NULL,NULL,'cay-van-loc-3.png',6),(25,NULL,NULL,_binary '\0',NULL,NULL,'cay-van-loc-4.png',6),(26,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-canh-buom-1.png',7),(27,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-canh-buom-2.png',7),(28,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-canh-buom-3.png',7),(29,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-canh-buom-4.png',7),(30,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-chuoi-ngoc-bi-1.png',8),(31,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-chuoi-ngoc-bi-2.png',8),(32,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-chuoi-ngoc-bi-3.png',8),(33,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-chuoi-ngoc-bi-4.png',8),(34,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-la-tim-1.png',9),(35,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-la-tim-2.png',9),(36,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-la-tim-3.png',9),(37,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-la-tim-4.png',9),(38,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-1.png',10),(39,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-2.png',10),(40,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-3.png',10),(41,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-1.png',10),(42,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mo-vit-1.png',11),(43,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mo-vit-2.png',11),(44,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mo-vit-3.png',11),(45,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-mo-vit-4.png',11),(46,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sao-xep-1.png',12),(47,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sao-xep-2.png',12),(48,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sao-xep-3.png',12),(49,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sao-xep-1.png',12),(50,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sedum-da-quang-1.png',13),(51,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sedum-da-quang-2.png',13),(52,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sedum-da-quang-3.png',13),(53,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-sedum-da-quang-4.png',13),(54,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-thach-ngoc-1.png',14),(55,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-thach-ngoc-2.png',14),(56,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-thach-ngoc-3.png',14),(57,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-thach-ngoc-4.png',14),(58,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-tu-phuong-1.png',15),(59,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-tu-phuong-2.png',15),(60,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-tu-phuong-3.png',15),(61,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-tu-phuong-4.png',15),(62,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-xanh-1.png',16),(63,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-xanh-2.png',16),(64,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-xanh-1.png',16),(65,NULL,NULL,_binary '\0',NULL,NULL,'sen-da-xanh-2.png',16),(66,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-kim-ho-thuy-sinh-1.png',17),(67,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-kim-ho-thuy-sinh-2.png',17),(68,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-kim-ho-thuy-sinh-1.png',17),(69,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-kim-ho-thuy-sinh-2.png',17),(70,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-tai-tho-1.png',18),(71,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-tai-tho-2.png',18),(72,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-tai-tho-3.png',18),(73,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-tai-tho-4.png',18),(74,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-thanh-son-1.png',19),(75,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-thanh-son-2.png',19),(76,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-thanh-son-3.png',19),(77,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-thanh-son-4.png',19),(78,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-trung-chim-1.png',20),(79,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-trung-chim-2.png',20),(80,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-trung-chim-3.png',20),(81,NULL,NULL,_binary '\0',NULL,NULL,'xuong-rong-trung-chim-4.png',20),(82,NULL,NULL,_binary '\0',NULL,NULL,'canh-dao-nho-1.png',21),(83,NULL,NULL,_binary '\0',NULL,NULL,'canh-dao-nho-2.png',21),(84,NULL,NULL,_binary '\0',NULL,NULL,'canh-dao-nho-3.png',21),(85,NULL,NULL,_binary '\0',NULL,NULL,'canh-dao-nho-4.png',21),(86,NULL,NULL,_binary '\0',NULL,NULL,'cay-bang-cam-thach-la-tim-1.png',22),(87,NULL,NULL,_binary '\0',NULL,NULL,'cay-bang-cam-thach-la-tim-2.png',22),(88,NULL,NULL,_binary '\0',NULL,NULL,'cay-bang-cam-thach-la-tim-3.png',22),(89,NULL,NULL,_binary '\0',NULL,NULL,'cay-bang-cam-thach-la-tim-4.png',22),(90,NULL,NULL,_binary '\0',NULL,NULL,'cay-binh-an-1.png',23),(91,NULL,NULL,_binary '\0',NULL,NULL,'cay-binh-an-2.png',23),(92,NULL,NULL,_binary '\0',NULL,NULL,'cay-binh-an-1.png',23),(93,NULL,NULL,_binary '\0',NULL,NULL,'cay-binh-an-3.png',23),(94,NULL,NULL,_binary '\0',NULL,NULL,'cay-cau-lua-vang-1.png',24),(95,NULL,NULL,_binary '\0',NULL,NULL,'cay-cau-lua-vang-2.png',24),(96,NULL,NULL,_binary '\0',NULL,NULL,'cay-cau-lua-vang-1.png',24),(97,NULL,NULL,_binary '\0',NULL,NULL,'cay-cau-lua-vang-3.png',24),(98,NULL,NULL,_binary '\0',NULL,NULL,'cay-hanh-phuc-cao-1m-1.png',25),(99,NULL,NULL,_binary '\0',NULL,NULL,'cay-hanh-phuc-cao-1m-2.png',25),(100,NULL,NULL,_binary '\0',NULL,NULL,'cay-hanh-phuc-cao-1m-3.png',25),(101,NULL,NULL,_binary '\0',NULL,NULL,'cay-hanh-phuc-cao-1m-4.png',25),(102,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-chau-con-voi-1.png',26),(103,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-chau-con-voi-2.png',26),(104,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-chau-con-voi-3.png',26),(105,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-chau-con-voi-4.png',26),(106,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-than-to-1.png',27),(107,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-than-to-2.png',27),(108,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-than-to-3.png',27),(109,NULL,NULL,_binary '\0',NULL,NULL,'cay-kim-ngan-than-to-4.png',27),(110,NULL,NULL,_binary '\0',NULL,NULL,'cay-mini-monstera-1.png',28),(111,NULL,NULL,_binary '\0',NULL,NULL,'cay-mini-monstera-2.png',28),(112,NULL,NULL,_binary '\0',NULL,NULL,'cay-mini-monstera-1.png',28),(113,NULL,NULL,_binary '\0',NULL,NULL,'cay-mini-monstera-3.png',28),(114,NULL,NULL,_binary '\0',NULL,NULL,'cay-monstera-1.png',29),(115,NULL,NULL,_binary '\0',NULL,NULL,'cay-monstera-2.png',29),(116,NULL,NULL,_binary '\0',NULL,NULL,'cay-monstera-3.png',29),(117,NULL,NULL,_binary '\0',NULL,NULL,'cay-monstera-4.png',29),(118,NULL,NULL,_binary '\0',NULL,NULL,'cay-quat-canh-nho-1.png',30),(119,NULL,NULL,_binary '\0',NULL,NULL,'cay-quat-canh-nho-2.png',30),(120,NULL,NULL,_binary '\0',NULL,NULL,'cay-quat-canh-nho-3.png',30),(121,NULL,NULL,_binary '\0',NULL,NULL,'cay-quat-canh-nho-4.png',30),(122,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuy-tung-1.png',31),(123,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuy-tung-2.png',31),(124,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuy-tung-3.png',31),(125,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuy-tung-4.png',31),(126,NULL,NULL,_binary '\0',NULL,NULL,'cay-truc-nhat-1.png',32),(127,NULL,NULL,_binary '\0',NULL,NULL,'cay-truc-nhat-2.png',32),(128,NULL,NULL,_binary '\0',NULL,NULL,'cay-truc-nhat-3.png',32),(129,NULL,NULL,_binary '\0',NULL,NULL,'cay-truc-nhat-4.png',32),(130,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ban-co-tien-1.png',33),(131,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ban-co-tien-2.png',33),(132,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ban-co-tien-3.png',33),(133,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ban-co-tien-4.png',33),(134,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-bo-3-1.png',34),(135,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-bo-3-2.png',34),(136,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-bo-3-1.png',34),(137,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-bo-3-3.png',34),(138,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-1.png',35),(139,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-2.png',35),(140,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-2.png',35),(141,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-1.png',35),(142,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ngoi-nha-nho-tren-thao-nguyen-1.png',36),(143,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ngoi-nha-nho-tren-thao-nguyen-2.png',36),(144,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ngoi-nha-nho-tren-thao-nguyen-1.png',36),(145,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-ngoi-nha-nho-tren-thao-nguyen-3.png',36),(146,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-bat-nho-1.png',37),(147,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-bat-nho-2.png',37),(148,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-bat-nho-2.png',37),(149,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-bat-nho-1.png',37),(150,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-1.png',38),(151,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-2.png',38),(152,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-3.png',38),(153,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-4.png',38),(154,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dinh-lang-1.png',39),(155,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dinh-lang-2.png',39),(156,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dinh-lang-3.png',39),(157,NULL,NULL,_binary '\0',NULL,NULL,'tieu-canh-sen-da-dinh-lang-4.png',39),(158,NULL,NULL,_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-1.png',40),(159,NULL,NULL,_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-2.png',40),(160,NULL,NULL,_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-1.png',40),(161,NULL,NULL,_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-3.png',40),(162,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuong-xuan-3.png',41),(163,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuong-xuan-4.png',41),(164,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuong-xuan-3.png',41),(165,NULL,NULL,_binary '\0',NULL,NULL,'cay-thuong-xuan-1.png',41),(166,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-1.png',42),(167,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-2.png',42),(168,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-1.png',42),(169,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-3.png',42),(170,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-1.png',43),(171,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-2.png',43),(172,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-3.png',43),(173,NULL,NULL,_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-4.png',43),(174,'Administrator','2022-05-08 00:16:06.358000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-3.png',44),(175,'Administrator','2022-05-08 00:16:06.358000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-1.png',44),(176,'Administrator','2022-05-08 00:16:06.358000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-2.png',44),(177,'Administrator','2022-05-08 00:16:06.358000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-3.png',44),(178,NULL,NULL,_binary '\0',NULL,NULL,'tuong-cay-xanh-1.png',45),(179,NULL,NULL,_binary '\0',NULL,NULL,'tuong-cay-xanh-2.png',45),(180,NULL,NULL,_binary '\0',NULL,NULL,'tuong-cay-xanh-3.png',45),(181,NULL,NULL,_binary '\0',NULL,NULL,'tuong-cay-xanh-4.png',45),(234,'admin','2022-05-05 20:59:02.484000',_binary '','admin','2022-05-05 20:59:44.857000','215105437_845327899735993_1837971570071364712_n.jpg',59),(235,'admin','2022-05-05 20:59:02.484000',_binary '','admin','2022-05-05 20:59:44.862000','81565873_172372737448164_8696632905461399552_n.jpg',59),(236,'admin','2022-05-05 20:59:02.484000',_binary '','admin','2022-05-05 20:59:44.867000','83314137_136847144454401_8153532323787177984_n.jpg',59),(237,'admin','2022-05-05 20:59:02.484000',_binary '','admin','2022-05-05 20:59:44.871000','blank_image.png',59),(238,'Administrator','2022-05-12 01:02:30.667000',_binary '','Administrator','2022-05-12 01:09:21.175000','+1.PNG',60),(239,'Administrator','2022-05-12 01:02:30.668000',_binary '','Administrator','2022-05-12 01:09:21.192000','83770032_653681048702750_2000754087561265152_n.jpg',60),(240,'Administrator','2022-05-12 01:02:30.668000',_binary '','Administrator','2022-05-12 01:09:21.197000','66b2a9a71483eeddb792.jpg',60),(241,'Administrator','2022-05-12 01:02:30.668000',_binary '','Administrator','2022-05-12 01:09:21.204000','blank_image.png',60);
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,_binary '\0',NULL,NULL,'ROLE_ADMIN'),(2,NULL,NULL,_binary '\0',NULL,NULL,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `username` varchar(45) NOT NULL,
  `cellphone` varchar(255) NOT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ej2cttx8iuwd51taump23b45` (`cellphone`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_f5fgr310aucvqex8djp780h1x` (`reset_password_token`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Administrator','2022-05-03 18:55:31.959000',_binary '\0','datdeptrai','2022-05-05 19:14:25.254000','66b2a9a71483eeddb792.jpg','user@gmail.com','Nguyễn Văn','Nam','Đạt','$2a$10$emuQ0pzdJeNRlL/rQ8xY/OeIlzCEScZtQsFd7.RI2v7uKfBzhuS/2','datdeptrai','0147852369',NULL),(2,'Administrator','2022-05-03 18:55:31.959000',_binary '\0','fck uifcsdf','2022-05-05 17:54:38.475000','+1.PNG','admin@gmail.com','Nguyen Van','Nam','Nammmm','$2a$10$1qPx.Mcj5e79qjU.MDpVleC7XG2JUB2nrBOHwe9IFSWQBC4gHefhi','Administrator','0369852177',NULL),(3,'Administrator','2022-05-03 18:55:31.959000',_binary '\0','fck ','2022-05-05 20:57:11.895000','avatar-default.png','vandat@gmail.com','Nguyen','Nam','Ha','$2a$10$zKpcsIu9I1VJxBOOBSmim.kE9MfJiWeM7taNZThD9tYxYhoBS5qX2','namnv','0123654789',NULL),(6,'Administrator','2022-05-03 18:55:31.959000',_binary '','John','2022-05-03 22:20:15.306000','avatar-default.png','a@gmail.com','Nguyen','Nam','Manh','$2a$10$4dtS8IpdYIDtViULwjPQ5ec/vGbDRMugBaNVnc8h6L53jbgAVKNXG','John','0369852159',NULL),(7,'fck','2022-05-03 18:55:31.959000',_binary '\0','datnv','2022-05-12 12:27:39.049000','83770032_653681048702750_2000754087561265152_n.jpg','datnv04112000@gmail.com','Nguyễn Văn','Nam','Đạt','$2a$10$Ya4H4Z9aX6YAYglyAunySOyWCFHo3vv9Za7.eVxUWE6bgTTj0yqE6','datnv','0868776094',NULL),(9,'Administrator','2022-05-06 00:14:35.471000',_binary '','Administrator','2022-05-11 23:12:14.855000','ae.PNG','demouser@gmail.com','Nguyen','Nữ','Ha ','$2a$10$YI35RUYe1TIF/TfkC99hJuW.sMBISGjZyZam9G.hl6pIX0K6/Z/6K','Demo user','0256398476',NULL),(10,'Administrator','2022-05-11 23:21:33.463000',_binary '\0',NULL,NULL,'66b2a9a71483eeddb792.jpg','hiimyi2911@gmail.com','John','Nam','Son','$2a$10$nlb9RrHnqZgXnYSdmnD52ur/3nzaBbjYaKDIQ7LjSgZCm2Vt/.46m','yi','0256398147',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,2),(2,1),(3,2),(6,2),(7,2),(9,2),(10,2);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voucher`
--

DROP TABLE IF EXISTS `voucher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `voucher` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `applicable_value` int NOT NULL,
  `code_voucher` varchar(255) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `percent_value` int NOT NULL,
  `quantity` int NOT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `times_of_use` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `up_to_value` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,NULL,NULL,_binary '\0','datnv','2022-05-12 07:25:56.260000',100000,'ABC','2022-05-20 00:00:00.000000','320px-Heart_corazón.svg.png',10,0,'2022-04-25 00:00:00.000000',1,'Giảm 10% tối đa 20k cho đơn hàng từ 100k trở lên',20000),(2,NULL,NULL,_binary '\0','datdeptrai','2022-05-06 15:47:39.737000',150000,'ABCD','2022-05-10 00:00:00.000000','blank_image.png',15,7,'2022-04-25 00:00:00.000000',1,'Giảm 15% tối đa 25k cho đơn hàng từ 150k trở lên',25000),(3,NULL,NULL,_binary '\0',NULL,NULL,200000,'ABCDE','2022-06-01 00:00:00.000000','blank_image.png',20,10,'2022-04-25 00:00:00.000000',1,'Giảm 20% tối đa 30k cho đơn hàng từ 200k trở lên',30000),(4,NULL,NULL,_binary '','Administrator','2022-05-03 19:46:11.521000',250000,'ABCDEF','2022-06-10 00:00:00.000000','134655036_938979950180273_2889716166034878766_n.jpg',25,10,'2022-04-25 00:00:00.000000',1,'Giảm 25% tối đa 35k cho đơn hàng từ 250k trở lên',35000),(5,'Administrator','2022-05-03 23:31:06.976000',_binary '\0','datdeptrai','2022-05-04 00:52:07.424000',500000,'GIAMGIA1111','2022-05-31 23:30:00.000000','hiep co ny.png',20,9,'2022-05-02 23:30:00.000000',1,'Giảm 20% tối đa 50000 cho đơn hàng từ 500000 trở lên',50000),(6,'Administrator','2022-05-04 21:22:01.724000',_binary '','Administrator','2022-05-04 21:22:32.161000',400000,'GIAMGIA5THANG5','2022-05-11 21:21:00.000000','83770032_653681048702750_2000754087561265152_n.jpg',15,20,'2022-05-01 21:21:00.000000',1,'Giảm 15% tối đa 50000 cho đơn hàng từ 400000 trở lên',50000),(7,'Administrator','2022-05-05 22:42:13.237000',_binary '','Administrator','2022-05-05 22:42:56.466000',500000,'GIAMGIA333','2022-05-15 22:42:00.000000','ae.PNG',25,10,'2022-05-04 22:42:00.000000',1,'Giảm 25% tối đa 50000 cho đơn hàng từ 500000 trở lên',50000),(8,'Administrator','2022-05-12 01:18:08.828000',_binary '','Administrator','2022-05-12 01:18:30.199000',300000,'GIAMGIA5THANG5','2022-05-20 01:18:00.000000','66b2a9a71483eeddb792.jpg',30,20,'2022-05-13 01:18:00.000000',1,'Giảm 30% tối đa 50000 cho đơn hàng từ 300000 trở lên',50000);
/*!40000 ALTER TABLE `voucher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-12 23:18:01
