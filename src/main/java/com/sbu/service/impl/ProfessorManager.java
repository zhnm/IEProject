/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.service.impl;

import com.sbu.dao.impl.ProfessorDao;
import com.sbu.entity.Professor;
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
}
