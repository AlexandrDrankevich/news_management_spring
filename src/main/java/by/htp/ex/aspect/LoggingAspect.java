package by.htp.ex.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	private Logger log = LogManager.getLogger();

	@AfterThrowing(pointcut = "execution(* by.htp.ex.dao.service.impl.*(..))", throwing = "theExc")
	public void afterThrowingsAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out which method we are advising on
		//String method = theJoinPoint.getSignature().toShortString();
		//System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		System.out.println("\n=====>>> The exception is: " + theExc);
		log.error(theExc);

	}

}
