package com.psoft.match.tcc.config;

import com.psoft.match.tcc.service.db.DBService;
import com.psoft.match.tcc.service.db.ProdDBService;
import com.psoft.match.tcc.service.email.EmailService;
import com.psoft.match.tcc.service.email.SmtpEmailService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class ProdConfig implements SystemConfig {

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {};
    }

    @Bean
    public boolean seedDB() {
        dbService().seed();
        return true;
    }

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
}
