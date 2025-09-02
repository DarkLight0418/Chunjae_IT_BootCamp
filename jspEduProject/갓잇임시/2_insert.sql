-- 1) user_badges (뱃지)
INSERT INTO user_badges (badge_name, description, min_points, max_points) VALUES
('새싹',   '처음 시작한 사용자',               0,    99),
('열공생', '꾸준히 활동하는 사용자',           100,  499),
('모범생', '활발한 학습 참여자',               500,  999),
('전교1등','압도적 공헌자',                    1000, NULL);

-- 2) board_types (보드 종류)
INSERT INTO board_types (code, name, description) VALUES
('QNA',   '질문과 답변',   '과목별 학습 질문/답변 공간'),
('KNOW',  '지식 공유',     '요약/정리/팁 공유'),
('FREE',  '자유 게시판',   '잡담/일상/진로 이야기'),
('NOTICE','공지사항',      '운영 공지');

-- 3) boards (보드)  ※ board_type_id는 위 INSERT 순서 기준 1:QNA, 2:KNOW, 3:FREE, 4:NOTICE
INSERT INTO boards (board_type_id, name, description, is_public) VALUES
(1, 'Q&A 게시판',     '과목 질문/답변 전용', TRUE),
(2, '지식 공유',       '요약/정리/학습자료 공유', TRUE),
(3, '자유 게시판',     '일상/수다/정보', TRUE),
(4, '공지사항',        '운영 공지/이벤트 안내', TRUE);

-- 4) board_categories (카테고리)
-- Q&A에서 사용하는 과목 카테고리들
INSERT INTO board_categories (code, name) VALUES
('QNA','국어'),
('QNA','수학'),
('QNA','영어'),
('QNA','과학'),
('QNA','물리'),
('QNA','화학'),
('QNA','생명과학'),
('QNA','지구과학'),
('QNA','사회'),
('QNA','한국사'),
('QNA','정보'),
('QNA','기술가정'),
('QNA','미술'),
('QNA','체육');

-- 5) users (회원) — 고등학생 10명
INSERT INTO users (username, email, password_hash, img_name, nickname, total_points, badge_id, status) VALUES
('kim_minsu',     'minsu.kim@hs.example.com',     'hash_pw01', 'default.png', '민수',   80,   NULL, 'ACTIVE'),
('park_jiyoon',   'jiyoon.park@hs.example.com',   'hash_pw02', 'jiyoon.png',  '지윤',  150,  NULL, 'ACTIVE'),
('lee_hyeonwoo',  'hyeonwoo.lee@hs.example.com',  'hash_pw03', 'default.png', '현우',  520,  NULL, 'ACTIVE'),
('choi_sua',      'sua.choi@hs.example.com',      'hash_pw04', 'sua.png',     '수아',  40,   NULL, 'ACTIVE'),
('jung_yujin',    'yujin.jung@hs.example.com',    'hash_pw05', 'default.png', '유진',  1020, NULL, 'ACTIVE'),
('yoon_dohyun',   'dohyun.yoon@hs.example.com',   'hash_pw06', 'default.png', '도현',  310,  NULL, 'ACTIVE'),
('han_seoyun',    'seoyun.han@hs.example.com',    'hash_pw07', 'seoyun.png',  '서윤',  610,  NULL, 'ACTIVE'),
('kang_jaemin',   'jaemin.kang@hs.example.com',   'hash_pw08', 'default.png', '재민',  220,  NULL, 'ACTIVE'),
('oh_arin',       'arin.oh@hs.example.com',       'hash_pw09', 'arin.png',    '아린',  980,  NULL, 'ACTIVE'),
('bae_junseo',    'junseo.bae@hs.example.com',    'hash_pw10', 'default.png', '준서',  55,   NULL, 'SUSPENDED');

-- 6) posts (게시글) — Q&A/공유/자유/공지 혼합, 내용은 고등학생 맥락
--  Q&A 게시판(board_id=1) 위주로 과목성 질문을 구성
INSERT INTO posts (board_id, user_id, title, content) VALUES
(1, 1, '수학 함수 극한 문제 도와주세요', '수열 극한에서 ε-정의가 헷갈립니다. 간단 예시로 설명 부탁드려요!'),
(1, 2, '영어 간접의문문 어순 질문', '간접의문문에서 do/does/did가 언제 생략되는지 궁금합니다.'),
(1, 3, '물리 역학 문제 풀이', '등가속도 운동에서 시간-변위 그래프 해석이 어려워요.'),
(1, 4, '국어 문법-피동/사동 구분', '피동/사동 구분 연습문제에서 헷갈리는 문장들 질문드립니다.'),
(2, 5, '한국사 근현대사 연표 PDF 공유', '시험 대비로 정리한 연표입니다. 학교 친구들 참고하세요!'),
(3, 6, '시험기간 스트레스 관리 팁', '여러분은 어떻게 멘탈 관리하시나요? 음악 추천도 환영!'),
(4, 5, '중간고사 대비 공지', '이번 주 금요일, 과목별 질의응답 라이브 세션 공지합니다.'),
(1, 7, '화학 몰농도/몰랄 농도 차이', '정의는 알겠는데 문제 풀 때 자꾸 혼동돼요. 팁 있을까요?'),
(1, 8, '생명과학 유전 단원', '상염색체/성염색체 유전에서 가계도 해석 팁 부탁드립니다.'),
(1, 9, '지구과학 대기 대순환', '행성풍과 제트기류의 관계를 간단하게 설명해주실 분?');

-- 7) post_comments (댓글) — 일부는 답변/채택 표시
INSERT INTO post_comments (post_id, user_id, content, is_answer, accepted) VALUES
(1, 3, 'ε-정의는 직관보다 형식화 도구라 간단 예시로 a_n=1/n 보시면 좋아요.', TRUE,  TRUE),
(1, 7, '그래프 기반 접근으로도 이해해보세요. 좌우로 ε, 위아래로 δ 생각!', FALSE, FALSE),
(2, 4, '간접의문문에는 평서 어순이고 조동사는 종종 생략됩니다.', TRUE,  FALSE),
(3, 2, '가속도 일정이면 x-t 그래프는 포물선 형태입니다.', TRUE,  TRUE),
(4, 1, '‘-이/히/리/기’가 붙는 피동 접미사 패턴을 먼저 잡아보세요.', FALSE, FALSE),
(8, 9, '가계도는 유전 양식 추정 → 대립유전자 가정 → 검증 순으로!', TRUE,  FALSE),
(9, 10, '대기 대순환은 해들리/페렐/극순환으로 3셀 모델이 기본이에요.', TRUE, FALSE);

-- 8) post_likes (좋아요)
INSERT INTO post_likes (post_id, user_id) VALUES
(1, 2), (1, 4), (1, 6),
(2, 1), (2, 5),
(3, 7),
(5, 3), (5, 8),
(6, 9),
(8, 1), (8, 4);

-- 9) post_scraps (스크랩)
INSERT INTO post_scraps (post_id, user_id) VALUES
(1, 5),
(2, 3),
(3, 4),
(5, 2),
(8, 6),
(9, 1);

-- 10) reports (신고) — 예시 1~2건
INSERT INTO reports (ref_type, ref_id, reporter_id, reason, status) VALUES
('POST',    6,  4, '주제와 무관한 홍보 의심(예시)', 'OPEN'),
('COMMENT', 2,  8, '과도한 비꼼 표현(예시)',       'IN_PROGRESS');