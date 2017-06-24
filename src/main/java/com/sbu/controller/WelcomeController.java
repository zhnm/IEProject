//FINAL 
package com.sbu.controller;

import com.sbu.controller.model.Login;
import com.sbu.dao.model.User;
import com.sbu.service.impl.Person;
import com.sbu.service.impl.ProfessorManager;
import com.sbu.service.impl.StudentManager;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/welcome")
public class WelcomeController {

    @Autowired
    @Qualifier("person")
    public Person person;

    @Autowired
    @Qualifier("studentManager")
    public StudentManager studentManager;

    @Autowired
    @Qualifier("professorManager")
    public ProfessorManager professorManager;

    @RequestMapping(method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public void saveUser(@RequestBody User user) {
              
	}

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        session.setAttribute("username", request.getParameter("form-username"));
        String user = (String) request.getParameter("form-username");
        String pass = (String) request.getParameter("form-password");
        if (person.isValid(user)) {
            if (!person.checkLengthPass(user)) { //student
                if (studentManager.isValid(Integer.parseInt(user), Integer.parseInt(pass))) {
                    session.setAttribute("role", "0");
                    return "redirect:/login/loginstd";
                } else {
                    return "welcome";
                }
            } else {//professor or manager
                if (professorManager.isValid(Integer.parseInt(user), Integer.parseInt(pass))) {
                    if (!professorManager.isManager(Integer.parseInt(user))) {
                        session.setAttribute("role", "1"); //professor
                        return "redirect:/login/loginprof";
                    } else {
                        session.setAttribute("role", "2"); //manager & professor
                        return "redirect:/login/loginmng";
                    }
                } else {
                    return "welcome";
                }
            }

            //return "welcome";
        } else { //user is not valid username!
            return "welcome";
        }

    }

}
