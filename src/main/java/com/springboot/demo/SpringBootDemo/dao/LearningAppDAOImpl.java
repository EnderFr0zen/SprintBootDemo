package com.springboot.demo.SpringBootDemo.dao;

import com.springboot.demo.SpringBootDemo.entity.Instructor;
import com.springboot.demo.SpringBootDemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
