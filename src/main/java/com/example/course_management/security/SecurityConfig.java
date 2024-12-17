package com.example.course_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/students/user/**").hasRole("USER")
                        .requestMatchers("/specialities/user/**").hasRole("USER")
                        .requestMatchers("/course/user/**").hasRole("USER")
                        .requestMatchers("/students/admin/**").hasRole("ADMIN")
                        .requestMatchers("/specialities/admin/**").hasRole("ADMIN")
                        .requestMatchers("/course/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login.permitAll())
                .exceptionHandling(exception -> exception.accessDeniedPage("/notAuthorized")); // Page d'accès refusé

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user").password(passwordEncoder().encode("123")).roles("USER").build(),
                User.withUsername("admin").password(passwordEncoder().encode("123")).roles("ADMIN", "USER").build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
