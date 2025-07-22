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

-- 테이블 modeling_schema.pt 수업 정보 구조 내보내기
CREATE TABLE IF NOT EXISTS `pt 수업 정보` (
  `트레이너_ID` int(11) DEFAULT NULL,
  `수업시간` int(11) DEFAULT NULL,
  `날짜` date DEFAULT NULL,
  `요일` varchar(50) DEFAULT NULL,
  `회원ID` int(11) DEFAULT NULL,
  KEY `fk_pt_트레이너` (`트레이너_ID`),
  KEY `fk_회원_ID` (`회원ID`),
  CONSTRAINT `fk_pt_트레이너` FOREIGN KEY (`트레이너_ID`) REFERENCES `트레이너` (`트레이너_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_회원_ID` FOREIGN KEY (`회원ID`) REFERENCES `회원카드` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.소모품 구조 내보내기
CREATE TABLE IF NOT EXISTS `소모품` (
  `품목명` varchar(20) NOT NULL,
  `남은갯수` int(11) DEFAULT 0,
  `주문예정일` date DEFAULT NULL,
  `가격` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`품목명`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.운동기구 구조 내보내기
CREATE TABLE IF NOT EXISTS `운동기구` (
  `기구명` varchar(30) NOT NULL,
  `상태` enum('정상','고장','수리중') DEFAULT NULL,
  `메이커` varchar(30) DEFAULT NULL,
  `갯수` int(11) DEFAULT NULL,
  `사용시작일` date DEFAULT NULL,
  PRIMARY KEY (`기구명`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.유지비 구조 내보내기
CREATE TABLE IF NOT EXISTS `유지비` (
  `전기세` decimal(10,2) DEFAULT NULL,
  `수도세` decimal(10,2) DEFAULT NULL,
  `PT_트레이너_월급` decimal(10,2) DEFAULT NULL,
  `월세` decimal(10,2) DEFAULT NULL,
  `소모품비` decimal(10,2) DEFAULT NULL,
  `날짜` date DEFAULT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.출입관리 구조 내보내기
CREATE TABLE IF NOT EXISTS `출입관리` (
  `번호` int(11) NOT NULL AUTO_INCREMENT,
  `회원ID` int(11) DEFAULT NULL,
  `방문일시` datetime DEFAULT NULL,
  `출입기록` text DEFAULT NULL,
  PRIMARY KEY (`번호`) USING BTREE,
  KEY `회원ID` (`회원ID`),
  CONSTRAINT `출입관리_ibfk_1` FOREIGN KEY (`회원ID`) REFERENCES `회원카드` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.트레이너 구조 내보내기
CREATE TABLE IF NOT EXISTS `트레이너` (
  `트레이너_ID` int(11) NOT NULL AUTO_INCREMENT,
  `이름` varchar(20) DEFAULT NULL,
  `성별` char(1) DEFAULT NULL,
  `담당_회원_리스트` text DEFAULT NULL,
  `근무_시간표` text DEFAULT NULL,
  `자격증_및_경력` text DEFAULT NULL,
  `월급` decimal(10,2) DEFAULT NULL,
  `근무요일` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`트레이너_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.회원결제정산 구조 내보내기
CREATE TABLE IF NOT EXISTS `회원결제정산` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `회원ID` int(11) DEFAULT NULL,
  `결제_내역` text DEFAULT NULL,
  `결제_방식` enum('카드','현금','계좌이체') DEFAULT NULL,
  `결제_일자` date DEFAULT NULL,
  `회원권` tinyint(1) DEFAULT NULL,
  `운동복` tinyint(1) DEFAULT NULL,
  `PT` tinyint(1) DEFAULT NULL,
  `라커룸` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `회원ID` (`회원ID`),
  CONSTRAINT `회원결제정산_ibfk_1` FOREIGN KEY (`회원ID`) REFERENCES `회원카드` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 modeling_schema.회원카드 구조 내보내기
CREATE TABLE IF NOT EXISTS `회원카드` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `이용_기간` enum('3개월','6개월','12개월') DEFAULT NULL,
  `시작일` date DEFAULT NULL,
  `종료일` date DEFAULT NULL,
  `라커룸_여부` tinyint(1) DEFAULT NULL,
  `PT_여부` tinyint(1) DEFAULT NULL,
  `이름` varchar(15) DEFAULT NULL,
  `전화번호` varchar(100) DEFAULT NULL,
  `우편번호` char(6) DEFAULT NULL,
  `성별` char(2) DEFAULT NULL,
  `나이` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
