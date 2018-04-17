package com.youbenzi;

import java.util.Date;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutitonTimeAspect {

	private Logger logger = Logger.getLogger(HelloController.class.getName());
	
	@Pointcut("execution(* com.youbenzi..*.*(..)) && args(num,..)")
//	@Pointcut("execution(* com.youbenzi..*.*(..))")
	private void pointcut(int num) {}
	
	@Around("@annotation(com.youbenzi.ExecutionTime)")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signnature = joinPoint.getSignature();
		long current = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		logger.info(signnature + " 执行时间为：" + (System.currentTimeMillis() - current) + " ms");
		return result;
	}
	
	@Before("pointcut(num)")
//	@Before("pointcut()")
	public void before(int num) {
		logger.info("num -> " + num);
		logger.info("开始执行：" + new Date());
	}

	@After("pointcut(num)")
//	@After("pointcut()")
	public void after(int num) {
		logger.info("num -> " + num);
		logger.info("执行结束：" + new Date());
	}
}
