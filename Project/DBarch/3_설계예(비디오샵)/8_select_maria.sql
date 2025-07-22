-- <1> 회원 정보
select M_NAME, M_PHONE, M_POST, M_ADDR, M_JOINDATE
from MEMBER;

-- <2> 비디오 테이프 목록: 장르명, 등급명, 비디오코드, 가맹점코드
select 
    V.V_CODE, V.C_CODE,
    J.J_NAME as 장르명,
    G.G_NAME as 등급명
from VIDEO V
join MOVIE M on V.MV_CODE = M.MV_CODE
join JANGR J on M.J_CODE = J.J_CODE
join GRADE G on M.G_CODE = G.G_CODE;

-- <3> 영화 상세정보: 제목, 제작자, 제작국가, 주연배우, 감독, 개봉일자
select 
    MV_SUBJECT, MV_PRODUCTION, MV_NATIONAL,
    MV_ACTOR, MV_BOSS, MV_OPENDATE
from MOVIE;

-- <4> 각 비디오의 파손여부, 대여여부
select 
    V_CODE, C_CODE, V_CONDITION
from VIDEO;

-- <5> 반납 예정일 초과 회원 정보 및 연체료
select 
    R.R_SEQ, R.M_EMAIL, R.V_CODE, R.R_DATE, R.R_RETURNDATE,
    L.L_FEE, L.L_INPUTDATE
from RENT R
join LATEFEE L on R.R_SEQ = L.R_SEQ
where R.R_DIVISION = '0'    -- 미반납
  and R.R_RETURNDATE < current_date();

-- <6> 회원별 대여횟수 및 포인트 (1회 = 1점), 무료 대여 가능 횟수
select 
    M_EMAIL, 
    count(*) as RENT_COUNT,
    count(*) as POINT,
    floor(count(*) / 10) as FREE_RENT_ELIGIBLE_COUNT
from RENT
group by M_EMAIL;

-- <7-1> 일별 매출액
select 
    A_RENTDATE as 날짜,
    A_ONEDAYAMOUNT as 매출액
from AMOUNT;

-- <7-2> 월별 매출액
select 
    date_format(A_RENTDATE, '%Y-%m') as 월,
    sum(A_ONEDAYAMOUNT) as 매출액
from AMOUNT
group by 월;

-- <7-3> 연도별 매출액
select 
    year(A_RENTDATE) as 연도,
    sum(A_ONEDAYAMOUNT) as 매출액
from AMOUNT
group by 연도;

-- <7-4> 비디오별 대여횟수
select 
    V_CODE, C_CODE, 
    count(*) as RENT_COUNT
from RENT
group by V_CODE, C_CODE;

-- <8> 회원별 연체 이력 (회원 ID, 연체일자, 연체료, 비디오번호)
select 
    R.M_EMAIL, R.V_CODE, R.C_CODE, 
    R.R_DATE, R.R_RETURNDATE,
    L.L_INPUTDATE, L.L_FEE
from RENT R
join LATEFEE L on R.R_SEQ = L.R_SEQ
order by R.M_EMAIL, L.L_INPUTDATE;

-- <9> 가장 많이 대여된 영화 순위 (영화 제목, 대여횟수)
select 
    M.MV_SUBJECT, 
    count(*) as RENT_COUNT
from RENT R
join VIDEO V on R.V_CODE = V.V_CODE and R.C_CODE = V.C_CODE
join MOVIE M on V.MV_CODE = M.MV_CODE
group by M.MV_SUBJECT
order by RENT_COUNT desc
limit 10;

-- <10> 최근 1개월 내 대여 활동 내역 (회원 ID, 대여일자, 비디오번호)
select 
    M_EMAIL, V_CODE, C_CODE, R_DATE
from RENT
where R_DATE >= current_date() - interval 1 month
order by R_DATE desc;

-- <11> 특정 등급/장르별 대여 비율 분석 (등급명, 장르명, 대여횟수, 전체 대비 비율 %)
select 
    G.G_NAME as 등급명,
    J.J_NAME as 장르명,
    count(*) as RENT_COUNT,
    round(count(*) / (select count(*) from RENT) * 100, 2) as RENT_PERCENT
from RENT R
join VIDEO V on R.V_CODE = V.V_CODE and R.C_CODE = V.C_CODE
join MOVIE M on V.MV_CODE = M.MV_CODE
join GRADE G on M.G_CODE = G.G_CODE
join JANGR J on M.J_CODE = J.J_CODE
group by G.G_NAME, J.J_NAME
order by RENT_COUNT desc;
