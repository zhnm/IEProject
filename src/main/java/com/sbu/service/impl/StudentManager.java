package com.sbu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbu.dao.impl.StudentDao;
import com.sbu.entity.Student;

@Service("studentManager")
public class StudentManager {

    public StudentManager() {
    }

    @Autowired
    public StudentDao studentDao;

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
    public boolean isValid(Integer id,Integer pass){
        Student std=findByID(id);
        return std != null && Integer.parseInt(std.getSpassword()) == pass;
    }
    
    @Transactional
    public void updateStudent(Student student) {
       
        studentDao.updateStudent(student);
       /* String name = student.getName();
        String familyName = student.getFamily();
        int phone = student.getMobile();
        String email = student.getEmail();
        String password = student.getSpassword();       
       
        if(name.length()!=0)
            studentDao.updateName(name);
        if(familyName.length()!=0)
            studentDao.updateFamilyName(familyName);
        if(phone!=0)
            studentDao.updatePhone(phone);
        if(email.length()!=0)
            studentDao.updateEmail(email);
        if(password.length()!=0)
            studentDao.updatePassword(password);*/
    }

}
