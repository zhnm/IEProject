package com.sbu.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import com.sbu.entity.Copreco;
import com.sbu.entity.Course;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CoprecoDao /*implements UserDAO*/ {

    public CoprecoDao() {
    }   
    
    @PersistenceContext
    public EntityManager entityManager;

    //@Override
    @Transactional
    public void insertPre(Copreco copreco) {
        entityManager.persist(copreco);
    }
    
    @Transactional
    public List<Course> getAllPreCos(Course course)
    {
        return entityManager.createNamedQuery("Copreco.findByCourse").setParameter("cid", course).getResultList();
    }
     
    
    //NEWWWWWWWWWWWWWWWWWWWWWWWWWW
    @Transactional
    public void removeAllDependencies(Course course) {
        entityManager.createNamedQuery("Copreco.removeByCourse").setParameter("cid", course).executeUpdate();
    }

}
