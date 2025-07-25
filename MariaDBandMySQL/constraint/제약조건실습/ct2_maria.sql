-- 1. 기존 테이블 삭제
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT 테이블 생성 
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14) default '개발부',
    LOC CHAR(1),
    primary key(DEPTNO), 
    check ( LOC in ('1', '2'))
);

-- 3. CT_EMP 테이블 생성 
create table CT_EMP (
    NO INT,
    NAME VARCHAR(10) not null,
    ADDR VARCHAR(6),
    JUMIN VARCHAR(13),
    RDATE DATETIME default current_timestamp,
    DEPTNO TINYINT, 
    primary key(NO), 
    check ( ADDR in ('서울', '부산')), 
    unique(JUMIN), 
    foreign key(DEPTNO) references CT_DEPT(DEPTNO)	
);