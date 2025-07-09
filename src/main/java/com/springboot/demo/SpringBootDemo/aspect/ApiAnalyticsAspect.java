package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ApiAnalyticsAspect {

    @Before("com.springboot.demo.SpringBootDemo.aspect.PointcutExpression.forAccountDAONoGetterSetter()")
    public void performApiAnalyticsAdvice() {
        System.out.println("\n --- Perform Api Analytics Executing @Before Advice Using Pointcut Declarations forDaoPackageNoGetterSetter() ---");
    }
}
