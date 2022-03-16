package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
import com.psoft.match.tcc.model.user.Student;
import org.springframework.stereotype.Component;

@Component
public class FakeEmailService implements EmailService {

    @Override
    public void notifyNewTCCToInterestedStudents(Student student, StudyArea studyArea, TCC tcc) {
        String email = String.format("NOVO TCC CADASTRADO NA ÁREA: %s: %s", studyArea.getDescription(), tcc.toEmailFormat());
        student.receiveEmail(email);
    }

    @Override
    public void notifyNewOrientationInterestToStudent(Student student, Professor interestedProfessor, TCC tcc) {
        String email = String.format("INTERESSE DE ORIENTAÇÃO NO TCC %s PELO PROFESSOR %s", tcc.getTitle(), interestedProfessor.getEmail());
        student.receiveEmail(email);
    }

    @Override
    public void notifyNewOrientationInterestToProfessor(Professor professor, Student interestedStudent, TCC tcc) {
        String email = String.format("INTERESSE DE ORIENTAÇÃO NO TCC: %s PELO ALUNO %s", tcc.getTitle(), interestedStudent.getFullName());
        professor.receiveEmail(email);
    }
}
