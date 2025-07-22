SELECT 성별, COUNT(*) AS 인원수
FROM 회원카드
GROUP BY 성별;

SELECT (전기세 + 수도세 + PT_트레이너_월급 + 월세 + 소모품비) AS 총_유지비
FROM 유지비
LIMIT 1;

SELECT 이름, 
       LENGTH(담당_회원_리스트) - LENGTH(REPLACE(담당_회원_리스트, ',', '')) + 1 AS 수업횟수
FROM 트레이너
WHERE 담당_회원_리스트 IS NOT NULL AND 담당_회원_리스트 != '';

SELECT 이름, DATEDIFF(종료일, CURDATE()) AS 남은_이용일수
FROM 회원카드
WHERE 이름 = '김예나';

SELECT 이름, PT_수업_정보
FROM 트레이너;

SELECT 이름, CONCAT('예정 수업 시간: ', 근무_시간표) AS 예정_수업
FROM 트레이너;

SELECT CONCAT('샴푸 ', 샴푸, ', 수건 ', 수건, ', 운동복 ', 운동복, ', 휴지 ', 휴지, ', 종이컵 ', 종이컵) AS 소모품_현황
FROM 소모품
LIMIT 1;

SELECT 기구명, 갯수
FROM 운동기구
WHERE 상태 = '정상';

SELECT DATE_FORMAT(시작일, '%Y-%m') AS 가입월, COUNT(*) AS 인원수
FROM 회원카드
WHERE 성별 = 'M' AND 나이 >= 20
GROUP BY 가입월;
