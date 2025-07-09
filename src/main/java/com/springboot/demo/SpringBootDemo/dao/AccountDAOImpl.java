package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    // for practice AOP Combining Pointcuts concept only
    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": Adding an account: " + account + ": " + vipFlag);
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    // for practice AOP Combining Pointcuts concept only
    @Override
    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
//        List<Account> accounts = new ArrayList<>();
//        // create sample accounts
//        Account account1 = new Account("Ninni", "Platinum");
//        Account account2 = new Account("Inch", "Gold");
//        // add them to accounts list
//        accounts.add(account1);
//        accounts.add(account2);
//        return accounts;
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // simulate an exception
        if (tripWire) {
            throw new RuntimeException("RuntimeException!");
        }
        List<Account> accounts = new ArrayList<>();
        // create sample accounts
        Account account1 = new Account("Ninni", "Platinum");
        Account account2 = new Account("Inch", "Gold");
        // add them to accounts list
        accounts.add(account1);
        accounts.add(account2);
        return accounts;
    }
}
