package com.lenhac.deprakt.services;


import com.lenhac.deprakt.models.Credentials;
import com.lenhac.deprakt.repositories.CredentialsRepository;
import com.lenhac.deprakt.repositories.EmployeeRepository;
import com.lenhac.deprakt.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        private final CredentialsRepository credentialsRepository;


    public CustomUserDetailsService(CredentialsRepository credentialsRepository) {
        this.credentialsRepository = credentialsRepository;

    }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByUsername(username);
        if (credentials == null) {
            throw new UsernameNotFoundException("User not found");
        }

            Long employeeId = credentials.getEmployee().getId();
            Collection<? extends GrantedAuthority> authorities = credentials.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(username, credentials.getPassword(), authorities);
    }

}
