package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity  // Mark as an entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person extends Base {

    @Id  // Add ID annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate ID
    private Long id;
    @OneToOne(mappedBy = "person")  // Unidirectional relationship
    private Employee employee;


    @Column(nullable = false)
    private String surname;  // Renamed from "surname" for consistency

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)  // Enforce unique email
    private String email;

    @Column  // No nullable constraint, making it optional
    private LocalDateTime dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    private String details;
}
