package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

//    @Column
    private Date dateOfBirth;

    private String phoneNumber;

    private String details;

    @OneToOne(mappedBy = "personForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private Employee employee;


}
