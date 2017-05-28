package com.sbu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sbu.entity.Major;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MajorDao /*implements UserDAO*/ {

    public MajorDao() {
    }

    
    
    @PersistenceContext
    public EntityManager entityManager;

    //@Override
    @Transactional
    public void insertMajor(Major major) {
        entityManager.persist(major);
    }
    
    @Transactional
    public List<Major> findAll() {
        return entityManager.createNamedQuery("Major.findAll").getResultList();
    }
       

}
