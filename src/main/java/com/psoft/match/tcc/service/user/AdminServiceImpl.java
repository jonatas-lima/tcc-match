package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.dto.TCCOrientationDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import com.psoft.match.tcc.model.user.Admin;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import com.psoft.match.tcc.repository.user.AdminRepository;
import com.psoft.match.tcc.response.TCCSummaryResponse;
import com.psoft.match.tcc.service.study_area.StudyAreaService;
import com.psoft.match.tcc.service.tcc.TCCService;
import com.psoft.match.tcc.util.exception.tcc.InvalidTermException;
import com.psoft.match.tcc.util.exception.tcc.PendingTCCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudyAreaService studyAreaService;

    @Autowired
    private TCCService tccService;

    @Override
    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    @Transactional
    @Override
    public Professor createProfessor(ProfessorDTO professorDTO) {
        return professorService.createProfessor(professorDTO);
    }

    @Transactional
    @Override
    public StudyArea createStudyArea(StudyAreaDTO studyAreaDTO) {
        return studyAreaService.createStudyArea(studyAreaDTO);
    }

    @Transactional
    @Override
    public Professor updateProfessor(Long professorId, ProfessorDTO professorDTO) {
        return professorService.updateProfessor(professorId, professorDTO);
    }

    @Transactional
    @Override
    public StudyArea updateStudyArea(Long studyAreaId, StudyAreaDTO studyArea) {
        return studyAreaService.updateStudyArea(studyAreaId, studyArea);
    }

    @Transactional
    @Override
    public void deleteProfessor(Long professorId) {
        professorService.deleteProfessor(professorId);
    }

    @Transactional
    @Override
    public void deleteStudyArea(Long studyAreaId) {
        studyAreaService.deleteStudyArea(studyAreaId);
    }

    @Transactional
    @Override
    public void registerTCC(TCCOrientationDTO tccOrientationDTO) {
        Student student = studentService.findById(tccOrientationDTO.getStudentId());
        TCC tcc = tccService.findTCCById(tccOrientationDTO.getTccId());

        if (tcc.getTccStatus().equals(TCCStatus.PENDING)) {
            throw new PendingTCCException(tccOrientationDTO.getTccId());
        }

        student.setTcc(tcc);
        tcc.setAdvisedStudent(student);
        tcc.setTccStatus(TCCStatus.ON_GOING);
        tcc.setTerm(tccOrientationDTO.getTerm());

        studentService.saveStudent(student);
        tccService.saveTCC(tcc);
    }

    @Override
    public void finalizeTCC(Long tccId, String term) {
        this.validateTerm(term);

        TCC tcc = tccService.findTCCById(tccId);

        tcc.setTerm(term);
        tcc.finalizeTCC();
        tccService.saveTCC(tcc);
    }

    @Override
    public TCCSummaryResponse getTCCSummary(String term) {
        this.validateTerm(term);

        Collection<TCC> onGoingTCCs = tccService.getTCCsByStatusAndTerm(TCCStatus.ON_GOING, term);
        Collection<TCC> finishedTCCs = tccService.getTCCsByStatusAndTerm(TCCStatus.FINISHED, term);

        return new TCCSummaryResponse(term, onGoingTCCs, finishedTCCs);
    }

    @Override
    public List<TCC> getTCCs(String tccStatus, String term) {
        TCCStatus status = TCCStatus.fromText(tccStatus);
        return tccService.getTCCsByStatusAndTerm(status, term);
    }

    private void validateTerm(String term) {
        Pattern pattern2000 = Pattern.compile("20[0-2]\\d\\.[1-2]");
        Pattern pattern1900 = Pattern.compile("19\\d{2}\\.[1-2]");

        if (!(pattern2000.matcher(term).find() || pattern1900.matcher(term).find())) throw new InvalidTermException(term);
    }
}
