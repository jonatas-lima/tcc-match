package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.StudyAreaDTO;
import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Admin;
import com.psoft.match.tcc.model.user.Professor;

import java.util.List;

public interface AdminService {

    List<Admin> findAllAdmins();

    Professor createProfessor(ProfessorDTO professorDTO);

    StudyArea createStudyArea(StudyAreaDTO studyAreaDTO);

    Professor updateProfessor(Long professorId, ProfessorDTO professorDTO);

    StudyArea updateStudyArea(Long studyAreaId, StudyAreaDTO studyAreaDTO);

    void deleteProfessor(Long professorId);

    void deleteStudyArea(Long studyAreaId);

    List<TCC> getTCCs(String tccStatus, String term);
}
