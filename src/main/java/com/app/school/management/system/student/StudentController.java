package com.app.school.management.system.student;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {
    @GetMapping(path = "/showSomething")
    public  String  ShowSomething(){
        return "Hi World";
    }




}
