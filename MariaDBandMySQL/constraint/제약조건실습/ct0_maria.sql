-- 1. 기존 테이블 삭제
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT 테이블 생성 (PK, DEFAULT, CHECK 부여예정)
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14),
    LOC CHAR(1)
);

-- 3. CT_EMP 테이블 생성 (PK, NOT NULL, CHECK, UNIQUE, DEFAULT, FK 부여예정)
create table CT_EMP (
    NO INT,
    NAME VARCHAR(10),
    ADDR VARCHAR(6),
    JUMIN VARCHAR(13),
    RDATE DATETIME,
    DEPTNO TINYINT
);