package com.springboot.demo.SpringBootDemo.student;

import org.springframework.stereotype.Component;

@Component
public class QUTStudent implements Student {

    @Override
    public String getNameInfo() {
        return "QUT Student Name: ";
    }
}
