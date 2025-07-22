--mysql -u video -p
--Enter password: java

--show databases;
--use modeling_schema
--show tables;

create table JANGR(
   J_CODE int,  
   J_NAME varchar(30) not null   
);
alter table JANGR add constraint JANGR_PK primary key(J_CODE);

create table GRADE(
   G_CODE int, -- 등급코드(PK) 
   G_NAME varchar(20) not null -- 등급명 	
);
alter table GRADE add constraint GRADE_PK primary key(G_CODE);

create table MOVIE(
   MV_CODE  char(5),           -- 영화코드(PK)
   MV_SUBJECT varchar(50) not null, -- 영화제목
   MV_PRODUCTION varchar(30), -- 제작사 
   MV_NATIONAL varchar(20),   -- 제작국가
   MV_BOSS varchar(30),       -- 감독
   MV_ACTOR varchar(30),      -- 주연배우 
   MV_OPENDATE date,           -- 개봉일자
   J_CODE int,           -- 장르코드(FK)
   G_CODE int            -- 등급코드(FK)
);
alter table MOVIE add constraint MOVIE_PK primary key(MV_CODE);
alter table MOVIE add constraint MOVIE_JANGR_FK foreign key(J_CODE) references JANGR on delete cascade;
alter table MOVIE add constraint MOVIE_GRADE_FK foreign key(G_CODE) references GRADE on delete cascade;

create table CHAINSTORE(
   C_CODE char(3),            -- 가맹점코드(PK)
   C_NAME varchar(30),       -- 가맹점이름
   C_BOSS varchar(30) not null, -- 대표자명  
   C_PHONE varchar(13),      -- 폰번호
   C_POST char(5),            -- 우편번호
   C_ADDR varchar(70),       -- 주소
   C_OPENDATE date            -- 개업일자
);
alter table CHAINSTORE add constraint CHAINSTORE_PK primary key(C_CODE);

create table VIDEO(
   V_CODE char(5),            -- 비디오코드(PK) 
   C_CODE char(3),            -- 가맹점코드(PK, FK)
   V_BUYDATE date,            -- 구입일 
   V_BUYAMOUNT int,        -- 구입금액
   V_CONDITION char(1),       -- 비디오상태
   MV_CODE char(5)            -- 영화코드(FK)
);
alter table VIDEO add constraint VIDEO_PK primary key(V_CODE, C_CODE);
alter table VIDEO add constraint VIDEO_CHAINSTORE_FK foreign key(C_CODE) references CHAINSTORE on delete cascade;
alter table VIDEO add constraint VIDEO_MOVIE_FK foreign key(MV_CODE) references MOVIE on delete cascade;

create table MEMBER(
   M_EMAIL varchar(50),       -- 회원이메일(PK)  
   C_CODE char(3),             -- 가맹점코드(PK,FK)
   M_NAME varchar(30),        -- 회원이름
   M_PHONE varchar(13) not null, -- 폰번호
   M_POST char(5),             -- 우편번호
   M_ADDR varchar(70),        -- 주소 
   M_JOINDATE date default current_date, -- 회원가입일
   M_POINT int              -- 포인트
);
alter table MEMBER add constraint MEMBER_PK primary key(M_EMAIL, C_CODE);
alter table MEMBER add constraint MEMBER_CHAINSTORE_FK foreign key(C_CODE) references CHAINSTORE;

create table RENT(
   R_SEQ int primary key auto_increment,               -- 대여일련번호(PK)
   R_DATE date,                -- 대여일자
   R_AMOUNT int,            -- 대여금액
   R_RETURNDATE date,          -- 반납일자
   R_DIVISION char(1),         -- 대여구분 [미반납:0, 반납완료:1]
   V_CODE char(5),             -- 비디오코드(FK)
   C_CODE char(3),             -- 가맹점코드(FK)
   M_EMAIL varchar(50)        -- 회원이메일(FK)
);
alter table RENT add constraint RENT_VIDEO_FK foreign key(V_CODE,C_CODE) references VIDEO(V_CODE,C_CODE);   
alter table RENT add constraint RENT_MEMBER_FK foreign key(M_EMAIL,C_CODE) references MEMBER(M_EMAIL,C_CODE);   

create table LATEFEE(
    R_SEQ int primary key auto_increment,             -- 대여일련번호(PK, FK)
    L_FEE int,             -- 연체료
    L_INPUTDATE date          -- 입금일자
);
alter table LATEFEE add constraint LATEFEE_FK foreign key(R_SEQ) references RENT;

create table AMOUNT(
    A_RENTDATE date,          -- 대여일자(PK) 
    A_ONEDAYAMOUNT int,    -- 일일매출액
    A_ONEDAYCOUNT int      -- 일일대여횟수
);
alter table AMOUNT add constraint AMOUNT_PK primary key(A_RENTDATE);

create table RENTAMOUNT( 
    RA_V_DIVISTION char(1),   -- 비디오구분(PK) [인기도 최하:0, 하:1, 중:2, 상:3, 최상:4]
    RA_DAYLATEFEE int,     -- 일일연체금액
    RA_AMOUNT int,         -- 대여금액
    RA_DESCRIPTION varchar(50) -- 설명
);
alter table RENTAMOUNT add constraint RENTAMOUNT_PK primary key(RA_V_DIVISTION);

-- MariaDB는 AUTO_INCREMENT 사용을 권장하므로 시퀀스 대신 AUTO_INCREMENT 적용 필요 시 별도 조치
-- create sequence RENT_SEQ increment by 1 start with 1 nocache;  -- MariaDB는 기본적으로 지원하지 않음

-- 확인용 쿼리
show tables;

select constraint_name, constraint_type, table_name 
from information_schema.table_constraints
where table_schema = database()
order by constraint_name;
