package com.springboot.demo.SpringBootDemo.service;

import com.springboot.demo.SpringBootDemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
