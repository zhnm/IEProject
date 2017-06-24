package com.sbu.dao.impl;

import com.sbu.entity.Concentration;
import com.sbu.entity.Major;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ConcentrationDao /*implements UserDAO*/ {

    public ConcentrationDao() {
    }

    
    
    @PersistenceContext
    public EntityManager entityManager;


    
   @Transactional
    public List<Concentration> findAll() {
        return entityManager.createNamedQuery("Concentration.findAll").getResultList();
    }
    //**********************NEW************************************
    @Transactional
    public List<Concentration> findByMajor(Major major) {
        return entityManager.createNamedQuery("Concentration.findByMajor").setParameter("mid", major).getResultList();
    }
    
    
   
    

}
