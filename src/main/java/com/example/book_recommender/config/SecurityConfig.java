package com.example.book_recommender.config;

import com.example.book_recommender.SecurityHandler.UserAuthenticationFailureHandler;
import com.example.book_recommender.SecurityHandler.UserAuthenticationSuccessHandler;
import com.example.book_recommender.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserAuthenticationFailureHandler failureHandler;
    @Autowired
    UserAuthenticationSuccessHandler successHandler;
    @Autowired
    UserDetailsServiceImpl service;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .cors()
                .and()
                .csrf().disable();


    }


}
