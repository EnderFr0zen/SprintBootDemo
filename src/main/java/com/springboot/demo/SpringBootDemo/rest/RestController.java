package com.springboot.demo.SpringBootDemo.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    // inject properties for: firstname.name, preferredname.name and lastname.name
    @Value("${firstname.name}")
    private String firstName;

    @Value("${preferredname.name}")
    private String preferredName;

    @Value("${lastname.name}")
    private String lastName;

    @GetMapping("/personalinfo")
    public String getPersonalInfo() {
        return "Name: " + firstName + " (" + preferredName + ") " + lastName;
    }

    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String helloSpring(){
        return "Hello Spring";
    }

    // expose a new endpoint for testing spring-boot-devtools
    @GetMapping("/devtools")
    public String devTools(){
        return "spring-boot-devtools Working";
    }
}