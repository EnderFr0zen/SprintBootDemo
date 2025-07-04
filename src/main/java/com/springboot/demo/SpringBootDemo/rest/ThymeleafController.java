package com.springboot.demo.SpringBootDemo.rest;

import com.springboot.demo.SpringBootDemo.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${programminglanguages}")
    private List<String> programmingLanguages;

    @Value("${operatingsystems}")
    private List<String> operatingSystems;

    // need a controller method to show initial HTML form
    @GetMapping("/showform")
    public String showForm() {
        return "thymeleafform-show";
    }

    // need a controller method to read form data and add data to the model
    @GetMapping("/processform")
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

    @GetMapping("/processbindparamform")
    public String processBindParamForm(@RequestParam("name") String name, Model model) {
        // convert the data to all caps
        name = name.toUpperCase();
        // create the message
        String message = "YO! SPRING BIND PARAM! " + name;
        // add message to the model
        model.addAttribute("message", message);
        return "thymeleafform-process";
    }

    @RequestMapping("/hello")
    public String helloThymeleaf(Model model){
        model.addAttribute("date", java.time.LocalDateTime.now());
        return "hellothymeleaf";
    }

    @GetMapping("/showstudentform")
    public String showStudentForm(Model model){
        // create a student object
        Student student = new Student();
        // add student object to the model
        model.addAttribute("student", student);
        // add the list of countries to the model
        model.addAttribute("countries", countries);
        // add the list of programming languages to the model
        model.addAttribute("programminglanguages", programmingLanguages);
        // add the list of operating systems to the model
        model.addAttribute("operatingsystems", operatingSystems);
        return "studentform-show";
    }

    @PostMapping("/processstudentform")
    public String processStudentForm(@ModelAttribute("student") Student student){
        // log the input data
        System.out.println("student: " + student.getFirstName() + " " + student.getLastName());
        return "studentform-confirmation";
    }
}
