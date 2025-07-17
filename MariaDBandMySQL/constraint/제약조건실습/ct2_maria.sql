-- 1. ���� ���̺� ����
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT ���̺� ���� 
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14) default '���ߺ�',
    LOC CHAR(1),
    primary key(DEPTNO), 
    check ( LOC in ('1', '2'))
);

-- 3. CT_EMP ���̺� ���� 
create table CT_EMP (
    NO INT,
    NAME VARCHAR(10) not null,
    ADDR VARCHAR(6),
    JUMIN VARCHAR(13),
    RDATE DATETIME default current_timestamp,
    DEPTNO TINYINT, 
    primary key(NO), 
    check ( ADDR in ('����', '�λ�')), 
    unique(JUMIN), 
    foreign key(DEPTNO) references CT_DEPT(DEPTNO)	
);