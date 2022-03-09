package com.psoft.match.tcc.service;

import com.psoft.match.tcc.dto.TCCProposalDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;
import java.util.List;

public interface StudentService {

    Collection<Student> getAllStudents();

	StudyArea addStudyAreaOnStudent(Long idStudyArea);
	
	List<Professor> listProfessorsInterested();
	
	TCCProposal addTccProposal(TCCProposalDTO tcc);
}
