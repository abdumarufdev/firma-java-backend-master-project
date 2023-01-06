package com.example.firmawebsayti.Security;

import com.example.firmawebsayti.Token.Filtr;
import com.example.firmawebsayti.Servise.IshchiServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Settings extends WebSecurityConfigurerAdapter {
    @Autowired
    IshchiServise ishchiServise;

    @Autowired
    Filtr filtr;

    @Bean
    PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(6);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/BulimJoyla").permitAll()
//                .antMatchers("/BulimJoyla/kiritish").permitAll()
//                .antMatchers("/kiritish/kiritamiz").permitAll()
//                .antMatchers("/VazifaJoylash/JoylaVaz").permitAll()
//                .antMatchers("/Joylaymiz/uqishHam").permitAll()
//                .antMatchers("/Joylaymiz/deleteIshchi/{{id}}").permitAll()
//                .antMatchers("/kiritish/deleteLavozim/{{id}}").permitAll()
//                .antMatchers("/Joylaymiz").permitAll()
                .antMatchers("/Joylaymiz/Registration","/Joylaymiz/login","/Joylaymiz/emailtasdiqlash").permitAll()
//                .antMatchers("/Joylaymiz/login").permitAll()
//                .antMatchers("/Joylaymiz/emailtasdiqlash").permitAll()
//                .antMatchers("/kiritish/uiqishHam").permitAll()
//                  .antMatchers("/Joylaymiz/kiritish").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        http
                .addFilterBefore(filtr, UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("farruxzaitov7176@gmail.com");
        mailSender.setPassword("akkypiiciwlwryew");
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(ishchiServise)
                .passwordEncoder(passwordEncoder());
    }
}
