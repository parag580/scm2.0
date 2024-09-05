package com.scm.controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import com.scm.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {
     private Logger logger = LoggerFactory.getLogger(UserController.class);
    //doubt request mapping .get  as param near value and we tried to login and failed but when removed we are able to lognin
    //user dashboard page
    @Autowired
    private UserService userService;
  
    @RequestMapping(value="/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }
    //user profile page
    @RequestMapping(value="/profile")
    public String userProfile(Model model,Authentication authentication) {
      
        return "user/profile";
    }
    
   
    //user view contact page

    //user edit contact page
    //user delete page
}
