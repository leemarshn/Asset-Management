package com.lenhac.deprakt.dto;
import com.lenhac.deprakt.services.AssetService;

import lombok.Data;

import java.util.Date;

@Data
public class AssetDTO {
    private Long id;
    private String name;
    private String user;
    private double value;
    private String depreciationDate;

    // Constructor
    public AssetDTO(Long id, String name, String user, double value, String depreciationDate) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.value = value;
        this.depreciationDate = depreciationDate;
    }
}
