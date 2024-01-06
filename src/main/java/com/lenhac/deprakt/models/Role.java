package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    @ManyToMany(mappedBy = "roles")
    private Set<Credentials> credentials = new HashSet<>();

}
