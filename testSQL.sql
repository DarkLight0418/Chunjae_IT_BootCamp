-- 문자셋/엔진 공통 기본값(선택)
SET NAMES utf8mb4;
SET time_zone = '+09:00';

-- 1) 기준 테이블들
CREATE TABLE `grade` (
  `GradeNumber` INT NOT NULL,
  `GradeName`   VARCHAR(50) NOT NULL,
  `requiredPoint` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`GradeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `admin` (
  `Email`     VARCHAR(255) NOT NULL,
  `AdminName` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `board` (
  `GradeNumber` INT NOT NULL,
  `gradeName`   VARCHAR(50) NOT NULL,
  PRIMARY KEY (`GradeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `Email`       VARCHAR(255) NOT NULL,
  `userPW`      VARCHAR(255) NOT NULL,
  `Name`        VARCHAR(100) NOT NULL,
  -- DATE에 now() 불가 → DATETIME으로 변경하고 CURRENT_TIMESTAMP 기본값
  `SignupDate`  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Point`       INT NOT NULL DEFAULT 0,
  `GradeNumber` INT NOT NULL,
  `Email2`      VARCHAR(255) NULL,
  PRIMARY KEY (`Email`),
  KEY `idx_user_grade` (`GradeNumber`),
  KEY `idx_user_admin` (`Email2`),
  CONSTRAINT `FK_grade_TO_user_1` FOREIGN KEY (`GradeNumber`) REFERENCES `grade` (`GradeNumber`),
  CONSTRAINT `FK_admin_TO_user_1` FOREIGN KEY (`Email2`) REFERENCES `admin` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2) 게시글
CREATE TABLE `post` (
  `postNumber`     INT NOT NULL AUTO_INCREMENT,
  `title`          VARCHAR(100) NULL,
  `content`        TEXT NULL,
  `file`           LONGBLOB NULL,          -- 실제 운영은 파일경로 권장
  `categories`     VARCHAR(50) NULL,
  `deleteFlag`     TINYINT(1) NOT NULL DEFAULT 0 COMMENT '0-false,1-true',
  `scrapCount`     INT NOT NULL DEFAULT 0,
  `fixedUpFlag`    TINYINT(1) NOT NULL DEFAULT 0,
  `reportCount`    INT NOT NULL DEFAULT 0,
  `updateDate`     DATETIME NULL,
  `commentCount`   INT NOT NULL DEFAULT 0,
  `Views`          INT NOT NULL DEFAULT 0, -- INT(25) 표시폭은 의미 없음 → INT
  `likeCount`      INT NOT NULL DEFAULT 0,
  `editDate`       DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `GradeNumber`    INT NOT NULL,
  `Email`          VARCHAR(255) NOT NULL,
  PRIMARY KEY (`postNumber`),
  KEY `idx_post_grade` (`GradeNumber`),
  KEY `idx_post_user` (`Email`),
  CONSTRAINT `FK_board_TO_post_1` FOREIGN KEY (`GradeNumber`) REFERENCES `board` (`GradeNumber`),
  CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 3) 북마크 / 좋아요: 중복 방지용 복합 PK 추가
CREATE TABLE `bookmark` (
  `postNumber` INT NOT NULL,
  `Email`      VARCHAR(255) NOT NULL,
  PRIMARY KEY (`postNumber`,`Email`),
  KEY `idx_bm_email` (`Email`),
  CONSTRAINT `FK_post_TO_bookmark_1` FOREIGN KEY (`postNumber`) REFERENCES `post` (`postNumber`),
  CONSTRAINT `FK_user_TO_bookmark_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `like` (
  `postNumber` INT NOT NULL,
  `Email`      VARCHAR(255) NOT NULL,
  PRIMARY KEY (`postNumber`,`Email`),
  KEY `idx_like_email` (`Email`),
  CONSTRAINT `FK_post_TO_like_1` FOREIGN KEY (`postNumber`) REFERENCES `post` (`postNumber`),
  CONSTRAINT `FK_user_TO_like_1` FOREIGN KEY (`Email`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 4) 신고
CREATE TABLE `report` (
  `reportID`   INT NOT NULL AUTO_INCREMENT,
  `contentID`  INT NULL,                  -- 용도 명확화 필요(코멘트ID?)
  `userEmail`  VARCHAR(255) NULL,         -- 신고자 이메일? FK를 걸려면 명확화 필요
  `reason`     TEXT NULL,
  `reportDate` DATETIME NULL,
  `postNumber` INT NULL,
  `Email2`     VARCHAR(255) NOT NULL,     -- FK_user_TO_report_1 대상(아마 신고 대상자?)
  PRIMARY KEY (`reportID`),
  KEY `idx_report_post` (`postNumber`),
  KEY `idx_report_user2` (`Email2`),
  CONSTRAINT `FK_post_TO_report_1` FOREIGN KEY (`postNumber`) REFERENCES `post` (`postNumber`),
  CONSTRAINT `FK_user_TO_report_1` FOREIGN KEY (`Email2`) REFERENCES `user` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
