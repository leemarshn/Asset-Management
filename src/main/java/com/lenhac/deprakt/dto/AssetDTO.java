package com.lenhac.deprakt.dto;
import lombok.Data;

@Data
public class AssetDTO {
    private Long id;
    private String name;
    private String user;
    private String value;
    private String depreciationDate;

    public AssetDTO(Long id, String name, String user, String value, String depreciationDate) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.value = value;
        this.depreciationDate = depreciationDate;
    }
}
