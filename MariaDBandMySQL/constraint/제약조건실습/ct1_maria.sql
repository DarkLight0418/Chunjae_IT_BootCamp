-- 1. ���� ���̺� ����
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT ���̺� ���� 
create table CT_DEPT (
    DEPTNO TINYINT primary key,
    DNAME VARCHAR(14) default '���ߺ�',
    LOC CHAR(1) check ( LOC in ('1', '2'))
);

-- 3. CT_EMP ���̺� ���� 
create table CT_EMP (
    NO INT primary key,
    NAME VARCHAR(10) not null,
    ADDR VARCHAR(6) check ( ADDR in ('����', '�λ�')),
    JUMIN VARCHAR(13) unique,
    RDATE DATETIME default current_timestamp,
    DEPTNO TINYINT references CT_DEPT(DEPTNO)
);