package com.lenhac.deprakt.security;

import com.lenhac.deprakt.models.Credentials;
import com.lenhac.deprakt.models.Role;
import com.lenhac.deprakt.repositories.CredentialsRepository;
import com.lenhac.deprakt.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    private final CredentialsRepository credentialsRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder; // Inject the password encoder

    @Autowired
    public CustomUserDetailsService(CredentialsRepository credentialsRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.credentialsRepository = credentialsRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credentials credentials = credentialsRepository.findByUsername(username);
        if (credentials == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Role role = rolesRepository.findByRoleName(credentials.getRole().getName());
        if (role == null) {
            throw new UsernameNotFoundException("Role not found.");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + credentialsRepository.findRoleNameByUsername(username)));

        // Adding the user's permissions associated with the role
        List<String> permissionNames = rolesRepository.findPermissionNamesByRoleId(role.getRoleId());
        for (String permissionName : permissionNames) {
            authorities.add(new SimpleGrantedAuthority(permissionName));
        }

        // Encode the password before creating the UserDetails object
        String encodedPassword = passwordEncoder.encode(credentials.getPassword());

        return new User(credentials.getUsername(), encodedPassword, authorities);
    }
}
