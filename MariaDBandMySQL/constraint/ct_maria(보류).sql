-- 1. 기존 테이블 삭제
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT 테이블 생성
create table CT_DEPT (
    DEPTNO TINYINT constraint CT_DEPT_PK primary key,
    DNAME VARCHAR(14) default '개발부',
    LOC CHAR(1) constraint CT_DEPT_CK check( LOC in ('1', '2'))
);

-- 3. CT_EMP 테이블 생성
create table CT_EMP (
    NO INT primary key,
    NAME VARCHAR(10) not null,
    ADDR VARCHAR(6) constraint CT_EMP_CK check( ADDR in ('서울', '부산')),
    JUMIN VARCHAR(13) constraint CT_EMP_UQ unique,
    RDATE DATETIME default current_timestamp,
    DEPTNO TINYINT constraint CT_EMP_FK references CT_DEPT(DEPTNO)
);