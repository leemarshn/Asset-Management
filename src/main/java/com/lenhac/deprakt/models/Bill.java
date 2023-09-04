package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

//@Entity
//@Table(name = "bills")
//@Data
public class Bill extends BaseEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "bill_id", updatable = false, nullable = false)
//    private Long id;
//
//    @ManyToOne // Many-to-one relationship with ServiceProvider (service provider associated with the bill)
//    @JoinColumn(name = "service_provider_id", nullable = false)
//    private ServiceProvider serviceProvider;
//
//    @Column(name = "bill_date", nullable = false)
//    private LocalDate billDate;
//
//    @Column(name = "amount", nullable = false)
//    private Double amount;
//
//    @Column(name = "description")
//    private String description;
//
//    @ManyToOne // Many-to-one relationship with Purchase (optional)
//    @JoinColumn(name = "purchase_id")
//    private Purchase purchase;
//
//    @ManyToOne // Many-to-one relationship with Maintenance (optional)
//    @JoinColumn(name = "maintenance_id")
//    private Maintenance maintenance;
//
//    // Constructors, getters, setters, and other methods...
//
//    // Constructor without arguments
//    public Bill() {
//    }
//
//    // Constructor with serviceProvider, billDate, amount, and description
//    public Bill(ServiceProvider serviceProvider, LocalDate billDate, Double amount, String description) {
//        this.serviceProvider = serviceProvider;
//        this.billDate = billDate;
//        this.amount = amount;
//        this.description = description;
//    }
//
//    // Getters and setters for id, serviceProvider, billDate, amount, description, purchase, and maintenance...
//
//    // Override toString() method for better representation
//    @Override
//    public String toString() {
//        return "Bill{" +
//                "id=" + id +
//                ", billDate=" + billDate +
//                ", amount=" + amount +
//                '}';
//    }
}
