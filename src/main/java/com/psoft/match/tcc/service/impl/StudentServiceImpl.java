package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.model.user.User;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.AuthService;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.service.StudyAreaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StudyAreaService studyAreaService;

    @Autowired
    private AuthService authService;
    
    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

	@Override
	public StudyArea addStudyAreaOnStudent(Long idStudyArea) {
		StudyArea studdedArea = studyAreaService.findStudyAreaById(idStudyArea);
		Student user = (Student) authService.getLoggedUser();
		user.addInterestedArea(studdedArea);
		studentRepository.save(user);
		return studdedArea;
	}
}
