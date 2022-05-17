package com.project.util;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@EnableAspectJAutoProxy
@Component
@Aspect
public class LogAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

	@Around("execution(* com.project.ManagementProgram.*Controller.*(..))")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{
		long start = System.currentTimeMillis();
		
		Object result = pjp.proceed();
		
		long end = System.currentTimeMillis();
		
		logger.info(String.format("%s is called, %s", pjp.getSignature().toString(), (end-start)+"ms"));
		
		return result;
	}
}
