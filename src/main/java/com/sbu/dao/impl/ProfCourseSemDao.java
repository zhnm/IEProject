package com.sbu.dao.impl;

import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Professor;
import com.sbu.entity.Semester;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProfCourseSemDao /*implements UserDAO*/ {

    public ProfCourseSemDao() {
    }

    @PersistenceContext
    public EntityManager entityManager;

    @Transactional
    public List<ProfCourseSem> findByCourseAndSemester(Course course, Semester sem) {
        return entityManager.createNamedQuery("ProfCourseSem.findByCourseAndSemester")
                .setParameter("cid", course)
                .setParameter("sid", sem).getResultList();
    }

    @Transactional
    public void save(ProfCourseSem pcs) {
        entityManager.persist(pcs);
    }

    @Transactional
    public List<ProfCourseSem> findBySemester(Semester sem) {
        return entityManager.createNamedQuery("ProfCourseSem.findBySemester")
                .setParameter("sid", sem).getResultList();
    }

    //*************NEW*****VIEW STUDENT LIST***********
    @Transactional
    public List<ProfCourseSem> findByProfessor(Professor prof) {
        return entityManager.createNamedQuery("ProfCourseSem.findByProfessor")
                .setParameter("pid", prof).getResultList();
    }

    @Transactional
    public List<ProfCourseSem> findByProfessorAndSemester(Professor prof, Semester sem) {
        return entityManager.createNamedQuery("ProfCourseSem.findByProfessorAndSemester")
                .setParameter("pid", prof)
                .setParameter("sid", sem)
                .getResultList();
    }

    @Transactional
    public ProfCourseSem findByID(int id) {
        return (ProfCourseSem) entityManager.createNamedQuery("ProfCourseSem.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

}
