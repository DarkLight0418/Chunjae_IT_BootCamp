drop table if exists DEPT2;
drop table if exists EMP2;

create table DEPT2 (
  DEPTNO int(2) PRIMARY KEY,
  DNAME VARCHAR(14),
  LOC   VARCHAR(13)
);

create table EMP2 (
  DEPTNO INT(2),
  EMPNO  INT(4),
  ENAME  VARCHAR(10),
  JOB  VARCHAR(9),
  MGR INT(4),
  HITEDATE date,
  SAL DECIMAL(7, 2),
  COMM DECIMAL(7, 2),

  primary key (DEPTNO, EMPNO),
  foreign key (DEPTNO) references DEPT(DEPTNO)
);