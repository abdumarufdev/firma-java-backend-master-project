package com.example.firmawebsayti.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class KimYozganiniQaytarish {
    @Bean
    public AuditorAware auditorAware(){
        return new KimYozganiniBilish();
    }

}
