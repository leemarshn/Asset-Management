package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String resource;
    private String action;
    private String status;
    private LocalDateTime created;
    private LocalDateTime modified;

    @Builder
    public PermissionEntity(String name, String description, String resource, String action, String status, LocalDateTime created, LocalDateTime modified) {
        this.name = name;
        this.description = description;
        this.resource = resource;
        this.action = action;
        this.status = status;
        this.created = created;
        this.modified = modified;
    }
}

