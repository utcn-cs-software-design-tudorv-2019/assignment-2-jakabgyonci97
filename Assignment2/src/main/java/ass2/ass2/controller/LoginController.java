package ass2.ass2.controller;

import ass2.ass2.business.LoginService;
import ass2.ass2.persistence.entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

@Controller
public class LoginController {

    @Inject
    private LoginService loginService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView viewLogin(){

        UserAccount user = new UserAccount();
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user",user);
        return mav;
    }

    @RequestMapping(value="/", method= RequestMethod.POST)
    public ModelAndView handleLogin(@ModelAttribute(value = "user") UserAccount user){
        UserAccount validUser = loginService.loginUser(user);
        System.out.println(validUser);
        if(validUser != null){
            if(validUser.getUserType().compareTo(UserAccount.ROLES[0])==0){
                System.out.println("STUDENT");
                Student student = loginService.loginAsStudent(validUser);

                ModelAndView mav = new ModelAndView("studentProfile");
                mav.addObject("student",student);
                return mav;
            }
            if(validUser.getUserType().compareTo(UserAccount.ROLES[1])==0){
                System.out.println("ADMIN");
                return new ModelAndView("adminProfile");
            }
        }
        return new ModelAndView("redirect:/");
    }
}
