package com.app.school.management.system.faculty;

import com.app.school.management.system.faculty.Faculty;
import com.app.school.management.system.faculty.FacultyRepository;
import com.app.school.management.system.subject.Subject;
import com.app.school.management.system.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepo;
    private final SubjectRepository subjectRepo;


    @Autowired
    public FacultyService(FacultyRepository facultyRepo, SubjectRepository subjectRepo) {
        this.facultyRepo = facultyRepo;
        this.subjectRepo = subjectRepo;
    }


    public Optional<Faculty> getFacultyInfo(String email){
        Optional<Faculty> faculty = facultyRepo.findFacultyByEmail(email);
        if(faculty.isPresent()){
            return faculty;
        }
        return null;

    }

    public String getAuthenticatedFacultyEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal().toString();
    }


}
