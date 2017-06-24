package com.sbu.service.impl;

import com.sbu.dao.impl.StudentDao;
import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import com.sbu.entity.StudentCourse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentManager")
public class StudentManager {

    public StudentManager() {
    }

    @Autowired
    public StudentDao studentDao;
    @Autowired//*****************new*************
    public CourseManager courseManager;
    @Autowired//*****************new*************
    public CoprecoManager coprecoManager;
    @Autowired//*****************new*************
    public StudentCourseManager studentCourseManager;
    @Autowired//*****************new*************
    public ProfCourseSemManager profCourseSemManager;

    //@Override
    @Transactional
    public void insertUser(Student user) {
        studentDao.insertUser(user);
    }

    @Transactional
    public Student findByID(Integer id) {
        return studentDao.findByID(id);
    }

    @Transactional
    public boolean isValid(Integer id, Integer pass) {
        Student std = findByID(id);
        return std != null && Integer.parseInt(std.getSpassword()) == pass;
    }

    @Transactional
    public void updateStudent(Student student) {

        studentDao.updateStudent(student);
    }

    //***************NEW******KARNAME******************
    @Transactional
    public ArrayList<String[]> generalInformation(Student student) {
        ArrayList<String[]> result = new ArrayList<>();
        //get all courses student has passed by semester
        HashMap<Semester, ArrayList<ProfCourseSem>> semesters = new HashMap<>();
        ArrayList<ProfCourseSem> courses;
        Semester sem;
        for (StudentCourse pcd : studentCourseManager.getByStudent(student)) {
            sem = pcd.getPcsid().getSemid();
            ProfCourseSem course = pcd.getPcsid();
            if (semesters.containsKey(sem)) {
                courses = semesters.get(sem);
                courses.add(course);
                semesters.put(sem, courses);
            } else {
                courses = new ArrayList<>();
                courses.add(course);
                semesters.put(sem, courses);
            }
        }
        int totalUnits = 0;
        int totalConfirmedUnits =0;
        double average = 0;
        StudentCourse sc;
        String[] row;
        //for each semester iterate through courses and calculate average
        Iterator it = semesters.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            courses = (ArrayList<ProfCourseSem>) pair.getValue();
            sem = (Semester) pair.getKey();
            for (ProfCourseSem c : courses) {
                sc = studentCourseManager.getByStudentAndPCS(student, c);
                totalUnits += c.getCourseid().getUnit();
                if(sc.getConfirmed())//include grade in average only if it's confirmed
                {average += (c.getCourseid().getUnit() * sc.getGrade());
                totalConfirmedUnits+=c.getCourseid().getUnit();
                }
            }
            row = new String[3];
            row[0] = sem.getName();
            row[1] = String.valueOf(totalUnits);
            row[2] = String.valueOf(average / totalConfirmedUnits);
            result.add(row);
            totalUnits = 0;
            totalConfirmedUnits =0;
            average = 0;
        }
        return result;
    }

    @Transactional
    public ArrayList<String[]> getSemesterGrades(Student student, Semester sem) {
        ArrayList<String[]> result = new ArrayList<>();
        String[] row;
        for (ProfCourseSem pcs:profCourseSemManager.findBySemester(sem)) {
            StudentCourse sc = studentCourseManager.getByStudentAndPCS(student, pcs);
            if (sc != null) {
                Course c = sc.getPcsid().getCourseid();
                row = new String[6];
                row[0] = c.getName();
                row[1] = String.valueOf(c.getUnit());
                row[2] = String.valueOf(sc.getGrade());
                row[3] = String.valueOf(sc.getConfirmed());
                row[4] = c.getCtype();
                //*****************************************************PROFESSOR NAME**************************
                row[5] = pcs.getProfid().getName()+" "+pcs.getProfid().getFamily();
                result.add(row);
            }

        }
        return result;
    }

}
