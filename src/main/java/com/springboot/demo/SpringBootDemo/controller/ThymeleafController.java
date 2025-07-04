package com.springboot.demo.SpringBootDemo.controller;

import com.springboot.demo.SpringBootDemo.entity.Customer;
import com.springboot.demo.SpringBootDemo.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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
    public String processStudentForm(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        // log the input data
        System.out.println("student: " + student.getFirstName() + " " + student.getLastName());
        if (bindingResult.hasErrors()) {
            // Add these back to the model
            model.addAttribute("countries", countries);
            model.addAttribute("programminglanguages", programmingLanguages);
            model.addAttribute("operatingsystems", operatingSystems);
            return "studentform-show";
        } else {
            return "studentform-confirmation";
        }
    }

    @GetMapping("/showcustomerform")
    public String showCustomerForm(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerform-show";
    }

    @PostMapping("/processcustomerform")
    public String processCustomerForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult){
        System.out.println("Last Name: |" + customer.getLastName() + "|");
        // for debugging tips for custom error names and put in messages.properties
        System.out.println("Binding Result: " + bindingResult.toString());
        if (bindingResult.hasErrors()) {
            return "customerform-show";
        } else {
            return "customerform-confirmation";
        }
    }

    // add an initbinder ... to convert trim input strings
    // remove leading and trailing whitespace
    // resolve issue for our validation
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        webDataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}
