-- 1. ���� ���̺� ����
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT ���̺� ���� 
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14) default '���ߺ�',
    LOC CHAR(1),
    primary key(DEPTNO), 
    constraint CT_DEPT_CK check ( LOC in ('1', '2'))
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
    constraint CT_EMP_CK check ( ADDR in ('����', '�λ�')), 
    constraint CT_EMP_UQ unique(JUMIN), 
    constraint CT_EMP_FK foreign key(DEPTNO) references CT_DEPT(DEPTNO)	
);