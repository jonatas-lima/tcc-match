package com.psoft.match.tcc.config;

import com.psoft.match.tcc.service.DBService;
import com.psoft.match.tcc.service.impl.TestDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean seedDB() {
        dbService.seed();
        return true;
    }

    @Bean
    public DBService dbService() {
        return new TestDBService();
    }
}
