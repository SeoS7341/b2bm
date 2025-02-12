package com.example.spring_boot_app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfig {

    @Value("${prefix}")
    private String prefix;

    @Bean
    public String prefix() {
        return this.prefix;
    }
}