package com.hms.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {


    private JWTFilter jwtFilter;

    public SecurityConfig(JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    )throws Exception
    {
        http.csrf().disable().cors().disable();//h(cd)2
        //haap-> it is for keeping all the url open
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class); //this filter will be called before the authentication filter
        http.authorizeHttpRequests().anyRequest().permitAll();
         return http.build();//it will create  an  http object
    }
}
