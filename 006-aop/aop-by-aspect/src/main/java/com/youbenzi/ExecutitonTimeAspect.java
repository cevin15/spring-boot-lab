package com.youbenzi;

import java.util.Date;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutitonTimeAspect {

	private Logger logger = Logger.getLogger(HelloController.class.getName());
	
	@Pointcut("execution(* com.youbenzi..*.*(..)) && args(userservice,..)")
	private void pointcut(UserService userservice) {}
	
	@Around("@annotation(com.youbenzi.ExecutionTime)")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		Signature signnature = joinPoint.getSignature();
		logger.info(signnature + " 开始执行：" + new Date());
		long current = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		logger.info(signnature + " 执行结束：" + new Date());
		logger.info(signnature + " 执行时间为：" + (System.currentTimeMillis() - current) + " ms");
		return result;
	}
	
//	@Before("execution(* com.youbenzi..*.*(..)) && args(userservice,..)")
	@Before("pointcut(userservice)")
	public void before(UserService userservice) {
		System.out.println(userservice.getNum());
		logger.info("开始执行：" + new Date());
	}
	
}
