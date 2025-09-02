SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE user_badges (
  badge_id    INT AUTO_INCREMENT PRIMARY KEY,
  badge_name  VARCHAR(20) NOT NULL UNIQUE,
  description VARCHAR(200),
  min_points  INT NOT NULL DEFAULT 0,
  max_points  INT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users (
  user_id       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 ID',
  username      VARCHAR(20)  NOT NULL COMMENT '이름',
  email         VARCHAR(255) NOT NULL UNIQUE COMMENT '이메일',
  password_hash VARCHAR(255) NOT NULL COMMENT '비밀번호 해시',
  img_name      VARCHAR(50)  NOT NULL DEFAULT 'default.png' COMMENT '프로필 이미지 파일명',
  nickname      VARCHAR(20)  NOT NULL COMMENT '닉네임',
  total_points  INT NOT NULL DEFAULT 0 COMMENT '누적 포인트 캐시',
  badge_id      INT NULL COMMENT '대표 칭호(뱃지)',
  status        ENUM('ACTIVE','SUSPENDED','DELETED') DEFAULT 'ACTIVE' COMMENT '계정 상태',
  created_at    DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시각',
  updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '갱신 시각',
  CONSTRAINT fk_users_badge FOREIGN KEY (badge_id) 
    REFERENCES user_badges(badge_id)
    ON DELETE SET NULL   -- 뱃지 삭제 시 사용자 컬럼 NULL로
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_points (
  point_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id    BIGINT NOT NULL,
  delta      INT NOT NULL,
  reason     ENUM('POST_CREATE','COMMENT_CREATE','ANSWER_ACCEPTED','LOGIN_STREAK','ADMIN_ADJUST','OTHER') NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_user_points_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE board_types (
  board_type_id TINYINT AUTO_INCREMENT PRIMARY KEY,
  code          ENUM('QNA','KNOW','FREE','NOTICE') NOT NULL UNIQUE,
  name          VARCHAR(50) NOT NULL,
  description   VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE board_categories (
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  code        ENUM('QNA','KNOW','FREE','NOTICE') NOT NULL,
  name        VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE boards (
  board_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
  board_type_id TINYINT NOT NULL,
  name          VARCHAR(100) NOT NULL,
  description   VARCHAR(200),
  is_public     BOOLEAN DEFAULT TRUE,
  post_count    INT DEFAULT 0,
  last_post_at  DATETIME,
  created_at    DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_boards_type FOREIGN KEY (board_type_id) REFERENCES board_types(board_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE posts (
  post_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
  board_id   BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  title      VARCHAR(200) NOT NULL,
  content    MEDIUMTEXT NOT NULL,
  deleted    BOOLEAN DEFAULT FALSE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_posts_board FOREIGN KEY (board_id) REFERENCES boards(board_id),
  CONSTRAINT fk_posts_user  FOREIGN KEY (user_id)  REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE post_comments (
  comment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  post_id    BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  content    MEDIUMTEXT NOT NULL,
  is_answer  BOOLEAN DEFAULT FALSE,
  accepted   BOOLEAN DEFAULT FALSE,
  deleted    BOOLEAN DEFAULT FALSE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  CONSTRAINT fk_post_comments_post FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
  CONSTRAINT fk_post_comments_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE post_likes (
  post_id    BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (post_id, user_id),
  CONSTRAINT fk_post_likes_post FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
  CONSTRAINT fk_post_likes_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE post_scraps (
  post_id    BIGINT NOT NULL,
  user_id    BIGINT NOT NULL,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (post_id, user_id),
  CONSTRAINT fk_post_scraps_post FOREIGN KEY (post_id) REFERENCES posts(post_id) ON DELETE CASCADE,
  CONSTRAINT fk_post_scraps_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE reports (
  report_id   BIGINT AUTO_INCREMENT PRIMARY KEY,
  ref_type    ENUM('POST','COMMENT') NOT NULL,
  ref_id      BIGINT NOT NULL,
  reporter_id BIGINT NOT NULL,
  reason      VARCHAR(200) NOT NULL,
  status      ENUM('OPEN','IN_PROGRESS','RESOLVED','REJECTED') DEFAULT 'OPEN',
  created_at  DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_reports_user FOREIGN KEY (reporter_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE notifications (
  notification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id    BIGINT NOT NULL,
  type       VARCHAR(30) NOT NULL,
  ref_id     BIGINT,
  message    VARCHAR(200),
  is_read    BOOLEAN DEFAULT FALSE,
  created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_notifications_user FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;