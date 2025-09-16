package khj.md.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import lombok.extern.log4j.Log4j;

@Log4j
@Aspect
@Component
public class SampleLogAdvice { //Aspect을 지시하는 클래스
	@Before("execution(* soo.md.service.SampleLogService*.*(..))") //pointcut표현식 
	public void logBefore() {
		log.info("#(3) logBefore() 수행");
	}
	@Around("execution(* soo.md.service.SampleLogService*.*(..))")
	public Object longTime(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		
		//로직 메소드 수행 
		log.info("#(1) longTime() target class: " + pjp.getTarget().getClass());
		log.info("#(2) longTime() args: " + Arrays.deepToString(pjp.getArgs()));
		
		Object result = pjp.proceed(); //비지니스 메소드로 진행하도록 하는 메소드 
		
		long end = System.currentTimeMillis();
		log.info("#(4) longTime() 수행시간: " + (end-start) + "ms");
		
		return result;
	}
	@After("execution(* soo.md.service.SampleLogService*.*(..))")
	public void logAfter() {
		log.info("#(5) logAfter() 수행");
	}
	@AfterThrowing(pointcut="execution(* soo.md.service.SampleLogService*.*(..))", throwing="exception")
	public void logAfterThrowing(Exception exception) {
		log.info("#(6) logAfterThrowing() 수행 exception: " + exception);
	}
}
