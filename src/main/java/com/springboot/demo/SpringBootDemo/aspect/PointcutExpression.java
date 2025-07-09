package com.springboot.demo.SpringBootDemo.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExpression {

    // create a pointcut for AccountDAO
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.*(..))")
    protected void forAccountDAO() {}

    // create a pointcut for getter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.get*(..))")
    protected void forGetter() {}

    // create a pointcut for setter methods
    @Pointcut("execution(* com.springboot.demo.SpringBootDemo.dao.AccountDAO.set*(..))")
    protected void forSetter() {}

    // create pointcut for including AccountDAO methods ... exclude getters/setters
    @Pointcut("forAccountDAO() && !(forGetter() || forSetter()))")
    protected void forAccountDAONoGetterSetter() {}
}
