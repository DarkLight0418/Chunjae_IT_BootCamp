-- 1. ���� ���̺� ����
drop table if exists CT_EMP;
drop table if exists CT_DEPT;

-- 2. CT_DEPT ���̺� ���� 
create table CT_DEPT (
    DEPTNO TINYINT,
    DNAME VARCHAR(14),
    LOC CHAR(1)
);
alter table CT_DEPT add primary key(DEPTNO);
alter table CT_DEPT add constraint CT_DEPT_CK check ( LOC in ('1', '2'));
alter table CT_DEPT modify DNAME VARCHAR(14) default '���ߺ�';

-- 3. CT_EMP ���̺� ���� 
create table CT_EMP (
    NO INT,
    NAME VARCHAR(10) not null,
    ADDR VARCHAR(6),
    JUMIN VARCHAR(13),
    RDATE DATETIME,
    DEPTNO TINYINT
);
alter table CT_EMP add primary key(NO);
alter table CT_EMP add constraint CT_EMP_CK check (ADDR in ('����', '�λ�'));
alter table CT_EMP add constraint CT_EMP_UQ unique(JUMIN);
alter table CT_EMP add constraint CT_EMP_FK foreign key(DEPTNO) references CT_DEPT(DEPTNO);
alter table CT_EMP modify RDATE DATETIME default current_timestamp;


