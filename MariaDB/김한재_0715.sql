EMPNO : 사원번호, MGR : 입사번호, COMM : 커미션, HIREDATE : 입사일

김한재_0715

정렬 이외 문제는 임의로 order by 절을 통해 보기 쉽게 정렬이 되게끔 했습니다!
* : 더 좋은 방법 찾아보기
@ : 해결 방법 알아오기 > 복습

(1) 부서번호가 10번인 부서의 사람 중 사원번호, 이름, 월급을 출력하라
select EMPNO, ENAME, SAL from EMP where DEPTNO=10;

(2) 사원번호가 7369인 사람 중 이름, 입사일, 부서번호를 출력하라
select ENAME, HIREDATE, DEPTNO from EMP where EMPNO=7369;

(3) 이름이 ALLEN 인 사람의 모든 정보를 출력하라
select * from EMP where ENAME='ALLEN';

(4) 입사일이 81/05/01 인 사원의 이름, 부서번호, 월급을 출력하라
select ENAME, DEPTNO, SAL from EMP where HIREDATE='81/05/01';

(5) 직업이 MANAGER 가 아닌 사람의 모든 정보를 출력하라
select * from EMP where JOB!='MANAGER';

(6) 입사일이 81/04/02 이후에 입사한 사원의 정보를 출력하라
select * from EMP where HIREDATE>='1981-04-02' order by HIREDATE;

(7) 급여가 $800 이상인 사람의 이름, 급여, 부서번호를 출력하라
select ENAME, SAL, DEPTNO from EMP where SAL>=800 order by SAL;

(8) 부서번호가 20번 이상인 사원의 모든 정보를 출력하라
select * from EMP where DEPTNO>=20 order by DEPTNO;

(9) 이름이 K로 시작하는 사람보다 높은 이름을 가진 사람의 이름을 출력하라
select ENAME from EMP where ENAME>='K%' order by ENAME;

(10) 입사일이 81/12/09 보다 먼저 입사한 사람들의 모든 정보를 출력하라
select * from EMP where HIREDATE<'1981-12-09' order by HIREDATE desc;

(11) 입사번호가 7698보다 작거나 같은 사람들의 입사번호와 이름을 출력하라
select MGR, ENAME from EMP where MGR>=7698 order by MGR;

(12) 입사일이 81/04/02 보다 늦고 82/12/09 보다 빠른 사원의 이름, 월급, 부서번호, 입사번호를 출력하라
select ENAME, SAL, DEPTNO, MGR from EMP where HIREDATE between '1981-04-02' and '1982-12-09' order by HIREDATE;

(13) 급여가 1,600 보다 크고 $3,000 보다 작은 사람의 이름, 직업, 급여를 출력하라
select ENAME, JOB, SAL from EMP where SAL > 1600 or SAL < 3000 order by SAL;

(14) 사원번호가 7654와 7782 사이 이외의 사원의 입사번호, 이름을 출력하라
select MGR, ENAME from EMP where not(MGR between 7654 and 7782) order by MGR;

(15) 이름이 B와 J사이의 모든 사원의 이름을 출력하라
select ENAME from EMP where ENAME between 'B' and 'J' order by ENAME;

(16) 입사일이 81년 이외에 입사한 사람의 입사일과 이름를 출력하라(*)
select HIREDATE, ENAME from EMP where not(HIREDATE between '1981-01-01' and '1981-12-31') order by HIREDATE;

(17) 직업이 MANAGER와 SALESMAN인 사람의 이름과 직업을 출력하라
select ENAME, JOB from EMP where JOB='MANAGER' or JOB='SALESMAN' order by ENAME;

(18) 부서번호가 20, 30번을 제외한 모든 사람의 이름, 사원번호, 부서번호를 출력하라
select ENAME, EMPNO, DEPTNO from EMP where DEPTNO not in(20, 30) order by DEPTNO;

(19) 이름이 S로 시작하는 사원의 사원번호, 이름, 입사일, 부서번호를 출력하라
select EMPNO, ENAME, HIREDATE, DEPTNO from EMP where ENAME like 'S%' order by ENAME;

(20) 입사일이 81년도인 사람의 입사일, 이름을 출력하라(*)
select HIREDATE, ENAME from EMP where HIREDATE between '1981-01-01' and '1981-12-31' order by HIREDATE;

(21) 이름 중 A자가 들어가 있는 사람만 입사번호, 이름을 출력하라
select MGR, ENAME from EMP where ENAME like '%A%' order by ENAME;

(22) 이름이 S로 시작하고 마지막 글자가 T인 사람의 이름을 출력하라
select ENAME from EMP where ENAME like 'S%T' order by ENAME;

(23) 이름의 두 번째 문자가 A인 사람의 이름을 출력하라
select ENAME from EMP where ENAME like '_A%' order by ENAME;

cf) 이름의 세 번째 문자가 A인 사람의 이름을 출력하라
select ENAME from EMP where ENAME like '__A%' order by ENAME;

(24) 커미션이 NULL인 사람의 이름과 커미션을 출력하라
select ENAME, COMM from EMP where COMM is null order by ENAME;

(25) 커미션이 NULL이 아닌 사람의 이름과 커미션을 출력하라
select ENAME, COMM from EMP where COMM is not null order by ENAME;

(26) 부서번호가 30번 부서이고, 급여가 $1,500 이상인 사람의 이름, 부서번호, 월급을 출력하라
select ENAME, DEPTNO, SAL from EMP where EMPNO=30 or SAL>=1500 order by SAL;

(27) 이름의 첫 글자가 K 로 시작하거나 부서번호가 30인 사람의 사원번호, 이름, 부서번호를 출력하라
select EMPNO, ENAME, DEPTNO from EMP where ENAME like 'K%' or DEPTNO=30 order by ENAME;

(28) 급여가 $1,500 이상이고, 부서번호가 30번인 사원 중 직업이

MANAGER인 사람의 급여, 부서번호, 직업을 출력하라(@)

select SAL, DEPTNO, JOB from EMP where JOB='MANAGER' all(select SAL, DEPTNO from EMP where SAL>=1500 and DEPTNO=30);


(29) 부서번호가 30인 사람의 이름,사원번호,부서번호를 사원번호로 오름 SORT(정렬)하라
select ENAME, EMPNO, DEPTNO from EMP where DEPTNO=30 order by EMPNO asc;


(30) 이름과 급여의 데이터를 급여가 많은 순으로 SORT 하라
select ENAME, SAL from EMP order by SAL desc;

(31) 부서번호로 ASCENDING SORT한 후 급여가 많은 사람 순으로 이름, 부서번호, 급여를 출력하라(@)
select ENAME, DEPTNO, SAL from EMP order by DEPTNO, SAL desc;

(32) 부서번호를 DESCENDING SORT한 후, 직업 순으로 ASCENDING SORT, 급여 순으로 DESCENDING SORT하라(@)
select * from EMP order by DEPTNO desc, JOB, SAL desc;
