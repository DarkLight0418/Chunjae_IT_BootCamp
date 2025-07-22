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


-- modeling_schema 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `modeling_schema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `modeling_schema`;

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

-- 테이블 데이터 modeling_schema.pt 수업 정보:~1 rows (대략적) 내보내기
INSERT INTO `pt 수업 정보` (`트레이너_ID`, `수업시간`, `날짜`, `요일`, `회원ID`) VALUES
	(1, 13, '2027-07-22', '월', 3);

-- 테이블 modeling_schema.소모품 구조 내보내기
CREATE TABLE IF NOT EXISTS `소모품` (
  `품목명` varchar(20) NOT NULL,
  `남은갯수` int(11) DEFAULT 0,
  `주문예정일` date DEFAULT NULL,
  `가격` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`품목명`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.소모품:~5 rows (대략적) 내보내기
INSERT INTO `소모품` (`품목명`, `남은갯수`, `주문예정일`, `가격`) VALUES
	('샴푸', 10, '2025-08-01', 2000.00),
	('수건', 25, '2025-08-03', 3000.00),
	('운동복', 15, '2025-08-05', 15000.00),
	('종이컵', 100, '2025-08-04', 500.00),
	('휴지', 50, '2025-08-02', 1000.00);

-- 테이블 modeling_schema.운동기구 구조 내보내기
CREATE TABLE IF NOT EXISTS `운동기구` (
  `기구명` varchar(30) NOT NULL,
  `상태` enum('정상','고장','수리중') DEFAULT NULL,
  `메이커` varchar(30) DEFAULT NULL,
  `갯수` int(11) DEFAULT NULL,
  `사용시작일` date DEFAULT NULL,
  PRIMARY KEY (`기구명`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.운동기구:~3 rows (대략적) 내보내기
INSERT INTO `운동기구` (`기구명`, `상태`, `메이커`, `갯수`, `사용시작일`) VALUES
	('런닝머신', '정상', '삼성헬스', 5, '2025-07-22'),
	('레그프레스', '정상', '인바디핏', 3, '2027-07-22'),
	('바벨세트', '수리중', '케이짐', 10, '2025-07-22');

-- 테이블 modeling_schema.유지비 구조 내보내기
CREATE TABLE IF NOT EXISTS `유지비` (
  `전기세` decimal(10,2) DEFAULT NULL,
  `수도세` decimal(10,2) DEFAULT NULL,
  `PT_트레이너_월급` decimal(10,2) DEFAULT NULL,
  `월세` decimal(10,2) DEFAULT NULL,
  `소모품비` decimal(10,2) DEFAULT NULL,
  `날짜` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.유지비:~3 rows (대략적) 내보내기
INSERT INTO `유지비` (`전기세`, `수도세`, `PT_트레이너_월급`, `월세`, `소모품비`, `날짜`) VALUES
	(500000.00, 200000.00, 8300000.00, 2500000.00, 400000.00, '2025-07-22'),
	(480000.00, 190000.00, 8200000.00, 2500000.00, 420000.00, '2025-08-23'),
	(510000.00, 210000.00, 8400000.00, 2500000.00, 390000.00, '2025-09-22');

-- 테이블 modeling_schema.출입관리 구조 내보내기
CREATE TABLE IF NOT EXISTS `출입관리` (
  `번호` int(11) NOT NULL AUTO_INCREMENT,
  `회원ID` int(11) DEFAULT NULL,
  `방문일시` datetime DEFAULT NULL,
  `출입기록` text DEFAULT NULL,
  PRIMARY KEY (`번호`) USING BTREE,
  KEY `회원ID` (`회원ID`),
  CONSTRAINT `출입관리_ibfk_1` FOREIGN KEY (`회원ID`) REFERENCES `회원카드` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.출입관리:~4 rows (대략적) 내보내기
INSERT INTO `출입관리` (`번호`, `회원ID`, `방문일시`, `출입기록`) VALUES
	(1, 1, '2025-07-21 09:00:00', '입장'),
	(2, 2, '2025-07-21 10:15:00', '입장'),
	(3, 3, '2025-07-21 11:30:00', '입장'),
	(4, 3, '2025-07-22 10:26:18', '퇴장');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.트레이너:~3 rows (대략적) 내보내기
INSERT INTO `트레이너` (`트레이너_ID`, `이름`, `성별`, `담당_회원_리스트`, `근무_시간표`, `자격증_및_경력`, `월급`, `근무요일`) VALUES
	(1, '김지훈', 'M', '1,2', '08:00~20:00', '생활스포츠지도사', 3000000.00, '수,목,금,토,일'),
	(2, '최수빈', 'F', '3', '13:00~20:00', '헬스 트레이너 2급', 2800000.00, '토,일'),
	(3, '장민서', 'F', '1', '09:00~20:00', '운동처방사', 2500000.00, '월,화,수,목,금');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.회원결제정산:~3 rows (대략적) 내보내기
INSERT INTO `회원결제정산` (`id`, `회원ID`, `결제_내역`, `결제_방식`, `결제_일자`, `회원권`, `운동복`, `PT`, `라커룸`) VALUES
	(1, 1, '3개월 등록 + 라커룸', '카드', '2025-07-01', 1, 0, 0, 1),
	(2, 2, '6개월 등록 + PT', '현금', '2025-06-01', 1, 1, 1, 0),
	(3, 3, '12개월 등록 Full Option', '계좌이체', '2025-01-01', 1, 1, 1, 1);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 테이블 데이터 modeling_schema.회원카드:~3 rows (대략적) 내보내기
INSERT INTO `회원카드` (`id`, `이용_기간`, `시작일`, `종료일`, `라커룸_여부`, `PT_여부`, `이름`, `전화번호`, `우편번호`, `성별`, `나이`, `email`) VALUES
	(1, '3개월', '2025-07-01', '2025-09-30', 1, 0, '김예나', '010-1111-1111', '12345', 'F', 26, 'yena1@example.com'),
	(2, '6개월', '2025-06-01', '2025-11-30', 0, 1, '이서연', '010-2222-2222', '23456', 'F', 30, 'seoyeon@example.com'),
	(3, '12개월', '2025-01-01', '2025-12-31', 1, 1, '박민준', '010-3333-3333', '34567', 'M', 35, 'minjun@example.com');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

