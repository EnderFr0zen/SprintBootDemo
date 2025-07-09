package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExpression {

    // create a pointcut for package
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.*(..))")
    protected void forDaoPackage() {}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.get*(..))")
    protected void forGetter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.*.set*(..))")
    protected void forSetter() {}

    // create pointcut for including package ... exclude getters/setters
    @Pointcut("forDaoPackage() && !(forGetter() || forSetter()))")
    protected void forDaoPackageNoGetterSetter() {}
}
