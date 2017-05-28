package com.sbu.service.impl;

import com.sbu.dao.impl.CourseDao;
import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.Major;
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

    //@Override
    @Transactional
    public void insertCourse(Course user) {
        courseDao.insertUser(user);
    }

    @Transactional
    public Course findByID(Integer id) { 
        List<Course> courses = courseDao.findAll();
        for (Course c : courses) {
            if(c.getId() == id)              
                return c;
        }
        return null;
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
    
    public boolean checkMajorConce(Course course)
    {
        Major major = course.getMajorid();
        Concentration conce= course.getConceid();
        return conce.getMajorid().getId() == major.getId();
    }
}
