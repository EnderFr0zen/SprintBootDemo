package com.springboot.demo.SpringBootDemo.student;

import org.springframework.stereotype.Component;

@Component
public class UniMelbStudent implements Student {

    @Override
    public String getNameInfo() {
        return "UniMelb Student Name: ";
    }
}
