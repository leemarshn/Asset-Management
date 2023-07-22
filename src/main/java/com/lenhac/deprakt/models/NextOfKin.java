package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "next_of_kin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NextOfKin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String relationship;
    @OneToOne
    private Person person;
    @ManyToMany(mappedBy = "nextOfKins") // Many-to-many relationship with Employee
    private List<Employee> employees;
}
