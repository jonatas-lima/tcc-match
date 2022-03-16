package com.psoft.match.tcc.service.email;

import com.psoft.match.tcc.model.StudyArea;
import com.psoft.match.tcc.model.tcc.TCC;
import com.psoft.match.tcc.model.user.Professor;
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
    public void notifyNewTCCToInterestedStudents(Student student, StudyArea studyArea, TCC tcc) {
        SimpleMailMessage mailMessage = this.buildMailMessage(student.getEmail(), String.format("Novo TCC cadastrado na área: %s", studyArea.getDescription().toUpperCase()), tcc.toEmailFormat());
        mailSender.send(mailMessage);
    }

    @Override
    public void notifyNewOrientationInterestToStudent(Student student, Professor interestedProfessor, TCC tcc) {
        String text = String.format("INTERESSE DE ORIENTAÇÃO NO TCC %s PELO PROFESSOR %s", tcc.getTitle(), interestedProfessor.getEmail());
        SimpleMailMessage mailMessage = this.buildMailMessage(student.getEmail(), text, tcc.toString());
        mailSender.send(mailMessage);
    }

    @Override
    public void notifyNewOrientationInterestToProfessor(Professor professor, Student interestedStudent, TCC tcc) {
        String text = String.format("INTERESSE DE ORIENTAÇÃO NO TCC %s PELO ALUNO %s", tcc.getTitle(), interestedStudent.getFullName());
        SimpleMailMessage mailMessage = this.buildMailMessage(professor.getEmail(), text, tcc.toEmailFormat());
        mailSender.send(mailMessage);
    }

    private SimpleMailMessage buildMailMessage(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(SENDER);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailMessage.setSentDate(new Date());
        return mailMessage;
    }
}
