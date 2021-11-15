package com.app.school.management.system.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo) {
        this.studentRepo = studentRepo;
    }

    public Optional<Student> getStudentInfo(String email){
        Optional<Student> student = studentRepo.findStudentByEmail(email);
        if(student.isPresent()){
            return student;
        }
        return null;

    }

   public String getAuthenticatedStudentEmail(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return authentication.getPrincipal().toString();
   }


}
