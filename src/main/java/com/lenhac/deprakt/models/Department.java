package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "departments")
@Data
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", updatable = false, nullable = false)
    private Long departmentId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne // Many-to-one relationship with Department (Self-relationship for parent department)
    @JoinColumn(name = "parent_department_id")
    private Department parentDepartment;

    @ManyToOne // Many-to-one relationship with Employee (Department Manager)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    @OneToMany(mappedBy = "department") // One-to-many relationship with Employee
    private List<Employee> employees;

    @OneToMany(mappedBy = "department") // One-to-many relationship with Position
    private List<Position> positions;

    // Constructors, getters, setters, and other methods...

    // Constructor without arguments
    public Department() {
    }

    // Constructor with name
    public Department(String name) {
        this.name = name;
    }

    // Getters and setters for departmentId, name, parentDepartment, manager, employees, and positions...

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                '}';
    }
}
