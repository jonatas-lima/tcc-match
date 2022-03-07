package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
