package com.lenhac.deprakt.models;

/**
 * Created by Lee N on 28, Fri,Jul,2023.
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id", updatable = false, nullable = false, unique = true, length = 36)
    private Long id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Employee> employees = new ArrayList<>();


    public Organization(@NonNull String name) {
        this.name = name;
    }
}
