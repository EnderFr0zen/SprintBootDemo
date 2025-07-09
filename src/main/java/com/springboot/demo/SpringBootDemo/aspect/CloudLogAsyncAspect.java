package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class CloudLogAsyncAspect {

    @Before("com.springboot.demo.SpringBootDemo.aspect.PointcutExpression.forAccountDAONoGetterSetter()")
    public void cloudLogAsyncAdvice() {
        System.out.println("\n --- Cloud Log Async Executing @Before Advice Using Pointcut Declarations forDaoPackageNoGetterSetter() ---");
    }
}
