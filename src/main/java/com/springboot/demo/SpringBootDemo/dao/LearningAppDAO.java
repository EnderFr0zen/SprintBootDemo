package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Instructor;
import com.springboot.demo.SpringBootDemo.entity.InstructorDetail;

public interface LearningAppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);
}
