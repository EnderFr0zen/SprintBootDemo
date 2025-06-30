package com.springboot.demo.SpringBootDemo.student;

import org.springframework.stereotype.Component;

@Component
public class UniMelbStudent implements Student {

    @Override
    public String getSchoolInfo() {
        return "UniMelb Student ";
    }
}
