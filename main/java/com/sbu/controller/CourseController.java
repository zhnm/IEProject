//FINAL 
package com.sbu.controller;

import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.Major;
import com.sbu.service.impl.ConcentrationManager;
import com.sbu.service.impl.CourseManager;
import com.sbu.service.impl.MajorManager;
import com.sbu.service.impl.ProfessorManager;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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
    @Autowired
    public ConcentrationManager concentrationManager;
    @Autowired
    public ProfessorManager professorManager;

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
        model.addAttribute("title", "تعریف درس");
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
            if (major == null) {
                model.addAttribute("massage", "گروه درسی نامعتبر است");
                return "createCourse";
            }
            c.setMajorid(major);
            inputs++;
        }
        if (request.getParameter("concentration").length() > 0) {
            Concentration conce = concentrationManager.findByName(request.getParameter("concentration"));
            if (conce == null) {
                model.addAttribute("massage", "گرایش درسی نامعتبر است");
                return "createCourse";
            }
            c.setConceid(conce);
            inputs++;
        }
        if (inputs != 5) {
            model.addAttribute("massage", "ورودی کافی نیست");
        } else {
            if (!coursetManager.checkMajorConce(c)) {
                model.addAttribute("massage", "گرایش درسی و گروه درسی همخوانی ندارند");
                return "createCourse";
            }
            if (!professorManager.checkAuthorization(Integer.parseInt((String) session.getAttribute("username")), c)) {
                model.addAttribute("massage", "اجازه تعریف درس در گرایش مورد نظر را ندارید");
                return "createCourse";
            }
            coursetManager.insertCourse(c);
            model.addAttribute("massage", "تعریف درس با موفقیت انجام شد");
        }

        return "createCourse";
    }

    @RequestMapping(value = "/changables", method = {RequestMethod.GET})
    public String viewChangableCourses(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        Concentration conce = professorManager.getConcentration(Integer.parseInt((String) session.getAttribute("username")));
        HashMap<String, Integer> courses = coursetManager.getCoursesByConce(conce);
        model.addAttribute("courselist", courses);
        return "editCourse";
    }

    @RequestMapping(value = "/editcourse", method = {RequestMethod.POST, RequestMethod.GET})
    public String editCourse(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        model.addAttribute("title", "تغییر درس");
        Course course = coursetManager.findByID(Integer.parseInt((String) session.getAttribute("changable")));
        model.addAttribute("name", course.getName());
        model.addAttribute("unit", course.getUnit());
        model.addAttribute("type", course.getCtype());
        model.addAttribute("major", course.getConceid().getName());
        session.setAttribute("courseID", course.getId());//**************new********************
        return "changeCourse";
    }

    @RequestMapping(value = "/editcourse/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveChanges(HttpSession session, HttpServletRequest request, Model model) {
        Course c = coursetManager.findByID(Integer.parseInt((String) session.getAttribute("courseID")));

        if (request.getParameter("name").length() > 0) {
            c.setName(request.getParameter("name"));
        }
        if (request.getParameter("unit").length() > 0) {
            c.setUnit(Integer.parseInt((String) request.getParameter("unit")));
        }
        if (request.getParameter("type").length() > 0) {
            c.setCtype(request.getParameter("type"));
        }
        if (request.getParameter("major").length() > 0) {
            Major major = majorManager.findByName(request.getParameter("major"));
            if (major == null) {
                model.addAttribute("massage", "گروه درسی نامعتبر است");
                return "changeCourse";
            }
            c.setMajorid(major);
        }
        if (request.getParameter("concentration").length() > 0) {
            Concentration conce = concentrationManager.findByName(request.getParameter("concentration"));
            if (conce == null) {
                model.addAttribute("massage", "گرایش درسی نامعتبر است");
                return "changeCourse";
            }
            c.setConceid(conce);
        }
        if (!coursetManager.checkMajorConce(c)) {
            model.addAttribute("massage", "گرایش درسی و گروه درسی همخوانی ندارند");
            return "createCourse";
        }
        if (!professorManager.checkAuthorization(Integer.parseInt((String) session.getAttribute("username")), c)) {
            model.addAttribute("massage", "اجازه ویرایش درس در گرایش مورد نظر را ندارید");
            return "createCourse";
        }
        coursetManager.updateCourse(c);
        model.addAttribute("massage", "تغییرات با موفقیت اعمال شد");

        return "changeCourse";
    }

}
