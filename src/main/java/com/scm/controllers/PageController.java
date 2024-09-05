package com.scm.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;

import jakarta.validation.Valid;



@Controller
public class PageController {
    @Autowired
    private UserService userService;
    
 @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home page handler");
        // sending data to view
        model.addAttribute("name", "Substring Technologies");
        model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
        model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }
     
    @GetMapping("/contact")
        public String contact(){
            return new String("contact");
        }

        @GetMapping("/login")
            public String login(){
                return new String("login");
            }

            @GetMapping("/register")
                public String register(Model model){
                    UserForm userForm=new UserForm();
                   // userForm.setName("techect");
                    model.addAttribute("userForm",userForm);

                    return  "register";
                }
    
          //processing register
          @RequestMapping(value = "/do-register",method = RequestMethod.POST)
          public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult, HttpSession session ){
            System.out.println("Processing registration");
            System.out.println(userForm);
            if(rBindingResult.hasErrors()){
                return "register";
            }
            //fetch the form data
            //UserForm class object receive whole data of form
            //validate form data
            //TODO:: validate userform
        
            //save to database

            //user services(all method which execute user business logic)
            // User user=User.builder()
            // .name(signupForm.getName())
            // .email(signupForm.getEmail())
            // .password(signupForm.getPassword())
            // .about(signupForm.getAbout())
            // .phoneNumber(signupForm.getPhoneNumber())
            // .profilePic("https://www.freepik.com/free-photo/orange-symbol-sharing-icon_2768368.htm#fromView=search&page=1&position=2&uuid=c3e5dfc4-4f6f-43d4-9b8d-6f00932344c5 ")
            // .build();
            User user=new User();
            user.setName(userForm.getName());
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user.setPassword(userForm.getPassword());
            user.setPhoneNumber(userForm.getPhoneNumber());
            user.setProfilePic("\"https://www.freepik.com/free-photo/orange-symbol-sharing-icon_2768368.htm#fromView=search&page=1&position=2&uuid=c3e5dfc4-4f6f-43d4-9b8d-6f00932344c5");
           User savedUser= userService.saveUser(user);
           System.out.println("savedUser");
            //message="Registration successful"
         Message message= Message.builder().content("Registration Successful").type(MessageType.green).build();
            session.setAttribute("message",message);
            //redirect to login page
            return "redirect:/register";
          }
  
}
