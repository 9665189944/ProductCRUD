package com.spring.RestAPI.Product.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SequrityConfigure {

    //Authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails akshay= User.builder()
                .username("akshay")
                .password("{noop}123")
                .roles("ADMIN","VENDOR","CUSTOMER")
                .build();
        UserDetails ajit= User.builder()
                .username("ajit")
                .password("{noop}456")
                .roles("VENDOR","CUSTOMER")
                .build();
        UserDetails amit= User.builder()
                .username("amit")
                .password("{noop}789")
                .roles("CUSTOMER")
                .build();
        return new  InMemoryUserDetailsManager  (akshay,ajit,amit);

    }

    //Authorization
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.authorizeHttpRequests(config ->
                config
                        .requestMatchers("/").hasRole("CUSTOMER")
                        .requestMatchers("/addproduct").hasRole("VENDOR")
                        .requestMatchers("/saveproduct").hasRole("VENDOR")
                        .requestMatchers("/deleteproduct/{id}").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(Config ->

                        Config.accessDeniedPage("/access-denied"));

        return http.build();


    }

}
