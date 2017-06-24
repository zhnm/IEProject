package com.sbu.dao.impl;

import com.sbu.entity.Concentration;
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
    public void insertCourse(Course course) {
        entityManager.persist(course);
    }

    @Transactional
    public List<Course> findByConcentration(Concentration conce) { 
        return entityManager.createNamedQuery("Course.findByConcentration").setParameter("conceid", conce).getResultList();
    }
    
   @Transactional
    public List<Course> findAll() {
        return entityManager.createNamedQuery("Course.findAll").getResultList();
    }
    
    //**************************EDITED******************************
    @Transactional
    public Course findByName(String name)
    {
        List<Course> courses = entityManager.createNamedQuery("Course.findByName").setParameter("name", name).getResultList();
        if(!courses.isEmpty())
            return courses.get(0);
        return null;
    }
    
    @Transactional
    public void updateCourse(Course course) {
        entityManager.merge(course);
    }
    
    @Transactional //***********NEW********TAKECOURSE***********
    public List<Course> getByAllowedConce(String conce) {
            return entityManager.createNamedQuery("Course.findByAllowedConcentration").setParameter("name", "%" + conce + "%").getResultList();
    }
   
   
    

}
