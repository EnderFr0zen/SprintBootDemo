package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Course;
import com.springboot.demo.SpringBootDemo.entity.Instructor;
import com.springboot.demo.SpringBootDemo.entity.InstructorDetail;

import java.util.List;

public interface LearningAppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);
}
