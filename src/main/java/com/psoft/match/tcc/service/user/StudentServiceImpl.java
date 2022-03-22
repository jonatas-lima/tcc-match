package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.StudentRepository;
import com.psoft.match.tcc.service.email.EmailService;
import com.psoft.match.tcc.service.study_area.StudyAreaService;
import com.psoft.match.tcc.service.tcc.TCCService;
import com.psoft.match.tcc.util.exception.student.StudentNotFoundException;
import com.psoft.match.tcc.util.exception.tcc.UnavailableTCCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TCCService tccService;

    @Autowired
    private StudyAreaService studyAreaService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private TCCMatchUserService tccMatchUserService;

    @Autowired
    private EmailService emailService;

    @Transactional
    @Override
    public void registerOrientationIssue(OrientationIssueDTO orientationIssueDTO) {
        Student student = tccMatchUserService.getLoggedUser();
        TCC tcc = student.getTcc();

        tccMatchUserService.registerOrientationIssue(student, tcc, orientationIssueDTO);
        studentRepository.save(student);
    }

    @Transactional
    @Override
    public void declareTccOrientationInterest(Long tccId) {
        Student student = tccMatchUserService.getLoggedUser();
        TCC interestedTcc = tccService.findTCCById(tccId);

        if (!interestedTcc.isAvailable()) {
            throw new UnavailableTCCException(tccId);
        }

        student.addOrientationInterest(interestedTcc);
        interestedTcc.addOrientationInterest(student);

        emailService.notifyNewOrientationInterestToProfessor(interestedTcc.getAdvisor(), student, interestedTcc);

        tccService.saveTCC(interestedTcc);
        studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
	public StudyArea addInterestedStudyArea(Long idStudyArea) {
		StudyArea studyArea = studyAreaService.findStudyAreaById(idStudyArea);
        Student student = tccMatchUserService.getLoggedUser();

		student.addInterestedArea(studyArea);
		studyArea.addInterestedStudent(student);

		studyAreaService.saveStudyArea(studyArea);
		studentRepository.save(student);
		return studyArea;
	}

	@Override
	public List<Professor> listInterestedProfessors() {
		Student student = tccMatchUserService.getLoggedUser();
		return professorService.getAvailableProfessorsWithSharedInterests(student);
	}

    @Transactional
	@Override
	public TCC createTCC(TCCDTO tcc) {
        Student student = tccMatchUserService.getLoggedUser();
		return tccService.createTCC(tcc, student);
	}

	@Override
	public List<TCC> listTccs() {
		return tccService.getAllTccs();
	}
}
