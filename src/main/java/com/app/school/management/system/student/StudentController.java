package com.app.school.management.system.student;



import com.app.school.management.system.registerLogin.RegisterLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    private final RegisterLoginService registerLoginService;
    private final StudentService studentService;

    @Autowired
    public StudentController(RegisterLoginService registerLoginService,
                             StudentService studentService) {
        this.registerLoginService = registerLoginService;
        this.studentService = studentService;
    }


    @GetMapping(path = " ")
    public String showStudentDashboard(){
        if(!registerLoginService.isAuthenticatedStudent())return "redirect:/student/login";
        return "studentDashboard";
    }

    @GetMapping(path = "/profile")
    public String showStudentProfile(Model model){
        if(!registerLoginService.isAuthenticatedStudent())return "redirect:/student/login";
         Optional<Student> student = studentService.getStudentInfo(studentService.getAuthenticatedStudentEmail());
         model.addAttribute("studProfile",student.get());
        return "studentProfile";
    }




}
