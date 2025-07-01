package com.springboot.demo.SpringBootDemo.student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class QUTStudent implements Student {

    @Override
    public String getSchoolInfo() {
        return "QUT Student ";
    }

    // define init method
    @PostConstruct
    public void init(){
        System.out.println("init (PostConstruct) " + getClass().getSimpleName());
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy (PreDestory) " + getClass().getSimpleName());
    }
}
