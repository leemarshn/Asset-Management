package com.lenhac.deprakt.models;

/**
 * Created by Lee N on 28, Fri,Jul,2023.
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Organization{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id", updatable = false, nullable = false, unique = true, length = 36)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;

    @Column(name = "unique_name", unique = true)  // Enforce unique constraint
    private String uniqueName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "pin_number")
    private Integer pinNumber;

    @Column(name = "logo_path")
    private String logoPath;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "created_by_id")
    private Person createdBy;

    @OneToOne
    @JoinColumn(name = "modified_by_id")
    private Person modifiedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime lastModifiedAt;


}
