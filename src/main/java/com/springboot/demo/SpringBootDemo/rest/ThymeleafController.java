package com.springboot.demo.SpringBootDemo.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    // need a controller method to show initial HTML form
    @RequestMapping("/showform")
    public String showForm() {
        return "thymeleafform-show";
    }

    // need a controller method to read form data and add data to the model
    @RequestMapping("/processform")
    public String processForm(HttpServletRequest request, Model model) {
        // read the request parameter from the HTML form
        String name = request.getParameter("name");
        // convert the data to all caps
        name = name.toUpperCase();
        // create the message
        String message = "YO! " + name;
        // add message to the model
        model.addAttribute("message", message);
        return "thymeleafform-process";
    }

    @GetMapping("/hello")
    public String helloThymeleaf(Model model){
        model.addAttribute("date", java.time.LocalDateTime.now());
        return "hellothymeleaf";
    }
}
