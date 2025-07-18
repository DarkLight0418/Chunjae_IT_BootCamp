< JOIN & SUB-QUERY >

김한재_0718
(*) 복습!

(1) EMP와 DEPT TABLE을 JOIN하여 부서번호, 부서명, 이름, 급여를 출력하라.
select e.DEPTNO, d.DNAME, e.ENAME, e.SAL from EMP e join DEPT d on e.DEPTNO=d.DEPTNO;

(2) 이름이 'ALLEN'인 사원의 부서명을 출력하라. 
select DNAME from DEPT where DEPTNO=(select DEPTNO from EMP where ENAME='ALLEN');

(3) EMP Table에 있는 DATA와 JOIN하여 모든 사원의 이름, 부서번호, 부서명, 급여를 출력하라.
select ENAME, DEPT.DEPTNO, DNAME, SAL from EMP join DEPT on EMP.DEPTNO=DEPT.DEPTNO;

(4) EMP Table에 있는 EMPNO와 MGR을 이용하여 서로의 관계를 다음과 같이 출력하라. (*)
select concat(e2.ENAME, "는 " ,e1.ENAME, "의 매니저입니다.") from EMP e1 join EMP e2 on e1.EMPNO=e2.MGR;

(5) 'ALLEN'의 직무와 같은 사람의 이름, 부서명, 급여, 직무를 출력하라. (*)
select ENAME, EMPNO, SAL, JOB from EMP where JOB=(select JOB from EMP where ENAME='ALLEN');

(6) 'JONES'가 속해있는 부서의 모든 사람의 부서번호, 사원번호, 이름, 입사일,
급여를 출력하라.
select DEPTNO, EMPNO, ENAME, HIREDATE, SAL from EMP where DEPTNO=(select DEPTNO from EMP where ENAME='JONES');

(7) 전체 사원의 평균임금보다 많은 사원의 사원번호, 이름, 부서명, 입사일, 지역,
급여를 출력하라. (*)
select e.EMPNO, e.ENAME, d.DNAME, e.HIREDATE, d.LOC, e.SAL from DEPT d, EMP e where e.SAL > (select avg(SAL) from EMP); 

(8) 10번 부서 사람들 중에 20번 부서의 사원과 같은 업무를 하는 사원의 사원번호,
업무, 이름, 부서명, 입사일, 지역을 출력하라. (*)
select e.EMPNO, e.JOB, e.ENAME, d.DNAME, e.HIREDATE, d.LOC from EMP e join DEPT d on e.DEPTNO=d.DEPTNO where e.DEPTNO=10 and JOB in (select distinct JOB from EMP where DEPTNO=20);

(9) 10번 부서중에서 30번 부서에는 없는 업무를 하는 사원의 사원번호, 업무,
이름, 부서명, 입사일, 지역을 출력하라. (*)
select EMPNO, JOB, ENAME, DNAME, HIREDATE, LOC from EMP join DEPT on EMP.DEPTNO=DEPT.DEPTNO where EMP.DEPTNO=10 and JOB not in (select distinct JOB from EMP where DEPTNO=30);

(10) 10번 부서와 같은 일을 하는 사원의 사원번호, 업무, 이름, 부서번호, 부서명,
지역, 급여를 급여가 많은 순으로 출력하라. (*)
select E.EMPNO, E.JOB, E.ENAME, E.DEPTNO, D.DNAME, D.LOC, E.SAL from EMP E join DEPT D on E.DEPTNO=D.DEPTNO where E.JOB in (select distinct job from EMP where DEPTNO = 10) order by E.SAL desc;

(11) 'MARTIN'이나 'SCOTT'의 급여와 같은 사원의 사원번호, 이름, 급여를 출력하라. (*)
select EMPNO, ENAME, SAL from EMP where SAL in (select SAL from EMP where ENAME='MARTIN' or ENAME='SCOTT');

(12) 급여가 30번 부서의 최고 급여보다 높은 사원의 사원번호, 이름, 급여를 출력하라. (*)
select EMPNO, ENAME, SAL from EMP where SAL > (select max(SAL) from EMP where DEPTNO=30);

(13) 급여가 30번 부서의 최저 급여보다 낮은 사원의 사원번호, 이름, 급여를 출력하라. (*)
select EMPNO, ENAME, SAL from EMP where SAL < (select min(SAL) from EMP where DEPTNO=30);