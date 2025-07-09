package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class EmployeeLoggingAspect {

    // set up logger
    private Logger logger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.controller.ThymeleafController.*(..))")
    private void forController() {}

    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.service.Employee*.*(..))")
    private void forService() {}

    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.Employee*.*(..))")
    private void forDAO() {}

    @Pointcut("forController() || forService() || forDAO()")
    private void forEmployeeAppFlow() {}

    // add @Before advice
    @Before("forEmployeeAppFlow()")
    public void before(JoinPoint joinPoint) {
        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        // display the arguments to the method
        logger.info("===== @Before: " + method);
        // display the arguments to the method
        // get the arguments
        Object[] args = joinPoint.getArgs();
        // loop through and display arguments
        for (Object arg : args) {
            logger.info("===== Argument: " + arg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(pointcut = "forEmployeeAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        // display method we are calling
        String method = joinPoint.getSignature().toShortString();
        logger.info("===== @AfterReturning: " + method);
        // display data returned
        logger.info("===== Result: " + result);
    }
}
