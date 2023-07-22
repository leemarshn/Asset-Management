package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@Data
public class Maintenance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id", updatable = false, nullable = false)
    private Long id;

    @ManyToOne // Many-to-one relationship with Asset (maintenance related to an asset)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @ManyToOne // Many-to-one relationship with ServiceProvider (service provider responsible for maintenance)
    @JoinColumn(name = "service_provider_id")
    private ServiceProvider serviceProvider;

    @Column(name = "maintenance_date")
    private LocalDate maintenanceDate;

    @Column(name = "maintenance_type")
    private String maintenanceType;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Double cost;


}

