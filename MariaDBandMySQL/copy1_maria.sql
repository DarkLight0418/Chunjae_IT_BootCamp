-- 1. ���̺� ����
drop table if exists EMP2;
drop table if exists DEPT2;

-- Oracle�� purge recyclebin�� MariaDB���� ����

-- 2. ���̺� ����
create table DEPT2 (
    DEPTNO TINYINT primary key,
    DNAME VARCHAR(14),
    LOC VARCHAR(13)
);

create table EMP2 (
    EMPNO INT primary key,
    ENAME VARCHAR(10),
    JOB VARCHAR(9),
    MGR INT,
    HIREDATE DATE,
    SAL DECIMAL(7,2),
    COMM DECIMAL(7,2),
    DEPTNO TINYINT,
    constraint EMP2_FK foreign key (DEPTNO) references DEPT2(DEPTNO) on delete cascade
);

-- 3. ���� ���̺��� ������ ����
insert into DEPT2 select * from DEPT;
insert into EMP2 select * from EMP;

commit;

-- 4. ���̺� ���� Ȯ��
-- MariaDB������ DESC �Ǵ� SHOW COLUMNS ���
desc DEPT2;
desc EMP2;

-- 5. ������ Ȯ��
select * from DEPT2;
select * from EMP2;

-- 6. ���� ���� Ȯ��
-- MariaDB������ `information_schema`�� ����ؾ� ��
select 
    TABLE_NAME, 
    CONSTRAINT_NAME, 
    CONSTRAINT_TYPE 
from information_schema.TABLE_CONSTRAINTS 
where TABLE_NAME IN ('DEPT2', 'EMP2')
  and CONSTRAINT_SCHEMA = DATABASE();
