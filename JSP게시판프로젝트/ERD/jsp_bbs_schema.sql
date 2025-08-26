-- JSP 교육용 게시판 스키마 (MySQL 8.x, InnoDB)
-- ✅ 목표: 최소 변경으로 안정적인 생성/실행 (CREATE → INDEX → FK 순서)
-- ⚠️ 실제 운영에 적용하기 전에 반드시 테스트 DB에서 검증하세요.

/* =============================
   0) 환경 설정 (선택)
============================= */
SET NAMES utf8mb4;
SET time_zone = '+09:00';

/* =============================
   1) 데이터베이스 (선택)
============================= */
CREATE DATABASE IF NOT EXISTS `jsp_board`
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;
USE `jsp_board`;

/* =============================
   2) 테이블 생성 (부모 → 자식 순서)
============================= */
-- 2-1) 등급 기준 테이블
CREATE TABLE IF NOT EXISTS `grade` (
  `GradeNumber`   INT NOT NULL,
  `GradeName`     VARCHAR(50) NOT NULL,
  `requiredPoint` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`GradeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 2-2) 게시판 테이블 (요구 스키마를 최대한 존중하여 GradeNumber를 PK로 사용)
CREATE TABLE IF NOT EXISTS `board` (
  `GradeNumber` INT NOT NULL,
  `gradeName`   VARCHAR(50) NOT NULL,
  PRIMARY KEY (`GradeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 2-3) 사용자 테이블
CREATE TABLE IF NOT EXISTS `user` (
  `Email`       VARCHAR(255) NOT NULL,
  `userPW`      VARCHAR(255) NOT NULL,           -- 비밀번호는 해시로 저장 권장 (예: bcrypt)
  `Name`        VARCHAR(100) NOT NULL,
  `SignupDate`  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Point`       INT          NOT NULL DEFAULT 0,
  `GradeNumber` INT          NOT NULL,
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 2-4) 관리자 테이블 (운영 정책에 따라 PK/UNIQUE 구성)
CREATE TABLE IF NOT EXISTS `admin` (
  `Email`     VARCHAR(255) NOT NULL,
  `AdminName` VARCHAR(100) NOT NULL,
  `AdminPW`   VARCHAR(255) NOT NULL,            -- 해시 저장 권장
  PRIMARY KEY (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 2-5) 게시글 테이블 (자식; FK로 user/board 참조)
CREATE TABLE IF NOT EXISTS `post` (
  `postNumber`   INT NOT NULL AUTO_INCREMENT,
  `title`        VARCHAR(100) NOT NULL,
  `content`      TEXT NULL,
  `file`         BLOB NULL,                      -- 대용량 필요시 MEDIUMBLOB/LONGBLOB 고려
  `categories`   VARCHAR(20) NULL,               -- 다중 카테고리 필요시 교차 테이블 권장
  `deleteFlag`   BOOLEAN NOT NULL DEFAULT 0 COMMENT '0 - false, 1 - true',
  `scrapCount`   INT NOT NULL DEFAULT 0,
  `fixedUpFlag`  BOOLEAN NOT NULL DEFAULT 0,
  `reportCount`  INT NOT NULL DEFAULT 0,
  `updateDate`   DATETIME NULL,                  -- 수동 갱신용 (자동 갱신 필요시 트리거/ON UPDATE 사용)
  `commentCount` INT NOT NULL DEFAULT 0,
  `Views`        INT NOT NULL DEFAULT 0,
  `likeCount`    INT NOT NULL DEFAULT 0,
  `editDate`     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `GradeNumber`  INT NOT NULL,
  `Email`        VARCHAR(255) NOT NULL,
  PRIMARY KEY (`postNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/* =============================
   3) 인덱스 (FK 컬럼에 보조 인덱스 권장)
============================= */
CREATE INDEX `idx_user_gradeNumber` ON `user` (`GradeNumber`);
CREATE INDEX `idx_post_gradeNumber` ON `post` (`GradeNumber`);
CREATE INDEX `idx_post_email`       ON `post` (`Email`);

/* =============================
   4) 외래키 (부모 PK 준비 완료 후 추가)
============================= */
-- user → grade
ALTER TABLE `user`
  ADD CONSTRAINT `fk_user_grade`
  FOREIGN KEY (`GradeNumber`) REFERENCES `grade`(`GradeNumber`)
  ON UPDATE CASCADE;

-- post → board
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_board`
  FOREIGN KEY (`GradeNumber`) REFERENCES `board`(`GradeNumber`)
  ON UPDATE CASCADE;

-- post → user
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_user`
  FOREIGN KEY (`Email`) REFERENCES `user`(`Email`)
  ON UPDATE CASCADE
  ON DELETE CASCADE;  -- 사용자 삭제 시 해당 사용자의 게시글도 함께 삭제 (정책에 맞게 변경 가능)

/* =============================
   5) 선택: editDate를 자동 갱신하고 싶다면 (대안)
   - DATETIME은 ON UPDATE 직접 지원 X → TIMESTAMP 사용 또는 트리거 작성 필요
   - 아래는 TIMESTAMP 예시 (필요 시 post 테이블 정의를 조정)
============================= */
-- ALTER TABLE `post`
--   MODIFY `editDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

/* =============================
   끝.
============================= */
