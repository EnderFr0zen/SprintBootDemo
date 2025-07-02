package com.springboot.demo.SpringBootDemo.rest;

import com.springboot.demo.SpringBootDemo.student.UniversityStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final UniversityStudent uniMelbStudent;
    private final UniversityStudent qutStudent;
    private final UniversityStudent monashUniversityStudent;

    @Autowired
    public RestController(@Qualifier("uniMelbStudent") UniversityStudent uniMelbStudent,
                          @Qualifier("QUTStudent") UniversityStudent qutStudent,
                          @Qualifier("monashuni") UniversityStudent monashUniversityStudent) {
        this.uniMelbStudent = uniMelbStudent;
        this.qutStudent = qutStudent;
        this.monashUniversityStudent = monashUniversityStudent;
    }

    @GetMapping("/student/unimelb")
    public String getUniMelbStudentName() {
        return uniMelbStudent.getSchoolInfo() + getPersonalInfo();
    }

    @GetMapping("/student/qut")
    public String getQUTStudentName() {
        return qutStudent.getSchoolInfo() + getPersonalInfo();
    }

    @GetMapping("/student/monash")
    public String getMonashStudentName() {
        return monashUniversityStudent.getSchoolInfo() + getPersonalInfo();
    }

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