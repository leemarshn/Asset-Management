package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", updatable = false, nullable = false, unique = true, length = 36)
    private String personId = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;


}
