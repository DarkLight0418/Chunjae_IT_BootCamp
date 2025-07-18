-- 사원번호와 급여인상률을 넣으면 해당사원의 급여가 인상되는 프로시져

delimiter $$

drop procedure if exists INCRE;

create procedure INCRE(in N int, in R decimal(10,2))
begin
    declare NEWPAY decimal(10,2);

    select SAL into NEWPAY from EMP2 where EMPNO=N;
    set NEWPAY = NEWPAY + (NEWPAY*R);

    update EMP2 set SAL=NEWPAY where EMPNO=N;
    commit;
end$$

-- call INCRE(7369, 0.2);