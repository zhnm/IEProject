package com.sbu.dao.impl;

import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Student;
import com.sbu.entity.StudentCourse;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StudentCourseDao /*implements UserDAO*/ {

    public StudentCourseDao() {
    }   
    
    @PersistenceContext
    public EntityManager entityManager;

    //@Override
    @Transactional
    public void insert(StudentCourse studentCourse) {
        entityManager.persist(studentCourse);
    }
    
    @Transactional
    public List<StudentCourse> getByStudent(Student student)
    {
        return entityManager.createNamedQuery("StudentCourse.findByStudent").setParameter("sid", student).getResultList();
    }
    
    @Transactional
    public StudentCourse getByStudentAndPCS(Student st,ProfCourseSem pcs)
    {
        List<StudentCourse> sc = entityManager.createNamedQuery("StudentCourse.findByStudentAndPCS")
                .setParameter("sid", st)
                .setParameter("pcsid",pcs)
                .getResultList();
        if(!sc.isEmpty())
            return sc.get(0);
        return null;
    }
    
       @Transactional
    public List<StudentCourse> getByPCS(ProfCourseSem pcs)
    {
        return entityManager.createNamedQuery("StudentCourse.findByPCS")
                .setParameter("pcsid",pcs)
                .getResultList();
    }
    
    //**********NEW********STUDENT LIST
    @Transactional
    public void update(StudentCourse sc) {
        entityManager.merge(sc);
    }
       

}
