package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Course;
import com.springboot.demo.SpringBootDemo.entity.Instructor;
import com.springboot.demo.SpringBootDemo.entity.InstructorDetail;
import com.springboot.demo.SpringBootDemo.entity.Learner;

import java.util.List;

public interface LearningAppDAO {

    void save(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);

    InstructorDetail findInstructorDetailById(int id);

    void deleteInstructorDetailById(int id);

    List<Course> findCoursesByInstructorId(int id);

    Instructor findInstructorByIdJoinFetch(int id);

    void update(Instructor instructor);

    void update(Course course);

    Course findCourseById(int id);

    void deleteCourseById(int id);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int id);

    Course findCourseAndLearnersByCourseId(int id);

    Learner findLearnerAndCoursesByLearnerId(int id);

    void update(Learner learner);

    void deleteLearnerById(int id);
}
