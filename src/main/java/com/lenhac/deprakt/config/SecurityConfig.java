package com.lenhac.deprakt.config;

import com.lenhac.deprakt.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

//    @Bean
//    public WebSecurityConfigurer webSecurityConfigurer() {
//        return new WebSecurityConfigurer() {
//            @Override
//            public void configure(WebSecurity web) {
//                web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/fragments/**");
//            }
//        };
//    }
/**
 *        @Bean
 *    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 * 		http
 * 			.securityMatcher(antMatcher("/api/**"))
 * 			.authorizeHttpRequests(authorize -> authorize
 * 				.requestMatchers(antMatcher("/user/**")).hasRole("USER")
 * 				.requestMatchers(regexMatcher("/admin/.*")).hasRole("ADMIN")
 * 				.requestMatchers(new MyCustomRequestMatcher()).hasRole("SUPERVISOR")
 * 				.anyRequest().authenticated()
 * 			)
 * 			.formLogin(withDefaults());
 * 		return http.build();
 *    }
 *
 *
 * */
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
            .authorizeRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers(antMatcher("/login")).permitAll()
                    .requestMatchers(antMatcher("/dashboard")).hasAnyRole()
                    .requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN")
                    .requestMatchers("/services/**", "/department/**", "/profile/**").hasAnyRole()
                    .requestMatchers("/css/**", "/js/**", "/images/**", "/include").permitAll()
                    .requestMatchers("/about", "/register").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/loginForm") // Updated to match your login form URL
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
