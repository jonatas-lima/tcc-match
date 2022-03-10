package com.psoft.match.tcc.service.impl;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.AuthService;
import com.psoft.match.tcc.service.ProfessorService;
import com.psoft.match.tcc.service.StudentService;
import com.psoft.match.tcc.service.StudyAreaService;
import com.psoft.match.tcc.service.TCCProposalService;
import com.psoft.match.tcc.service.TCCService;
import com.psoft.match.tcc.dto.TCCProposalDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	public StudyArea addStudyAreaOnStudent(Long idStudyArea) {
		StudyArea studdedArea = studyAreaService.findStudyAreaById(idStudyArea);
		Student user = (Student) authService.getLoggedUser();
		user.addInterestedArea(studdedArea);
		studentRepository.save(user);
		return studdedArea;
	}

	@Override
	public List<Professor> listProfessorsInterested() {
		Student user = (Student) authService.getLoggedUser();
		
		List<Professor> professorsOnSystem = professorService.getAllProfessors();
		List<Professor> professorsToReturn = new ArrayList<Professor>();
		
		for(Professor p: professorsOnSystem) {
			if(p.getQuota() == 0) {
				continue;
			} else if(haveSharedInterests(p.getStudyAreas(), user.getStudyAreas())) {
				professorsToReturn.add(p);
			}
		}
		return professorsToReturn;
	}
	
	private boolean haveSharedInterests(Collection<StudyArea> professorAreas, Collection<StudyArea> studentAreas) {
		for(StudyArea s : studentAreas) {
			if(professorAreas.contains(s)) {
				return true;
			}
		}
		return false;
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
