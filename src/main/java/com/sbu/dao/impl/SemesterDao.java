package com.sbu.dao.impl;


import com.sbu.entity.Major;
import com.sbu.entity.Semester;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SemesterDao /*implements UserDAO*/ {

    public SemesterDao() {
    }

    
    
    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public void save(Semester sem)
    {
        entityManager.persist(sem);
    }
    @Transactional
    public Semester getCurrentSemester() {
        List<Semester> sems = entityManager.createNamedQuery("Semester.findAllOrdered").getResultList();
        if(!sems.isEmpty())
            return sems.get(0);
        return null;
    }
    
    //new*******************
    @Transactional
    public Semester findByName(String name)
    {
        List<Semester> sems = entityManager.createNamedQuery("Semester.findByName").setParameter("name",name).getResultList();
        if(!sems.isEmpty())
            return sems.get(0);
        return null;
    }
       

}
