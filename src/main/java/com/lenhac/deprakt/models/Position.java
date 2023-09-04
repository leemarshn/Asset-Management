package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


//@Entity
//@Table(name = "positions")
//@Data
public class Position {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "position_id", updatable = false, nullable = false)
//    private Long positionId;
//
//    @Column(name = "title", nullable = false)
//    private String title;
//
//    @Column(name = "description")
//    private String description;
//
//    @ManyToOne // Many-to-one relationship with Department
//    @JoinColumn(name = "department_id", nullable = false)
//    private Department department;
//
//    @ManyToMany // Many-to-many relationship with Duty
//    @JoinTable(
//            name = "position_duty", // Name of the join table
//            joinColumns = @JoinColumn(name = "position_id"), // FK column for Position
//            inverseJoinColumns = @JoinColumn(name = "duty_id") // FK column for Duty
//    )
//    private List<Duty> duties;
//
//    @Column(name = "highest_salary", nullable = false)
//    private Double highestSalary;
//
//    @Column(name = "lowest_salary", nullable = false)
//    private Double lowestSalary;
}
