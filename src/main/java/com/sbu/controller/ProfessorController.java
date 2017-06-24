/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Professor;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import com.sbu.service.impl.ProfCourseSemManager;
import com.sbu.service.impl.ProfessorManager;
import com.sbu.service.impl.SemesterManager;
import com.sbu.service.impl.StudentManager;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class ProfessorController {

    @Autowired
    ProfessorManager professorManager;
    @Autowired
    SemesterManager semesterManager;
    @Autowired
    ProfCourseSemManager profCourseSemManager;
    @Autowired
    StudentManager studentManager;

    @RequestMapping(value = "/viewsemesters", method = {RequestMethod.GET})
    public String allSemesters(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) == 0) {
            return "redirect:/login/logout";
        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        model.addAttribute("semesters", professorManager.getTeachedSemesters(prof));
        return "viewsemlist";
    }

    //ajax is comming :)
    @RequestMapping(value = "/viewcourses", method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody
    HashMap<Integer, String> semesterCourses(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
//        if (session.getAttribute("username") == null) {
//            return "welcome";
//        }
//        if (Integer.parseInt((String) session.getAttribute("role")) == 0) {
//            return "redirect:/login/logout";
//        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        Semester sem = semesterManager.findByName((String) request.getParameter("semester"));
        HashMap<Integer, String> courseList = professorManager.getCoursesBySemester(prof, sem);
        //model.addAttribute("courses", professorManager.getCoursesBySemester(prof, sem));

        return courseList;
    }

    @RequestMapping(value = "/studentlist", method = {RequestMethod.GET, RequestMethod.POST})
    public String studentList(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) == 0) {
            return "redirect:/login/logout";
        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        ProfCourseSem pcs = profCourseSemManager.findByID(Integer.parseInt((String) request.getParameter("pcsid")));
        session.setAttribute("pcsid", pcs.getId());
        model.addAttribute("students", professorManager.getStudentList(pcs));
        model.addAttribute("coursename", pcs.getCourseid().getName());
        model.addAttribute("term", pcs.getSemid().getName());
        return "studentlist";
    }

    @RequestMapping(value = "/studentlist/save", method = {RequestMethod.GET, RequestMethod.POST})
    public String studentGrading(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) == 0) {
            return "redirect:/login/logout";
        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        ProfCourseSem pcs = profCourseSemManager.findByID((int) session.getAttribute("pcsid"));

        int listSize = professorManager.getStudentList(pcs).size();
        String message = "نمرات با موفقیت ثبت شد";
        Student student;
        float grade;
        //get grade input per student and upadate
        for (int i = 0; i < listSize; i++) {
            try {
                grade = Float.parseFloat((String) request.getParameter("g" + i));
                //check for grade validation
                if (!professorManager.validateGrade(grade)) {
                    message = "لطفا نمرات را در محدوده مجاز تعریف نمایید";
                    break;
                }
            } catch (NumberFormatException e) {
                message = "لطفا نمرات را به عدد وارد کنید";
                break;
            }
            student = studentManager.findByID(Integer.parseInt((String) request.getParameter("s" + i)));
            if (!professorManager.setGrade(pcs, student, grade)) {
                message = " امکان ثبت مجدد نمره دانشجو شماره" + student.getId() + "را ندارید";
                break;
            }
        }
        model.addAttribute("message", message);
        model.addAttribute("students", professorManager.getStudentList(pcs));
        model.addAttribute("coursename", pcs.getCourseid().getName());
        model.addAttribute("term", pcs.getSemid().getName());
        return "studentlist";
    }

    @RequestMapping(value = "/professorlist", method = {RequestMethod.GET})
    public String allProfessors(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        model.addAttribute("list", professorManager.getProfessorsByConce(prof.getConceid()));
        return "print";
    }

    @RequestMapping(value = "/professorlist/courselist", method = {RequestMethod.GET, RequestMethod.POST})
    public String professorCourses(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }

        Professor prof = professorManager.findByID(Integer.parseInt((String) request.getParameter("id")));
        session.setAttribute("professor", prof.getId());
        model.addAttribute("list", profCourseSemManager.getAllCoursesByProfessor(prof));
        return "print1";
    }

    @RequestMapping(value = "/professorlist/courselist/students", method = {RequestMethod.GET, RequestMethod.POST})
    public String professorStudents(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        ProfCourseSem pcs = profCourseSemManager.findByID(Integer.parseInt((String) request.getParameter("id")));
        Professor prof = professorManager.findByID((Integer) session.getAttribute("professor"));
        model.addAttribute("list", professorManager.getStudentList(pcs));
        return "print1";
    }
}
