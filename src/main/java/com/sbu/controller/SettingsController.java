//FINAL 
package com.sbu.controller;

import com.sbu.controller.model.Login;
import com.sbu.dao.model.User;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/login")
public class SettingsController {

    
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
        session = request.getSession(false);
        if (session.getAttribute("username") == null) {
            return "welcome";
        }
        model.addAttribute("massage", "Changes Succesfully Saved!");
        return "editinfo";
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
         model.addAttribute("massage",session.getAttribute("username"));
        return "loginstd";
    }
}
