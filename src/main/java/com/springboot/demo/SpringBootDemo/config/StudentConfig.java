package com.springboot.demo.SpringBootDemo.config;

import com.springboot.demo.SpringBootDemo.student.MonashUniversityStudent;
import com.springboot.demo.SpringBootDemo.student.UniversityStudent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean("monashuni")
    public UniversityStudent monashStudent() {
        return new MonashUniversityStudent();
    }
}
