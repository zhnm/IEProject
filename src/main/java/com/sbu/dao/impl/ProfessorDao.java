/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.dao.impl;

import com.sbu.entity.Concentration;
import com.sbu.entity.Professor;
import com.sbu.entity.Student;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WIN 10
 */
@Repository
public class ProfessorDao {

    public ProfessorDao() {
    }

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public Professor findByID(Integer id) {
        return (Professor) entityManager.createNamedQuery("Professor.findById").setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void updateProfessor(Student studnet) {
        entityManager.merge(studnet);
    }
    
    @Transactional
    public List<Professor> findByConce(Concentration conce) {
        return entityManager.createNamedQuery("Professor.findByConce").setParameter("conceid", conce).getResultList();
    }
}
