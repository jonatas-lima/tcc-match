package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;

public interface StudentService {

    Collection<Student> getAllStudents();

    void performTccOrientationIssue(Long tccId, OrientationIssueDTO orientationIssue);

    void declareTccOrientationInterest(Long tccId);
}
