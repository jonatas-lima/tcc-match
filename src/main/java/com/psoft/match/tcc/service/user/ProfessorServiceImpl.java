package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.ProfessorRepository;
import com.psoft.match.tcc.service.email.EmailService;
import com.psoft.match.tcc.service.study_area.StudyAreaService;
import com.psoft.match.tcc.service.tcc.TCCService;
import com.psoft.match.tcc.util.exception.professor.InvalidQuotaException;
import com.psoft.match.tcc.util.exception.professor.ProfessorAlreadyInterestedInStudyAreaException;
import com.psoft.match.tcc.util.exception.professor.ProfessorNotFoundException;
import com.psoft.match.tcc.util.exception.professor.TCCDoesNotBelongToProfessorException;
import com.psoft.match.tcc.util.exception.student.StudentDoesNotHaveOrientationInterestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TCCMatchUserService tccMatchUserService;

    @Autowired
    private TCCService tccService;

    @Autowired
    private StudyAreaService studyAreaService;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Professor> getAvailableProfessors() {
        return professorRepository
                .findAll()
                .stream()
                .filter(professor -> professor.getQuota() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Professor> getAvailableProfessorsWithSharedInterests(Student student) {
        return this.getAvailableProfessors()
                .stream()
                .filter(professor -> professor.hasSharedInterestedWith(student))
                .collect(Collectors.toList());
    }

    @Override
    public Professor findProfessorById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException(id));
    }

    @Transactional
    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Transactional
    @Override
    public void approveOrientationInterest(Long tccId, Long studentId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        Student student = studentService.findStudentById(studentId);
        TCC tcc = tccService.findTCCById(tccId);

        this.validateOrientation(professor, tcc, student);

        tcc.approveTCC();
        tcc.setAdvisedStudent(student);
        tcc.setAdvisor(professor);

        emailService.notifyApprovedOrientationToAdmin(tcc);

        tccService.saveTCC(tcc);
    }

    @Transactional
    @Override
    public void refuseOrientationInterest(Long tccId, Long studentId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        Student student = studentService.findStudentById(studentId);
        TCC tcc = tccService.findTCCById(tccId);

        this.validateOrientation(professor, tcc, student);

        tcc.removeOrientationInterest(student);
        student.removeOrientationInterest(tcc);

        tccService.saveTCC(tcc);
        studentService.saveStudent(student);
    }

    private void validateOrientation(Professor professor, TCC tcc, Student student) {
        if (!professor.getRegisteredTCCs().contains(tcc)) throw new TCCDoesNotBelongToProfessorException(tcc.getId(), professor.getFullName());
        if (!tcc.getInterestedStudents().contains(student)) throw new StudentDoesNotHaveOrientationInterestException(student.getFullName());
    }

    @Transactional
    @Override
    public void deleteProfessor(Professor professor) {
        professorRepository.delete(professor);
    }

    @Transactional
    @Override
    public void declareOrientationInterest(Long tccId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        TCC tcc = tccService.findTCCById(tccId);

        professor.addOrientationInterest(tcc);
        tcc.addOrientationInterest(professor);

        emailService.notifyNewOrientationInterestToStudent(tcc.getAdvisedStudent(), professor, tcc);

        tccService.saveTCC(tcc);
        professorRepository.save(professor);
    }

    @Transactional
    @Override
    public void addInterestedStudyArea(Long studyAreaId) {
        Professor professor = tccMatchUserService.getLoggedUser();
        StudyArea studyArea = studyAreaService.findStudyAreaById(studyAreaId);

        this.validateAddInterestedStudyArea(professor, studyArea);

        professor.addInterestedStudyArea(studyArea);
        studyArea.addInterestedProfessor(professor);

        professorRepository.save(professor);
        studyAreaService.saveStudyArea(studyArea);
    }

    private void validateAddInterestedStudyArea(Professor professor, StudyArea studyArea){
        if(professor.getInterestedStudyAreas().contains(studyArea)){
            throw new ProfessorAlreadyInterestedInStudyAreaException(professor.getFullName(), studyArea.getDescription());
        }
    }

    @Transactional
    @Override
    public void updateQuota(Integer quota) {
        Professor professor = tccMatchUserService.getLoggedUser();

        this.validateQuota(quota);

        professor.setQuota(quota);

        professorRepository.save(professor);
    }

    @Transactional
    @Override
    public void registerOrientationIssue(Long tccId, OrientationIssueDTO orientationIssueDTO) {
        Professor professor = tccMatchUserService.getLoggedUser();
        TCC tcc = tccService.findTCCById(tccId);

        if (professor.getRegisteredTCCs().contains(tcc)) throw new TCCDoesNotBelongToProfessorException(tccId, professor.getFullName());

        tccMatchUserService.registerOrientationIssue(professor, tcc, orientationIssueDTO);

        professorRepository.save(professor);
    }

    private void validateQuota(Integer quota) {
        if(quota < 0) throw new InvalidQuotaException();
    }

    @Override
    public Collection<TCC> getRegisteredTCCs() {
        Professor professor = tccMatchUserService.getLoggedUser();

        return professor.getRegisteredTCCs();
    }

    @Override
    public Collection<TCC> getStudentsTCCs() {
        return tccService.getStudentsTCCs();
    }

    @Transactional
    @Override
    public TCC createTCC(TCCDTO tccdto) {
        Professor professor = tccMatchUserService.getLoggedUser();
        Collection<StudyArea> studyAreas = studyAreaService.findStudyAreasById(tccdto.getStudyAreasIds());

        TCC tcc = tccService.createTCC(tccdto, professor);
        professor.registerTCC(tcc);

        professorRepository.save(professor);
        studyAreaService.notifyNewTCCToInterestedStudents(studyAreas, tcc);

        return tcc;
    }

	@Override
	public Collection<TCC> getOngoingGuidelines() {
		Professor professor = tccMatchUserService.getLoggedUser();
		Collection<TCC> orientations = new HashSet<TCC>();
		
        Collection<TCC> tccs = professor.getRegisteredTCCs();
         
        for(TCC tcc : tccs) {
        	if(tcc.getAdvisedStudent() != null) {
        		orientations.add(tcc);
        	}
        }
        return orientations;
	}

}
