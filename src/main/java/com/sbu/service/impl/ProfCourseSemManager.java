package com.sbu.service.impl;

import com.sbu.dao.impl.ProfCourseSemDao;
import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Professor;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfCourseSemManager /*implements UserManager*/ {
    public ProfCourseSemManager() {
    }
    @Autowired
    public ProfCourseSemDao profCourseSemDao;
    @Autowired
    public CourseManager courseManager;
    @Autowired
    public SemesterManager semesterManager;
    @Autowired
    public StudentCourseManager studentCourseManager;

    public ArrayList<String[]> availableCourses(Student student) {
        ArrayList<String[]> result = new ArrayList<>();
        ArrayList<Integer> allPassedCourses = studentCourseManager.passedCourses(student);
        Semester currentSem = semesterManager.getCurrentSemester();
        String[] row;
        for (ProfCourseSem pcs : findByConceAndSemester(student.getConcenid(), currentSem)) {
            if (!allPassedCourses.contains(pcs.getCourseid().getId())) {
                row = new String[5];
                row[0] = String.valueOf(pcs.getId());//ProfCourseSem id
                row[1] = pcs.getCourseid().getName();//Course name
                row[2] = pcs.getCourseid().getConceid().getName();//Concentration name
                row[3] = String.valueOf(pcs.getCourseid().getUnit());//Units
                row[4] = pcs.getProfid().getName()+" "+pcs.getProfid().getFamily();//Professor name     
                result.add(row);//*****************NEW*********************************
            }
        }
        return result;
    }

    //find all the courses that student can take based on his/her concentration
    @Transactional
    public List<ProfCourseSem> findByConceAndSemester(Concentration conce, Semester sem) {
        List<ProfCourseSem> pcs = new ArrayList<>();
        List<Course> courses = courseManager.getByAllowedConce(conce.getName());
        for (Course c : courses) {
            pcs.addAll(profCourseSemDao.findByCourseAndSemester(c, sem));
        }
        return pcs;
    }

    @Transactional
    public ArrayList<String[]> currentSemester(Concentration conce) {
        ArrayList<String[]> result = new ArrayList<>();
        Semester currentSem = semesterManager.getCurrentSemester();
        String[] row;
        List<Course> courses = courseManager.findByConcentration(conce);
        for (Course c : courses) {
            for (ProfCourseSem pcs : profCourseSemDao.findByCourseAndSemester(c, currentSem)) {
                row = new String[5];
                row[0] = String.valueOf(pcs.getId());//ProfCourseSem id
                row[1] = pcs.getCourseid().getName();//Course name
                row[2] = pcs.getCourseid().getConceid().getName();//Concentration name
                row[3] = String.valueOf(pcs.getCourseid().getUnit());//Units
                row[4] = pcs.getProfid().getName() + " " + pcs.getProfid().getFamily();//Professor name      
                result.add(row);//*****************NEW*********************************
            }
        }
        return result;
    }

    @Transactional
    public void save(ProfCourseSem pcs) {
        profCourseSemDao.save(pcs);
    }

    @Transactional
    public List<ProfCourseSem> findBySemester(Semester sem) {
        return profCourseSemDao.findBySemester(sem);
    }

    //*************NEW*****VIEW STUDENT LIST***********
    @Transactional
    public List<ProfCourseSem> findByProfessor(Professor prof) {
        return profCourseSemDao.findByProfessor(prof);
    }

    @Transactional
    public List<ProfCourseSem> findByProfessorAndSemester(Professor prof, Semester sem) {
        return profCourseSemDao.findByProfessorAndSemester(prof, sem);
    }

    @Transactional
    public ProfCourseSem findByID(int id) {
        return profCourseSemDao.findByID(id);
    }

    public ArrayList<String[]> getAllCoursesByProfessor(Professor professor) {
        ArrayList<String[]> result = new ArrayList<>();
        String[] row;
        for (ProfCourseSem pcs : findByProfessor(professor)) {
            row = new String[3];
            row[0] = String.valueOf(pcs.getId());
            row[1] = pcs.getCourseid().getName();
            row[2] = pcs.getSemid().getName();
            result.add(row);
        }
        return result;
    }
}
