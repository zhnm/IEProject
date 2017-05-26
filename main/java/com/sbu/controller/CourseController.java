//FINAL 
package com.sbu.controller;


import com.sbu.entity.Student;
import com.sbu.service.impl.CourseManager;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/login")
public class CourseController {
    
@Autowired
    public CourseManager coursetManager;
    
    @RequestMapping(value = "/newcourse", method = {RequestMethod.GET})
    public String settings(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);       
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        return "createCourse";
    }  
    
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String newCourse(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        //coursetManager.insertCourse(c);
        model.addAttribute("massage", "تعریف درس با موفقیت انجام شد");
        
        return "createCourse";
    }
    
}
