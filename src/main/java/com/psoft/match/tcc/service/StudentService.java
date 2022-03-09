package com.psoft.match.tcc.service;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;

public interface StudentService {

    Collection<Student> getAllStudents();

	StudyArea addStudyAreaOnStudent(Long idStudyArea);
}
