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

-- 테이블 newbiehealth.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `user_key` varchar(20) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` enum('남','여') DEFAULT NULL,
  `height_cm` float DEFAULT NULL,
  `weight_kg` float DEFAULT NULL,
  `fitness_level` int(11) DEFAULT NULL,
  `goal` enum('다이어트','벌크업','유지어트') DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `survey_completed` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`user_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 newbiehealth.user:~41 rows (대략적) 내보내기
INSERT INTO `user` (`user_key`, `user_id`, `password`, `name`, `age`, `gender`, `height_cm`, `weight_kg`, `fitness_level`, `goal`, `created_at`, `survey_completed`) VALUES
	('U001', 'test01', '1234', '최윤서', 25, '남', 175.5, 70, 1, '다이어트', '2025-07-30', 1),
	('U002', 'test02', '1234', '김영희', 30, '여', 162, 55, 2, '유지어트', '2025-07-30', 0),
	('U003', 'test03', '1234', '이철수', 28, '남', 180.2, 82, 2, '벌크업', '2025-07-30', 1),
	('U004', 'test04', 'pw44', '박지민', 22, '여', 158.3, 48, 1, '다이어트', '2025-07-30', 0),
	('U005', 'test05', 'pass5', '최태현', 35, '남', 172.5, 76.5, 2, '유지어트', '2025-07-30', 1),
	('U006', 'test06', 'pw66', '강민지', 27, '여', 167, 60, 1, '다이어트', '2025-07-30', 0),
	('U007', 'test07', 'test7', '정우성', 40, '남', 183, 85, 3, '벌크업', '2025-07-30', 0),
	('U008', 'test08', 'pw88', '한지은', 31, '여', 160, 57, 2, '유지어트', '2025-07-30', 1),
	('U009', 'test09', '1239', '오준혁', 26, '남', 176.4, 72, 2, '다이어트', '2025-07-30', 0),
	('U010', 'test10', 'abcd10', '서유리', 29, '여', 165, 58, 2, '유지어트', '2025-07-30', 0),
	('U011', 'test11', '3493', '김구', 23, '남', 180.2, 78, 2, '벌크업', '2025-07-30', 0),
	('U012', 'test12', 'ab12', '이다현', 26, '여', 163, 54, 1, '다이어트', '2025-07-30', 0),
	('U013', 'test13', 'qwe13', '최지훈', 33, '남', 178, 74, 2, '벌크업', '2025-07-30', 0),
	('U014', 'test14', 'pw14', '민유라', 28, '여', 165, 56, 2, '유지어트', '2025-07-30', 0),
	('U015', 'test15', 'pass15', '유현수', 36, '남', 182, 81, 3, '벌크업', '2025-07-30', 0),
	('U016', 'test16', 'abcd16', '강수빈', 25, '여', 160, 52, 1, '다이어트', '2025-07-30', 0),
	('U017', 'test17', 'zxc17', '조기훈', 32, '남', 174, 68, 2, '유지어트', '2025-07-30', 0),
	('U018', 'test18', 'pw18', '한지원', 27, '여', 167, 55, 2, '유지어트', '2025-07-30', 0),
	('U019', 'test19', 'test19', '문지호', 38, '남', 179, 85, 3, '벌크업', '2025-07-30', 0),
	('U020', 'test20', '2020', '이소은', 29, '여', 168, 59, 2, '유지어트', '2025-07-30', 0),
	('U021', 'test21', 'pw21', '정석진', 31, '남', 180, 76, 2, '다이어트', '2025-07-30', 0),
	('U022', 'test22', 'pw22', '이선우', 27, '남', 177, 73, 2, '벌크업', '2025-07-30', 0),
	('U023', 'test23', 'pass23', '김채영', 30, '여', 162, 52, 1, '다이어트', '2025-07-30', 0),
	('U024', 'test24', 'abcd24', '정태현', 35, '남', 180, 76, 2, '유지어트', '2025-07-30', 0),
	('U025', 'test25', 'pw25', '박유림', 24, '여', 164, 50, 1, '다이어트', '2025-07-30', 0),
	('U026', 'test26', 'pass26', '윤정우', 33, '남', 176, 70, 2, '유지어트', '2025-07-30', 0),
	('U027', 'test27', 'pw27', '송지은', 26, '여', 160, 48, 1, '다이어트', '2025-07-30', 0),
	('U028', 'test28', 'pass28', '오재현', 31, '남', 179, 75, 2, '벌크업', '2025-07-30', 0),
	('U029', 'test29', 'pw29', '하윤지', 28, '여', 166, 53, 2, '유지어트', '2025-07-30', 0),
	('U030', 'test30', 'pass30', '노지훈', 37, '남', 181, 84, 3, '벌크업', '2025-07-30', 0),
	('U031', 'test31', 'pw31', '김민서', 25, '여', 163, 51, 1, '다이어트', '2025-07-30', 0),
	('U032', 'test32', 'pass32', '최우성', 34, '남', 178, 72, 2, '유지어트', '2025-07-30', 0),
	('U033', 'test33', 'pw33', '백하은', 29, '여', 165, 55, 2, '유지어트', '2025-07-30', 0),
	('U034', 'test34', 'pass34', '남태우', 39, '남', 182, 86, 3, '벌크업', '2025-07-30', 0),
	('U035', 'test35', 'pw35', '윤소민', 27, '여', 162, 49, 1, '다이어트', '2025-07-30', 0),
	('U036', 'test36', 'pass36', '오민규', 36, '남', 180, 77, 2, '유지어트', '2025-07-30', 0),
	('U037', 'test37', 'pw37', '정가은', 31, '여', 168, 58, 2, '유지어트', '2025-07-30', 0),
	('U038', 'test38', 'pass38', '임도현', 40, '남', 183, 88, 3, '벌크업', '2025-07-30', 0),
	('U039', 'test39', 'pw39', '최다인', 28, '여', 164, 54, 2, '유지어트', '2025-07-30', 0),
	('U040', 'test40', 'pass40', '배진수', 32, '남', 177, 74, 2, '유지어트', '2025-07-30', 0),
	('U041', 'test41', 'pw41', '서예진', 26, '여', 160, 50, 1, '다이어트', '2025-07-30', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
