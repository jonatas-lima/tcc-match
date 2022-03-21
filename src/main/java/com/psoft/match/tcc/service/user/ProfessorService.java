package com.psoft.match.tcc.service.user;

import com.psoft.match.tcc.dto.OrientationIssueDTO;
import com.psoft.match.tcc.dto.ProfessorDTO;
import com.psoft.match.tcc.dto.TCCDTO;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

import java.util.Collection;
import java.util.List;

public interface ProfessorService  {

    List<Professor> getAvailableProfessors();

    List<Professor> getAvailableProfessorsWithSharedInterests(Student student);

    Professor findProfessorById(Long id);

    Professor createProfessor(ProfessorDTO professorDTO);

    Professor updateProfessor(Long id, ProfessorDTO professorDTO);

    void approveOrientationInterest(Long tccId, Long studentId);

    void refuseOrientationInterest(Long tccId, Long studentId);

    void deleteProfessor(Long id);

    void declareOrientationInterest(Long tccProposalId);

    void addInterestedStudyArea(Long studyAreaId);

    void updateQuota(Integer quota);

    void registerOrientationIssue(Long tccId, OrientationIssueDTO orientationIssueDTO);

    Collection<TCC> getRegisteredTCCs();

    Collection<TCC> getStudentsTCCs();

    TCC createTCC(TCCDTO tccdto);
    
    Collection<TCC> getOngoingGuidelines();
}
