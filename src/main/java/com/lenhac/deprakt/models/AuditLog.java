package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data // Lombok's annotation to generate getters, setters, toString, and equals methods
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String action; // INSERT, UPDATE, DELETE

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Long userId; // The ID of the user who made the change

    @Column(nullable = false)
    private String tableName; // The name of the modified table

    @Column(nullable = false)
    private String recordId; // The ID of the modified record in the table

    @Lob
    @Column
    private String oldValues; // JSON representation of the old values of the changed fields

    @Lob
    @Column
    private String newValues; // JSON representation of the new values of the changed fields

    @ManyToOne
    @JoinColumn(name = "session_info_id") // The foreign key column in the audit log table
    private UserSessionInfo sessionInfo;

    // Constructors (including a no-args constructor), getters, setters, and other methods...
}
