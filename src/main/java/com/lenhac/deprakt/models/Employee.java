package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "employees")
@Getter
@Setter
@PrimaryKeyJoinColumn(name="person_id")
public class Employee  extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "national_id", nullable = false, unique = true)
    private String nationalId;

    @Column(nullable = false)
    private LocalDate employmentStartDate;

    @Column(nullable = false)
    private Status status;

    @OneToOne(mappedBy = "employee")
    private Credentials credentials;

    private String Position;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

}
