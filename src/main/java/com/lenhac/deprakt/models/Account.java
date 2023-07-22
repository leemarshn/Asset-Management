package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank_accounts")
@Data
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false, nullable = false)
    private Long accountId;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne // Many-to-one relationship with BankBranch
    @JoinColumn(name = "branch_id", nullable = false)
    private BankBranch bankBranch;

    @Column(name = "balance")
    private Double balance;

    @ManyToOne // One-to-one relationship with Person
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @ManyToOne // One-to-one relationship with Business
    @JoinColumn(name = "business_id", nullable = false, unique = true)
    private ServiceProvider business;



    public Account() {
    }

    // Constructor with accountNumber and balance
    public Account(String accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    // Getters and setters for accountId, accountNumber, bankBranch, and balance...

    // Override toString() method for better representation
    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
