package com.psoft.match.tcc.config;

import com.psoft.match.tcc.service.db.DBService;
import com.psoft.match.tcc.service.db.ProdDBService;
import com.psoft.match.tcc.service.email.EmailService;
import com.psoft.match.tcc.service.email.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@Profile("prod")
public class ProdConfig implements SystemConfig {

    @Bean
    @Override
    public EmailService emailService() {
        return new SmtpEmailService();
    }

    @Bean
    @Override
    public DBService dbService() {
        return new ProdDBService();
    }

    @Bean
    @Override
    public MailSender mailSender() {
        return new JavaMailSenderImpl();
    }
}
