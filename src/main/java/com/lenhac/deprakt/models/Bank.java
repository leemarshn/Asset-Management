package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "banks")
@Data
public class Bank extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id", updatable = false, nullable = false)
    private Long bankId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL) // One-to-many relationship with BankBranch
    private List<BankBranch> branches;

    // Constructors, getters, setters, and other methods...

    // Constructor without arguments
    public Bank() {
    }

    // Constructor with name
    public Bank(String name) {
        this.name = name;
    }

    // Getters and setters for bankId, name, and branches...

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", name='" + name + '\'' +
                '}';
    }
}
