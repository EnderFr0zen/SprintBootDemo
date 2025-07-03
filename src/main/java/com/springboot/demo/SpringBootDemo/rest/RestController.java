package com.springboot.demo.SpringBootDemo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.springboot.demo.SpringBootDemo.entity.Employee;
import com.springboot.demo.SpringBootDemo.entity.Student;
import com.springboot.demo.SpringBootDemo.exception.StudentNotFoundException;
import com.springboot.demo.SpringBootDemo.service.EmployeeService;
import com.springboot.demo.SpringBootDemo.student.UniversityStudent;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/manual")
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

    // use service layer instead direct use EmployeeDAO
    private final EmployeeService employeeService;

    private final ObjectMapper objectMapper;

    private List<Student> students;

    @Autowired
    public RestController(@Qualifier("uniMelbStudent") UniversityStudent uniMelbStudent,
                          @Qualifier("QUTStudent") UniversityStudent qutStudent,
                          @Qualifier("monashUni") UniversityStudent monashUniversityStudent,
                          EmployeeService employeeService,
                          ObjectMapper objectMapper) {
        this.uniMelbStudent = uniMelbStudent;
        this.qutStudent = qutStudent;
        this.monashUniversityStudent = monashUniversityStudent;
        this.employeeService = employeeService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // add mapping for GET /employees/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
        return employee;
    }

    // add mapping for POST /employees - add new employee
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        // just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // add mapping for PUT /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // add mapping for PATCH /employees/{employeeId} - patch employee ... partial update
    @PatchMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId,
                                   @RequestBody Map<String, Object> patchPayload) {
        Employee employee = employeeService.findById(employeeId);
        // throw exception if null
        if (employee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
        // throw exception if request body contains "id" key
        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + employeeId);
        }
        Employee patchedEmployee = apply(patchPayload, employee);
        Employee dbEmployee = employeeService.save(patchedEmployee);
        return dbEmployee;
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        // Convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);
        // Convert the patchPayload map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);
        // Merge the patch updates into the employee node
        employeeNode.setAll(patchNode);
        Employee patchedEmployee = objectMapper.convertValue(employeeNode, Employee.class);
        return patchedEmployee;
    }

    // add mapping for DELETE /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        // throw exception if null
        if (employee == null) {
            throw new RuntimeException("Employee with id " + employeeId + " not found");
        }
        employeeService.deleteById(employeeId);
        return "Employee with id " + employeeId + " deleted";
    }

    // define @PostConstruct to load the student data ... only once
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("FirstName1", "LastName1"));
        students.add(new Student("FirstName2", "LastName2"));
        students.add(new Student("FirstName3", "LastName3"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    // define endpoint or "/students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // check the studentId again list size
        if (studentId < 0 || studentId >= students.size()) {
            throw new StudentNotFoundException(studentId + " is out of bounds");
        }
        return students.get(studentId);
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
