package com.lenhac.deprakt.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {


    @Column(name = "status", nullable = false)
    private String status;

    public BaseEntity() {
        status = "ACTIVE";
    }
}
