package com.sbu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.dao.impl.ConcentrationDao;
import com.sbu.entity.Concentration;

@Service
public class ConcentrationManager /*implements UserManager*/ {

    public ConcentrationManager() {
    }

    @Autowired
    public ConcentrationDao concentrationDao;


    @Transactional
    public Concentration findByName(String name) { 
        List<Concentration> conces = concentrationDao.findAll();
        for (Concentration c : conces) {
            if(c.getName().equals(name))
                return c;                       
        }
        return null;
    }

}
