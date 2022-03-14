package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.tcc.TCCProposal;
import com.psoft.match.tcc.model.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SmtpEmailService implements EmailService {

    @Value("${email.sender}")
    private String SENDER;

    @Autowired
    private MailSender mailSender;

    @Override
    public void notifyNewTCCStudyArea(Student student, StudyArea studyArea, TCC tcc) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(student.getEmail());
        mailMessage.setFrom(SENDER);
        mailMessage.setSubject(String.format("Novo TCC cadastrado na área: %s", studyArea.getDescription().toUpperCase()));
        mailMessage.setSentDate(new Date());
        mailMessage.setText(tcc.toEmailFormat());

        mailSender.send(mailMessage);
    }

    @Override
    public void notifyInterestedTCCProposal(Student student, TCCProposal tccProposal) {

    }
}
