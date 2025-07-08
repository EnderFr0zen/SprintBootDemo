package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LearningAppDAOImpl implements LearningAppDAO {

    // define field for entity manager
    private final EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public LearningAppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor == null) {
            System.out.println("Instructor with id " + id + " not found. Skipping delete.");
            return;
        }
        // get the courses
        List<Course> courses = instructor.getCourses();
        if (courses != null) {
            for (Course course : courses) {
                course.setInstructor(null);
            }
        }
        // delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        // retrieve instructor detail
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, id);
        // remove the associated object reference
        // break bi-directional link
        instructorDetail.getInstructor().setInstructorDetail(null);
        // delete the instructorDetail
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query  = entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);
        query.setParameter("data", id);
        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int id) {
        // create query
        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id = :data", Instructor.class);
        query.setParameter("data", id);
        // execute query
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseById(int id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id) {
        // retrieve the course
        Course course = entityManager.find(Course.class, id);
        // delete the course
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.reviews WHERE c.id = :data", Course.class);
        query.setParameter("data", id);
        // execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Course findCourseAndLearnersByCourseId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("SELECT c FROM Course c JOIN FETCH c.learners WHERE c.id = :data", Course.class);
        query.setParameter("data", id);
        // execute query
        Course course = query.getSingleResult();
        return course;
    }

    @Override
    public Learner findLearnerAndCoursesByLearnerId(int id) {
        // create query
        TypedQuery<Learner> query = entityManager.createQuery("SELECT l from Learner l JOIN FETCH l.courses WHERE l.id = :data", Learner.class);
        query.setParameter("data", id);
        // execute query
        Learner learner = query.getSingleResult();
        return learner;
    }

    @Override
    @Transactional
    public void update(Learner learner) {
        entityManager.merge(learner);
    }
}
