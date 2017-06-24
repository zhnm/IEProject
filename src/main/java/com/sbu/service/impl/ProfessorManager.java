/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.service.impl;

import com.sbu.dao.impl.ProfessorDao;
import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Professor;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import com.sbu.entity.StudentCourse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author WIN 10
 */
@Service("professorManager")
public class ProfessorManager {

    @Autowired
    public ProfessorDao professorDao;
    @Autowired
    public ConcentrationManager concentrationManager;
    @Autowired
    public ProfCourseSemManager profCourseSemManager;
    @Autowired
    public StudentCourseManager studentCourseManager;

    @Transactional
    public boolean isManager(Integer id) {
        return professorDao.findByID(id).getRole() != 0;
    }

    @Transactional
    public Professor findByID(Integer id) {
        return professorDao.findByID(id);
    }

    @Transactional
    public boolean isValid(Integer id, Integer pass) {
        Professor prof = findByID(id);
        return prof != null && Integer.parseInt(prof.getPpassword()) == pass;
    }

    @Transactional
    public Concentration getConcentration(Integer id) {
        Professor prof = findByID(id);
        return prof.getConceid();
    }

    public boolean checkAuthorization(Integer profID, Course course) {
        Professor prof = findByID(profID);
        return course.getConceid().getId() == prof.getConceid().getId();
    }

    public List<Concentration> getAllowedConce(Professor professor) {
        return concentrationManager.findByMajor(professor.getConceid().getMajorid());
    }

    @Transactional
    public List<Professor> findByConce(Concentration conce) {
        return professorDao.findByConce(conce);
    }

    @Transactional
    public HashMap<Integer, String> getProfessorsByConce(Concentration conce) {
        HashMap<Integer, String> names = new HashMap<Integer, String>();
        List<Professor> professors = findByConce(conce);
        for (Professor p : professors) {
            names.put(p.getId(),p.getName() + " " + p.getFamily());
        }
        return names;
    }

    //*************NEW*****VIEW STUDENT LIST***********
    @Transactional
    public LinkedHashMap<String, String> getTeachedSemesters(Professor professor) {
        LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
        TreeSet<Semester> semesters = new TreeSet<>();
        //get all semesters professors teached
        for (ProfCourseSem pcs : profCourseSemManager.findByProfessor(professor)) {
            semesters.add(pcs.getSemid());
        }
        //decode semester name in a readable format
        for (Semester s : semesters) {
            String year = s.getName().substring(0, 4);
            String type = s.getName().substring(4);
            if (type.equals("0")) {
                type = "پاییز";
            } else {
                type = "زمستان";
            }
            result.put(s.getName(), type + " " + year);
        }
        //format = semester id - readble format
        return result;
    }

    @Transactional
    public HashMap<Integer, String> getCoursesBySemester(Professor professor, Semester semester) {
        HashMap<Integer, String> courses = new HashMap<Integer, String>();
        for (ProfCourseSem pcs : profCourseSemManager.findByProfessorAndSemester(professor, semester)) {
            courses.put(pcs.getId(), pcs.getCourseid().getName());
        }
        return courses;
    }

    @Transactional
    public ArrayList<String[]> getStudentList(ProfCourseSem pcs) {
        ArrayList<String[]> result = new ArrayList<>();
        String[] row;
        for (StudentCourse sc : studentCourseManager.getByPCS(pcs)) {
            Student student = sc.getStid();
            row = new String[5];
            row[0] = String.valueOf(student.getId());
            row[1] = student.getName() + " " + student.getFamily();
            row[2] = student.getConcenid().getName();
            row[3] = String.valueOf(sc.getGrade());
            row[4] = String.valueOf(sc.getConfirmed());
            result.add(row);
        }
        return result;
    }

    @Transactional
    public boolean setGrade(ProfCourseSem pcs, Student student, float grade) {
        StudentCourse sc = studentCourseManager.getByStudentAndPCS(student, pcs);
        if (sc.getConfirmed()) {
            return false;
        }
        sc.setConfirmed(Boolean.TRUE);
        sc.setGrade(grade);
        studentCourseManager.update(sc);
        return true;
    }

    public boolean validateGrade(float grade) {
        if (grade >= 0 && grade <= 20) {
            return true;
        }
        return false;
    }



}
