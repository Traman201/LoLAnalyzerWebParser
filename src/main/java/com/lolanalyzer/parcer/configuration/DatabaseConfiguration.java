package com.lolanalyzer.parcer.configuration;

import com.lolanalyzer.parcer.service.DataSaver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Configuration
@EnableScheduling
public class DatabaseConfiguration {

    @Bean
    public DataSaver dataSaver(){
        return new DataSaver();
    }
}
