package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCStatus;
import com.psoft.match.tcc.model.user.Admin;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.repository.user.AdminRepository;
import com.psoft.match.tcc.service.study_area.StudyAreaService;
import com.psoft.match.tcc.service.tcc.TCCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ProfessorService professorService;

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

    @Override
    public List<TCC> getTCCs(String tccStatus, String term) {
        TCCStatus status = TCCStatus.fromText(tccStatus);
        return tccService.getTCCsByStatusAndTerm(status, term);
    }

}
