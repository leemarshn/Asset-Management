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



@Entity
@Table(name = "organizations")
@Data
@NoArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true, length = 36)

    private String id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(unique = true)
    private String name;

    public Organization(@NonNull String name) {
        this.name = name;
    }
}
