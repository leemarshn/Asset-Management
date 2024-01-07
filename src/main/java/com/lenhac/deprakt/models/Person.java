package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

  // Mark as an entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person extends Base {


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private LocalDateTime dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    private String details;
}
