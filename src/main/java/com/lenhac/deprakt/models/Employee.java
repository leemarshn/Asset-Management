package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 7, max = 12)
    private String nationalId;

    private Date employmentStartDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToOne(mappedBy = "employee")
    private Credentials credentials;

    private String Position;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person personForm;


//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private Department department;


}
