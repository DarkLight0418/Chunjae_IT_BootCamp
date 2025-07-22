-- --------------------------------------------------------
-- ȣ��Ʈ:                          127.0.0.1
-- ���� ����:                        10.11.8-MariaDB - mariadb.org binary distribution
-- ���� OS:                        Win64
-- HeidiSQL ����:                  12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- modeling_schema �����ͺ��̽� ���� ��������
CREATE DATABASE IF NOT EXISTS `modeling_schema` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `modeling_schema`;

-- ���̺� modeling_schema.pt ���� ���� ���� ��������
CREATE TABLE IF NOT EXISTS `pt ���� ����` (
  `Ʈ���̳�_ID` int(11) DEFAULT NULL,
  `�����ð�` int(11) DEFAULT NULL,
  `��¥` date DEFAULT NULL,
  `����` varchar(50) DEFAULT NULL,
  `ȸ��ID` int(11) DEFAULT NULL,
  KEY `fk_pt_Ʈ���̳�` (`Ʈ���̳�_ID`),
  KEY `fk_ȸ��_ID` (`ȸ��ID`),
  CONSTRAINT `fk_pt_Ʈ���̳�` FOREIGN KEY (`Ʈ���̳�_ID`) REFERENCES `Ʈ���̳�` (`Ʈ���̳�_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ȸ��_ID` FOREIGN KEY (`ȸ��ID`) REFERENCES `ȸ��ī��` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.�Ҹ�ǰ ���� ��������
CREATE TABLE IF NOT EXISTS `�Ҹ�ǰ` (
  `ǰ���` varchar(20) NOT NULL,
  `��������` int(11) DEFAULT 0,
  `�ֹ�������` date DEFAULT NULL,
  `����` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`ǰ���`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.��ⱸ ���� ��������
CREATE TABLE IF NOT EXISTS `��ⱸ` (
  `�ⱸ��` varchar(30) NOT NULL,
  `����` enum('����','����','������') DEFAULT NULL,
  `����Ŀ` varchar(30) DEFAULT NULL,
  `����` int(11) DEFAULT NULL,
  `��������` date DEFAULT NULL,
  PRIMARY KEY (`�ⱸ��`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.������ ���� ��������
CREATE TABLE IF NOT EXISTS `������` (
  `���⼼` decimal(10,2) DEFAULT NULL,
  `������` decimal(10,2) DEFAULT NULL,
  `PT_Ʈ���̳�_����` decimal(10,2) DEFAULT NULL,
  `����` decimal(10,2) DEFAULT NULL,
  `�Ҹ�ǰ��` decimal(10,2) DEFAULT NULL,
  `��¥` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.���԰��� ���� ��������
CREATE TABLE IF NOT EXISTS `���԰���` (
  `��ȣ` int(11) NOT NULL AUTO_INCREMENT,
  `ȸ��ID` int(11) DEFAULT NULL,
  `�湮�Ͻ�` datetime DEFAULT NULL,
  `���Ա��` text DEFAULT NULL,
  PRIMARY KEY (`��ȣ`) USING BTREE,
  KEY `ȸ��ID` (`ȸ��ID`),
  CONSTRAINT `���԰���_ibfk_1` FOREIGN KEY (`ȸ��ID`) REFERENCES `ȸ��ī��` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.Ʈ���̳� ���� ��������
CREATE TABLE IF NOT EXISTS `Ʈ���̳�` (
  `Ʈ���̳�_ID` int(11) NOT NULL AUTO_INCREMENT,
  `�̸�` varchar(20) DEFAULT NULL,
  `����` char(1) DEFAULT NULL,
  `���_ȸ��_����Ʈ` text DEFAULT NULL,
  `�ٹ�_�ð�ǥ` text DEFAULT NULL,
  `�ڰ���_��_���` text DEFAULT NULL,
  `����` decimal(10,2) DEFAULT NULL,
  `�ٹ�����` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Ʈ���̳�_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.ȸ���������� ���� ��������
CREATE TABLE IF NOT EXISTS `ȸ����������` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ȸ��ID` int(11) DEFAULT NULL,
  `����_����` text DEFAULT NULL,
  `����_���` enum('ī��','����','������ü') DEFAULT NULL,
  `����_����` date DEFAULT NULL,
  `ȸ����` tinyint(1) DEFAULT NULL,
  `���` tinyint(1) DEFAULT NULL,
  `PT` tinyint(1) DEFAULT NULL,
  `��Ŀ��` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ȸ��ID` (`ȸ��ID`),
  CONSTRAINT `ȸ����������_ibfk_1` FOREIGN KEY (`ȸ��ID`) REFERENCES `ȸ��ī��` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

-- ���̺� modeling_schema.ȸ��ī�� ���� ��������
CREATE TABLE IF NOT EXISTS `ȸ��ī��` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `�̿�_�Ⱓ` enum('3����','6����','12����') DEFAULT NULL,
  `������` date DEFAULT NULL,
  `������` date DEFAULT NULL,
  `��Ŀ��_����` tinyint(1) DEFAULT NULL,
  `PT_����` tinyint(1) DEFAULT NULL,
  `�̸�` varchar(15) DEFAULT NULL,
  `��ȭ��ȣ` varchar(100) DEFAULT NULL,
  `�����ȣ` char(6) DEFAULT NULL,
  `����` char(2) DEFAULT NULL,
  `����` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ������ �����Ͱ� ���õǾ� ���� �ʽ��ϴ�.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
