package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "gods")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
