package com.sbu.service.impl;


import com.sbu.dao.impl.MajorDao;
import com.sbu.dao.impl.SemesterDao;
import com.sbu.entity.Major;
import com.sbu.entity.Semester;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SemesterManager /*implements UserManager*/ {

    public SemesterManager() {
    }

    @Autowired
    public SemesterDao semesterDao;

    //**NEW***SAVE-TEARM***
    @Transactional
    public void save(Semester semester)
    {
        semesterDao.save(semester);
    }
    public boolean validateYear(String yaer)
    {
        try{
        int y = Integer.parseInt(yaer);
        if(y>=1300 && y<=1410)
            return true;
        return false;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    @Transactional
    public Semester getCurrentSemester() { 
        return semesterDao.getCurrentSemester();
    }
    
    @Transactional
    public Semester findByName(String name)
    {
        return semesterDao.findByName(name);
    }

}
