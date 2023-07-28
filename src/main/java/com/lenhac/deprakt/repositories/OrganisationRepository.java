package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee N on 28, Fri,Jul,2023.
 */

@Repository
public interface OrganisationRepository extends JpaRepository<Organization, String> {
}
