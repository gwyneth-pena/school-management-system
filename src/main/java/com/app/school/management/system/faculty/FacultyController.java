package com.app.school.management.system.faculty;

import com.app.school.management.system.registerLogin.RegisterLoginService;
import com.app.school.management.system.faculty.Faculty;
import com.app.school.management.system.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/faculty")
public class FacultyController {

    private final RegisterLoginService registerLoginService;
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(RegisterLoginService registerLoginService,
                             FacultyService facultyService) {
        this.registerLoginService = registerLoginService;
        this.facultyService = facultyService;
    }


    @GetMapping(path = " ")
    public String showFacultyDashboard(){
        if(!registerLoginService.isAuthenticatedFaculty())return "redirect:/faculty/login";
        return "facultyDashboard";
    }

    @GetMapping(path = "/profile")
    public String showStudentProfile(Model model){
        if(!registerLoginService.isAuthenticatedFaculty())return "redirect:/faculty/login";
        Optional<Faculty> faculty = facultyService.getFacultyInfo(facultyService.getAuthenticatedFacultyEmail());
        model.addAttribute("facultyProfile",faculty.get());
        return "facultyProfile";
    }




}
