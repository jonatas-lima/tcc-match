package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudyAreaService studyAreaService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private TCCProposalService tccProposalService;

    @Autowired
    private TCCService tccService;
    
    @Override
    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

	@Override
	public StudyArea addInterestedStudyArea(Long idStudyArea) {
		StudyArea studyArea = studyAreaService.findStudyAreaById(idStudyArea);
		Student student = authService.getLoggedUser();

		student.addInterestedArea(studyArea);
		studyArea.addInterestedStudent(student);

		studyAreaService.saveStudyArea(studyArea);
		studentRepository.save(student);
		return studyArea;
	}

	@Override
	public List<Professor> listInterestedProfessors() {
		Student user = authService.getLoggedUser();
		return professorService.getAvailableProfessorsWithSharedInterests(user);
	}

	@Override
	public TCCProposal addTccProposal(TCCProposalDTO tcc) {
		return tccProposalService.createTCCProposal(tcc);
	}

	@Override
	public List<TCC> listTccs() {
		return tccService.getAllTccs();
	}
}
