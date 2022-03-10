package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.model.tcc.OrientationInterest;
import com.psoft.match.tcc.model.tcc.OrientationIssue;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.*;
import com.psoft.match.tcc.util.exception.tcc.TCCDoesNotBelongToStudentException;
import com.psoft.match.tcc.util.exception.tcc.UnavailableTCCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TCCService tccService;

    @Autowired
    private AuthService authService;

    @Autowired
    private OrientationIssueService orientationIssueService;

    @Autowired
    private OrientationInterestService orientationInterestService;

    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public void performTccOrientationIssue(Long tccId, OrientationIssueDTO orientationIssueDTO) {
        Student student = authService.getLoggedUser();
        TCC issuedTcc = tccService.findTCCById(tccId);

        if (!issuedTcc.equals(student.getTcc())) {
            throw new TCCDoesNotBelongToStudentException(student.getFullName(), tccId);
        }

        OrientationIssue orientationIssue = new OrientationIssue(orientationIssueDTO.getRelatedIssue(), student, student.getTcc());
        student.addOrientationIssue(orientationIssue);

        orientationIssueService.saveOrientationIssue(orientationIssue);
        studentRepository.save(student);
        tccService.saveTCC(issuedTcc);
    }

    @Transactional
    @Override
    public void declareTccOrientationInterest(Long tccId) {
        Student student = authService.getLoggedUser();
        TCC interestedTcc = tccService.findTCCById(tccId);

        if (!interestedTcc.isAvailable()) {
            throw new UnavailableTCCException(tccId);
        }

        OrientationInterest orientationInterest = new OrientationInterest(student, interestedTcc);
        student.addOrientationInterest(orientationInterest);

        orientationInterestService.saveOrientationInterest(orientationInterest);
        studentRepository.save(student);
    }

}
