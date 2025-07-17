-- 1. ���̺� ����
drop table if exists EMP3;
drop table if exists DEPT3;

-- 2. Oracle�� `purge recyclebin`�� MariaDB�� ����

-- 3. ���̺� ���� (���� ���̺��� ����)
-- MariaDB�� `CREATE TABLE ... AS SELECT ...`�� ���������� ���������� ������� ����

create table DEPT3 as select * from DEPT;
create table EMP3 as select * from EMP;

-- 4. ���� ���� �߰�
alter table DEPT3 
  add constraint DEPT3_PK primary key (DEPTNO);

alter table EMP3 
  add constraint EMP3_PK primary key (EMPNO);

alter table EMP3 
  add constraint EMP3_FK foreign key (DEPTNO) 
  references DEPT3(DEPTNO) 
  on delete cascade;

-- 5. ���̺� ���� Ȯ��
desc DEPT3;
desc EMP3;

-- 6. ������ Ȯ��
select * from DEPT3;
select * from EMP3;

-- 7. �������� Ȯ��
select 
    TABLE_NAME, 
    CONSTRAINT_NAME, 
    CONSTRAINT_TYPE 
from information_schema.TABLE_CONSTRAINTS
where TABLE_NAME IN ('DEPT3', 'EMP3')
  and CONSTRAINT_SCHEMA = DATABASE();
