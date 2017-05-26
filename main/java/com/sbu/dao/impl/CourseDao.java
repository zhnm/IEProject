package com.sbu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sbu.entity.Course;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CourseDao /*implements UserDAO*/ {

    public CourseDao() {
    }

    
    
    @PersistenceContext
    public EntityManager entityManager;

    //@Override
    @Transactional
    public void insertUser(Course course) {
        entityManager.persist(course);
    }

    //@Override
   /* public List<Course> findAllStudents() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> cq = builder.createQuery(Course.class);
        Root<Course> root = cq.from(Course.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }*/
    
   @Transactional
    public ArrayList<Course> findAll() {
        return (ArrayList) entityManager.createNamedQuery("Course.findAll").getResultList();
    }
    
    @Transactional
    public Course findByID(Integer id) {
        return (Course) entityManager.createNamedQuery("Course.findById").setParameter("id", id).getSingleResult();
    }
    
    
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }
    

}
