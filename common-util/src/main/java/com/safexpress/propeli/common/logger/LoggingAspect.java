package com.safexpress.propeli.common.logger;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.safexpress.propeli.*.controller.*.*(..)) || execution(* com.safexpress.propeli.*.service.*.*(..)) || execution(* com.safexpress.propeli.*.integration.*.*(..)) || execution(* com.safexpress.propeli.*.dao.*.*(..))")
	private void selectAll() {
		// Do nothing
	}

	@AfterReturning(pointcut = "execution(* com.safexpress.propeli.*.controller.*.*(..))", returning = "retVal")
	public void logAfterMethod(JoinPoint joinPoint, Object retVal) {
		Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		log.debug("Method {} , Returning {}", joinPoint.getSignature().getName(), retVal);
	}

	@Before("selectAll()")
	public void logBeforeMethod(JoinPoint joinPoint) {
		Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		log.debug("Entering {} with params {}", joinPoint.getSignature().getName(),
				Arrays.toString(joinPoint.getArgs()));

	}

	@After("selectAll()")
	public void logAfterMethod(JoinPoint joinPoint) {
		Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		log.debug("Exiting {}", joinPoint.getSignature().getName());
	}

	@AfterThrowing(pointcut = "selectAll()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		Logger log = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		log.error("Exception Thrown", e.getCause());
	}

	@Pointcut("execution(* com.safexpress.propeli.*.controller.*.*(..))")
	public void businessMethods() {
		// Do nothing
	}

	@Around("businessMethods()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		Logger log = LoggerFactory.getLogger(pjp.getTarget().getClass());
		try {
			long start = System.currentTimeMillis();
			Object output = pjp.proceed();
			Long elapsedTime = System.currentTimeMillis() - start;
			MDC.put("API", pjp.getSignature().toLongString());
			MDC.put("executionTime", String.valueOf(elapsedTime));
			log.debug("Method {} execution time: {} milliseconds", pjp.getSignature().getName(), elapsedTime);
			return output;
		} finally {
			MDC.clear();
		}
	}
}
