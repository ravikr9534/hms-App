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
        //http.authorizeHttpRequests().requestMatchers("/api/v1/users/login","/api/v1/users/signup","/api/v1/users/signup-property-owner").permitAll()
              //  .requestMatchers("/api/v1/countries/addCountry").hasAnyRole("OWNER","ADMIN")
               // .anyRequest().authenticated();
         return http.build();//it will create  an  http object
    }
}
