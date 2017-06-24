/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sbu.controller;

import com.sbu.entity.Course;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Semester;
import com.sbu.entity.Student;
import com.sbu.entity.StudentCourse;
import com.sbu.service.impl.CoprecoManager;
import com.sbu.service.impl.CourseManager;
import com.sbu.service.impl.ProfCourseSemManager;
import com.sbu.service.impl.SemesterManager;
import com.sbu.service.impl.StudentCourseManager;
import com.sbu.service.impl.StudentManager;
import java.util.ArrayList;
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
public class StudentController {

    @Autowired
    StudentManager studentManager;
    @Autowired
    SemesterManager semesterManager;
    @Autowired
    ProfCourseSemManager profCourseSemManager;
    @Autowired
    CoprecoManager coprecoManager;
    @Autowired
    StudentCourseManager studentCourseManager;

    @RequestMapping(value = "/general", method = {RequestMethod.GET, RequestMethod.POST})
    public String generalInfo(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) != 0) {
            return "redirect:/login/logout";
        }

        Student s = studentManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        ArrayList<String[]> studentInfo = studentManager.generalInformation(s);
        model.addAttribute("studentInfo", studentInfo);
        model.addAttribute("name",s.getName()+" "+s.getFamily());
        return "gradeTerm";
    }

    @RequestMapping(value = "/semesterGrades", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ArrayList<String[]> semesterGrades(HttpSession session, HttpServletRequest request, Model model, String semester) {
        session = request.getSession(false);

        Student st = studentManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        Semester sem = semesterManager.findByName(semester);
        ArrayList<String[]> studentInfo = studentManager.getSemesterGrades(st, sem);

        return studentInfo;
    }

    @RequestMapping(value = "/takecourse", method = {RequestMethod.GET, RequestMethod.POST})
    public String takeCourse(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        if (Integer.parseInt((String) session.getAttribute("role")) != 0) {
            return "redirect:/login/logout";
        }

        Student st = studentManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        ArrayList<String[]> allowedCo = profCourseSemManager.availableCourses(st);
        model.addAttribute("allowedCo", allowedCo);
        return "takecourse";
    }

    @RequestMapping(value = "/submitcourse", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    ArrayList<String[]> submitCourse(HttpSession session, HttpServletRequest request, Model model, String pcs, String profName) {
        session = request.getSession(false);
        ArrayList<String[]> studentInfo = new ArrayList<String[]>();

        ProfCourseSem pcs1 = profCourseSemManager.findByID(Integer.parseInt(pcs));//find pcs(which prof which course which semester)
        Student student = studentManager.findByID(Integer.parseInt((String) session.getAttribute("username")));

        //Error code
        //0--->precourse
        //1--->retaking
        //2--->reselecting
        //3--->limit unit
        String[] strError = new String[1];
        //*********ZAHRA MOVE THIS TO MANAGER !!!!!!*************
        //check preCourses

        Course c = pcs1.getCourseid(); //find which course is selected
        ArrayList<Integer> allPreCoId = coprecoManager.getAllPreCoID(c); //find list of precourses of c
        //check to be sure that all precourses have been conformed
        ArrayList<Integer> allPassedCo = studentCourseManager.passedCourses(student);
        for (int i = 0; i < allPreCoId.size(); ++i) {
            if (!allPassedCo.contains(allPreCoId.get(i))) {
                strError[0] = "0";
                studentInfo.add(strError);
                return studentInfo; //it is empty
            }
        }
        //*********ZAHRA MOVE THIS TO MANAGER !!!!!!*************

        //*****************ASAL**********************
        if (studentCourseManager.hasPassed(student, pcs1)) {
            System.out.println("امکان اخذ مجدد درس گذرانده شده وجود ندارد");
            strError[0] = "1";
            studentInfo.add(strError);
            return studentInfo;
        }
        if (studentCourseManager.alreadySelected(student, pcs1)) {
            System.out.println("درس قبلا انتخاب شده است");
            strError[0] = "2";
            studentInfo.add(strError);
            return studentInfo;
        }
        if (studentCourseManager.unitLimitBroken(student)) {
            System.out.println("حداکثر واحد مجاز اخذ شده است");
            strError[0] = "3";
            studentInfo.add(strError);
            return studentInfo;
        }

        StudentCourse sc = new StudentCourse();
        sc.setStid(student);
        sc.setPcsid(pcs1);
        sc.setGrade(0f);
        sc.setConfirmed(false);
        studentCourseManager.insert(sc);
        
        String[] str = new String[]{Integer.toString(c.getId()),c.getName(),profName ,Integer.toString(c.getUnit()),c.getCtype()};
        studentInfo.add(str);
        return studentInfo;
    }

}
