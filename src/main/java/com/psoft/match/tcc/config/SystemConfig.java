package com.psoft.match.tcc.config;

import com.psoft.match.tcc.service.db.DBService;
import com.psoft.match.tcc.service.email.EmailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public interface SystemConfig {

    EmailService emailService();

    DBService dbService();

    MailSender mailSender();

}
