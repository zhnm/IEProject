//FINAL 
package com.sbu.controller;

import com.sbu.entity.Course;
import com.sbu.entity.Major;
import com.sbu.service.impl.CourseManager;
import com.sbu.service.impl.MajorManager;
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
    @Autowired
    public MajorManager majorManager;

    @RequestMapping(value = "/newcourse", method = {RequestMethod.GET})
    public String settings(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        return "createCourse";
    }

    @RequestMapping(value = "/savecourse", method = {RequestMethod.POST, RequestMethod.GET})
    public String newCourse(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        Course c = new Course();
        int inputs = 0;
        if (request.getParameter("name").length() > 0) {
            c.setName(request.getParameter("name"));
            inputs++;
        }
        if (request.getParameter("unit").length() > 0) {
            c.setUnit(Integer.parseInt((String) request.getParameter("unit")));
            inputs++;
        }
        if (request.getParameter("type").length() > 0) {
            c.setCtype(request.getParameter("type"));
            inputs++;
        }
        if (request.getParameter("major").length() > 0) {
            Major major = majorManager.findByName(request.getParameter("major"));
            if(major==null)
            {
                model.addAttribute("massage", "گروه درسی نامعتبر است");
                return "createCourse";
            }
            c.setMajorid(major);
            inputs++;
        }
        if (inputs != 4) {
           model.addAttribute("massage", "ورودی کافی نیست");
        } else {
            coursetManager.insertCourse(c);
            model.addAttribute("massage", "تعریف درس با موفقیت انجام شد");
        }

        return "createCourse";
    }

}
