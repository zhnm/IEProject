package com.sbu.service.impl;

import com.sbu.dao.impl.CoprecoDao;
import com.sbu.entity.Copreco;
import com.sbu.entity.Course;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoprecoManager /*implements UserManager*/ {

    public CoprecoManager() {
    }

    @Autowired
    public CoprecoDao coprecoDao;
    @Autowired
    public CourseManager courseManager;

    //@Override
    @Transactional
    public void insertPre(Copreco copreco) {
        coprecoDao.insertPre(copreco);
    }

    @Transactional
    public List<Course> getAllPreCos(Course course)
    {
        return coprecoDao.getAllPreCos(course);
    }
    
    @Transactional
    public ArrayList<Integer> getAllPreCoID(Course course)
    {
        List<Course> courses = coprecoDao.getAllPreCos(course);
        ArrayList<Integer> names = new ArrayList();
        for(int i=0;i<courses.size();i++)
        {
            names.add(courses.get(i).getId());
        }
        return names;
    }
    
    @Transactional
    public ArrayList<String> getAllPreCoNames(Course course)
    {
        List<Course> courses = coprecoDao.getAllPreCos(course);
        ArrayList<String> names = new ArrayList();
        for(int i=0;i<courses.size();i++)
        {
            names.add(courses.get(i).getName());
        }
        return names;
    }
    
    //***************NEWWWWWWWWWWWWW************
    public void updateDependencies(Course course,ArrayList<String> precolist)
    {
        removeAllDependencies(course);
        for(int i=0;i<precolist.size();i++)
        {
            Course pre = courseManager.findByName(precolist.get(i));
            Copreco copreco = new Copreco();
            copreco.setCid(course);
            copreco.setPrecid(pre);
            insertPre(copreco);           
        }
        
    }
    public void removeAllDependencies(Course course)
    {
        coprecoDao.removeAllDependencies(course);
    }
}
