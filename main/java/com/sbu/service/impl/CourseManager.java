package com.sbu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.dao.impl.CourseDao;
import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import java.util.HashMap;

@Service
public class CourseManager /*implements UserManager*/ {

    public CourseManager() {
    }

    @Autowired
    public CourseDao courseDao;

    //@Override
    @Transactional
    public void insertCourse(Course user) {
        courseDao.insertUser(user);
    }

    @Transactional
    public Course findByID(Integer id) { 
        return courseDao.findByID(id);
    }
    
    @Transactional
    public List<Course> findByConcentration(Concentration conce) { 
        return courseDao.findByConcentration(conce);
    }
    
    @Transactional
    public void updateCourse(Course course) {       
        courseDao.updateCourse(course);
    }
      
    @Transactional
    public HashMap<String,Integer> getCoursesByConce(Concentration conce)
    {
        HashMap<String,Integer> names = new HashMap<String,Integer>();
        List<Course> courses = findByConcentration(conce);
        for (Course c : courses) {
            names.put(c.getName(),c.getId());
        }
        return names;
    }

}
