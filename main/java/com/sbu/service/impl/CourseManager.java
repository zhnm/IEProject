package com.sbu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.dao.impl.CourseDao;
import com.sbu.entity.Course;

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
    public void updateCourse(Course course) {       
        courseDao.updateCourse(course);
    }

}
