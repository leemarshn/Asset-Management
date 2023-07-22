package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_branches")
@Data
public class BankBranch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id", updatable = false, nullable = false)
    private Long branchId;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne // Many-to-one relationship with Bank
    @JoinColumn(name = "bank_id", nullable = false)
    private Bank bank;

    // Other fields related to BankBranch...

    // Constructors, getters, setters, and other methods...

    // Constructor without arguments
    public BankBranch() {
    }

    // Constructor with name
    public BankBranch(String name) {
        this.name = name;
    }

    // Getters and setters for branchId, name, and bank...

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "BankBranch{" +
                "branchId=" + branchId +
                ", name='" + name + '\'' +
                '}';
    }
}
