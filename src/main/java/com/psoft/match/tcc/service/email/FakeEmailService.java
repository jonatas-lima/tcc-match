package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Student;
import org.springframework.stereotype.Component;

@Component
public class FakeEmailService implements EmailService {

    @Override
    public void notifyNewTCCStudyArea(Student student, StudyArea studyArea, TCC tcc) {
        System.out.println(String.format("%s: %s", student.getFullName(), tcc.toEmailFormat()));
    }

    @Override
    public void notifyInterestedTCCProposal(Student student, TCCProposal tccProposal) {

    }
}
