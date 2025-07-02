package com.springboot.demo.SpringBootDemo.student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class UniMelbStudent implements UniversityStudent {

    @Override
    public String getSchoolInfo() {
        return "UniMelb Student ";
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
