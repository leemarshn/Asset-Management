package com.lenhac.deprakt.models;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "assets")
//@Data
public class Asset extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "asset_id", updatable = false, nullable = false)
//    private Long id;
//
//    @Column(name = "name", nullable = false)
//    private String name;
//
//    @Column(name = "description")
//    private String description;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "asset_type", nullable = false)
//    private AssetType assetType;
//
//    @Column(name = "category")
//    private String category;
//
//    @Column(name = "serial_number", unique = true)
//    private String serialNumber;
//
//    @Column(name = "acquisition_date")
//    private LocalDate acquisitionDate;
//
//    @Column(name = "purchase_cost")
//    private double purchaseCost;
//
//
//    @Column(name = "life_expectancy")
//    private Integer lifeExpectancy;
//
//    @Column(name = "current_value")
//    private double currentValue;
//
//    @Column(name = "recurring_cost")
//    private double recurringCost;
//
//    @Column(name = "status")
//    private String status;
//
//    @ManyToOne // Many-to-one relationship with Department (asset belongs to a department)
//    @JoinColumn(name = "department_id")
//    private Department department;
//
//    @ManyToOne // Many-to-one relationship with ServiceProvider (asset's service provider)
//    @JoinColumn(name = "service_provider_id")
//    private ServiceProvider serviceProvider;
//
//    @ManyToOne // Self-referencing relationship with parent component (asset)
//    @JoinColumn(name = "parent_component_id")
//    private Asset parentComponent;
//
//    @OneToMany(mappedBy = "parentComponent", cascade = CascadeType.ALL, orphanRemoval = true) // One-to-many relationship with child components (sub-components)
//    private List<Asset> subComponents = new ArrayList<>();
//

}

