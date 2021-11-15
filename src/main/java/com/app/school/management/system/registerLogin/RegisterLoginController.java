package com.app.school.management.system.registerLogin;


import com.app.school.management.system.admin.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(path="/")
public class RegisterLoginController {

    private final RegisterLoginService registerLoginService;



    @Autowired
    public RegisterLoginController(RegisterLoginService registerLoginService) {
        this.registerLoginService = registerLoginService;
    }


    @GetMapping(path="/admin/login")
    public String showAdminLoginForm(Model model){
        model.addAttribute("adminLogin",new User());
        if(registerLoginService.isAuthenticatedAdmin())return "redirect:/admin";
        if(registerLoginService.isAuthenticatedStudent()) return "redirect:/student";
        return "adminLogin";
    }



    @GetMapping(path="/student/login")
    public String showStudentLoginForm(Model model){
       model.addAttribute("studentLogin",new User());
        if(registerLoginService.isAuthenticatedStudent())return "redirect:/student";
        if(registerLoginService.isAuthenticatedAdmin()) return "redirect:/admin";
        return "studentLogin";
    }

    @GetMapping(path = "admin/logout")
    public String goBackToAdminLogin(){
        SecurityContextHolder.clearContext();
        return "redirect:/admin/login";
    }
    @GetMapping(path = "student/logout")
    public String goBackToStudentLogin(){
        SecurityContextHolder.clearContext();
        return "redirect:/student/login";
    }

    @GetMapping(path="/admin/register")
    public String showRegisterForm(Model model){
        if(registerLoginService.isAuthenticatedAdmin())return "redirect:/admin";
        model.addAttribute("admin",new Admin());
        return "adminRegister";
    }

    @PostMapping(path="/admin/register")
    public String addAdmin(@ModelAttribute Admin admin){
        registerLoginService.addAdmin(admin);
        return "redirect:/admin/login";
    }


    @PostMapping(path = "/admin/login")
    public String validateAdminLogin(@ModelAttribute User user){
        if(registerLoginService.validateLogin(user, "ROLE_ADMIN")==" "){
            return "redirect:/admin";
        }
        return "redirect:/admin/login?error";

    }

    @PostMapping(path = "/student/login")
    public String validateStudentLogin(@ModelAttribute User user){
        if(registerLoginService.validateLogin(user, "ROLE_STUDENT")==" "){
            return "redirect:/student";
        }
        return "redirect:/student/login?error";

    }


}
