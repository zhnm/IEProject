//FINAL 
package com.sbu.controller;

import com.sbu.entity.Concentration;
import com.sbu.entity.Course;
import com.sbu.entity.Major;
import com.sbu.entity.ProfCourseSem;
import com.sbu.entity.Professor;
import com.sbu.entity.Semester;
import com.sbu.service.impl.ConcentrationManager;
import com.sbu.service.impl.CoprecoManager;
import com.sbu.service.impl.CourseManager;
import com.sbu.service.impl.MajorManager;
import com.sbu.service.impl.ProfCourseSemManager;
import com.sbu.service.impl.ProfessorManager;
import com.sbu.service.impl.SemesterManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    public CoprecoManager coprecoManager;
    @Autowired
    public SemesterManager semesterManager;
    @Autowired
    public ProfCourseSemManager profCourseSemManager;

    @RequestMapping(value = "/newcourse", method = {RequestMethod.GET})
    public String settings(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        Professor professor = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        List<Concentration> allowedCon = professorManager.getAllowedConce(professor);
        ArrayList<String> allowed = new ArrayList<String>();
        for (int i = 0; i < allowedCon.size(); ++i) {
            allowed.add(allowedCon.get(i).getName());
        }
        session.setAttribute("allowed", allowed);
        return "createCourse";
    }

    @RequestMapping(value = "/savecourse", method = {RequestMethod.POST, RequestMethod.GET})
    public String newCourse(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
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
            c.setAllowedConce(conce.getName());
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

            String cname = "n";
            int i = 0;
            ArrayList<String> precos = new ArrayList();
            String preCourseName = request.getParameter(cname + String.valueOf(i));
            while (preCourseName != null && preCourseName.length() > 0) {
                precos.add(preCourseName);
                i++;
                preCourseName = request.getParameter(cname + String.valueOf(i));
            }
            //allowedConc
            String[] allowedStr = request.getParameterValues("allowedConc");
            //***********************ASAAAAAAAALLLL************************************
            if (allowedStr == null) {
                model.addAttribute("massage", "حداقل یک گروه درسی مجاز انتخاب کنید");
                return "createCourse";
            }
            String setAllowedCo = "";
            for (int index = 0; index < allowedStr.length - 1; ++index) {
                setAllowedCo += allowedStr[index] + "&";
            }

            setAllowedCo += allowedStr[allowedStr.length - 1];
            c.setAllowedConce(setAllowedCo);
            coursetManager.insertCourse(c, precos);
            model.addAttribute("massage", "تعریف درس با موفقیت انجام شد");
        }

        return "createCourse";
    }

    @RequestMapping(value = "/newterm", method = {RequestMethod.POST, RequestMethod.GET})
    public String newTerm(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        return "createTerm";
    }

    @RequestMapping(value = "/saveterm", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveterm(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        String year = request.getParameter("year");
        String type = request.getParameter("type");
        if (semesterManager.validateYear(year)) {
            if(semesterManager.findByName(year + type)==null)//new
            {
                Semester semestre = new Semester();
            semestre.setName(year + type);
            semesterManager.save(semestre);
            model.addAttribute("massage", "ترم با موفقیت ثبت شد");
            }
            else//new
                model.addAttribute("massage", "ترم تحصیلی قبلا تعریف شده است");//new
            
        } else {
            model.addAttribute("massage", "سال تحصیلی نامعتبر است");
        }
        return "createTerm";
    }

    @RequestMapping(value = "/changables", method = {RequestMethod.GET})
    public String viewChangableCourses(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        Concentration conce = professorManager.getConcentration(Integer.parseInt((String) session.getAttribute("username")));
        HashMap<String, Integer> courses = coursetManager.getCoursesByConce(conce);
        model.addAttribute("courselist", courses);

        return "editCourse";
    }

    @RequestMapping(value = "/editcourse", method = {RequestMethod.POST, RequestMethod.GET})
    public String editCourse(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        model.addAttribute("title", "تغییر درس");
        Course course = coursetManager.findByID(Integer.parseInt((String) request.getParameter("changable")));
        session.setAttribute("cname", course.getName());
        session.setAttribute("unit", course.getUnit());
        session.setAttribute("type", course.getCtype());
        session.setAttribute("major", course.getMajorid().getName());
        session.setAttribute("concentration", course.getConceid().getName());
        session.setAttribute("courseID", course.getId());
        ArrayList<String> precourses = coprecoManager.getAllPreCoNames(course);//******************NEW***************************
        session.setAttribute("precourses", precourses);//******************NEW***************************
        ArrayList<String> precouncheck = coursetManager.findByMajorID(course.getMajorid().getId());
        precouncheck.remove(precouncheck.indexOf(course.getName()));
        for (int i = 0; i < precouncheck.size(); ++i) {
            if (precourses.contains(precouncheck.get(i))) {
                precouncheck.remove(i);
            }
        }
        session.setAttribute("precouncheck", precouncheck);
        String[] allowed = course.getAllowedConce();
        Professor professor = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        List<Concentration> cons = professorManager.getAllowedConce(professor);
        List<String> consname = new ArrayList<>();

        for (int j = 0; j < cons.size(); ++j) {
            consname.add(cons.get(j).getName());
        }
        for (int z = 0; z < allowed.length; ++z) {
            if (consname.contains(allowed[z])) {
                consname.remove(allowed[z]);
            }
        }
        session.setAttribute("allowed", allowed);
        session.setAttribute("allowedun", consname);
        return "changeCourse";
    }

    @RequestMapping(value = "/editcourse/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveChanges(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        //Course c = coursetManager.findByID(Integer.parseInt((String) session.getAttribute("courseID")));
        Course c = coursetManager.findByID((Integer) session.getAttribute("courseID"));
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
        //pre course
        String[] precourse = request.getParameterValues("preco");

        ArrayList<String> precolist = new ArrayList<>();
        Collections.addAll(precolist, precourse);

        //*************ASAL***********
        coprecoManager.updateDependencies(c, precolist);

        //allowedCon
        String[] allowedStr = request.getParameterValues("allow");
        //***********************ASAAAAAAAALLLL************************************
        if (allowedStr == null) {
            model.addAttribute("massage", "حداقل یک گروه درسی مجاز انتخاب کنید");
            return "changeCourse";
        }
        String setAllowedCo = "";
        for (int index = 0; index < allowedStr.length - 1; ++index) {
            setAllowedCo += allowedStr[index] + "&";
        }

        setAllowedCo += allowedStr[allowedStr.length - 1];
        c.setAllowedConce(setAllowedCo);

        coursetManager.updateCourse(c);
        model.addAttribute("massage", "تغییرات با موفقیت اعمال شد");

        return "changeCourse";
    }

    @RequestMapping(value = "/editCurrentSemester", method = {RequestMethod.POST, RequestMethod.GET})
    public String editCurrentSemester(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }

        //upper table contents
        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        model.addAttribute("current", profCourseSemManager.currentSemester(prof.getConceid()));
        HashMap<String, Integer> courses = coursetManager.getCoursesByConce(prof.getConceid());
        model.addAttribute("courselist", courses);
        HashMap<Integer, String> professors = professorManager.getProfessorsByConce(prof.getConceid());
        model.addAttribute("proflist", professors);
        return "offerCourse";
    }

    @RequestMapping(value = "/editCurrentSemester/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveCurrentSemesterChanges(HttpSession session, HttpServletRequest request, Model model) {
        if (Integer.parseInt((String) session.getAttribute("role")) != 2) {
            return "redirect:/login/logout";
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        
        ProfCourseSem pcs = new ProfCourseSem();
        pcs.setCourseid(coursetManager.findByID(Integer.parseInt((String) request.getParameter("course"))));
        pcs.setProfid(professorManager.findByID(Integer.parseInt((String) request.getParameter("prof"))));
        pcs.setSemid(semesterManager.getCurrentSemester());
        pcs.setPtime("-");
        profCourseSemManager.save(pcs);
        model.addAttribute("massage", "درس با موفقیت در ترم جاری ارائه شد");
        
        //upper table contents
        Professor prof = professorManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        model.addAttribute("current", profCourseSemManager.currentSemester(prof.getConceid()));
       
        HashMap<String, Integer> courses = coursetManager.getCoursesByConce(prof.getConceid());
        model.addAttribute("courselist", courses);
        HashMap<Integer, String> professors = professorManager.getProfessorsByConce(prof.getConceid());
        model.addAttribute("proflist", professors);//changed

        

        return "offerCourse";
    }

}
