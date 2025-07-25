SELECT id, 이름, 전화번호, email
FROM 회원카드
WHERE PT_여부 = 1;

SELECT id, 이름, 이용_기간, 성별, 나이
FROM 회원카드
WHERE 성별 = 'F' AND 이용_기간 IN ('6개월', '12개월');

SELECT 이름, 나이, 라커룸_여부
FROM 회원카드
WHERE 라커룸_여부 = 1 AND 나이 >= 30;

SELECT *
FROM 회원카드
WHERE 시작일 = '2025-07-01';

SELECT id, 이름, email
FROM 회원카드
WHERE 이름 LIKE '%윤%' OR email LIKE '%yoon%';

-- 월급이 280만원 이상인 트레이너를 출력하세요

SELECT 이름, 월급, 자격증_및_경력
FROM 트레이너
WHERE 월급 >= 2800000.0;

-- 월요일에 근무하는 트레이너를 출력하세요
SELECT 이름, 근무요일
FROM 트레이너
WHERE 근무요일 LIKE '%월%';

-- 헬스 트레이너 자격증을 보유한 트레이너를 출력하세요
SELECT 트레이너_ID, 이름, 자격증_및_경력
FROM 트레이너
WHERE 자격증_및_경력 LIKE '헬스 트레이너%';

-- 근무시간이 8시부터 시작하는 트레이너를 출력하세요
SELECT 이름, 근무_시간표
FROM 트레이너
WHERE 근무_시간표 LIKE '08:%';

-- 여성 트레이너 중 월급이 가장 높은 사람을 춯력하세요
SELECT 이름, 월급
FROM 트레이너
WHERE 성별 = 'F'
ORDER BY 월급 DESC
LIMIT 1;
