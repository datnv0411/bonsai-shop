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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'datnv','2022-05-17 01:50:44.286000',_binary '\0',NULL,NULL,'0868776094','Thanh Thuy','Nguyễn Văn Đạt',_binary '\0','Bắc Ninh','Khu 11','Phú Thọ','Nhà',1),(2,'datnv','2022-05-17 01:51:07.462000',_binary '\0','datnv','2022-05-17 01:51:31.317000','0256398476','Tam Nông','Nguyễn Hà',_binary '','Bắc Ninh','Khu 13','Bắc Giang','Nhà bạn',1),(3,'datnv','2022-05-17 01:51:48.851000',_binary '','datnv','2022-05-17 01:51:51.435000','0256398523','Nam Từ Liêm','Mark Donut',_binary '\0','Bắc Ninh','ngách 80/21','Phú Thọ','Công ty',1),(4,'somebody','2022-05-18 18:14:57.869000',_binary '\0',NULL,NULL,'0256398523','Tam Nông','Mark Donut',_binary '','Bắc Ninh','ngách 80/21','Phú Thọ','Nhà bạn',4),(5,'hieunguyen','2022-05-18 18:25:29.172000',_binary '\0',NULL,NULL,'0256398147','Nam Từ Liêm','Hiếu Nguyễn',_binary '','Bắc Ninh','Khu 13','Phú Thọ','Nhà bạn',7),(6,'tiennguyen','2022-05-18 18:32:15.959000',_binary '\0',NULL,NULL,'0236514587','Nam Từ Liêm','Tiến Nguyễn ',_binary '','Bắc Ninh','ngách 80/21','Phú Thọ','Nhà ',8),(7,'manhnguyen','2022-05-18 23:32:14.767000',_binary '\0',NULL,NULL,'0256398147','Tam Đảo','Mạnh Nguyễn',_binary '','Bắc Ninh','Khu 13','Phú Thọ','Nhà bạn',13),(8,'hiepngo','2022-05-18 23:38:00.123000',_binary '\0',NULL,NULL,'0165329487','Lục Ngạn','Hiệp Ngô',_binary '','Bắc Ninh','Khu 11','Bắc Giang','Nhà',15),(9,'datnv','2022-05-19 22:55:42.129000',_binary '','datnv','2022-05-19 22:55:46.785000','0123456998','Thanh Thuy','Demotodelete',_binary '\0','Bắc Ninh','ngách 80/21','Bắc Giang','Công ty',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
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
INSERT INTO `category` VALUES (1,'admin','2022-05-16 20:55:46.577000',_binary '\0',NULL,NULL,'Cây Cảnh Phong Thủy',NULL),(2,'admin','2022-05-16 20:56:09.698000',_binary '\0',NULL,NULL,'Cây Cảnh Trong Nhà',NULL),(3,'admin','2022-05-16 20:56:19.861000',_binary '\0',NULL,NULL,'Cây Cảnh Để Bàn',NULL),(4,'admin','2022-05-16 20:56:31.114000',_binary '\0',NULL,NULL,'Cây Cảnh Văn Phòng',NULL),(5,'admin','2022-05-16 20:56:40.226000',_binary '\0',NULL,NULL,'Cây Cảnh Loại To',NULL),(6,'admin','2022-05-16 20:56:50.919000',_binary '\0',NULL,NULL,'Cây Cảnh Sen Đá',NULL),(7,'admin','2022-05-16 20:57:00.889000',_binary '\0',NULL,NULL,'Cây Cảnh Thủy Sinh',NULL),(8,'admin','2022-05-16 20:57:09.111000',_binary '\0',NULL,NULL,'Cây Dây Leo',NULL),(9,'admin','2022-05-16 20:57:20.101000',_binary '\0',NULL,NULL,'Xương Rồng Cảnh',NULL),(10,'admin','2022-05-16 20:57:20.101000',_binary '\0','admin','2022-05-16 20:58:55.854000','Thiết Kế Ban Công Căn Hộ',NULL),(11,NULL,NULL,_binary '','admin','2022-05-19 22:50:36.807000','demo 2',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'datnv','2022-05-17 02:25:57.669000',_binary '\0',NULL,NULL,750,15000,1,8,12),(2,'datnv','2022-04-17 19:52:53.873000',_binary '\0',NULL,NULL,15900,318000,2,11,69),(3,'datnv','2022-05-18 01:29:19.936000',_binary '\0',NULL,NULL,23900,478000,2,12,1),(4,'datnv','2022-05-18 01:29:19.943000',_binary '\0',NULL,NULL,750,15000,1,12,4),(5,'somebody','2022-03-18 18:15:08.445000',_binary '\0',NULL,NULL,45000,900000,2,13,28),(6,'somebody','2022-05-18 18:15:08.483000',_binary '\0',NULL,NULL,10450,209000,1,13,24),(7,'somebody','2022-05-18 18:20:19.596000',_binary '','admin','2022-05-19 22:53:20.915000',6250,125000,1,22,66),(8,'hieunguyen','2022-05-18 18:25:42.183000',_binary '\0',NULL,NULL,15500,310000,1,23,67),(9,'tiennguyen','2022-05-18 18:32:32.673000',_binary '\0',NULL,NULL,15500,310000,1,24,67),(10,'tiennguyen','2022-05-18 18:37:41.967000',_binary '\0',NULL,NULL,17500,350000,1,25,72),(11,'tiennguyen','2022-05-18 18:39:17.549000',_binary '\0',NULL,NULL,7950,159000,1,26,69),(12,'tiennguyen','2022-05-18 23:28:10.702000',_binary '\0',NULL,NULL,11950,239000,1,27,1),(13,'manhnguyen','2022-05-18 23:32:22.202000',_binary '\0',NULL,NULL,17450,349000,1,28,9),(14,'hiepngo','2022-05-18 23:38:15.744000',_binary '\0',NULL,NULL,34900,698000,2,29,71),(15,'hiepngo','2022-05-19 11:44:25.840000',_binary '\0',NULL,NULL,750,15000,1,30,2),(16,'hiepngo','2022-05-19 11:44:25.848000',_binary '\0',NULL,NULL,1500,30000,2,30,5),(17,'datnv','2022-05-19 11:50:46.487000',_binary '\0',NULL,NULL,22500,450000,1,31,28),(18,'datnv','2022-05-19 11:52:52.605000',_binary '\0',NULL,NULL,3000,60000,2,32,46),(19,'datnv','2022-05-19 11:54:26.939000',_binary '\0',NULL,NULL,750,15000,1,33,37),(20,'hieunguyen','2022-05-19 12:01:19.788000',_binary '\0',NULL,NULL,750,15000,1,34,47),(21,'tiennguyen','2022-05-19 12:08:47.624000',_binary '\0',NULL,NULL,750,15000,1,35,37),(22,'datnv','2022-05-19 12:34:38.429000',_binary '\0',NULL,NULL,6250,125000,1,36,33),(23,'somebody','2022-05-19 23:30:10.855000',_binary '\0',NULL,NULL,1000,20000,1,37,39),(24,'somebody','2022-05-19 23:31:57.167000',_binary '\0',NULL,NULL,750,15000,1,38,38),(25,'hiepngo','2022-05-19 23:41:29.660000',_binary '\0',NULL,NULL,15500,310000,1,39,67),(26,'hiepngo','2022-05-19 23:44:33.727000',_binary '\0',NULL,NULL,11950,239000,1,40,1),(27,'hiepngo','2022-05-19 23:45:15.586000',_binary '\0',NULL,NULL,1500,30000,2,41,2);
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
  `order_code` varchar(255) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `address_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `voucher_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41kb30ru4dhyoiuadqhp7232r` (`address_id`),
  KEY `FKjqbh5jbj1olkur6fpmle5r9ev` (`user_id`),
  KEY `FKr3oqsy8q6r4fqu4ycwo9n2nsv` (`voucher_id`),
  CONSTRAINT `FK41kb30ru4dhyoiuadqhp7232r` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
  CONSTRAINT `FKjqbh5jbj1olkur6fpmle5r9ev` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKr3oqsy8q6r4fqu4ycwo9n2nsv` FOREIGN KEY (`voucher_id`) REFERENCES `voucher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordered`
--

LOCK TABLES `ordered` WRITE;
/*!40000 ALTER TABLE `ordered` DISABLE KEYS */;
INSERT INTO `ordered` VALUES (8,'datnv','2022-05-17 02:25:57.629000',_binary '\0','admin','2022-05-19 22:53:05.898000','rvuteP','Đang_giao_hàng',2,1,NULL),(11,'datnv','2022-04-17 19:52:53.873000',_binary '\0','admin','2022-05-18 02:17:54.099000','81rNBi','Đã_giao',1,1,NULL),(12,'datnv','2022-05-18 01:29:19.918000',_binary '\0',NULL,NULL,'gLAulH','Chờ',1,1,NULL),(13,'somebody','2022-03-18 18:15:08.445000',_binary '\0',NULL,NULL,'k1qabD','Đã_giao',4,4,NULL),(22,'somebody','2022-05-18 18:20:19.587000',_binary '\0','admin','2022-05-19 22:53:20.927000','S0rPjS','Đã_hủy',4,4,NULL),(23,'hieunguyen','2022-05-18 18:25:42.146000',_binary '\0',NULL,NULL,'DVfi18','Đã_giao',5,7,NULL),(24,'tiennguyen','2022-05-18 18:32:32.634000',_binary '\0',NULL,NULL,'wwxJfi','Đã_giao',6,8,NULL),(25,'tiennguyen','2022-05-18 18:37:41.959000',_binary '\0',NULL,NULL,'WEga19','Đang_giao_hàng',6,8,NULL),(26,'tiennguyen','2022-05-18 18:39:17.539000',_binary '\0',NULL,NULL,'lJYfPe','Đã_giao',6,8,NULL),(27,'tiennguyen','2022-05-18 23:28:10.671000',_binary '\0',NULL,NULL,'DuSx0c','Chờ',6,8,NULL),(28,'manhnguyen','2022-05-18 23:32:22.169000',_binary '\0',NULL,NULL,'DjyO1G','Đã_giao',7,13,NULL),(29,'hiepngo','2022-05-18 23:38:15.709000',_binary '\0',NULL,NULL,'9Q1W6c','Đã_giao',8,15,NULL),(30,'hiepngo','2022-05-19 11:44:25.816000',_binary '\0',NULL,NULL,'18qokD','Đã_giao',8,15,NULL),(31,'datnv','2022-05-19 11:50:46.461000',_binary '\0',NULL,NULL,'8PJIpR','Đang_giao_hàng',1,1,NULL),(32,'datnv','2022-05-19 11:52:52.576000',_binary '\0',NULL,NULL,'oPR0nS','Đang_giao_hàng',1,1,NULL),(33,'datnv','2022-05-19 11:54:26.930000',_binary '\0',NULL,NULL,'zLjz0r','Chờ',1,1,NULL),(34,'hieunguyen','2022-05-19 12:01:19.754000',_binary '\0',NULL,NULL,'LZRAVq','Chờ',5,7,NULL),(35,'tiennguyen','2022-05-19 12:08:47.585000',_binary '\0',NULL,NULL,'kXAJa4','Chờ',6,8,NULL),(36,'datnv','2022-05-19 12:34:38.401000',_binary '\0','admin','2022-05-19 23:12:04.853000','EyM0D8','Đang_giao_hàng',1,1,NULL),(37,'somebody','2022-05-19 23:30:10.808000',_binary '\0','admin','2022-05-19 23:40:31.121000','eaiaXU','Đã_giao',4,4,NULL),(38,'somebody','2022-05-19 23:31:57.160000',_binary '\0',NULL,NULL,'kladN0','Chờ',4,4,NULL),(39,'hiepngo','2022-05-19 23:41:29.633000',_binary '\0',NULL,NULL,'21PZ3c','Chờ',8,15,NULL),(40,'hiepngo','2022-05-19 23:44:33.703000',_binary '\0',NULL,NULL,'uUjfDW','Chờ',8,15,NULL),(41,'hiepngo','2022-05-19 23:45:15.578000',_binary '\0','admin','2022-05-19 23:50:03.215000','YywaKs','Đã_giao',8,15,NULL);
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
INSERT INTO `payment` VALUES (1,NULL,NULL,_binary '\0',NULL,NULL,'Truc Tiep'),(2,NULL,NULL,_binary '\0',NULL,NULL,'VN Pay');
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_order`
--

LOCK TABLES `payment_order` WRITE;
/*!40000 ALTER TABLE `payment_order` DISABLE KEYS */;
INSERT INTO `payment_order` VALUES (1,'datnv','2022-05-17 02:25:57.630000',_binary '\0','admin','2022-05-19 22:53:05.922000','Đã thanh toán',42250,8,1),(2,'datnv','2022-05-17 19:52:53.874000',_binary '\0','admin','2022-05-18 02:17:54.152000','Đã thanh toán',390700,11,2),(3,'datnv','2022-05-18 01:29:19.919000',_binary '\0',NULL,NULL,'Chưa thanh toán',591950,12,1),(4,'somebody','2022-05-18 18:15:08.448000',_binary '\0',NULL,NULL,'Đã thanh toán',1275350,13,1),(13,'somebody','2022-05-18 18:20:19.587000',_binary '','admin','2022-05-19 22:53:20.903000','Chưa thanh toán',143750,22,1),(14,'hieunguyen','2022-05-18 18:25:42.149000',_binary '\0',NULL,NULL,'Đã thanh toán',381500,23,1),(15,'tiennguyen','2022-05-18 18:32:32.637000',_binary '\0',NULL,NULL,'Đã thanh toán',356500,24,1),(16,'tiennguyen','2022-05-18 18:37:41.959000',_binary '\0',NULL,NULL,'Chưa thanh toán',402500,25,1),(17,'tiennguyen','2022-05-18 18:39:17.540000',_binary '\0',NULL,NULL,'Đã thanh toán',182850,26,2),(18,'tiennguyen','2022-05-18 23:28:10.673000',_binary '\0',NULL,NULL,'Chưa thanh toán',274850,27,1),(19,'manhnguyen','2022-05-18 23:32:22.171000',_binary '\0',NULL,NULL,'Đã thanh toán',401350,28,1),(20,'hiepngo','2022-05-18 23:38:15.712000',_binary '\0',NULL,NULL,'Đã thanh toán',827700,29,1),(21,'hiepngo','2022-05-19 11:44:25.817000',_binary '\0',NULL,NULL,'Đã thanh toán',76750,30,1),(22,'datnv','2022-05-19 11:50:46.463000',_binary '\0',NULL,NULL,'Chưa thanh toán',542500,31,1),(23,'datnv','2022-05-19 11:52:52.579000',_binary '\0',NULL,NULL,'Chưa thanh toán',69000,32,1),(24,'datnv','2022-05-19 11:54:26.930000',_binary '\0',NULL,NULL,'Đã thanh toán',17250,33,2),(25,'hieunguyen','2022-05-19 12:01:19.756000',_binary '\0',NULL,NULL,'Chưa thanh toán',17250,34,1),(26,'tiennguyen','2022-05-19 12:08:47.589000',_binary '\0',NULL,NULL,'Chưa thanh toán',17250,35,1),(27,'datnv','2022-05-19 12:34:38.403000',_binary '\0','admin','2022-05-19 23:12:04.865000','Chưa thanh toán',193750,36,1),(28,'somebody','2022-05-19 23:30:10.808000',_binary '\0','admin','2022-05-19 23:40:31.133000','Đã thanh toán',23000,37,1),(29,'somebody','2022-05-19 23:31:57.160000',_binary '\0',NULL,NULL,'Đã thanh toán',17250,38,2),(30,'hiepngo','2022-05-19 23:41:29.633000',_binary '\0',NULL,NULL,'Chưa thanh toán',356500,39,1),(31,'hiepngo','2022-05-19 23:44:33.705000',_binary '\0',NULL,NULL,'Chưa thanh toán',274850,40,1),(32,'hiepngo','2022-05-19 23:45:15.578000',_binary '\0','admin','2022-05-19 23:50:03.230000','Đã thanh toán',34500,41,2);
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
  `description` varchar(2550) DEFAULT NULL,
  `import_date` varchar(255) DEFAULT NULL,
  `import_price` varchar(255) DEFAULT NULL,
  `import_quantity` varchar(255) DEFAULT NULL,
  `name_product` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `price` bigint NOT NULL,
  `price_sale` bigint NOT NULL,
  `product_search` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `supplier_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FK2kxvbr72tmtscjvyp9yqb12by` (`supplier_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FK2kxvbr72tmtscjvyp9yqb12by` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'admin','2022-05-16 22:20:34.422000',_binary '\0','admin','2022-05-19 22:18:17.943000','Với chiều cao khoảng 70cm, lá có hình trái tim xanh viền lá và mặt lá phía sau có màu vàng kem. Cây thích hợp để trang trí nhà, decor quán cà phê, các góc nhỏ để chụp hình thư giãn.','2022-05-14T22:17','217000','50','Cây Bàng Cẩm Thạch Lá Tim','Việt Nam',239000,239000,'cay-bang-cam-thach-la-tim',50,'Còn_hàng',1,10),(2,'admin','2022-05-16 22:37:48.151000',_binary '\0',NULL,NULL,'Cây mọc thành bụi, dạng đài dễ sống và phát triển. Phù hợp làm cây trang trí, bày quán cafe, ban công...','2022-05-14T22:35','13000','30','Sen Đá Nuda','Việt Nam',15000,15000,'sen-da-nuda',26,'Còn_hàng',1,10),(3,'admin','2022-05-16 22:45:28.257000',_binary '\0',NULL,NULL,'Cây sen đá hoa cúc tím với hình dạng giống bông hoa cúc, cây mang ý nghĩa cho sự trường thọ và một tình bạn, tình yêu vĩnh cửu...','2022-05-13T22:43','13000','20','Sen Đá Cúc Tím','Việt Nam',15000,15000,'sen-da-cuc-tim',20,'Còn_hàng',1,10),(4,'admin','2022-05-16 22:54:45.152000',_binary '\0',NULL,NULL,'Cây Sen Đá Giva có cánh hơi bầu màu xanh, phù hợp làm cây phong thủy cho người mệnh mộc và hỏa. Cây phù hợp để ban công, cửa sổ, ngoài hiên, quán cà phê.','2022-05-13T22:48','14000','20','Sen Đá Giva','Việt Nam',15000,15000,'sen-da-giva',20,'Còn_hàng',1,10),(5,'admin','2022-05-16 23:08:59.808000',_binary '\0',NULL,NULL,'Sen Đá Liên Đài Hồng thuộc dòng sen đài tuy nhiên lại có sức sống rất mạnh, phù hợp cho các bạn mới chơi thích dòng sen đài chưa biết chăm sóc.','2022-05-13T23:07','13000','20','Sen Đá Liên Đài Hồng','Việt Nam',15000,15000,'sen-da-lien-dai-hong',20,'Còn_hàng',1,10),(6,'admin','2022-05-16 23:11:58.173000',_binary '\0',NULL,NULL,'Cây dạng đài đẹp và bắt mắt. cây có thể phát triển đến kích thước vài chục cm. Cây phù hợp để trang trí quán cà phê, cây trồng ngoài trời, ban công.','2022-05-14T23:10','14000','30','Sen Đá Bắp Cải Tím','Việt Nam',15000,15000,'sen-da-bap-cai-tim',30,'Còn_hàng',1,10),(7,'admin','2022-05-16 23:15:43.407000',_binary '\0',NULL,NULL,'Cây mọc thành bụi nhỏ do đó phù hợp để trồng làm cây nền khi mix các loại sen đá trong một chậu với nhau.','2022-05-13T23:13','14000','20','Sen Đá Sedum Lông','Việt Nam',15000,15000,'sen-da-sedum-long',20,'Còn_hàng',1,10),(8,'admin','2022-05-16 23:18:43.813000',_binary '\0',NULL,NULL,'Sen đá Bánh Bao Lửa là một loại cây mọng nước. Đặc trưng bởi màu xanh và đỏ về phần ngọn lá. Khi hai màu kết hợp với nhau tạo nên một tổng thể rất bắt mắt và độc dáo. Nhiều người tin rằng loại sen đá này mang lại nhiều may mắn, tài lộc cho gia chủ.','2022-05-14T23:17','14000','20','Sen Đá Bánh Bao Lửa','Việt Nam',15000,15000,'sen-da-banh-bao-lua',20,'Còn_hàng',1,10),(9,'admin','2022-05-16 23:35:54.206000',_binary '\0',NULL,NULL,'Cây Trúc Nhật với thân hình mềm mại. Cây mang ý nghĩa phong thủy gặp dữ hóa lành, thăng tiến trong công việc và sự nghiệp nên được tặng trong các dịp như khai trương, sinh nhật hay thăng quan tiến chức.','2022-05-13T23:34','345000','30','Cây Trúc Nhật','Nhật Bản',349000,349000,'cay-truc-nhat',30,'Còn_hàng',1,4),(10,'admin','2022-05-16 23:38:34.838000',_binary '\0',NULL,NULL,'Cây phù hợp để trang trí ban công, trồng cùng các loại sen đá hoặc xương rồng khác. Đế Vương Xám có sức sống mạnh liệt và khỏe hơn các loại sen đài khác.','2022-05-13T23:37','14000','20','Sen Đá Đế Vương Xám','Việt Nam',15000,15000,'sen-da-de-vuong-xam',20,'Còn_hàng',1,4),(11,'admin','2022-05-16 23:44:38.165000',_binary '\0',NULL,NULL,'Cây phát triển thành bụi mới màu sắc khá lạ mặt. Phù hợp để làm cây trồng chung tiểu cảnh, cây làm nền.','2022-05-13T23:43','13000','20','Sen Đá Cỏ Tím','Việt Nam',15000,15000,'sen-da-co-tim',20,'Còn_hàng',1,4),(12,'admin','2022-05-16 23:54:45.528000',_binary '\0',NULL,NULL,'Cây có lá giống hình con cá heo. Cây mọc thành bụi rủ xuống khi mọc dài.','2022-05-14T23:51','14000','30','Sen Đá Cá Heo','Việt Nam',15000,15000,'sen-da-ca-heo',30,'Còn_hàng',1,10),(13,'admin','2022-05-17 00:03:50.294000',_binary '\0',NULL,NULL,'Trầu Bà Thanh Xuân còn có tên gọi khác như Trầu Bà Tay Phật. Thuộc loại thực vật họ Ráy có thân thảo, mọc thành bụi nhỏ. Chiều cao trung bình của cây khoảng 70cm. khi trưởng thành có thể đạt kích thước lên tới 1,5m. Khác với những hình dạng lá thông thường, lá của Trầu Bà Thanh xuân có hình dạng rất độc đáo. Lá cây bản to, xẻ nhánh thuỳ sâu giống như chân vịt, bẹ lá ôm sát vào thân và có màu xanh sẫm. Giống với đa số các cây họ Ráy, hoa của loại cây này có hình mo nhỏ mập mạp.','2022-05-13T00:00','345000','20','Cây Trầu Bà Thanh Xuân','Việt Nam',349000,349000,'cay-trau-ba-thanh-xuan',20,'Còn_hàng',1,10),(14,'admin','2022-05-17 00:07:19.066000',_binary '\0',NULL,NULL,'Cây phù hợp để trước hiên, cạnh cửa sổ, ban công, nơi có nhiều ánh sáng.','2022-05-13T00:05','460000','20','Cây Cau Lụa Vàng','Việt Nam',480000,480000,'cay-cau-lua-vang',20,'Còn_hàng',1,10),(15,'admin','2022-05-17 00:10:00.657000',_binary '\0',NULL,NULL,'Cây cúc mâm xôi với tán rộng 70cm chiều cao 60. Rất hợp chọn làm cây trưng tết đặt ở trước cửa nhà để đón khách. Với ý nghĩa trường thọ và may mắn.','2022-05-15T00:08','265000','20','Cây Cúc Mâm Xôi','Việt Nam',279000,279000,'cay-cuc-mam-xoi',20,'Còn_hàng',1,3),(16,'admin','2022-05-17 00:11:20.384000',_binary '\0',NULL,NULL,'Cành đào nhỏ phù hợp cắm lọ để trưng ban thờ, để bàn uống nước, kệ tivi, quầy lễ tân. ','2022-05-15T00:10','95000','15','Cành đào nhỏ','Việt Nam',99000,99000,'canh-dao-nho',15,'Còn_hàng',1,3),(17,'admin','2022-05-17 00:14:10.571000',_binary '\0',NULL,NULL,'Cây Kim Ngân Xoắn 3 thân có chiều cao khoảng 40 cm, chậu với độ rộng 11cm. Rất phù hợp với bàn làm việc không rộng nhưng là muốn có cây xanh xum xuê, ấn tượng và khác biệt.','2022-05-12T00:12','200000','20','Cây Kim Ngân Xoắn 3 Thân','Việt Nam',209000,209000,'cay-kim-ngan-xoan-3-than',20,'Còn_hàng',1,3),(18,'admin','2022-05-17 12:21:37.261000',_binary '\0',NULL,NULL,'Cây Phát Tài Núi hay còn gọi là cây Đại Lộc đang là một trong những cây nội thất rất được ưa chuộng. Cây có chiều cao 1m6 - 1m7 phù hợp làm quà tặng khai trương, tân gia, cây để văn phòng, trong nhà.','2022-05-13T12:19','789000','10','Cây Phát Tài Núi','Việt Nam',799000,799000,'cay-phat-tai-nui',10,'Còn_hàng',1,10),(19,'admin','2022-05-17 12:24:56.020000',_binary '\0',NULL,NULL,'Cây Mật Cật có lá rất đẹp, tượng trưng cho sự thăng tiến, một mối quan hệ bền chặt. Thường được dùng để làm quà tặng trọng những dịp đặc biệt như khai trương, thăng chức…','2022-05-16T12:22','340000','20','Cây Trúc Mây','Việt Nam',349000,349000,'cay-truc-may',20,'Còn_hàng',1,3),(20,'admin','2022-05-17 12:26:58.834000',_binary '\0',NULL,NULL,'Cây Ngân Hậu hay còn gọi là cây Minh Ty Rằn. Là loại cây được dùng nhiều trong trang trí nội thất. Có ý nghĩa xua đuổi những điều không may mắn, mang lại sự bình an, yên ổn cho gia chủ. ','2022-05-14T12:25','230000','20','Cây Ngân Hậu','Việt Nam',249000,249000,'cay-ngan-hau',20,'Còn_hàng',1,10),(21,'admin','2022-05-17 12:30:04.147000',_binary '\0',NULL,NULL,'Cây Hạnh Phúc để bàn đơn thân có chiều 40cm cây thân gỗ khỏe khắn, phù hợp làm cây để bàn, cây trang trí các góc nhỏ, để kệ...','2022-05-10T12:28','150000','30','Cây Hạnh Phúc đơn thân để bàn','Việt Nam',180000,180000,'cay-hanh-phuc-don-than-de-ban',30,'Còn_hàng',2,5),(22,'admin','2022-05-17 12:32:06.897000',_binary '\0',NULL,NULL,'Cây Lưỡi Hổ thủy sinh cao khoảng 30cm rất phù hợp làm cây để bàn, cây trang trí không gian nhỏ, tặng quà. ','2022-05-11T12:30','100000','20','Cây Lưỡi Hổ Thủy Sinh','Việt Nam',125000,125000,'cay-luoi-ho-thuy-sinh',20,'Còn_hàng',2,5),(23,'admin','2022-05-17 12:34:12.998000',_binary '\0',NULL,NULL,'Với chiều cao 1m nếu kê thêm kệ gỗ là 1m2 rất phù hợp với không gian lớn mà khoảng cách giừa trần và sàn thấp. Cây bàng Singapore nhiều nhánh hợp tặng khai trương, tân gia, phòng họp, phòng khách...','2022-05-11T12:33','330000','20','Cây Bàng Singapore nhiều nhánh','Singapore',349000,349000,'cay-bang-singapore-nhieu-nhanh',20,'Còn_hàng',2,9),(24,'admin','2022-05-17 12:36:41.633000',_binary '\0',NULL,NULL,'Đối với không gian bàn rộng hoặc gia chủ không thích cây quá cao thì cây kim tiền để bàn bụi to này rất hợp. Cây không phải chăm sóc nhiều và có thể sống được trong điều kiện máy lạnh. Cây có chiều cao 30cm, chậu có đường kính 16cm','2022-05-11T12:35','190000','10','Cây Kim Tiền để bàn bụi to','Việt Nam',209000,209000,'cay-kim-tien-de-ban-bui-to',10,'Còn_hàng',2,9),(25,'admin','2022-05-17 12:38:54.482000',_binary '\0',NULL,NULL,'Với chiều cao cả chậu 1m cây Bàng Singapore đơn thân gọn gàng, những chiếc lá to tròn mang ý nghĩa phong thủy may mắn và giàu sang. Cây phù hợp để trang trí nhà, các shop muốn để cây xanh nhưng khoảng không gian thừa ít, tặng khai trương, tân gia…','2022-05-11T12:37','200000','10','Cây Bàng Singapore 1m','Singapore',249000,249000,'cay-bang-singapore-1m',10,'Còn_hàng',2,9),(26,'admin','2022-05-17 12:41:06.625000',_binary '\0',NULL,NULL,'Để cho cây kim tiền của bạn thêm sinh động và là món quà hấp dẫn hơn thì bạn không thể bỏ qua chậu cây kim tiền mix với cẩm nhung hay còn gọi là lá may mắn','2022-05-13T12:39','150000','20','Cây Kim Tiền mix để bàn','Việt Nam',185000,185000,'cay-kim-tien-mix-de-ban',20,'Còn_hàng',2,6),(27,'admin','2022-05-17 12:44:09.568000',_binary '\0',NULL,NULL,'Cây Mini Monstera khá nổi bật với những chiếc lá xanh đậm và khuyết. Cây không cần chậu quá to chính vì thế cây rất phù hợp để trang trí những góc nhỏ trong nhà, quán cà phê, quà tặng...','2022-05-13T12:42','200000','20','Cây Mini Monstera','Việt Nam',249000,249000,'cay-mini-monstera',20,'Còn_hàng',2,6),(28,'admin','2022-05-17 12:45:25.450000',_binary '\0',NULL,NULL,'Với lá hình trái tim có nhiều vết xẻ lạ mắt, sống và phát triển tốt ở văn phòng và trong nhà. Chính vì thế cây đang rất được ưa thích làm cây nội thất, cây quà tặng','2022-05-11T12:44','400000','10','Cây Monstera','Việt Nam',450000,450000,'cay-monstera',10,'Còn_hàng',2,7),(29,'admin','2022-05-17 13:01:16.462000',_binary '\0',NULL,NULL,'Khá nổi bật với sự pha trộn đặc sắc của lá giữa mà trắng và màu xanh. Cây đặc biệt dễ chăm sóc có thể thích nghi với mọi môi trường, rất phù hợp với người thích cây mà lười chăm sóc.','2022-05-11T12:58','110000','10','Cây Tróc Bạc Thủy Sinh','Việt Nam',129000,129000,'cay-troc-bac-thuy-sinh',10,'Còn_hàng',2,6),(30,'admin','2022-05-17 13:03:27.108000',_binary '\0',NULL,NULL,'Với sắc lá đỏ và sống tốt trong điều kiện thiếu sáng, cây Vạn Lộc thủy sinh cực kỳ phù hợp và cây nội thất, cây trang trí, làm quà tặng và cây phong thủy cho mệnh hỏa và thổ.','2022-05-14T13:02','150000','20','Cây Vạn Lộc Thủy Sinh','Việt Nam',179000,179000,'cay-van-loc-thuy-sinh',20,'Còn_hàng',2,2),(31,'admin','2022-05-17 13:05:10.102000',_binary '\0',NULL,NULL,'Nhờ đặc điểm dễ chăm sóc, không cần ánh nắng nhiều, tán lá nổi bật nên cây Sao Sáng phù hợp làm cây trang trí nội thất, cây phong thủy và làm quà tặng.','2022-05-13T13:04','120000','20','Cây Sao Sáng Thủy Sinh','Việt Nam',139000,139000,'cay-sao-sang-thuy-sinh',20,'Còn_hàng',2,3),(32,'admin','2022-05-17 13:07:29.483000',_binary '\0',NULL,NULL,'Cây Trầu Bà Lá Lỗ có lá rất đặc biệt mà không giống với loại cây nào. Chính vì thế cây thường được chọn để tạo sự nổi bật và đặc sắc như trang trí nội thất, văn phòng, quà tặng, người chơi hệ lá...','2022-05-12T13:06','115000','30','Cây Trầu Bà Lá Lỗ','Việt Nam',139000,139000,'cay-trau-ba-la-lo',30,'Còn_hàng',2,5),(33,'admin','2022-05-17 13:09:17.464000',_binary '\0',NULL,NULL,'Cây Ngũ Gia Bì thủy sinh giúp gia chủ phát triển vững vàng, cũng có thể ổn định tài vận, giữ được tiền tài. Cây trồng trong bình thủy tinh nên rất sạch sẽ phù hợp để bàn, trang trí nội thất, bàn làm việc, quán cà phê, bàn lễ tân...','2022-05-13T13:08','95000','20','Cây Ngũ Gia Bì Thủy Sinh','Việt Nam',125000,125000,'cay-ngu-gia-bi-thuy-sinh',20,'Còn_hàng',2,3),(34,'admin','2022-05-17 13:11:24.869000',_binary '\0',NULL,NULL,'Cây có phiến lá nhẵn, xanh rất bắt mắt. Là cây thủy sinh đựng trong bình thủy tinh nên rất sạch sẽ. Phù hợp làm cây để bàn, văn phòng, trang trí quán, các góc nhỏ trong nhà...','2022-05-12T13:10','100000','10','Cây Trầu Bà Đế Vương Xanh thủy sinh','Việt Nam',129000,129000,'cay-trau-ba-de-vuong-xanh-thuy-sinh',10,'Còn_hàng',2,5),(35,'admin','2022-05-17 13:13:04.585000',_binary '\0',NULL,NULL,'Cây Thủy Tùng trong tự nhiên là loại cây mọc và sống trung gian giữa đất và nước nên bản thân nó mang ý nghĩa của sự hài hòa rất tốt trong ngũ hành phong thủy. Cây phù hợp làm cây để bàn, quầy lễ tân, trang trí nhà, quán cà phê...','2022-05-11T13:12','110000','10','Cây Thủy Tùng','Việt Nam',125000,125000,'cay-thuy-tung',10,'Còn_hàng',2,3),(36,'admin','2022-05-17 13:15:54.663000',_binary '\0',NULL,NULL,'Cây phát tài hay còn gọi là cây thiết mộc lan, phát tài khúc với ý nghĩa mang lại may mắn cho gia chủ trong cuộc sống và công việc. Sản phẩm phù hợp với gia chủ muốn mua cây phòng khách, phòng hội họp, văn phòng công sở, nhà hàng, khách sạn, hoặc dùng làm quà tặng trong những dịp mừng lễ, tết, thăng chức, khai trương...Hoa của cây phát tài rất thơm, một hương thơm dịu','2022-05-11T13:14','125000','10','Cây Phát Tài','Việt Nam',149000,149000,'cay-phat-tai',10,'Còn_hàng',3,10),(37,'admin','2022-05-17 13:18:10.896000',_binary '\0',NULL,NULL,'Cây sen đá xanh mang ý nghĩa cho một tình yêu, tình bạn vĩnh cửu theo thời gian. Cây nhỏ thường từ 10 - 20 cm, phù hợp dùng để trang trí bàn làm việc, góc học tập hoặc góc nhỏ riêng của bạn.','2022-05-11T13:16','12000','30','Sen Đá Xanh','Việt Nam',15000,15000,'sen-da-xanh',30,'Còn_hàng',3,9),(38,'admin','2022-05-17 13:20:02.119000',_binary '\0',NULL,NULL,'Cây Sen đá phật bà là một trong những loại sen đá có nhiều lá nhất, như phật bà quan âm nghìn mắt nhìn tay. Cây sen phật bà không chỉ mang ý nghĩa về một tình yêu và tình bạn bền chặt mà nó còn mang đến sự may mắn như quan âm phù hộ bên người sở hữu. Sen phật bà phù hợp trang trí bàn cưới, cà phê, bàn làm việc, góc học tập, bàn tiếp khách ở các khách sạn, góc riêng….','2022-05-12T13:19','10000','20','Sen Đá Phật Bà','Việt Nam',15000,15000,'sen-da-phat-ba',20,'Còn_hàng',3,5),(39,'admin','2022-05-17 13:22:01.750000',_binary '\0',NULL,NULL,'Sen đá cánh bướm là một trong những loại sen đá lớn lá phát triển tối đa có thể từ 30 -40 cm một lá. Cây sen cánh bướm thường dùng để trang trí cách bàn làm việc lớn, khách sạn, nhà hàng, cây nhỏ có thể là tiểu cảnh hoặc trang trí bàn làm việc góc học tập. Cây mang ý nghĩa người bạn tri kỷ của bạn sẽ không trăng hoa óc và om bướm.','2022-05-11T13:20','14000','20','Sen Đá Cánh Bướm','Việt Nam',20000,20000,'sen-da-canh-buom',0,'Còn_hàng',3,6),(40,'admin','2022-05-17 13:23:32.452000',_binary '\0',NULL,NULL,'Cây cảnh sen đá thạch ngọc được lựa chọn để trang trí những nơi sang trọng và gắn liều nhiều với tiền bạc. Cây có lá căng mọng, lá được phủ một lớn phấn nhẹ, đầu lá có thể có viền tím, đỏ, hoặc xanh. Sen đá thạch ngọc mang ý nghĩa đầy đủ, và nhiều tiền bạc mang đến cho chủ sở hữu.','2022-05-14T13:22','14000','20','Sen Đá Thạch Ngọc','Việt Nam',20000,20000,'sen-da-thach-ngoc',20,'Còn_hàng',3,4),(41,'admin','2022-05-17 13:24:52.377000',_binary '\0',NULL,NULL,'Cây sen đá ống điếu là một trong những cây có thể phát triển cao tới 90 cm, cây có thể làm bonsai. Sen ống điếu phù hợp để làm tiểu cảnh, cây để bàn làm việc, khách sạn, quán cà phê, góc học tập...','2022-05-12T13:24','14000','10','Sen Đá Ống Điếu','Việt Nam',15000,15000,'sen-da-ong-dieu',10,'Còn_hàng',3,3),(42,'admin','2022-05-17 13:26:35.188000',_binary '\0',NULL,NULL,'Sen đá hoa cúc phù hợp để bàn làm việc, bàn cưới, quán cà phê, khách sạn...Khiến cho chiếc bàn của bạn sẽ sang trọng hơn rất nhiều. Ngoài ra có thể dùng nó để tặng quà cho người yêu, bạn cùng lứa...','2022-05-10T13:25','14000','10','Sen Đá Hoa Cúc','Việt Nam',15000,15000,'sen-da-hoa-cuc',10,'Còn_hàng',3,1),(43,'admin','2022-05-17 13:28:27.975000',_binary '\0',NULL,NULL,'Sen đá bắp cải có ý nghĩa cho một tình bạn, tình yêu vĩnh cửu theo thời gian. Ngoài ra về phong thủy nó còn mang ý nghĩa sung túc và đầm ấm trong gia đình. Cây sen bắp cải phù hợp để trang trí khách sạn, quán cà phê, bàn làm việc, góc học tập, cửa sổ....','2022-05-13T13:27','13000','20','Sen Đá Bắp Cải','Việt Nam',15000,15000,'sen-da-bap-cai',20,'Còn_hàng',3,3),(44,'admin','2022-05-17 13:29:50.926000',_binary '\0',NULL,NULL,'Sen đá hoa hồng trắng biểu tượng của tình yêu mãnh liệt, cây phù hợp để trang trí bàn làm việc, bàn cà phê, góc học tập, góc riêng của bạn...','2022-05-12T13:29','10000','20','Sen Đá Hoa Hồng Trắng','Việt Nam',15000,15000,'sen-da-hoa-hong-trang',20,'Còn_hàng',3,4),(45,'admin','2022-05-17 13:31:29.529000',_binary '\0',NULL,NULL,'Sen đá 3 màu là một loại sen có rất nhiều màu sắc và bắt mắt cây thường cao khoảng tầm >10 cm, có các cây con bên hông mọc phát triển như cây chính. Cây mang ý nghĩa cho sự sung túc, đuề huề và đầy đủ.','2022-05-12T13:30','50000','10','Sen Đá 3 Màu','Nhật Bản',75000,75000,'sen-da-3-mau',10,'Còn_hàng',3,4),(46,'admin','2022-05-17 13:33:32.588000',_binary '\0',NULL,NULL,'Sen đá thái là một trong những cây có rất nhiều lá lá xếp chồng nên nhau, dưới góc lại thường hay mọc các cây con nhỏ nhìn rất thích mắt. Cây mang biểu tượng cho một tình yêu, tình bạn luôn bền vững mãi theo thời gian. Cây phù hợp để bàn làm việc, bàn học, trang trí khách sạn, quán cà phê...','2022-05-12T13:32','20000','20','Sen Đá Thái','Thái Lan',30000,30000,'sen-da-thai',20,'Còn_hàng',3,4),(47,'admin','2022-05-17 13:35:28.534000',_binary '\0',NULL,NULL,'Sen đá đất mang ý nghĩa cho một tình yêu, tình bạn vĩnh cửu theo thời gian. Cây có 2 màu xanh lá và xanh dương, rất phù hợp để trang trí bàn lễ tân, khách sạn, quán cà phê, bàn làm việc, bàn học...','2022-05-14T13:34','10000','10','Sen Đá Đất','Việt Nam',15000,15000,'sen-da-dat',10,'Còn_hàng',3,5),(48,'admin','2022-05-17 13:37:15.796000',_binary '\0',NULL,NULL,'Tiểu cảnh sen đá điền viên mang ý nghĩa một cuộc sống yên bình, thư thái và nhàn nhã. Tiểu cảnh phù hợp để trong bàn làm việc của sếp, quà biếu, phòng khách, sản khách sạn, quán cà phê...','2022-05-14T13:36','399000','10','Tiểu Cảnh Sen Đá Điền Viên','Việt Nam',449000,449000,'tieu-canh-sen-da-dien-vien',10,'Còn_hàng',3,10),(49,'admin','2022-05-17 13:38:58.064000',_binary '\0',NULL,NULL,'Sản phẩm phù hợp để bàn ở phòng khách, bàn trang điểm, trang trí khách sạn, quán cà phê, bàn làm việc...Ý nghĩa luôn được vui vẻ và tươi cười','2022-05-12T13:38','100000','10','Tiểu Cảnh Một Vợ Hai Con','Việt Nam',129000,129000,'tieu-canh-mot-vo-hai-con',10,'Còn_hàng',3,10),(50,'admin','2022-05-17 13:41:57.911000',_binary '\0',NULL,NULL,'Terrarium Bách Niên Giai Lão mang ý nghĩa cùng nhau sống đến trăm tuổi, trăm năm hạnh phúc. Bình rất phù hợp để trang trí bàn làm việc, quán cà phê, bàn học, sảng khách sạn...Và là món quà ấn tượng dành cho cặp đôi','2022-05-10T13:41','130000','20','Terrarium Bách Niên Giai Lão','Việt Nam',159000,159000,'terrarium-bach-nien-giai-lao',20,'Còn_hàng',4,2),(51,'admin','2022-05-17 13:43:24.844000',_binary '\0',NULL,NULL,'Terrarium ngôi nhà nhỏ trên thảo nguyên khắc họa lại hình ảnh thảo nguyên thu nhỏ, mang đến cho bạn cảm giác bình yên. Bình phù hợp để làm quà tặng, trang trí quán cà phê, phòng khách, bàn làm việc, lễ tân...','2022-05-14T13:42','250000','10','Terrarium Ngôi Nhà Nhỏ Trên Thảo Nguyên','Việt Nam',279000,279000,'terrarium-ngoi-nha-nho-tren-thao-nguyen',10,'Còn_hàng',4,1),(52,'admin','2022-05-17 13:45:24.631000',_binary '\0',NULL,NULL,'Cây cẩm nhung Fittonia có màu đỏ, trắng, hồng...Cây còn gọi là lá may mắn, dễ sống và dễ chăm sóc. Cây phù hợp làm cây cảnh để bàn, trồng terrarium, tiểu cảnh, trang trí quán cà phê, làm quà tặng...','2022-05-13T13:44','40000','10','Cây Cẩm Nhung Fittonia','Nhật Bản',59000,59000,'cay-cam-nhung-fittonia',10,'Còn_hàng',4,1),(53,'admin','2022-05-17 13:47:36.786000',_binary '\0',NULL,NULL,'Cây Câu Tiểu Trâm hay còn gọi là cau may mắn, cây ưa mát không cần nhiều ánh sáng vẫn có thể phát triển, Cau Tiểu Trâm có khả năng hút các chất khí độc và lọc sạch không ý. Cây mang ý nghĩa may mắn và tài lộc trong phong thủy.','2022-05-12T13:46','50000','30','Cau Tiểu Trâm','Việt Nam',69000,69000,'cau-tieu-tram',30,'Còn_hàng',4,7),(54,'admin','2022-05-17 14:52:08.849000',_binary '\0','admin','2022-05-17 15:07:38.468000','Cây Cỏ Đồng Tiền thủy sinh có ý nghĩa phong thủy giúp tài vận thăng tiến, đồng thời cũng có thể giúp năng lực phân tích cá nhân, làm bạn mạnh mẽ hơn, làm việc quyết đoán hơn, đặc biệt cây giúp gia chủ nắm bắt tốt thời cơ, không bỏ qua cơ hội kiếm tiền.','2022-05-11T14:51','80000','20','Cỏ Đồng Tiền','Việt Nam',99000,79000,'co-dong-tien',20,'Còn_hàng',4,10),(55,'admin','2022-05-17 15:40:25.589000',_binary '\0',NULL,NULL,'Cây Phát Tài Búp Sen được tượng trưng cho sự thanh khiết, thánh thiện, cây có ý nghĩa phong thủy mang đến tài lộc, may mắn và thành công cho gia chủ, vì vậy Phát Tài Búp Sen rất phù hợp làm quà mừng tân gia, để trên bàn làm việc, bàn uống nước, văn phòng...','2022-05-12T15:39','100000','20','Cây Phát Tài Búp Sen','Việt Nam',115000,115000,'cay-phat-tai-bup-sen',20,'Còn_hàng',4,6),(56,'admin','2022-05-17 15:44:35.536000',_binary '\0',NULL,NULL,'Cây Ngũ Gia Bì giúp gia chủ phát triển vững vàng, cũng có thể ổn định tài vận, giữ được tiền tài. Cây phù hợp để bàn, trang trí nội thất, bàn làm việc, quán cà phê, bàn lễ tân...','2022-05-12T15:43','100000','20','Cây Ngũ Gia Bì','Việt Nam',125000,125000,'cay-ngu-gia-bi',20,'Còn_hàng',4,4),(57,'admin','2022-05-17 15:46:04.951000',_binary '\0',NULL,NULL,'Cây Trầu Bà có ý nghĩa phong thủy mang đến cho gia chủ may mắn, thành đạt và bình an. Cây phù hợp để phòng khách, trang trí sảng, treo của sổ, hiên nhà, treo bancol, hoặc để bàn làm việc.','2022-05-12T15:45','100000','10','Cây Trầu Bà Thủy Sinh','Việt Nam',105000,105000,'cay-trau-ba-thuy-sinh',10,'Còn_hàng',4,7),(58,'admin','2022-05-17 15:47:53.791000',_binary '\0',NULL,NULL,'Cây Trầu Bà Cột rất được ưa chuộng vì cây rất dễ sống, lá to mang đến cho bạn một cảm giác xanh, cây phù hợp để phòng khách, sản khách sạn, tặng tân gia, để phòng sếp, quầy lễ tân...','2022-05-13T15:47','500000','10','Cây Trầu Bà Cột','Việt Nam',599000,599000,'cay-trau-ba-cot',10,'Còn_hàng',4,10),(59,'admin','2022-05-17 15:52:28.322000',_binary '\0',NULL,NULL,'Cây Phú Quý có ý nghĩa phong thủy mang đến cho gia chủ sự may mắn, giàu sang và phú quý. Cây phù hợp để trang trí quán cà phê, góc nhỏ, sảnh, khách sạn...','2022-05-12T15:52','140000','20','Cây Phú Quý','Việt Nam',155000,155000,'cay-phu-quy',20,'Còn_hàng',4,10),(60,'admin','2022-05-17 15:55:00.978000',_binary '\0',NULL,NULL,'Cây Vạn Niên Thanh có ý nghĩa phong thủy tăng tài vận cho gia chủ. Người ta quan niệm, trồng vạn niên thanh trong nhà ngày tết mang đến sự sung túc, trong hôn nhân cầu chúc hòa hợp như ý, trong lễ mừng thọ chúc được sống lâu. Cây phù hợp làm cây cảnh trong nhà, cây cảnh văn phòng giúp lọc sạch không khí, mang đến không gian sự tươi mới.','2022-05-13T15:54','170000','20','Cây Vạn Niên Thanh','Việt Nam',199000,199000,'cay-van-nien-thanh',20,'Còn_hàng',4,9),(61,'admin','2022-05-17 15:58:05.143000',_binary '\0',NULL,NULL,'Cây Ngọc Ngân hay còn có tên là cây Valentine trong tình cảm nó được đại diện cho tình yêu, nó sẽ là một món quà ý nghĩa đối với các cặp đôi. Nếu đặt ở văn phòng, bàn làm việc, trong nhà thì Ngọc Ngân sẽ mang đến sự may mắn và bổng lộc cho gia chủ.','2022-05-11T15:56','140000','10','Cây Ngọc Ngân','Việt Nam',155000,155000,'cay-ngoc-ngan',10,'Còn_hàng',4,2),(62,'admin','2022-05-17 16:00:08.782000',_binary '\0',NULL,NULL,'Cây Lan Ý thủy sinh có tác dụng thanh lọc không khí, hấp thụ các chất gây ung thư như formaldehyde, benzen và trichloroethylene, ngoài ra nó còn có khả năng giúp hấp thụ các bức xạ nhân tạo phát ra từ máy tính, tivi, lò vi sóng, đồ hồ điện tử...Cây phù hợp để bàn làm việc, trang ý quán cà phê, bàn uống nước....','2022-05-14T15:59','140000','20','Cây Lan ý thủy sinh','Việt Nam',155000,155000,'cay-lan-y-thuy-sinh',20,'Còn_hàng',4,1),(63,'admin','2022-05-17 16:01:35.556000',_binary '\0',NULL,NULL,'Terrarium gồm 10 cây sen đá các loại được trồng vào trong bình thủy tinh với ý nghĩa công việc, tình cảm, tiền bạc đều được như mong muốn, toàn vẹn và như ý. Terrarium phù hợp để bàn làm việc, trang trí quán cà phê, khách sạn...','2022-05-14T16:00','500000','10','Terrarium 10 Cây Sen Đá','Việt Nam',549000,549000,'terrarium-10-cay-sen-da',10,'Còn_hàng',4,7),(64,'admin','2022-05-17 16:03:20.784000',_binary '\0',NULL,NULL,'Cây Phú Quý thủy sinh rất phù hợp để trong văn phòng, trang trí nhà cửa, quán cà phê. Được đựng trong bình thủy tinh nên cây rất sạch sẽ. Cây phù hợp với người mệnh thổ và mệnh hỏa. Cây mang ý nghĩa phong thủy mang đến phú quý, may mắn và giàu sang cho gia chủ.','2022-05-11T16:02','150000','30','Cây Phú Quý Thủy Sinh','Việt Nam',170000,170000,'cay-phu-quy-thuy-sinh',30,'Còn_hàng',4,7),(65,'admin','2022-05-17 16:05:04.890000',_binary '\0',NULL,NULL,'Cây Hồng Môn tượng trưng cho tình yêu và lòng hiếu khách, cây phù hợp để bày phòng khách, quầy lễ tân, nơi nhiều người thường xuyên qua lại, hoặc để trang trí nội thất đều rất hợp.','2022-05-11T16:04','120000','30','Cây Hồng Môn','Việt Nam',135000,135000,'cay-hong-mon',30,'Còn_hàng',4,1),(66,'admin','2022-05-17 16:06:39.484000',_binary '\0',NULL,NULL,'Cây Tùng Bồng Lai để bàn mang ý nghĩa phong thủy nhiều tới sức khỏe, tài lộc, thịnh vượng, giữ tiền và giữ của cho người sở hữu. Cây phong thủy dành cho người tuổi Thân','2022-05-11T16:05','100000','20','Cây Tùng Bồng Lai','Việt Nam',125000,125000,'cay-tung-bong-lai',20,'Còn_hàng',4,6),(67,'admin','2022-05-17 16:09:03.780000',_binary '\0',NULL,NULL,'Cây Kim Tiền loại nhỡ cao khoảng 100cm, chậu có chiều cao 43 rộng 28cm. Phù hợp để đặt và trang trí không gian không quá lớn như văn phòng, shop, quán cà phê nhỏ','2022-05-14T16:08','290000','15','Cây Kim Tiền Trung','Việt Nam',310000,310000,'cay-kim-tien-trung',15,'Còn_hàng',5,1),(68,'admin','2022-05-17 16:12:23.422000',_binary '\0',NULL,NULL,'Cây Kim Tiền To có kích thước cao gần 1 mét độ rộng tán khoảng 50cm. Chậu cao 46cm rộng 34cm phù hợp để trang trí những không gian lớn, như sảnh khách sạn, Spa, quần lễ tân, tặng khai trương công ty...','2022-05-11T16:11','400000','20','Cây Kim Tiền To','Việt Nam',480000,480000,'cay-kim-tien-to',20,'Còn_hàng',5,9),(69,'admin','2022-05-17 16:14:13.145000',_binary '\0',NULL,NULL,'Cây Hoa Trạng Nguyên có ý nghĩa phong thủy mang đến thành công, đỗ đạt và may mắn. Cây phù hợp làm cây cảnh văn phòng, quầy lễ tân, quầy thu ngân, cây cảnh để trước cửa nhà.','2022-05-14T16:13','130000','20','Cây Trạng Nguyên','Việt Nam',159000,159000,'cay-trang-nguyen',20,'Còn_hàng',5,1),(70,'admin','2022-05-17 16:15:38.602000',_binary '\0',NULL,NULL,'Cây Tùng Thơm hay còn Tùng Hương, Tùng Chanh cây có mùi hương chanh nhẹ dễ chịu, màu sắc cây tươi sáng nhìn rất nổi bật phù hợp làm cây trang trí, để văn phòng, bàn lễ tân...','2022-05-09T16:14','200000','10','Cây Tùng Thơm','Việt Nam',215000,215000,'cay-tung-thom',10,'Còn_hàng',5,8),(71,'admin','2022-05-17 16:17:37.022000',_binary '\0',NULL,NULL,'Cây Chuối Rẻ Quạt là loại cây có lá to và xanh tốt quanh năm, cây phù hợp trồng biệt thự sân vườn, bể bơi, cây trồng trong nhà, cây trồng văn phòng, sản khách sạn, nhà hàng, quán cafe','2022-05-09T16:16','300000','20','Cây Chuối Rẻ Quạt','Việt Nam',349000,349000,'cay-chuoi-re-quat',20,'Còn_hàng',5,4),(72,'admin','2022-05-17 16:19:14.421000',_binary '\0',NULL,NULL,'Cây Bạch Mã Hoàng Tử giúp cho gia chủ phát triển con đường sự nghiệp nhanh chóng, thuận buồm xuôi gió và may mắn trong cuộc sống. Cây phù hợp làm cây để phòng khách, lễ tân, quầy thu ngân, làm cây trang trí không gian, tặng khai trương, thăng chức...','2022-05-14T16:18','300000','15','Cây Bạch Mã Hoàng Tử','Việt Nam',350000,350000,'cay-bach-ma-hoang-tu',15,'Còn_hàng',5,9),(73,'admin','2022-05-17 16:20:27.074000',_binary '\0',NULL,NULL,'Bạn muốn tìm loại cây ấn tượng bản lá to mà không gian không quá lớn. Thì cây Đuôi Công Táo Xanh là một sự lựa chọn tuyệt vời cho bạn. Cây phù hợp làm cây trang trí kệ tivi, phòng khách, quầy lễ tân, quán cà phê, văn phòng...','2022-05-11T16:19','200000','20','Cây Đuôi Công Táo Xanh','Thái Lan',249000,249000,'cay-duoi-cong-tao-xanh',20,'Còn_hàng',5,4),(74,'admin','2022-05-17 16:26:06.180000',_binary '\0','admin','2022-05-19 22:18:00.491000','Cây sen đá hàm cá sấu mang ý nghĩa con người luôn có điểm tốt điểm xấu, bạn đừng nên nhìn mà điểm xấu mà quên đi cái tốt, nhìn vào cái tốt để suy nghĩ tích cực để cuộc sống vui vẻ hơn. Cây phù hợp để ở bàn làm việc, góc học tập, trồng với tiểu cảnh, các góc riêng nhỏ.','2022-05-12T16:23','10000','30','Sen Đá Hàm Cá Sấu','Việt Nam',15000,10000,'sen-da-ham-ca-sau',30,'Còn_hàng',6,6),(75,'admin','2022-05-19 22:22:23.677000',_binary '\0',NULL,NULL,'Sen đá móng rồng mang ý nghĩa cho sự che chở và bảo vệ. Cây phù hợp để làm quà, để bàn làm việc, góc học tập...','2022-05-13T22:20','13000','30','Sen Đá Móng Rồng','Việt Nam',15000,15000,'sen-da-mong-rong',30,'Còn_hàng',6,6);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comment`
--

LOCK TABLES `product_comment` WRITE;
/*!40000 ALTER TABLE `product_comment` DISABLE KEYS */;
INSERT INTO `product_comment` VALUES (1,'tiennguyen','2022-05-19 12:21:18.382000',_binary '\0',NULL,NULL,'Sản phẩm được vẫn chuyển rất cẩn thận, giá cả phải chăng.',NULL,69,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,'admin','2022-05-16 22:20:34.462000',_binary '','admin','2022-05-17 15:08:44.722000','cay-bang-cam-thach-la-tim-1.png',1),(2,'admin','2022-05-16 22:20:34.477000',_binary '','admin','2022-05-17 15:08:44.732000','cay-bang-cam-thach-la-tim-2.png',1),(3,'admin','2022-05-16 22:20:34.486000',_binary '','admin','2022-05-17 15:08:44.737000','cay-bang-cam-thach-la-tim-3.png',1),(4,'admin','2022-05-16 22:20:34.497000',_binary '','admin','2022-05-17 15:08:44.743000','cay-bang-cam-thach-la-tim-4.png',1),(5,'admin','2022-05-16 22:37:48.195000',_binary '\0',NULL,NULL,'cay-sen-da-nuda-1.png',2),(6,'admin','2022-05-16 22:37:48.207000',_binary '\0',NULL,NULL,'cay-sen-da-nuda-2.png',2),(7,'admin','2022-05-16 22:37:48.213000',_binary '\0',NULL,NULL,'cay-sen-da-nuda-3.png',2),(8,'admin','2022-05-16 22:37:48.221000',_binary '\0',NULL,NULL,'cay-sen-nuda-4.png',2),(9,'admin','2022-05-16 22:45:28.302000',_binary '\0',NULL,NULL,'cay-sen-da-cuc-tim-1.png',3),(10,'admin','2022-05-16 22:45:28.314000',_binary '\0',NULL,NULL,'cay-sen-da-cuc-tim-2.png',3),(11,'admin','2022-05-16 22:45:28.322000',_binary '\0',NULL,NULL,'cay-sen-da-cuc-tim-3.png',3),(12,'admin','2022-05-16 22:45:28.329000',_binary '\0',NULL,NULL,'cay-sen-da-cuc-tim-4.png',3),(13,'admin','2022-05-16 22:54:45.162000',_binary '\0',NULL,NULL,'sen-da-giva-1.png',4),(14,'admin','2022-05-16 22:54:45.169000',_binary '\0',NULL,NULL,'sen-da-giva-2.png',4),(15,'admin','2022-05-16 22:54:45.176000',_binary '\0',NULL,NULL,'sen-da-giva-3.png',4),(16,'admin','2022-05-16 22:54:45.183000',_binary '\0',NULL,NULL,'sen-da-giva-4.png',4),(17,'admin','2022-05-16 23:08:59.859000',_binary '\0',NULL,NULL,'cay-sen-da-lien-dai-hong-1.png',5),(18,'admin','2022-05-16 23:08:59.873000',_binary '\0',NULL,NULL,'cay-sen-da-lien-dai-hong-2.png',5),(19,'admin','2022-05-16 23:08:59.880000',_binary '\0',NULL,NULL,'cay-sen-da-lien-dai-hong-3.png',5),(20,'admin','2022-05-16 23:08:59.886000',_binary '\0',NULL,NULL,'cay-sen-da-lien-dai-hong-4.png',5),(21,'admin','2022-05-16 23:11:58.193000',_binary '\0',NULL,NULL,'sen-da-bap-cai-tim-dep-1.png',6),(22,'admin','2022-05-16 23:11:58.207000',_binary '\0',NULL,NULL,'sen-da-bap-cai-tim-dep-2.png',6),(23,'admin','2022-05-16 23:11:58.225000',_binary '\0',NULL,NULL,'sen-da-bap-cai-tim-dep-3.png',6),(24,'admin','2022-05-16 23:11:58.231000',_binary '\0',NULL,NULL,'sen-da-bap-cai-tim-dep-4.png',6),(25,'admin','2022-05-16 23:15:43.414000',_binary '\0',NULL,NULL,'cay-sen-da-sedum-long-1.png',7),(26,'admin','2022-05-16 23:15:43.424000',_binary '\0',NULL,NULL,'cay-sen-da-sedum-long-2.png',7),(27,'admin','2022-05-16 23:15:43.439000',_binary '\0',NULL,NULL,'cay-sen-da-sedum-long-3.png',7),(28,'admin','2022-05-16 23:15:43.446000',_binary '\0',NULL,NULL,'cay-sen-da-sedum-long-4.png',7),(29,'admin','2022-05-16 23:18:43.824000',_binary '\0',NULL,NULL,'sen-da-banh-bao-lua-1.png',8),(30,'admin','2022-05-16 23:18:43.834000',_binary '\0',NULL,NULL,'sen-da-banh-bao-lua-2.png',8),(31,'admin','2022-05-16 23:18:43.840000',_binary '\0',NULL,NULL,'sen-da-banh-bao-lua-3.png',8),(32,'admin','2022-05-16 23:18:43.850000',_binary '\0',NULL,NULL,'sen-da-banh-bao-lua-4.png',8),(33,'admin','2022-05-16 23:35:54.254000',_binary '\0',NULL,NULL,'cay-truc-nhat-dep-1.png',9),(34,'admin','2022-05-16 23:35:54.268000',_binary '\0',NULL,NULL,'cay-truc-nhat-dep-2.png',9),(35,'admin','2022-05-16 23:35:54.274000',_binary '\0',NULL,NULL,'cay-truc-nhat-dep-3.png',9),(36,'admin','2022-05-16 23:35:54.283000',_binary '\0',NULL,NULL,'cay-truc-nhat-dep-4.png',9),(37,'admin','2022-05-16 23:38:34.852000',_binary '\0',NULL,NULL,'cay-sen-da-de-vuong-xam-1.png',10),(38,'admin','2022-05-16 23:38:34.863000',_binary '\0',NULL,NULL,'cay-sen-da-de-vuong-xam-2.png',10),(39,'admin','2022-05-16 23:38:34.869000',_binary '\0',NULL,NULL,'cay-sen-da-de-vuong-xam-3.png',10),(40,'admin','2022-05-16 23:38:34.880000',_binary '\0',NULL,NULL,'cay-sen-da-de-vuong-xam-4.png',10),(41,'admin','2022-05-16 23:46:20.338000',_binary '\0',NULL,NULL,'cay-sen-da-co-tim-1.png',11),(42,'admin','2022-05-16 23:46:20.346000',_binary '\0',NULL,NULL,'cay-sen-da-co-tim-2.png',11),(43,'admin','2022-05-16 23:46:20.352000',_binary '\0',NULL,NULL,'cay-sen-da-co-tim-3.png',11),(44,'admin','2022-05-16 23:46:20.358000',_binary '\0',NULL,NULL,'cay-sen-da-co-tim-4.png',11),(45,'admin','2022-05-16 23:54:51.790000',_binary '\0',NULL,NULL,'cay-sen-da-ca-heo-1.png',12),(46,'admin','2022-05-16 23:54:51.802000',_binary '\0',NULL,NULL,'cay-sen-da-ca-heo-2.png',12),(47,'admin','2022-05-16 23:54:51.809000',_binary '\0',NULL,NULL,'cay-sen-da-ca-heo-3.png',12),(48,'admin','2022-05-16 23:54:51.814000',_binary '\0',NULL,NULL,'cay-sen-da-ca-heo-4.png',12),(49,'admin','2022-05-17 00:03:55.314000',_binary '\0',NULL,NULL,'cay-trau-ba-thanh-xuan-1.png',13),(50,'admin','2022-05-17 00:03:55.333000',_binary '\0',NULL,NULL,'cay-trau-ba-thanh-xuan-2.png',13),(51,'admin','2022-05-17 00:03:55.339000',_binary '\0',NULL,NULL,'cay-trau-ba-thanh-xuan-3.png',13),(52,'admin','2022-05-17 00:03:55.344000',_binary '\0',NULL,NULL,'cay-trau-ba-thanh-xuan-4.png',13),(53,'admin','2022-05-17 00:07:22.626000',_binary '\0',NULL,NULL,'cau-vang-1.png',14),(54,'admin','2022-05-17 00:07:22.632000',_binary '\0',NULL,NULL,'cau-vang-2.png',14),(55,'admin','2022-05-17 00:07:22.637000',_binary '\0',NULL,NULL,'cau-vang-3.png',14),(56,'admin','2022-05-17 00:07:22.642000',_binary '\0',NULL,NULL,'cau-vang-4.png',14),(57,'admin','2022-05-17 00:10:00.668000',_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-1.png',15),(58,'admin','2022-05-17 00:10:00.674000',_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-2.png',15),(59,'admin','2022-05-17 00:10:00.684000',_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-3.png',15),(60,'admin','2022-05-17 00:10:00.690000',_binary '\0',NULL,NULL,'cay-cuc-mam-xoi-3.png',15),(61,'admin','2022-05-17 00:11:20.395000',_binary '\0',NULL,NULL,'canh-dao-nho-1.png',16),(62,'admin','2022-05-17 00:11:20.400000',_binary '\0',NULL,NULL,'canh-dao-nho-2.png',16),(63,'admin','2022-05-17 00:11:20.405000',_binary '\0',NULL,NULL,'canh-dao-nho-3.png',16),(64,'admin','2022-05-17 00:11:20.412000',_binary '\0',NULL,NULL,'canh-dao-nho-4.png',16),(65,'admin','2022-05-17 00:14:10.579000',_binary '\0',NULL,NULL,'cay-kim-ngan-xoan-ba-than-1.png',17),(66,'admin','2022-05-17 00:14:10.585000',_binary '\0',NULL,NULL,'cay-kim-ngan-xoan-ba-than-2.png',17),(67,'admin','2022-05-17 00:14:10.591000',_binary '\0',NULL,NULL,'cay-kim-ngan-xoan-ba-than-3.png',17),(68,'admin','2022-05-17 00:14:10.597000',_binary '\0',NULL,NULL,'cay-kim-ngan-xoan-ba-than-4.png',17),(69,'admin','2022-05-17 12:21:37.296000',_binary '\0',NULL,NULL,'cay-phat-tai-nui-dai-loc-1.png',18),(70,'admin','2022-05-17 12:21:37.309000',_binary '\0',NULL,NULL,'cay-phat-tai-nui-dai-loc-2.png',18),(71,'admin','2022-05-17 12:21:37.316000',_binary '\0',NULL,NULL,'cay-phat-tai-nui-dai-loc-3.png',18),(72,'admin','2022-05-17 12:21:37.322000',_binary '\0',NULL,NULL,'cay-phat-tai-nui-dai-loc-4.png',18),(73,'admin','2022-05-17 12:24:56.032000',_binary '\0',NULL,NULL,'cay-truc-cay-noi-that-1.png',19),(74,'admin','2022-05-17 12:24:56.043000',_binary '\0',NULL,NULL,'cay-truc-cay-noi-that-2.png',19),(75,'admin','2022-05-17 12:24:56.048000',_binary '\0',NULL,NULL,'cay-truc-cay-noi-that-3.png',19),(76,'admin','2022-05-17 12:24:56.055000',_binary '\0',NULL,NULL,'cay-truc-cay-noi-that-4.png',19),(77,'admin','2022-05-17 12:26:58.841000',_binary '\0',NULL,NULL,'cay-ngan-hau-1.png',20),(78,'admin','2022-05-17 12:26:58.850000',_binary '\0',NULL,NULL,'cay-ngan-hau-2.png',20),(79,'admin','2022-05-17 12:26:58.857000',_binary '\0',NULL,NULL,'cay-ngan-hau-3.png',20),(80,'admin','2022-05-17 12:26:58.864000',_binary '\0',NULL,NULL,'cay-ngan-hau-4.png',20),(81,'admin','2022-05-17 12:30:04.160000',_binary '\0',NULL,NULL,'cay-hanh-phuc-don-than-de-ban-1.png',21),(82,'admin','2022-05-17 12:30:04.171000',_binary '\0',NULL,NULL,'cay-hanh-phuc-don-than-de-ban-2.png',21),(83,'admin','2022-05-17 12:30:04.177000',_binary '\0',NULL,NULL,'cay-hanh-phuc-don-than-de-ban-3.png',21),(84,'admin','2022-05-17 12:30:04.185000',_binary '\0',NULL,NULL,'cay-hanh-phuc-don-than-de-ban-4.png',21),(85,'admin','2022-05-17 12:32:06.906000',_binary '\0',NULL,NULL,'cay-luoi-ho-thuy-sinh-1.png',22),(86,'admin','2022-05-17 12:32:06.914000',_binary '\0',NULL,NULL,'cay-luoi-ho-thuy-sinh-2.png',22),(87,'admin','2022-05-17 12:32:06.920000',_binary '\0',NULL,NULL,'cay-luoi-ho-thuy-sinh-3.png',22),(88,'admin','2022-05-17 12:32:06.926000',_binary '\0',NULL,NULL,'cay-luoi-ho-thuy-sinh-4.png',22),(89,'admin','2022-05-17 12:34:13.011000',_binary '\0',NULL,NULL,'bang-singapore-nhieu-nhanh-1.png',23),(90,'admin','2022-05-17 12:34:13.020000',_binary '\0',NULL,NULL,'bang-singapore-nhieu-nhanh-2.png',23),(91,'admin','2022-05-17 12:34:13.027000',_binary '\0',NULL,NULL,'bang-singapore-nhieu-nhanh-3.png',23),(92,'admin','2022-05-17 12:34:13.032000',_binary '\0',NULL,NULL,'bang-singapore-nhieu-nhanh-4.png',23),(93,'admin','2022-05-17 12:36:41.643000',_binary '\0',NULL,NULL,'cay-kim-tien-bui-to-1.png',24),(94,'admin','2022-05-17 12:36:41.648000',_binary '\0',NULL,NULL,'cay-kim-tien-bui-to-2.png',24),(95,'admin','2022-05-17 12:36:41.655000',_binary '\0',NULL,NULL,'cay-kim-tien-bui-to-3.png',24),(96,'admin','2022-05-17 12:36:41.660000',_binary '\0',NULL,NULL,'cay-kim-tien-bui-to-4.png',24),(97,'admin','2022-05-17 12:38:54.495000',_binary '\0',NULL,NULL,'cay-bang-singapore-1m.png',25),(98,'admin','2022-05-17 12:38:54.501000',_binary '\0',NULL,NULL,'cay-bang-singapore-1m-1.png',25),(99,'admin','2022-05-17 12:38:54.510000',_binary '\0',NULL,NULL,'cay-bang-singapore-1m-2.png',25),(100,'admin','2022-05-17 12:38:54.514000',_binary '\0',NULL,NULL,'cay-bang-singapore-1m-3.png',25),(101,'admin','2022-05-17 12:41:06.631000',_binary '\0',NULL,NULL,'chau-cay-kim-tien-dep-mix-1.png',26),(102,'admin','2022-05-17 12:41:06.636000',_binary '\0',NULL,NULL,'chau-cay-kim-tien-dep-mix-2.png',26),(103,'admin','2022-05-17 12:41:06.642000',_binary '\0',NULL,NULL,'chau-cay-kim-tien-dep-mix-3.png',26),(104,'admin','2022-05-17 12:41:06.647000',_binary '\0',NULL,NULL,'chau-cay-kim-tien-dep-mix-4.png',26),(105,'admin','2022-05-17 12:44:09.579000',_binary '\0',NULL,NULL,'cay-mini-monstera-1.png',27),(106,'admin','2022-05-17 12:44:09.585000',_binary '\0',NULL,NULL,'cay-mini-monstera-2.png',27),(107,'admin','2022-05-17 12:44:09.591000',_binary '\0',NULL,NULL,'cay-mini-monstera-3.png',27),(108,'admin','2022-05-17 12:44:09.601000',_binary '\0',NULL,NULL,'cay-monstera-4.png',27),(109,'admin','2022-05-17 12:45:25.457000',_binary '\0',NULL,NULL,'cay-monstera-1.png',28),(110,'admin','2022-05-17 12:45:25.461000',_binary '\0',NULL,NULL,'cay-monstera-2.png',28),(111,'admin','2022-05-17 12:45:25.468000',_binary '\0',NULL,NULL,'cay-monstera-3.png',28),(112,'admin','2022-05-17 12:45:25.473000',_binary '\0',NULL,NULL,'cay-monstera-4.png',28),(113,'admin','2022-05-17 13:01:16.478000',_binary '\0',NULL,NULL,'cay-troc-bac-thuy-sinh-1.png',29),(114,'admin','2022-05-17 13:01:16.488000',_binary '\0',NULL,NULL,'cay-troc-bac-thuy-sinh-2.png',29),(115,'admin','2022-05-17 13:01:16.493000',_binary '\0',NULL,NULL,'cay-troc-bac-thuy-sinh-3.png',29),(116,'admin','2022-05-17 13:01:16.498000',_binary '\0',NULL,NULL,'cay-troc-bac-thuy-sinh-4.png',29),(117,'admin','2022-05-17 13:03:27.118000',_binary '\0',NULL,NULL,'la-cay-van-loc-1.png',30),(118,'admin','2022-05-17 13:03:27.126000',_binary '\0',NULL,NULL,'hoa-cay-van-loc-420x420.jpg',30),(119,'admin','2022-05-17 13:03:27.132000',_binary '\0',NULL,NULL,'cay-van-loc-thuy-sinh-420x420.jpg',30),(120,'admin','2022-05-17 13:03:27.139000',_binary '\0',NULL,NULL,'cay-van-loc-de-ban-420x420.jpg',30),(121,'admin','2022-05-17 13:05:10.110000',_binary '\0',NULL,NULL,'cay-sao-sang-420x420.jpg',31),(122,'admin','2022-05-17 13:05:10.117000',_binary '\0',NULL,NULL,'cay-sao-sang-trong-nuoc-420x420.jpg',31),(123,'admin','2022-05-17 13:05:10.122000',_binary '\0',NULL,NULL,'cay-sao-sang-thuy-sinh-420x420.jpg',31),(124,'admin','2022-05-17 13:05:10.127000',_binary '\0',NULL,NULL,'la-cay-sao-sang-420x420.jpg',31),(125,'admin','2022-05-17 13:07:29.489000',_binary '\0',NULL,NULL,'cay-trau-ba-la-lo-420x420.jpg',32),(126,'admin','2022-05-17 13:07:29.497000',_binary '\0',NULL,NULL,'cay-trau-ba-la-lo-de-ban-420x420.jpg',32),(127,'admin','2022-05-17 13:07:29.503000',_binary '\0',NULL,NULL,'cay-trau-ba-la-lo-leo-420x420.jpg',32),(128,'admin','2022-05-17 13:07:29.510000',_binary '\0',NULL,NULL,'la-cay-trau-ba-la-lo-420x420.jpg',32),(129,'admin','2022-05-17 13:09:17.474000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi-thuy-sinh-420x420.jpg',33),(130,'admin','2022-05-17 13:09:17.480000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi-thuy-sinh-de-ban-420x420.jpg',33),(131,'admin','2022-05-17 13:09:17.500000',_binary '\0',NULL,NULL,'ngu-gia-bi-thuy-sinh-420x420.jpg',33),(132,'admin','2022-05-17 13:09:17.511000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi-thuy-sinh-de-ban-420x420 (1).jpg',33),(133,'admin','2022-05-17 13:11:24.876000',_binary '\0',NULL,NULL,'cay-trau-ba-de-vuong-thuy-sinh-420x420.jpg',34),(134,'admin','2022-05-17 13:11:24.882000',_binary '\0',NULL,NULL,'cay-trau-ba-de-vuong-thuy-sinh-de-ban-420x420.jpg',34),(135,'admin','2022-05-17 13:11:24.887000',_binary '\0',NULL,NULL,'trau-ba-de-vuong-thuy-sinh-420x420 (1).jpg',34),(136,'admin','2022-05-17 13:11:24.892000',_binary '\0',NULL,NULL,'trau-ba-de-vuong-thuy-sinh-420x420.jpg',34),(137,'admin','2022-05-17 13:13:04.593000',_binary '\0',NULL,NULL,'cay-thuy-tung-1.png',35),(138,'admin','2022-05-17 13:13:04.599000',_binary '\0',NULL,NULL,'cay-thuy-tung-2.png',35),(139,'admin','2022-05-17 13:13:04.606000',_binary '\0',NULL,NULL,'cay-thuy-tung-3.png',35),(140,'admin','2022-05-17 13:13:04.610000',_binary '\0',NULL,NULL,'cay-thuy-tung-4.png',35),(141,'admin','2022-05-17 13:15:54.674000',_binary '\0',NULL,NULL,'cay-phat-tai-1.png',36),(142,'admin','2022-05-17 13:15:54.679000',_binary '\0',NULL,NULL,'cay-phat-tai-2.png',36),(143,'admin','2022-05-17 13:15:54.683000',_binary '\0',NULL,NULL,'cay-phat-tai-3.png',36),(144,'admin','2022-05-17 13:15:54.690000',_binary '\0',NULL,NULL,'cay-phat-tai-4.png',36),(145,'admin','2022-05-17 13:18:10.911000',_binary '\0',NULL,NULL,'sen-da-xanh-2-420x420.jpg',37),(146,'admin','2022-05-17 13:18:10.920000',_binary '\0',NULL,NULL,'sen-da-xanh-1-1-420x359.jpg',37),(147,'admin','2022-05-17 13:18:10.925000',_binary '\0',NULL,NULL,'sen-da-xanh-2-420x420 (1).jpg',37),(148,'admin','2022-05-17 13:18:10.928000',_binary '\0',NULL,NULL,'sen-da-xanh-1-1-420x359 (1).jpg',37),(149,'admin','2022-05-17 13:20:02.135000',_binary '\0',NULL,NULL,'sen-da-phat-ba-1-1-420x405.jpg',38),(150,'admin','2022-05-17 13:20:02.143000',_binary '\0',NULL,NULL,'sen-da-phat-ba-2-1-420x420.jpg',38),(151,'admin','2022-05-17 13:20:02.149000',_binary '\0',NULL,NULL,'sen-da-phat-ba-3-1-420x420.jpg',38),(152,'admin','2022-05-17 13:20:02.157000',_binary '\0',NULL,NULL,'sen-da-phat-ba-5-420x420.jpg',38),(153,'admin','2022-05-17 13:22:01.757000',_binary '\0',NULL,NULL,'sen-da-canh-buom-1.png',39),(154,'admin','2022-05-17 13:22:01.763000',_binary '\0',NULL,NULL,'sen-da-canh-buom-2.png',39),(155,'admin','2022-05-17 13:22:01.770000',_binary '\0',NULL,NULL,'sen-da-canh-buom-3.png',39),(156,'admin','2022-05-17 13:22:01.775000',_binary '\0',NULL,NULL,'sen-da-canh-buom-4.png',39),(157,'admin','2022-05-17 13:23:32.460000',_binary '\0',NULL,NULL,'sen-da-thach-ngoc-2-1-420x420.jpg',40),(158,'admin','2022-05-17 13:23:32.466000',_binary '\0',NULL,NULL,'sen-da-thach-ngoc-1-1-420x420.jpg',40),(159,'admin','2022-05-17 13:23:32.472000',_binary '\0',NULL,NULL,'sen-da-thach-ngoc-4-1-420x420.jpg',40),(160,'admin','2022-05-17 13:23:32.476000',_binary '\0',NULL,NULL,'sen-da-thach-ngoc-6.jpg',40),(161,'admin','2022-05-17 13:24:52.384000',_binary '\0',NULL,NULL,'sen-da-ong-dieu1-1-420x420.jpg',41),(162,'admin','2022-05-17 13:24:52.389000',_binary '\0',NULL,NULL,'sen-da-ong-dieu-1-420x420.jpg',41),(163,'admin','2022-05-17 13:24:52.392000',_binary '\0',NULL,NULL,'sen-da-ong-dieu-2-1-420x356.jpg',41),(164,'admin','2022-05-17 13:24:52.398000',_binary '\0',NULL,NULL,'sen-da-ong-dieu-3-1-420x420.jpg',41),(165,'admin','2022-05-17 13:26:35.198000',_binary '\0',NULL,NULL,'sen-da-hoa-cuc-7-420x420.jpg',42),(166,'admin','2022-05-17 13:26:35.205000',_binary '\0',NULL,NULL,'sen-da-hoa-cuc-1-1.jpg',42),(167,'admin','2022-05-17 13:26:35.212000',_binary '\0',NULL,NULL,'sen-da-hoa-cuc-2-1-420x380.jpg',42),(168,'admin','2022-05-17 13:26:35.218000',_binary '\0',NULL,NULL,'sen-da-hoa-cuc-4-1-420x420.jpg',42),(169,'admin','2022-05-17 13:28:27.983000',_binary '\0',NULL,NULL,'sen-da-bap-cai-2-1.jpg',43),(170,'admin','2022-05-17 13:28:27.988000',_binary '\0',NULL,NULL,'sen-da-bap-cai-3-1-420x360.jpg',43),(171,'admin','2022-05-17 13:28:27.992000',_binary '\0',NULL,NULL,'sen-da-bap-cai-7-360x420.jpg',43),(172,'admin','2022-05-17 13:28:28.001000',_binary '\0',NULL,NULL,'sen-da-bap-cai-1-1.jpg',43),(173,'admin','2022-05-17 13:29:50.934000',_binary '\0',NULL,NULL,'sen-da-bong-hong-trang-2-1-420x420.jpg',44),(174,'admin','2022-05-17 13:29:50.944000',_binary '\0',NULL,NULL,'sen-da-bong-hong-trang-1-1-420x420.jpg',44),(175,'admin','2022-05-17 13:29:50.948000',_binary '\0',NULL,NULL,'sen-da-bong-hong-trang-7-420x420.jpg',44),(176,'admin','2022-05-17 13:29:50.952000',_binary '\0',NULL,NULL,'sen-da-bong-hong-trang-1-1-420x420 (1).jpg',44),(177,'admin','2022-05-17 13:31:29.536000',_binary '\0',NULL,NULL,'sen-da-3-mau-1-420x420.jpg',45),(178,'admin','2022-05-17 13:31:29.542000',_binary '\0',NULL,NULL,'sen-da-3-mau-2-1-420x420.jpg',45),(179,'admin','2022-05-17 13:31:29.547000',_binary '\0',NULL,NULL,'sen-da-3-mau-3-1-420x420.jpg',45),(180,'admin','2022-05-17 13:31:29.551000',_binary '\0',NULL,NULL,'sen-da-3-mau-4-1.jpg',45),(181,'admin','2022-05-17 13:33:32.596000',_binary '\0',NULL,NULL,'sen-da-thai-1-420x420.jpg',46),(182,'admin','2022-05-17 13:33:32.603000',_binary '\0',NULL,NULL,'sen-da-thai-2-1-420x420.jpg',46),(183,'admin','2022-05-17 13:33:32.608000',_binary '\0',NULL,NULL,'sen-da-thai-4-1-420x420.jpg',46),(184,'admin','2022-05-17 13:33:32.616000',_binary '\0',NULL,NULL,'sen-da-thai1-1-420x420.jpg',46),(185,'admin','2022-05-17 13:35:28.541000',_binary '\0',NULL,NULL,'DSC_0072-1-420x420.jpg',47),(186,'admin','2022-05-17 13:35:28.548000',_binary '\0',NULL,NULL,'sen-da-dat-6-1-420x420.jpg',47),(187,'admin','2022-05-17 13:35:28.553000',_binary '\0',NULL,NULL,'sen-da-dat-hoa-1-420x330.jpg',47),(188,'admin','2022-05-17 13:35:28.560000',_binary '\0',NULL,NULL,'cay-sen-da-xanh-1-1-420x413.jpg',47),(189,'admin','2022-05-17 13:37:15.804000',_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-1.png',48),(190,'admin','2022-05-17 13:37:15.807000',_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-2.png',48),(191,'admin','2022-05-17 13:37:15.812000',_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-3.png',48),(192,'admin','2022-05-17 13:37:15.818000',_binary '\0',NULL,NULL,'tieu-canh-sen-da-dien-vien-4.png',48),(193,'admin','2022-05-17 13:38:58.069000',_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-1.png',49),(194,'admin','2022-05-17 13:38:58.073000',_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-2.png',49),(195,'admin','2022-05-17 13:38:58.077000',_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-1.png',49),(196,'admin','2022-05-17 13:38:58.087000',_binary '\0',NULL,NULL,'tieu-canh-mot-vo-hai-con-2.png',49),(197,'admin','2022-05-17 13:41:57.919000',_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-1.png',50),(198,'admin','2022-05-17 13:41:57.927000',_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-2.png',50),(199,'admin','2022-05-17 13:41:57.931000',_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-3.png',50),(200,'admin','2022-05-17 13:41:57.935000',_binary '\0',NULL,NULL,'terrarium-bach-nien-giai-lao-4.png',50),(201,'admin','2022-05-17 13:43:24.851000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-1.png',51),(202,'admin','2022-05-17 13:43:24.856000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-2.png',51),(203,'admin','2022-05-17 13:43:24.860000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-3.png',51),(204,'admin','2022-05-17 13:43:24.867000',_binary '\0',NULL,NULL,'terrarium-ngoi-nha-nho-tren-thao-nguyen-2.png',51),(205,'admin','2022-05-17 13:45:24.637000',_binary '\0',NULL,NULL,'cay-cam-nhung-xanh-1-420x420.jpg',52),(206,'admin','2022-05-17 13:45:24.642000',_binary '\0',NULL,NULL,'cay-cam-nhung-cac-loai-420x420.jpg',52),(207,'admin','2022-05-17 13:45:24.648000',_binary '\0',NULL,NULL,'cay-cam-nhung1-1-420x420.jpg',52),(208,'admin','2022-05-17 13:45:24.652000',_binary '\0',NULL,NULL,'cay-cam-nhung-dep-420x420.jpg',52),(209,'admin','2022-05-17 13:47:36.803000',_binary '\0',NULL,NULL,'cau-tieu-tram-1-420x420.jpg',53),(210,'admin','2022-05-17 13:47:36.815000',_binary '\0',NULL,NULL,'cau-tieu-tram-420x420.jpg',53),(211,'admin','2022-05-17 13:47:36.818000',_binary '\0',NULL,NULL,'cay-cau-tieu-tram-1-420x420.jpg',53),(212,'admin','2022-05-17 13:47:36.822000',_binary '\0',NULL,NULL,'cay-cau-tieu-tram-1-420x420 (1).jpg',53),(213,'admin','2022-05-17 14:52:08.893000',_binary '\0',NULL,NULL,'co-dong-tien-420x420.jpg',54),(214,'admin','2022-05-17 14:52:08.908000',_binary '\0',NULL,NULL,'co-dong-tien-dep-420x420.jpg',54),(215,'admin','2022-05-17 14:52:08.918000',_binary '\0',NULL,NULL,'co-dong-tien-thuy-sinh-420x420.jpg',54),(216,'admin','2022-05-17 14:52:08.926000',_binary '\0',NULL,NULL,'la-co-dong-tien-420x420.jpg',54),(217,'admin','2022-05-17 15:40:25.625000',_binary '\0',NULL,NULL,'cay-phat-tai-bup-sen-1-1.jpg',55),(218,'admin','2022-05-17 15:40:25.635000',_binary '\0',NULL,NULL,'cay-phat-tai-bup-sen-2-1-420x420.jpg',55),(219,'admin','2022-05-17 15:40:25.642000',_binary '\0',NULL,NULL,'cay-phat-tai-bup-sen-3-420x420.jpg',55),(220,'admin','2022-05-17 15:40:25.648000',_binary '\0',NULL,NULL,'cay-phat-tai-bup-sen-2-1-420x420 (1).jpg',55),(221,'admin','2022-05-17 15:44:35.548000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi1-1-420x420.jpg',56),(222,'admin','2022-05-17 15:44:35.559000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi-de-ban-1-420x420.jpg',56),(223,'admin','2022-05-17 15:44:35.565000',_binary '\0',NULL,NULL,'cay-ngu-gia-bi-to-1-420x420.jpg',56),(224,'admin','2022-05-17 15:44:35.572000',_binary '\0',NULL,NULL,'ngu-gia-bi-1-420x420.jpg',56),(225,'admin','2022-05-17 15:46:04.958000',_binary '\0',NULL,NULL,'cay-trau-ba-thuy-sinh-1-420x402.jpg',57),(226,'admin','2022-05-17 15:46:04.964000',_binary '\0',NULL,NULL,'cay-trau-ba-phong-thuy.jpg',57),(227,'admin','2022-05-17 15:46:04.969000',_binary '\0',NULL,NULL,'cay-trau-ba-thuy-sinh-420x420.jpg',57),(228,'admin','2022-05-17 15:46:04.974000',_binary '\0',NULL,NULL,'cay-trau-ba-thuy-sinh-de-ban-420x420.jpg',57),(229,'admin','2022-05-17 15:47:53.799000',_binary '\0',NULL,NULL,'cay-trau-ba-cot1-1-420x420.jpg',58),(230,'admin','2022-05-17 15:47:53.806000',_binary '\0',NULL,NULL,'cay-trau-ba-cot-420x420.jpg',58),(231,'admin','2022-05-17 15:47:53.811000',_binary '\0',NULL,NULL,'cay-trau-ba-cot-dep-1-420x420.jpg',58),(232,'admin','2022-05-17 15:47:53.817000',_binary '\0',NULL,NULL,'cay-trau-ba-cot-dep-420x420.jpg',58),(233,'admin','2022-05-17 15:52:28.332000',_binary '\0',NULL,NULL,'cay-phu-quy-2-1-420x420.jpg',59),(234,'admin','2022-05-17 15:52:28.340000',_binary '\0',NULL,NULL,'cay-phu-quy-1-1-420x420.jpg',59),(235,'admin','2022-05-17 15:52:28.347000',_binary '\0',NULL,NULL,'cay-phu-quy-3-1-420x420.jpg',59),(236,'admin','2022-05-17 15:52:28.354000',_binary '\0',NULL,NULL,'cay-phu-quy-4-420x420.jpg',59),(237,'admin','2022-05-17 15:55:00.985000',_binary '\0',NULL,NULL,'van-nien-thanh-2-1-420x420.jpg',60),(238,'admin','2022-05-17 15:55:00.990000',_binary '\0',NULL,NULL,'van-nien-thanh-5-1-420x420.jpg',60),(239,'admin','2022-05-17 15:55:00.999000',_binary '\0',NULL,NULL,'hoa-van-nien-thanh-1-420x420.jpg',60),(240,'admin','2022-05-17 15:55:01.003000',_binary '\0',NULL,NULL,'van-nien-thanh-6-420x420.jpg',60),(241,'admin','2022-05-17 15:58:05.152000',_binary '\0',NULL,NULL,'cay-ngoc-ngan-phong-thuy-420x420.jpg',61),(242,'admin','2022-05-17 15:58:05.160000',_binary '\0',NULL,NULL,'cay-ngoc-ngan-2-1-420x420.jpg',61),(243,'admin','2022-05-17 15:58:05.166000',_binary '\0',NULL,NULL,'cay-ngoc-ngan-5-420x420.jpg',61),(244,'admin','2022-05-17 15:58:05.171000',_binary '\0',NULL,NULL,'ngoc-ngan_680x0-1-420x420.jpg',61),(245,'admin','2022-05-17 16:00:08.791000',_binary '\0',NULL,NULL,'cay-lan-y-thuy-sinh-420x420.jpg',62),(246,'admin','2022-05-17 16:00:08.797000',_binary '\0',NULL,NULL,'cay-lan-y-thuy-sinh-dep-420x420.jpg',62),(247,'admin','2022-05-17 16:00:08.805000',_binary '\0',NULL,NULL,'lan-y-thuy-sinh-3-420x420.jpg',62),(248,'admin','2022-05-17 16:00:08.813000',_binary '\0',NULL,NULL,'lan-y-thuy-sinh-2-1-420x419.jpg',62),(249,'admin','2022-05-17 16:01:35.567000',_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-1.png',63),(250,'admin','2022-05-17 16:01:35.575000',_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-2.png',63),(251,'admin','2022-05-17 16:01:35.582000',_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-3.png',63),(252,'admin','2022-05-17 16:01:35.590000',_binary '\0',NULL,NULL,'terrarium-10-cay-sen-da-2.png',63),(253,'admin','2022-05-17 16:03:20.793000',_binary '\0',NULL,NULL,'cay-phu-quy-thuy-sinh-420x420.jpg',64),(254,'admin','2022-05-17 16:03:20.799000',_binary '\0',NULL,NULL,'cay-phu-quy-thuy-sinh-2-420x420.jpg',64),(255,'admin','2022-05-17 16:03:20.804000',_binary '\0',NULL,NULL,'cay-phu-quy-thuy-sinh-dep-420x420.jpg',64),(256,'admin','2022-05-17 16:03:20.810000',_binary '\0',NULL,NULL,'cay-phu-quy-thuy-sinh-2-420x420.jpg',64),(257,'admin','2022-05-17 16:05:04.905000',_binary '\0',NULL,NULL,'cay-hong-mon-dep-420x420.jpg',65),(258,'admin','2022-05-17 16:05:04.918000',_binary '\0',NULL,NULL,'cay-hong-mon-1-1-420x420.jpg',65),(259,'admin','2022-05-17 16:05:04.924000',_binary '\0',NULL,NULL,'cay-hong-mon-phu-hop-voi-cung-song-tu-1-420x420.jpg',65),(260,'admin','2022-05-17 16:05:04.930000',_binary '\0',NULL,NULL,'cay-hong-mon-do-1-420x420.jpg',65),(261,'admin','2022-05-17 16:06:39.490000',_binary '\0',NULL,NULL,'cay-tung-bong-lai-1-420x420.jpg',66),(262,'admin','2022-05-17 16:06:39.496000',_binary '\0',NULL,NULL,'cay-tung-bong-lai-to-420x420.jpg',66),(263,'admin','2022-05-17 16:06:39.504000',_binary '\0',NULL,NULL,'tung-bong-lai-1-420x420.jpg',66),(264,'admin','2022-05-17 16:06:39.509000',_binary '\0',NULL,NULL,'tung-bong-lai-420x420.jpg',66),(265,'admin','2022-05-17 16:09:03.789000',_binary '\0',NULL,NULL,'cay-kim-tien-chau-men-mat-420x420.jpg',67),(266,'admin','2022-05-17 16:09:03.799000',_binary '\0',NULL,NULL,'cay-kim-tien-dep-de-van-phong-420x420.jpg',67),(267,'admin','2022-05-17 16:09:03.804000',_binary '\0',NULL,NULL,'cay-kim-tien-loai-nho.jpg',67),(268,'admin','2022-05-17 16:09:03.810000',_binary '\0',NULL,NULL,'chau-kim-tien-nho-420x420.jpg',67),(269,'admin','2022-05-17 16:12:23.429000',_binary '\0',NULL,NULL,'tong-hop-cay-canh-phong-thuy-trong-phong-khach-420x420.jpg',68),(270,'admin','2022-05-17 16:12:23.436000',_binary '\0',NULL,NULL,'cay-kim-tien-to-420x420.jpg',68),(271,'admin','2022-05-17 16:12:23.440000',_binary '\0',NULL,NULL,'cay-kim-tien-loai-to-420x420.jpg',68),(272,'admin','2022-05-17 16:12:23.444000',_binary '\0',NULL,NULL,'cay-kim-tien-de-van-phong-1-420x420.jpg',68),(273,'admin','2022-05-17 16:14:13.154000',_binary '\0',NULL,NULL,'cay-trang-nguyen-1-420x420.jpg',69),(274,'admin','2022-05-17 16:14:13.159000',_binary '\0',NULL,NULL,'cay-trang-nguyen-dep-420x420.jpg',69),(275,'admin','2022-05-17 16:14:13.163000',_binary '\0',NULL,NULL,'cay-trang-nguyen-do-1-420x420.jpg',69),(276,'admin','2022-05-17 16:14:13.168000',_binary '\0',NULL,NULL,'hoa-cay-trang-nguyen-1-420x420.jpg',69),(277,'admin','2022-05-17 16:15:38.608000',_binary '\0',NULL,NULL,'cay-tung-thom-1-1-420x420.jpg',70),(278,'admin','2022-05-17 16:15:38.616000',_binary '\0',NULL,NULL,'la-cay-tung-thom-1-420x420.jpg',70),(279,'admin','2022-05-17 16:15:38.622000',_binary '\0',NULL,NULL,'cay-tung-thom-noel-1-420x420.jpg',70),(280,'admin','2022-05-17 16:15:38.626000',_binary '\0',NULL,NULL,'cay-tung-thom-re-1-420x420.jpg',70),(281,'admin','2022-05-17 16:17:37.032000',_binary '\0',NULL,NULL,'cay-chuoi-re-quat-de-trong-nha-420x420.jpg',71),(282,'admin','2022-05-17 16:17:37.038000',_binary '\0',NULL,NULL,'cay-chuoi-re-quat-de-phong-khach-420x420.jpg',71),(283,'admin','2022-05-17 16:17:37.045000',_binary '\0',NULL,NULL,'cay-chuoi-re-quat-dep-420x420.jpg',71),(284,'admin','2022-05-17 16:17:37.049000',_binary '\0',NULL,NULL,'cay-chuoi-re-quat-noi-that-420x420.jpg',71),(285,'admin','2022-05-17 16:19:14.430000',_binary '\0',NULL,NULL,'cay-bach-ma-hoang-tu-420x420.jpg',72),(286,'admin','2022-05-17 16:19:14.436000',_binary '\0',NULL,NULL,'y-nghia-phong-thuy-cay-bach-ma-420x389.jpg',72),(287,'admin','2022-05-17 16:19:14.443000',_binary '\0',NULL,NULL,'cham-soc-cay-bach-ma-hoang-tu-420x420.jpg',72),(288,'admin','2022-05-17 16:19:14.448000',_binary '\0',NULL,NULL,'y-nghia-phong-thuy-cay-bach-ma-420x389.jpg',72),(289,'admin','2022-05-17 16:20:27.085000',_binary '\0',NULL,NULL,'cay-duoi-cong-tao-xanh-420x420 (1).jpg',73),(290,'admin','2022-05-17 16:20:27.090000',_binary '\0',NULL,NULL,'cay-duoi-cong-tao-xanh-420x420.jpg',73),(291,'admin','2022-05-17 16:20:27.098000',_binary '\0',NULL,NULL,'cay-duoi-cong-tao-xanh-dep-420x420.jpg',73),(292,'admin','2022-05-17 16:20:27.102000',_binary '\0',NULL,NULL,'cay-duoi-cong-tao-xanh-420x420.jpg',73),(293,'admin','2022-05-17 16:26:06.187000',_binary '\0',NULL,NULL,'sen-da-ham-ca-sau-3-1-420x420.jpg',74),(294,'admin','2022-05-17 16:26:06.192000',_binary '\0',NULL,NULL,'sen-da-ham-ca-sau-2-1-420x420.jpg',74),(295,'admin','2022-05-17 16:26:06.198000',_binary '\0',NULL,NULL,'sen-da-ham-ca-sau-5-420x420.jpg',74),(296,'admin','2022-05-17 16:26:06.207000',_binary '\0',NULL,NULL,'sen-da-ham-ca-sau-2-1-420x420.jpg',74),(297,'admin','2022-05-19 22:22:23.708000',_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-1.png',75),(298,'admin','2022-05-19 22:22:23.722000',_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-2.png',75),(299,'admin','2022-05-19 22:22:23.726000',_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-3.png',75),(300,'admin','2022-05-19 22:22:23.730000',_binary '\0',NULL,NULL,'sen-da-mong-rong-vien-trang-2.png',75);
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
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(100) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `deleted_flag` bit(1) DEFAULT NULL,
  `updated_by` varchar(100) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `name_supplier` varchar(255) DEFAULT NULL,
  `tax_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k0ee7t6b4ldf66vgoeoq83jhr` (`cellphone`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'admin','2022-05-16 22:06:26.102000',_binary '\0',NULL,NULL,'Hải Châu','0511399888','Công ty TNHH Toàn Pháp','568941'),(2,'admin','2022-05-16 22:07:18.771000',_binary '\0',NULL,NULL,'Liên Chiều','0512399888','Công ty Cổ phần Đông Du','456789'),(3,'admin','2022-05-16 22:07:18.771000',_binary '\0','admin','2022-05-16 22:13:02.930000','Thuận Hòa','0256369854','Ông Nguyễn Văn Tiến','325667'),(4,'admin','2022-05-16 22:08:31.642000',_binary '\0',NULL,NULL,'Nam Châu','0255114999','Công ty Cổ phần Toàn Cầu Xanh','326541'),(5,'admin','2022-05-16 22:09:03.394000',_binary '\0',NULL,NULL,'Thanh Khê','0231666777','Công ty TNHH AMA','985362'),(6,'admin','2022-05-16 22:09:31.627000',_binary '\0',NULL,NULL,'Bắc Ninh','0258416937','Bà Nguyễn Thị Hà','449130'),(7,'admin','2022-05-16 22:10:10.977000',_binary '\0',NULL,NULL,'Bắc Giang','0358668777','Công ty TNHH Phan Thành','241569'),(8,'admin','2022-05-16 22:10:55.993000',_binary '\0',NULL,NULL,'Cẩm Khê','0125462115','Ông Nguyễn Văn B','225741'),(9,'admin','2022-05-16 22:11:31.290000',_binary '\0',NULL,NULL,'Hà Nội','0336777888','Tập đoàn Vườn Cây Xanh','251478'),(10,'admin','2022-05-16 22:12:08.424000',_binary '\0',NULL,NULL,'Hải Phòng','0123456999','Công ty Cổ phần Xanh Sạch Đẹp','237814'),(11,'admin','2022-05-16 22:13:32.957000',_binary '','admin','2022-05-16 22:13:36.799000','Hải Phòng','0123456998','Demotodelete','145698'),(12,'admin','2022-05-19 22:32:07.483000',_binary '\0','admin','2022-05-19 22:33:40.688000','Hải Phòng','0326154999','Công ty TNHH Xanh La Cay','125369');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
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
  `cellphone` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(64) NOT NULL,
  `reset_password_token` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9ej2cttx8iuwd51taump23b45` (`cellphone`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_f5fgr310aucvqex8djp780h1x` (`reset_password_token`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'datnv','2022-05-16 14:26:16.732000',_binary '\0','datnv','2022-05-19 22:55:03.447000','anti bonk helmet.jpg','0868776094','datnv04112000@gmail.com','Nguyễn Văn','Nam','Đạt dz','$2a$10$9PUMm1TCqIE6kzx7o1Rma.dTxN6dzTppdh/avsLTk7zAGzOe4/Cj6',NULL,'datnv'),(3,'admin','2022-05-16 14:26:16.732000',_binary '\0','admin','2022-05-19 22:31:16.993000','anti bonk helmet.jpg','0265398147','admin@gmail.com','Nguyễn Văn','Nam','Đạt ','$2a$10$9G5/jHpQZXM4/3qKnbF85eZ4S56WLUebjF0Ui6zWCPoZYc2oOMJ6K',NULL,'admin'),(4,'somebody','2022-05-16 14:29:23.402000',_binary '\0','admin','2022-05-16 15:44:50.071000','83314137_136847144454401_8153532323787177984_n.jpg','0256398147','hiimyi2911@gmail.com','John','Nam','Son','$2a$10$tDlrZzM.HcxJePaCEfOU4uMdJNIKE8EPeDnC854NXAN5/RYE6EDpC',NULL,'somebody'),(5,'user01','2022-05-16 14:30:28.500000',_binary '','admin','2022-05-16 15:26:35.781000','avatar-default.png','0123456789','user01@gmail.com','Nguyễn','Nữ','Hà','$2a$10$f/AUZdTIae0q2tlUa7NF8ueAQQW7ZpaXW0AA.KAf1TJNwE1Wrdr9K',NULL,'user01'),(6,'user02','2022-05-16 14:31:09.655000',_binary '','admin','2022-05-16 23:30:45.624000','83770032_653681048702750_2000754087561265152_n.jpg','0124536798','huenguyen@gmail.com','Nguyễn','Nữ','Huệ aaaaa','$2a$10$fajuYNbzCOtZnCZN5T8I/uYFnrre.psVr5R8fqMDe3kaeURMAu5l6',NULL,'user02'),(7,'hieunguyen','2022-05-16 14:31:50.498000',_binary '\0',NULL,NULL,'avatar-default.png','0236519478','hieunguyen@gmail.com','Nguyễn','Nam','Hiếu','$2a$10$VNf53KRPMPsUYjHsSM3I.Opxf4f4LCVslaSrb75dCoLCx5SOKGpbm',NULL,'hieunguyen'),(8,'tiennguyen','2022-05-16 14:32:22.172000',_binary '\0',NULL,NULL,'avatar-default.png','0231589647','tiennguyen01@gmail.com','Nguyễn','Nam','Tiến','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'tiennguyen'),(13,'admin','2022-05-16 14:45:55.046000',_binary '\0',NULL,NULL,'avatar-default.png','0214536897','manhnguyen@gmail.com','Nguyen','Nam','Manh','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'manhnguyen'),(15,'admin','2022-05-16 14:45:55.046000',_binary '\0',NULL,NULL,'avatar-default.png','0255841367','hiepngo@gmail.com','Ngô','Nam','Hiệp','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'hiepngo'),(16,'admin','2022-05-16 14:45:55.046000',_binary '\0',NULL,NULL,'+1.PNG','033625841','duongtran@gmail.com','Trần','Nam','Dương','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'duongtran'),(17,'admin','2022-05-16 14:45:55.046000',_binary '\0',NULL,NULL,'320px-Heart_corazón.svg.png','0258874166','hoangnguyen@gmail.com','Nguyễn','Nữ','Hoàng','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'hoangnguyen'),(18,'admin','2022-05-16 14:45:55.046000',_binary '\0','admin','2022-05-19 21:55:54.872000','avatar-default.png','0114477856','haiquyen@gmail.com','Quyền','Nam','Hải','$2a$10$V7MZR9LQyxcuwCq0XEPgve7PvtAIgzRNPpEGFtnq3K4Wzbj1oDgBW',NULL,'haiquyen'),(19,'admin','2022-05-16 15:20:03.633000',_binary '\0','admin','2022-05-19 21:55:24.906000','avatar-default.png','0236511474','duyle@gmail.com','Lê Đức','Nam','Duy','$2a$10$pcaemgVEKZLlAhggXuJV4eZmW49hlsKHrL9eTZCUog5F/4D2TSnPe',NULL,'leduy'),(20,'admin','2022-05-16 15:24:13.275000',_binary '\0','admin','2022-05-19 22:13:33.028000','anti bonk helmet.jpg','0326584179','hienha@gmailcom','Hà','Nam','Hiển','$2a$10$eo.Ot7p9G481jzs89nj2..yVVFWKOyn2LtTWvTGqRv0re8gMY2Z0.',NULL,'hahien'),(21,'admin','2022-05-19 21:57:21.343000',_binary '\0',NULL,NULL,'khai-niem-cong-ty-tnhh-1-thanh-vien.jpg','0132645798','hanguyen@gmail.com','Nguyễn Thị','Nữ','Hà','$2a$10$CmTmTyDWa4a.D4ToitFbM.StnGHCjdcu./CTXhJXBK0itHU9rlVFS',NULL,'hanguyen');
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
INSERT INTO `users_roles` VALUES (4,2),(5,2),(6,2),(7,2),(8,2),(13,2),(15,2),(16,2),(17,2),(18,2),(19,2),(21,2),(20,2),(3,1);
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
  `end_time` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `percent_value` int NOT NULL,
  `quantity` int NOT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `times_of_use` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `up_to_value` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voucher`
--

LOCK TABLES `voucher` WRITE;
/*!40000 ALTER TABLE `voucher` DISABLE KEYS */;
INSERT INTO `voucher` VALUES (1,'admin','2022-05-19 22:52:31.412000',_binary '\0',NULL,NULL,100000,'GIAMGIA6THANG6','2022-06-06T22:52','khai-niem-cong-ty-tnhh-1-thanh-vien.jpg',5,10,'2022-06-05T12:00',1,'Giảm 5% tối đa 10000 cho đơn hàng từ 100000 trở lên',10000);
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

-- Dump completed on 2022-05-20  0:10:30
