package com.springboot.demo.SpringBootDemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Adding an account");
    }

    @Override
    public void addStar() {
        System.out.println(getClass() + ": Adding STAR*****");
    }

    @Override
    public boolean addBoolean() {
        System.out.println(getClass() + ": Adding a boolean");
        return false;
    }
}
