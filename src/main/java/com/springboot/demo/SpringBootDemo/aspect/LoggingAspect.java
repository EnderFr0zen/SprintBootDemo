package com.springboot.demo.SpringBootDemo.aspect;

import com.springboot.demo.SpringBootDemo.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
public class LoggingAspect {

    // this is where we add all of our related advices for logging
    // @Before advice
    @Before("com.springboot.demo.SpringBootDemo.aspect.PointcutExpression.forAccountDAO()")
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

    // new advice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.findAccounts(..))", returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n --- Executing @AfterReturning Advice on " + method + " ---");
        // print out the results of the method call
        System.out.println("Result: " + result);
        // do post-process the data ... modified it
        // convert the account names to UPPERCASE
        convertAccountNameToUpperCase(result);
        System.out.println("Result (Modified): " + result);
    }

    private void convertAccountNameToUpperCase(List<Account> result) {
        // loop through accounts
        for (Account account : result) {
            // get uppercase version of name
            String upperCaseName = account.getName().toUpperCase();
            // update the name on the account
            account.setName(upperCaseName);
        }
    }

    @AfterThrowing(pointcut = "execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.findAccounts(..))", throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n --- Executing @AfterThrowing Advice on " + method + " ---");
        // log the exception
        System.out.println("@AfterThrowing Exception: " + exception);
    }

    @After("execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n --- Executing @After (Finally) Advice on " + method + " ---");
    }

    @Around("execution(* com.springboot.demo.SpringBootDemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // print out method we are advertising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n --- Executing @Around Advice on " + method + " ---");
        // get begin timestamp
        long startTime = System.nanoTime();
        // execute the method
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            // log the exception
            System.out.println(e.getMessage());
            // give user custom message
            result = "Major accident! Exception Handled!";
        }
        // get end timestamp
        long endTime = System.nanoTime();
        // compute duration and display it
        long executionTime = endTime - startTime;
        System.out.println("Execution time: " + executionTime + " nanoseconds");
        return result;
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
