package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Student;

public interface EmailService {

    void notifyNewTCCStudyArea(Student student, StudyArea studyArea, TCC tcc);

    void notifyInterestedTCCProposal(Student student, TCCProposal tccProposal);
}
