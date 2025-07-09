package com.springboot.demo.SpringBootDemo.aspect;

import com.springboot.demo.SpringBootDemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class LoggingAspect {

    // this is where we add all of our related advices for logging
    // @Before advice
    @Before("com.springboot.demo.SpringBootDemo.aspect.PointcutExpression.forDaoPackage()")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("\n --- Executing @Before Advice Using Pointcut Declarations on any return type * com.springboot.demo.SpringBootDemo.dao.*.*(..) ---");
        // display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Logged Method Signature: " + methodSignature);
        // display method arguments
        // get args
        Object[] args = joinPoint.getArgs();
        // loop through args
        for (Object arg : args) {
            System.out.println("Logged Arg: " + arg);
            if (arg instanceof Account) {
                // downcast and print Account specific info
                Account account = (Account) arg;
                System.out.println("Logged Account Name: " + account.getName());
                System.out.println("Logged Account Level: " + account.getLevel());
            }
        }

    }

//    @Before("execution(* com.springboot.demo.SpringBootDemo.dao.*.*(..))")
//    public void beforeAdvice() {
//        System.out.println("\n --- Executing @Before Advice on any return type * com.springboot.demo.SpringBootDemo.dao.*.*(..) ---");
//    }

//    @Before("execution(* add*(com.springboot.demo.SpringBootDemo.entity.Account, ..))")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on any return type * add*(Account, ..) ---");
//    }

//    @Before("execution(* add*(com.springboot.demo.SpringBootDemo.entity.Account))")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on any return type * add*(Account) ---");
//    }
//
//    @Before("execution(* add*())")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on any return type * add*() ---");
//    }

//    @Before("execution(void add*())")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on void add*() ---");
//    }

//    @Before("execution(public void add*())")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on add*() ---");
//    }

//    @Before("execution(public void com.springboot.demo.SpringBootDemo.dao.AccountDAO.addAccount())")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on AccountDAO.addAccount() ---");
//    }

//    @Before("execution(public void addAccount())")
//    public void beforeAddAdvice() {
//        System.out.println("\n --- Executing @Before Advice on addAccount() ---");
//    }
}
