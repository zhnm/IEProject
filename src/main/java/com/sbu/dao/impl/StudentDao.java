package com.sbu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sbu.entity.Student;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentDao /*implements UserDAO*/ {

    public StudentDao() {
    }

    
    
    @PersistenceContext
    public EntityManager entityManager;

    //@Override
    @Transactional
    public void insertUser(Student user) {
        //entityManager.getTransaction().begin();
        entityManager.persist(user);
        //entityManager.getTransaction().commit();
    }

    //@Override
    public List<Student> findAllStudents() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = builder.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }
    
     /*public List<Student> findAllStudents() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = builder.createQuery(Student.class);
        Root<Student> root = cq.from(Student.class);
        cq.select(root);
        return entityManager.createQuery(cq).getResultList();
    }*/

}
