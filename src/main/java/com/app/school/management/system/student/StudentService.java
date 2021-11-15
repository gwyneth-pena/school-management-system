package com.app.school.management.system.student;


import com.app.school.management.system.subject.Subject;
import com.app.school.management.system.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepo;
    private final SubjectRepository subjectRepo;


    @Autowired
    public StudentService(StudentRepository studentRepo, SubjectRepository subjectRepo) {
        this.studentRepo = studentRepo;
        this.subjectRepo = subjectRepo;
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
