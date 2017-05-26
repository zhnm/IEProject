//FINAL 
package com.sbu.controller;

import com.sbu.controller.model.Login;
import com.sbu.dao.model.User;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value="/welcome")
public class WelcomeController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(HttpServletRequest request, Model model) {
       HttpSession session = request.getSession();
       session.setAttribute("username",request.getParameter("form-username"));
       return "redirect:editinfo";
    }

}
