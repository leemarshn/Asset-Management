package com.lenhac.deprakt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/dashboard").permitAll()
                        .requestMatchers("/organization/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/services/**", "/department/**", "/profile/**").hasAnyRole()
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/include").permitAll()
                        .requestMatchers("/about", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Updated to match your login form URL
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Custom logout URL
                        .logoutSuccessUrl("/login") // Redirect to the login page after successful logout
                        .permitAll()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/access-denied") // Custom access-denied page
                );
        return http.build(); // Build and return the SecurityFilterChain
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
