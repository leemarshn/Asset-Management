package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", updatable = false, nullable = false)
    private Long employeeId;

    @Column(name = "national_id", nullable = false, unique = true)
    private String nationalId;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @ManyToOne // Many-to-one relationship with Role
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToMany
    @JoinTable(
            name = "employee_nextofkin", // Name of the join table
            joinColumns = @JoinColumn(name = "employee_id"), // FK column for Employee
            inverseJoinColumns = @JoinColumn(name = "nextofkin_id") // FK column for NextOfKin
    )
    private List<NextOfKin> nextOfKins;

    @ManyToOne // Many-to-one relationship with Position
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    // Other fields, constructors, and methods...
}
