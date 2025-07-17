-- 1. ���� ���̺� ����
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT ���̺� ���� (PK, DEFAULT, CHECK �ο�����)
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14),
    LOC CHAR(1)
);

-- 3. CT_EMP ���̺� ���� (PK, NOT NULL, CHECK, UNIQUE, DEFAULT, FK �ο�����)
create table CT_EMP (
    NO INT,
    NAME VARCHAR(10),
    ADDR VARCHAR(6),
    JUMIN VARCHAR(13),
    RDATE DATETIME,
    DEPTNO TINYINT
);