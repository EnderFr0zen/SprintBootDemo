package com.springboot.demo.SpringBootDemo.rest;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
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