-- 사원번호와 급여인상률을 넣어면 해당사원의 급여가 인상되는 프로시져

delimiter $$
drop procedure if exists INCRE2;

create procedure INCRE2(in N int, in R decimal(5,2))
begin
    declare NEWPAY decimal(10,2);

    select SAL into NEWPAY from EMP2 where EMPNO=N;
    set NEWPAY = NEWPAY + (NEWPAY*R/100);

    update EMP2 set SAL=NEWPAY where EMPNO=N;
    commit;	
end$$

delimiter ;


-- call INCRE2(7839, 10);
-- select * from EMP2 where EMPNO=7839;