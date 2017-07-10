package com.insigma.mvc.component.log;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.insigma.mvc.model.SLog;
import com.insigma.mvc.service.log.LogService;

/**
 * 日志记录器
 * 
 * @author wengsh
 *
 */
@Aspect
@Component
public class SystemLogAspect {

	Log log = LogFactory.getLog(SystemLogAspect.class);
	
	@Resource
	private LogService logservice;

	// Controller层切点
	@Pointcut("execution (* com.insigma.mvc.controller..*.*(..))")
	public void controllerAspect() {
	}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	private void doBefore(JoinPoint joinPoint) {
		//log.info("执行controller前置通知");
	}

	/**
	 * 配置controller环绕通知,使用在方法aspect()上注册的切入点
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	private Object doAround(ProceedingJoinPoint  pjp) throws Throwable {
		long start = System.currentTimeMillis();
        //调用核心逻辑  
        Object retVal = pjp.proceed();  
        long end = System.currentTimeMillis();
		log.info(pjp + ",总共花费时间:" + (end - start)+ "毫秒!");
		SLog slog=new SLog();
		slog.setContent(pjp + ",总共花费时间:" + (end - start)+ "毫秒!");
		logservice.saveLogInfo(slog);
		return retVal;  
	}

	/**
	 * 后置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 */
	@After("controllerAspect()")
	private void doAfter(JoinPoint joinPoint) {
		//log.info("执行controller后置通知");
	}

	/**
	 * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * 
	 * @param joinPoint
	 */
	@AfterReturning("controllerAspect()")
	private void doReturn(JoinPoint joinPoint) {
		//log.info("执行controller后置返回通知");
	}

	/**
	 * 异常通知 用于拦截记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	private void doThrowing(JoinPoint joinPoint, Throwable e) {
		log.info("异常通知开始,异常代码:" + e.getClass().getName()+"异常信息:" + e.getMessage());
	}
}
