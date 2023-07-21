package com.lenhac.deprakt.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Asset Name can't be blank")
    private String name;
    @NotBlank(message = "Type is Require")
    private String type;
    @Column(name = "asset_user")
    private String user;
    private String supplier;
    private String model;
    private String make;
    @Min(value = 1,message = "an Asset should have a shelf life of more than 1 year")
    @Max(value = 99, message = "You will not be alive in 100years time")
    private int shelfLife;
    @Min(value = 1, message = "The value of the asset must be greater than 1")
    private double value;
    private Date purchaseDate;
    private String notes;


}
