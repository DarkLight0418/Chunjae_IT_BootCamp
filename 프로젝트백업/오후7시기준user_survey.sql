-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.11.8-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- newbiehealth 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `newbiehealth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `newbiehealth`;

-- 테이블 newbiehealth.user_survey 구조 내보내기
CREATE TABLE IF NOT EXISTS `user_survey` (
  `user_key` varchar(20) NOT NULL,
  `goal_weight` float NOT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `goal_daily_calories` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_key`),
  CONSTRAINT `user_survey_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 newbiehealth.user_survey:~2 rows (대략적) 내보내기
INSERT INTO `user_survey` (`user_key`, `goal_weight`, `created_at`, `goal_daily_calories`) VALUES
	('U001', 65, '2025-08-03 08:20:57', 400),
	('U002', 324, '2025-08-03 08:22:04', NULL);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
