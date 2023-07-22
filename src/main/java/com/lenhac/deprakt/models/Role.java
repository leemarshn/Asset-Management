package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false, nullable = false)
    private Long roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany // Many-to-many relationship with Permission
    @JoinTable(
            name = "role_permission", // Name of the join table
            joinColumns = @JoinColumn(name = "role_id"), // FK column for Role
            inverseJoinColumns = @JoinColumn(name = "permission_id") // FK column for Permission
    )
    private List<Permission> permissions;
}
