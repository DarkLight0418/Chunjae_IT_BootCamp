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

-- 여성 트레이너 중 월급이 가장 높은 사람을 출력하세요
SELECT 이름, 월급
FROM 트레이너
WHERE 성별 = 'F'
ORDER BY 월급 DESC
LIMIT 1;

-- 기구 상태가 정상이고, 메이커가 삼성헬스고, 갯수가 3개인 기구를 출력하시오.
SELECT * FROM 운동기구 WHERE 상태 = '정상' AND 메이커 = '삼성헬스' AND 갯수 = 3;
-- 기구가 고장났고 사용시작일이 2020년01월17일 이후인 기구를 사용시작일이 최근인 순서로 정렬하여라.
SELECT * FROM 운동기구 WHERE 상태 = '고장' AND 사용시작일 > '2020-01-17' ORDER BY 사용시작일 DESC;
-- 기구 상태가 정상이고, 메이커가 뉴텍이고 사용시작일이 2015년12월30일 이전인 기구를 갯수가 많은순서로 정렬하여라.
SELECT * FROM 운동기구 WHERE 상태 = '정상' AND 메이커 = '뉴텍' AND 사용시작일 < '2015-12-30' ORDER BY 갯수 DESC;
-- 기구 상태가 수리중이고, 메이커가 케이짐이고 사용시작일이 오래된 순서대로 정렬하여라.
SELECT * FROM 운동기구 WHERE 상태 = '수리중' AND 메이커 = '케이짐' ORDER BY 사용시작일 ASC;
-- 기구 상태가 수리중이고, 메이커가 뉴텍이고 사용시작일이 2020년01월13일에서2023년08월09일 사이에 있는 운동기구를 모두 출력하여라 
SELECT * FROM 운동기구 WHERE 상태 = '수리중' AND 메이커 = '뉴텍' AND 사용시작일 BETWEEN '2020-01-13' AND '2023-08-09';