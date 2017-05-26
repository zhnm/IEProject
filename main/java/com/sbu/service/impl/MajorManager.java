package com.sbu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.dao.impl.MajorDao;
import com.sbu.entity.Major;

@Service
public class MajorManager /*implements UserManager*/ {

    public MajorManager() {
    }

    @Autowired
    public MajorDao majorDao;

    //@Override
    @Transactional
    public void insertMajor(Major major) {
        majorDao.insertMajor(major);
    }

    @Transactional
    public Major findByName(String name) { 
        List<Major> majors = majorDao.findAll();
        for (Major m : majors) {
            if(m.getName().equals(name))
                return m;                       
        }
        return null;
    }

}
