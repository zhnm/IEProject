package com.sbu.service.impl;

import com.sbu.dao.impl.CourseDao;
import com.sbu.dao.impl.CoprecoDao;
import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.Copreco;
import com.sbu.entity.Major;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseManager /*implements UserManager*/ {

    public CourseManager() {
    }

    @Autowired
    public CourseDao courseDao;
    @Autowired
    public CoprecoManager coprecoManager;

    //@Override
    @Transactional
    public void insertCourse(Course course, ArrayList<String> precos) {
        courseDao.insertCourse(course);
        Course precourse;
        Copreco copreco;
        for (int i = 0; i < precos.size(); i++) {
            copreco = new Copreco();
            copreco.setCid(course);
            precourse = findByName(precos.get(i));
            copreco.setPrecid(precourse);
            coprecoManager.insertPre(copreco);
        }
    }

    @Transactional
    public Course findByID(Integer id) {
        List<Course> courses = courseDao.findAll();
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    @Transactional
    public List<Course> findByConcentration(Concentration conce) {
        return courseDao.findByConcentration(conce);
    }

    @Transactional
    public ArrayList<String> findByMajorID(Integer id) {
        List<Course> courses = courseDao.findAll();
        ArrayList<String> names = new ArrayList<>();
        for (Course c : courses) {
            if (c.getMajorid().getId()== id) {
               names.add(c.getName());
            }
        }
        return names;
    }

    @Transactional
    public void updateCourse(Course course) {
        courseDao.updateCourse(course);
    }

    @Transactional
    public HashMap<String, Integer> getCoursesByConce(Concentration conce) {
        HashMap<String, Integer> names = new HashMap<String, Integer>();
        List<Course> courses = findByConcentration(conce);
        for (Course c : courses) {
            names.put(c.getName(), c.getId());
        }
        return names;
    }

    @Transactional
    public boolean checkMajorConce(Course course) {
        Major major = course.getMajorid();
        Concentration conce = course.getConceid();
        return conce.getMajorid().getId() == major.getId();
    }

    @Transactional
    public Course findByName(String name) {
        return courseDao.findByName(name);
    }

    @Transactional
    public boolean checkPreCoConce(ArrayList<String> precos, Concentration courseConce) {
        Concentration preConce;
        for (int i = 0; i < precos.size(); i++) {
            preConce = findByName(precos.get(i)).getConceid();//retrive conce of input course
            if (preConce.getId() != courseConce.getId()) {
                return false;
            }
        }
        return true;
    }

    @Transactional //***********NEW********TAKECOURSE***********
    public List<Course> getByAllowedConce(String conce) {
        return courseDao.getByAllowedConce(conce);
    }
    

}
