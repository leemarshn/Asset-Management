package com.lenhac.deprakt.services;

import com.lenhac.deprakt.exceptions.InvalidException;
import com.lenhac.deprakt.models.Credentials;
import com.lenhac.deprakt.repositories.CredentialsRepository;
import com.lenhac.deprakt.repositories.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */
@Service
@Transactional
public class AuthenticationService {

    private final CredentialsRepository credentialsRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    private static final int MIN_USERNAME_LENGTH = 8;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final String ALPHANUMERIC_REGEX = "^[a-zA-Z0-9]+$";



    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public AuthenticationService(CredentialsRepository credentialsRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveCredentials(String username, String password, Long loggedInEmployeeId) {
        validateCredentials(username, password);

        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(passwordEncoder.encode(password)); // Securely store password

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long employeeId = (Long) authentication.getDetails();



        credentials.setEmployee(employeeRepository.getOrganizationIdById(employeeId));
        credentialsRepository.save(credentials);
    }


    public void validateCredentials(String username, String password) {
        if (isEmpty(username)) {
            throw new InvalidException("Username is required");
        }
        if (username.length() < MIN_USERNAME_LENGTH) {
            throw new InvalidException("Username must be at least " + MIN_USERNAME_LENGTH + " characters long");
        }
        if (!username.matches(ALPHANUMERIC_REGEX)) {
            throw new InvalidException("Username must be alphanumeric");
        }

        if (isEmpty(password)) {
            throw new InvalidException("Password is required");
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidException("Password must be at least " + MIN_PASSWORD_LENGTH + " characters long");
        }
        if (!password.matches(ALPHANUMERIC_REGEX)) {
            throw new InvalidException("Password must be alphanumeric");
        }
    }




}
