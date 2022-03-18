package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;

public interface EmailService {

    void notifyNewTCCToInterestedStudents(Student student, StudyArea studyArea, TCC tcc);

    void notifyNewOrientationInterestToStudent(Student student, Professor interestedProfessor, TCC tcc);

    void notifyNewOrientationInterestToProfessor(Professor professor, Student interestedStudent, TCC tcc);

    void notifyApprovedOrientationToAdmin(TCC tcc);
}
