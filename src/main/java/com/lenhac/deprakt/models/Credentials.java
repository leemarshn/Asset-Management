package com.lenhac.deprakt.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "credentials")
@Getter
@Setter
public class Credentials implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @JsonIgnore // Prevent password exposure
    private String password;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER) // Eager loading for efficient role retrieval
    @JoinTable(
            name = "credentials_roles",
            joinColumns = @JoinColumn(name = "credentials_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != Status.EXPIRED;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != Status.LOCKED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == Status.ACTIVE;
    }
}

