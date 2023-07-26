package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "user_session_info")
@Getter
@Setter
public class UserSessionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id", nullable = false, unique = true)
    private String sessionId;
    @JoinColumn(name = "user_name", nullable = false)
    private String username;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "user_agent")
    private String userAgent;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "start_time", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime startTime;

    // Constructors, getters, setters, and other methods...
}

