package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // create a pointcut for package
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.*(..))")
    private void forDaoPackage() {}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.get*(..))")
    private void forGetter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.set*(..))")
    private void forSetter() {}

    // create pointcut for including package ... exclude getters/setters
    @Pointcut("forDaoPackage() && !(forGetter() || forSetter()))")
    private void forDaoPackageNoGetterSetter() {}

    // this is where we add all of our related advices for logging
    // @Before advice
    @Before("forDaoPackage()")
    public void beforeAddAdvice() {
        System.out.println("\n --- Executing @Before Advice Using Pointcut Declarations on any return type * com.springboot.demo.SpringBootDemo.dao.*.*(..) ---");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalyticsAdvice() {
        System.out.println("\n --- Perform Api Analytics Executing @Before Advice Using Pointcut Declarations forDaoPackageNoGetterSetter() ---");
    }

//    @Before("execution(* com.springboot.demo.SpringBootDemo.dao.*.*(..))")
//    public void beforeAddAdvice() {
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
