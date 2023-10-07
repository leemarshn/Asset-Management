package com.lenhac.deprakt.config;

//import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


//@Configuration
//@EnableWebSecurity
public class _SecurityConfig {



//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
//            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
//                    .requestMatchers("/login").permitAll()
//                    .requestMatchers("/dashboard").hasAnyRole()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/services/**", "/department/**", "/profile/**").hasAnyRole()
//                    .requestMatchers("/css/**", "/js/**", "/images/**", "/include").permitAll()
//                    .requestMatchers("/about", "/register").permitAll()
//                    .anyRequest().authenticated()
//            )
//            .formLogin(formLogin -> formLogin
//                    .loginPage("/loginForm") // Updated to match your login form URL
//                    .permitAll()
//            )
//            .logout(logout -> logout
//                    .logoutUrl("/logout") // Custom logout URL
//                    .logoutSuccessUrl("/login") // Redirect to the login page after successful logout
//                    .permitAll()
//            )
//            .exceptionHandling(exceptionHandling -> exceptionHandling
//                    .accessDeniedPage("/access-denied") // Custom access-denied page
//            );
//    return http.build(); // Build and return the SecurityFilterChain
//}
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
