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
CREATE DATABASE IF NOT EXISTS `newbiehealth_update` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `newbiehealth_update`;

-- 테이블 newbiehealth.statistics 구조 내보내기
CREATE TABLE IF NOT EXISTS `statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_key` varchar(20) NOT NULL,
  `date` date NOT NULL,
  `day_of_week` enum('Mon','Tue','Wed','Thu','Fri','Sat','Sun') NOT NULL,
  `workout_count` int(11) DEFAULT 0,
  `goal_achievement_rate` tinyint(3) unsigned DEFAULT NULL CHECK (`goal_achievement_rate` <= 100),
  `calories_burned` int(10) unsigned DEFAULT NULL,
  `muscle_strength` int(11) DEFAULT NULL,
  `body_fat_before` decimal(5,1) DEFAULT NULL,
  `body_fat_after` decimal(5,1) DEFAULT NULL,
  `muscle_mass_before` decimal(5,1) DEFAULT NULL,
  `muscle_mass_after` decimal(5,1) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `daily_calorie_goal` int(11) DEFAULT 0 COMMENT '해당 날짜의 목표 칼로리(kcal)',
  `activity_minutes` int(11) DEFAULT 0 COMMENT '운동 시간 (분 단위)',
  `dominant_equipment` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '기구별 사용 횟수 및 시간 요약' CHECK (json_valid(`dominant_equipment`)),
  `dominant_equipment_text` text DEFAULT NULL COMMENT '기구별 사용 정보 (JSON string)',
  PRIMARY KEY (`id`),
  KEY `user_key` (`user_key`),
  CONSTRAINT `statistics_ibfk_1` FOREIGN KEY (`user_key`) REFERENCES `user` (`user_key`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 newbiehealth.statistics:~62 rows (대략적) 내보내기
INSERT INTO `statistics` (`id`, `user_key`, `date`, `day_of_week`, `workout_count`, `goal_achievement_rate`, `calories_burned`, `muscle_strength`, `body_fat_before`, `body_fat_after`, `muscle_mass_before`, `muscle_mass_after`, `created_at`, `daily_calorie_goal`, `activity_minutes`, `dominant_equipment`, `dominant_equipment_text`) VALUES
	(46, 'U001', '2025-07-01', 'Tue', 0, 62, 247, 234, 25.0, 24.5, 29.5, 29.9, '2025-06-30 15:00:00', 400, 82, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 41}, "BARBEL": {"count": 3, "minutes": 41}}'),
	(47, 'U001', '2025-07-02', 'Wed', 0, 66, 266, 253, 25.1, 24.7, 30.9, 31.2, '2025-07-01 15:00:00', 400, 57, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 14}, "MACHINE": {"count": 3, "minutes": 43}}'),
	(48, 'U001', '2025-07-03', 'Thu', 0, 66, 262, 222, 23.0, 22.8, 29.2, 29.4, '2025-07-02 15:00:00', 400, 70, NULL, '{"BODYWEIGHT": {"count": 2, "minutes": 28}, "CABLE": {"count": 3, "minutes": 42}}'),
	(49, 'U001', '2025-07-04', 'Fri', 0, 100, 420, 226, 24.4, 24.2, 28.9, 29.2, '2025-07-03 15:00:00', 400, 78, NULL, '{"BARBEL": {"count": 1, "minutes": 78}}'),
	(50, 'U001', '2025-07-05', 'Sat', 0, 66, 262, 231, 25.0, 24.5, 29.1, 29.6, '2025-07-04 15:00:00', 400, 81, NULL, '{"CABLE": {"count": 3, "minutes": 81}}'),
	(51, 'U001', '2025-07-06', 'Sun', 0, 100, 402, 224, 24.9, 24.6, 28.9, 29.1, '2025-07-05 15:00:00', 400, 62, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 62}}'),
	(52, 'U001', '2025-07-07', 'Mon', 0, 69, 275, 225, 23.8, 23.7, 29.9, 30.2, '2025-07-06 15:00:00', 400, 41, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 18}, "MACHINE": {"count": 1, "minutes": 6}, "CABLE": {"count": 3, "minutes": 17}}'),
	(53, 'U001', '2025-07-08', 'Tue', 0, 100, 417, 242, 22.6, 22.2, 28.4, 28.8, '2025-07-07 15:00:00', 400, 69, NULL, '{"CABLE": {"count": 1, "minutes": 17}, "MACHINE": {"count": 1, "minutes": 17}, "BODYWEIGHT": {"count": 2, "minutes": 35}}'),
	(54, 'U001', '2025-07-09', 'Wed', 0, 100, 511, 242, 23.7, 23.4, 28.5, 28.7, '2025-07-08 15:00:00', 400, 73, NULL, '{"BARBEL": {"count": 2, "minutes": 36}, "BODYWEIGHT": {"count": 2, "minutes": 37}}'),
	(55, 'U001', '2025-07-10', 'Thu', 0, 31, 125, 260, 24.2, 24.0, 31.0, 31.4, '2025-07-09 15:00:00', 400, 31, NULL, '{"CABLE": {"count": 3, "minutes": 31}}'),
	(56, 'U001', '2025-07-11', 'Fri', 0, 68, 272, 235, 24.6, 24.5, 29.0, 29.4, '2025-07-10 15:00:00', 400, 45, NULL, '{"BARBEL": {"count": 2, "minutes": 22}, "CABLE": {"count": 2, "minutes": 23}}'),
	(57, 'U001', '2025-07-12', 'Sat', 0, 90, 358, 253, 22.9, 22.5, 29.9, 30.0, '2025-07-11 15:00:00', 400, 85, NULL, '{"CABLE": {"count": 2, "minutes": 34}, "BODYWEIGHT": {"count": 3, "minutes": 51}}'),
	(58, 'U001', '2025-07-13', 'Sun', 0, 100, 406, 223, 23.8, 23.4, 30.8, 31.3, '2025-07-12 15:00:00', 400, 68, NULL, '{"CABLE": {"count": 2, "minutes": 27}, "MACHINE": {"count": 1, "minutes": 14}, "BODYWEIGHT": {"count": 2, "minutes": 27}}'),
	(59, 'U001', '2025-07-14', 'Mon', 0, 100, 403, 239, 24.5, 24.3, 29.8, 30.2, '2025-07-13 15:00:00', 400, 73, NULL, '{"BARBEL": {"count": 3, "minutes": 31}, "CABLE": {"count": 1, "minutes": 10}, "MACHINE": {"count": 3, "minutes": 32}}'),
	(60, 'U001', '2025-07-15', 'Tue', 0, 91, 365, 248, 24.0, 23.7, 30.2, 30.6, '2025-07-14 15:00:00', 400, 63, NULL, '{"CABLE": {"count": 1, "minutes": 16}, "MACHINE": {"count": 2, "minutes": 32}, "BARBEL": {"count": 1, "minutes": 15}}'),
	(61, 'U001', '2025-07-16', 'Wed', 0, 35, 140, 248, 23.1, 22.8, 28.8, 29.2, '2025-07-15 15:00:00', 400, 34, NULL, '{"BARBEL": {"count": 2, "minutes": 10}, "MACHINE": {"count": 3, "minutes": 15}, "CABLE": {"count": 2, "minutes": 9}}'),
	(62, 'U001', '2025-07-17', 'Thu', 0, 57, 228, 239, 23.4, 23.1, 29.6, 30.1, '2025-07-16 15:00:00', 400, 53, NULL, '{"BARBEL": {"count": 2, "minutes": 53}}'),
	(63, 'U001', '2025-07-18', 'Fri', 0, 79, 317, 224, 24.7, 24.4, 28.8, 29.3, '2025-07-17 15:00:00', 400, 80, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 80}}'),
	(64, 'U001', '2025-07-19', 'Sat', 0, 68, 272, 247, 23.7, 23.5, 29.9, 30.1, '2025-07-18 15:00:00', 400, 41, NULL, '{"CABLE": {"count": 2, "minutes": 16}, "BARBEL": {"count": 3, "minutes": 25}}'),
	(65, 'U001', '2025-07-20', 'Sun', 0, 100, 410, 224, 22.5, 22.4, 30.1, 30.5, '2025-07-19 15:00:00', 400, 69, NULL, '{"BODYWEIGHT": {"count": 2, "minutes": 23}, "CABLE": {"count": 1, "minutes": 12}, "BARBEL": {"count": 3, "minutes": 34}}'),
	(66, 'U001', '2025-07-21', 'Mon', 0, 100, 593, 255, 23.7, 23.4, 30.1, 30.4, '2025-07-20 15:00:00', 400, 87, NULL, '{"BARBEL": {"count": 1, "minutes": 87}}'),
	(67, 'U001', '2025-07-22', 'Tue', 0, 41, 164, 233, 22.4, 22.0, 29.1, 29.5, '2025-07-21 15:00:00', 400, 50, NULL, '{"CABLE": {"count": 1, "minutes": 50}}'),
	(68, 'U001', '2025-07-23', 'Wed', 0, 35, 139, 250, 24.5, 24.1, 29.0, 29.2, '2025-07-22 15:00:00', 400, 31, NULL, '{"MACHINE": {"count": 2, "minutes": 10}, "BODYWEIGHT": {"count": 1, "minutes": 5}, "CABLE": {"count": 3, "minutes": 16}}'),
	(69, 'U001', '2025-07-24', 'Thu', 0, 100, 518, 254, 25.6, 25.2, 30.5, 30.6, '2025-07-23 15:00:00', 400, 78, NULL, '{"CABLE": {"count": 2, "minutes": 26}, "MACHINE": {"count": 3, "minutes": 39}, "BARBEL": {"count": 1, "minutes": 13}}'),
	(70, 'U001', '2025-07-25', 'Fri', 0, 56, 223, 233, 22.4, 22.2, 30.5, 30.7, '2025-07-24 15:00:00', 400, 47, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 12}, "BARBEL": {"count": 1, "minutes": 12}, "CABLE": {"count": 2, "minutes": 23}}'),
	(71, 'U001', '2025-07-26', 'Sat', 0, 71, 285, 238, 22.2, 21.7, 30.3, 30.4, '2025-07-25 15:00:00', 400, 77, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 58}, "BARBEL": {"count": 1, "minutes": 19}}'),
	(72, 'U001', '2025-07-27', 'Sun', 0, 57, 227, 222, 25.0, 24.7, 30.0, 30.1, '2025-07-26 15:00:00', 400, 66, NULL, '{"MACHINE": {"count": 3, "minutes": 50}, "BARBEL": {"count": 1, "minutes": 16}}'),
	(73, 'U001', '2025-07-28', 'Mon', 0, 100, 614, 239, 24.8, 24.6, 29.8, 30.3, '2025-07-27 15:00:00', 400, 86, NULL, '{"BARBEL": {"count": 3, "minutes": 86}}'),
	(74, 'U001', '2025-07-29', 'Tue', 0, 74, 297, 236, 22.3, 22.0, 28.1, 28.4, '2025-07-28 15:00:00', 400, 67, NULL, '{"CABLE": {"count": 1, "minutes": 22}, "MACHINE": {"count": 2, "minutes": 45}}'),
	(75, 'U001', '2025-07-30', 'Wed', 0, 34, 138, 238, 24.5, 24.4, 30.3, 30.6, '2025-07-29 15:00:00', 400, 35, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 12}, "BARBEL": {"count": 1, "minutes": 12}, "CABLE": {"count": 1, "minutes": 11}}'),
	(76, 'U001', '2025-07-31', 'Thu', 0, 84, 336, 230, 23.6, 23.4, 29.4, 29.5, '2025-07-30 15:00:00', 400, 89, NULL, '{"BODYWEIGHT": {"count": 2, "minutes": 36}, "MACHINE": {"count": 1, "minutes": 18}, "BARBEL": {"count": 2, "minutes": 35}}'),
	(77, 'U001', '2025-08-01', 'Fri', 0, 90, 360, 235, 22.3, 21.9, 28.3, 28.8, '2025-07-31 15:00:00', 400, 58, NULL, '{"MACHINE": {"count": 3, "minutes": 58}}'),
	(78, 'U001', '2025-08-02', 'Sat', 0, 27, 107, 258, 24.8, 24.5, 30.7, 31.0, '2025-08-01 15:00:00', 400, 34, NULL, '{"BARBEL": {"count": 1, "minutes": 8}, "BODYWEIGHT": {"count": 3, "minutes": 26}}'),
	(79, 'U001', '2025-08-03', 'Sun', 0, 66, 263, 250, 25.7, 25.3, 28.2, 28.4, '2025-08-02 15:00:00', 400, 87, NULL, '{"MACHINE": {"count": 3, "minutes": 33}, "CABLE": {"count": 2, "minutes": 22}, "BARBEL": {"count": 3, "minutes": 32}}'),
	(80, 'U001', '2025-08-04', 'Mon', 0, 51, 203, 260, 23.5, 23.1, 29.4, 29.8, '2025-08-03 15:00:00', 400, 46, NULL, '{"CABLE": {"count": 3, "minutes": 20}, "MACHINE": {"count": 1, "minutes": 7}, "BARBEL": {"count": 3, "minutes": 19}}'),
	(81, 'U001', '2025-08-05', 'Tue', 0, 38, 151, 238, 25.7, 25.3, 28.8, 29.2, '2025-08-04 15:00:00', 400, 50, NULL, '{"BODYWEIGHT": {"count": 2, "minutes": 50}}'),
	(82, 'U001', '2025-08-06', 'Wed', 0, 89, 357, 233, 25.5, 25.0, 30.3, 30.4, '2025-08-05 15:00:00', 400, 54, NULL, '{"BARBEL": {"count": 3, "minutes": 27}, "CABLE": {"count": 3, "minutes": 27}}'),
	(83, 'U001', '2025-08-07', 'Thu', 0, 31, 123, 257, 24.7, 24.4, 29.4, 29.6, '2025-08-06 15:00:00', 400, 32, NULL, '{"BARBEL": {"count": 1, "minutes": 32}}'),
	(84, 'U001', '2025-08-08', 'Fri', 0, 100, 448, 235, 24.6, 24.4, 30.1, 30.6, '2025-08-07 15:00:00', 400, 78, NULL, '{"CABLE": {"count": 2, "minutes": 78}}'),
	(85, 'U001', '2025-08-09', 'Sat', 0, 69, 276, 239, 25.2, 25.0, 29.8, 29.9, '2025-08-08 15:00:00', 400, 86, NULL, '{"CABLE": {"count": 2, "minutes": 43}, "MACHINE": {"count": 2, "minutes": 43}}'),
	(86, 'U001', '2025-08-10', 'Sun', 0, 46, 182, 245, 24.1, 23.8, 29.7, 29.9, '2025-08-09 15:00:00', 400, 52, NULL, '{"BARBEL": {"count": 1, "minutes": 9}, "BODYWEIGHT": {"count": 2, "minutes": 17}, "CABLE": {"count": 3, "minutes": 26}}'),
	(87, 'U001', '2025-08-11', 'Mon', 0, 100, 412, 253, 25.3, 25.0, 29.0, 29.4, '2025-08-10 15:00:00', 400, 79, NULL, '{"MACHINE": {"count": 3, "minutes": 79}}'),
	(88, 'U001', '2025-08-12', 'Tue', 0, 78, 310, 232, 24.0, 23.8, 28.6, 29.0, '2025-08-11 15:00:00', 400, 83, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 42}, "MACHINE": {"count": 2, "minutes": 28}, "CABLE": {"count": 1, "minutes": 13}}'),
	(89, 'U001', '2025-08-13', 'Wed', 0, 80, 321, 226, 24.4, 24.2, 28.2, 28.6, '2025-08-12 15:00:00', 400, 87, NULL, '{"MACHINE": {"count": 1, "minutes": 29}, "BODYWEIGHT": {"count": 2, "minutes": 58}}'),
	(90, 'U001', '2025-08-14', 'Thu', 0, 68, 271, 227, 23.2, 22.8, 29.4, 29.8, '2025-08-13 15:00:00', 400, 60, NULL, '{"CABLE": {"count": 2, "minutes": 60}}'),
	(91, 'U001', '2025-08-15', 'Fri', 0, 35, 141, 221, 23.8, 23.5, 29.8, 29.9, '2025-08-14 15:00:00', 400, 45, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 19}, "BARBEL": {"count": 2, "minutes": 13}, "CABLE": {"count": 2, "minutes": 13}}'),
	(92, 'U001', '2025-08-16', 'Sat', 0, 81, 324, 254, 25.4, 24.9, 30.8, 31.2, '2025-08-15 15:00:00', 400, 84, NULL, '{"BARBEL": {"count": 1, "minutes": 84}}'),
	(93, 'U001', '2025-08-17', 'Sun', 0, 100, 409, 256, 24.8, 24.5, 29.0, 29.2, '2025-08-16 15:00:00', 400, 81, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 61}, "BARBEL": {"count": 1, "minutes": 20}}'),
	(94, 'U001', '2025-08-18', 'Mon', 0, 100, 496, 234, 24.6, 24.4, 30.5, 30.6, '2025-08-17 15:00:00', 400, 73, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 73}}'),
	(95, 'U001', '2025-08-19', 'Tue', 0, 100, 496, 259, 24.1, 23.9, 28.9, 29.1, '2025-08-18 15:00:00', 400, 81, NULL, '{"BODYWEIGHT": {"count": 3, "minutes": 40}, "CABLE": {"count": 3, "minutes": 41}}'),
	(96, 'U001', '2025-08-20', 'Wed', 0, 100, 598, 221, 24.0, 23.7, 30.3, 30.7, '2025-08-19 15:00:00', 400, 89, NULL, '{"MACHINE": {"count": 3, "minutes": 89}}'),
	(97, 'U001', '2025-08-21', 'Thu', 0, 79, 316, 249, 25.1, 24.9, 28.1, 28.2, '2025-08-20 15:00:00', 400, 57, NULL, '{"CABLE": {"count": 3, "minutes": 57}}'),
	(98, 'U001', '2025-08-22', 'Fri', 0, 100, 543, 260, 25.7, 25.2, 29.5, 29.7, '2025-08-21 15:00:00', 400, 85, NULL, '{"CABLE": {"count": 3, "minutes": 36}, "BARBEL": {"count": 2, "minutes": 24}, "MACHINE": {"count": 2, "minutes": 25}}'),
	(99, 'U001', '2025-08-23', 'Sat', 0, 81, 323, 251, 23.1, 22.8, 30.3, 30.4, '2025-08-22 15:00:00', 400, 82, NULL, '{"CABLE": {"count": 3, "minutes": 62}, "BODYWEIGHT": {"count": 1, "minutes": 20}}'),
	(100, 'U001', '2025-08-24', 'Sun', 0, 66, 266, 242, 24.7, 24.6, 28.4, 28.6, '2025-08-23 15:00:00', 400, 70, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 18}, "BARBEL": {"count": 3, "minutes": 52}}'),
	(101, 'U001', '2025-08-25', 'Mon', 0, 67, 269, 260, 26.0, 25.8, 29.2, 29.3, '2025-08-24 15:00:00', 400, 43, NULL, '{"MACHINE": {"count": 3, "minutes": 43}}'),
	(102, 'U001', '2025-08-26', 'Tue', 0, 76, 302, 256, 24.9, 24.7, 30.5, 30.8, '2025-08-25 15:00:00', 400, 55, NULL, '{"CABLE": {"count": 3, "minutes": 28}, "MACHINE": {"count": 3, "minutes": 27}}'),
	(103, 'U001', '2025-08-27', 'Wed', 0, 74, 294, 251, 24.9, 24.5, 30.0, 30.4, '2025-08-26 15:00:00', 400, 78, NULL, '{"BARBEL": {"count": 3, "minutes": 26}, "CABLE": {"count": 3, "minutes": 26}, "MACHINE": {"count": 3, "minutes": 26}}'),
	(104, 'U001', '2025-08-28', 'Thu', 0, 77, 309, 247, 25.6, 25.2, 28.5, 28.7, '2025-08-27 15:00:00', 400, 45, NULL, '{"BODYWEIGHT": {"count": 1, "minutes": 45}}'),
	(105, 'U001', '2025-08-29', 'Fri', 0, 100, 496, 257, 25.6, 25.3, 28.7, 29.0, '2025-08-28 15:00:00', 400, 77, NULL, '{"BARBEL": {"count": 2, "minutes": 77}}'),
	(106, 'U001', '2025-08-30', 'Sat', 0, 97, 389, 238, 23.8, 23.5, 29.5, 29.9, '2025-08-29 15:00:00', 400, 59, NULL, '{"MACHINE": {"count": 1, "minutes": 20}, "CABLE": {"count": 2, "minutes": 39}}'),
	(107, 'U001', '2025-08-31', 'Sun', 0, 76, 305, 229, 25.6, 25.3, 28.4, 28.8, '2025-08-30 15:00:00', 400, 81, NULL, '{"MACHINE": {"count": 2, "minutes": 32}, "BODYWEIGHT": {"count": 3, "minutes": 49}}');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
