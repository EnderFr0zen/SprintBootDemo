package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Account;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);

    boolean doWork();

    // for practice AOP Combining Pointcuts concept only
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
