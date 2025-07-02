package com.springboot.demo.SpringBootDemo.student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class MonashUniversityStudent implements UniversityStudent {

    @Override
    public String getSchoolInfo() {
        return "Monash University Student ";
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
