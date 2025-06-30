package com.springboot.demo.SpringBootDemo.student;

import org.springframework.stereotype.Component;

@Component
public class QUTStudent implements Student {

    @Override
    public String getSchoolInfo() {
        return "QUT Student ";
    }
}
