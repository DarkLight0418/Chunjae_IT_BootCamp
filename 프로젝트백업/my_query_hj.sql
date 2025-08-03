-- 1. 운동 목표 달성률 (하루 소모 칼로리 / 하루 목표 칼로리 * 100)

UPDATE statistics s
-- user_survey 테이블과 user_id 기준으로 JOIN
JOIN user_survey u ON s.user_key = u.user_key
-- 목표 달성률(goal_achievement_rate)을 계산하여 설정
SET s.goal_achievement_rate = 
    -- (소모 칼로리 / 목표 칼로리) * 100 값을 소수점 2자리로 반올림
    -- 단, 100%를 초과할 경우 LEAST 함수로 최대 100%로 제한
    LEAST(100, ROUND((s.calories_burned / u.goal_daily_calories) * 100, 2))
-- 목표 칼로리 값이 존재하고, 0보다 클 경우에만 계산 수행 (0으로 나누는 오류 방지)
WHERE u.goal_daily_calories IS NOT NULL AND u.goal_daily_calories > 0;


-- 2. workout_record에서 유저별, 날짜별로 세트/횟수/중량 합계를 구해서 통계 테이블에 반영하는 쿼리
UPDATE statistics s
JOIN (
    SELECT 
        user_key,        -- 사용자 고유 식별자
        date,            -- 운동 일자
        SUM(sets) AS total_sets,  -- 총 세트 수
        SUM(reps * sets) AS total_reps,  -- 총 반복 횟수 (세트 수 * 반복 수)
        SUM(reps * sets * weight) AS total_weight_volume  -- 총 볼륨 (세트 * 반복 * 중량)
    FROM workout_record
    GROUP BY user_key, date  -- 사용자별, 날짜별 집계
) wr ON s.user_key = wr.user_key AND s.date = wr.date  -- 기준 일치 조건
SET 
    s.total_sets = wr.total_sets,
    s.total_reps = wr.total_reps,
    s.total_weight_volume = wr.total_weight_volume;  -- statistics에 값 갱신

-- 3. user_survey 하루 목표 칼로리 컬럼 추가 쿼리문

ALTER TABLE user_survey
ADD COLUMN goal_daily_calories INT DEFAULT NULL;

