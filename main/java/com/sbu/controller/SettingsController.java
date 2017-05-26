//FINAL 
package com.sbu.controller;


import com.sbu.entity.Student;
import com.sbu.service.impl.StudentManager;
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
public class SettingsController {
    @Autowired
    public StudentManager studentManager;
    
    @RequestMapping(value = "/editinfo", method = {RequestMethod.GET})
    public String settings(HttpSession session, HttpServletRequest request, Model model) {
        session = request.getSession(false);       
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        return "editinfo";
    }

    
        @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String saveSettings(HttpSession session, HttpServletRequest request, Model model) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        Student s = studentManager.findByID(Integer.parseInt((String) session.getAttribute("username")));
        int phoneNumber = 0;
        String email = "";

        //validate phone number
        if (request.getParameter("telephone").length() != 0) {
            if (validatePhoneNumber(request.getParameter("telephone"))) {
                phoneNumber = Integer.parseInt(request.getParameter("telephone"));
                s.setMobile(phoneNumber);
            } else {
                model.addAttribute("massage", "شماره تلفن نامعتبر است");
                return "editinfo";
            }
        }
        //validate email
        if (request.getParameter("emailAdd").length() > 0) {
            if (validateEmail(request.getParameter("emailAdd"))) {
                email = request.getParameter("emailAdd");
                s.setEmail(email);
            } else {
                model.addAttribute("massage", "آدرس الکترونیکی درست نیست");
                return "editinfo";
            }
        }
        //validate password 
        if (request.getParameter("password").length() > 0) {
            if (!(request.getParameter("password").equals(request.getParameter("confirm")))) {
                model.addAttribute("massage", "تکرار رمز عبور صحیح نیست");
                return "editinfo";
            } else {
                s.setSpassword(request.getParameter("password"));
            }
        }

        if (request.getParameter("name").length() > 0) {
                s.setName(request.getParameter("name"));       
        }
        if (request.getParameter("surname").length() > 0) {
            s.setFamily(request.getParameter("surname"));
        }

        studentManager.updateStudent(s);
        model.addAttribute("massage", "تغییرات با موفقیت انجام شد");
        return "editinfo";
    }
        private boolean validatePhoneNumber(String phoneNo) {
        //validate phone numbers of format "1234567890"
        if (phoneNo.matches("\\d{10}")) {
            return true;
        } //validating phone number with -, . or spaces
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) {
            return true;
        } //validating phone number with extension length from 3 to 5
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) {
            return true;
        } //validating phone number where area code is in braces ()
        else if (phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) {
            return true;
        } //return false if nothing matches the input
        else {
            return false;
        }

    }

    private boolean validateEmail(String mail) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher((CharSequence) mail);
        return matcher.matches();
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(HttpSession session, HttpServletRequest request, Model model) {

        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "welcome";
    }

        @RequestMapping(value = "/loginstd", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginstd(HttpSession session, HttpServletRequest request, Model model) {
        
        Student std=studentManager.findByID(Integer.parseInt((String)session.getAttribute("username")));
        model.addAttribute("name",std.getName());
        model.addAttribute("family",std.getFamily());
        return "loginstd";
    }
}
