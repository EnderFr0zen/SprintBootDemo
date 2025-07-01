package com.springboot.demo.SpringBootDemo.config;

import com.springboot.demo.SpringBootDemo.student.MonashStudent;
import com.springboot.demo.SpringBootDemo.student.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean("monashuni")
    public Student monashStudent() {
        return new MonashStudent();
    }
}
