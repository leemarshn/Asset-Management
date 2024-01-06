package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Department extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", updatable = false, nullable = false)
    private Long departmentId;

    @Column(name = "name", nullable = false)
    private String name;

}
