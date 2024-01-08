package com.lenhac.deprakt.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Base {

//    @ManyToOne
//    @JoinColumn(name = "created_by_id")
//    private Employee createdBy;
//
//    @ManyToOne
//    @JoinColumn(name = "modified_by_id")
//    private Employee modifiedBy;

//    @ManyToOne
//    @JoinColumn(name = "organization_id")
//    private Organization organization;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;;
}
