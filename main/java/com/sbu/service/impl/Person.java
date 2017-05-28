/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sbu.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author WIN 10
 */
@Service("person")
public class Person {
    
    
    public boolean isValid(String user){
        return user.length() == 6 || user.length() == 8;
    }
    
    //0--->student(8)
    //1--->professor(6)
    public boolean checkLengthPass(String user){ 
        return user.length() != 8;
    }
}
