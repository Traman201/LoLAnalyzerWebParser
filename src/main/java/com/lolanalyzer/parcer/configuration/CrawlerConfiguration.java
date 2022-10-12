package com.lolanalyzer.parcer.configuration;

import com.lolanalyzer.parcer.datagather.crawler.Crawler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.persistence.Basic;

@Configuration
@EnableAsync
public class CrawlerConfiguration {

    @Bean
    public Crawler getCrawler(){
        return new Crawler();
    }
}
