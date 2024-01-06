package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.Organization;
import com.lenhac.deprakt.repositories.OrganisationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */
@Service
public class OrganizationService {

    private final OrganisationRepository organizationRepository;

    public OrganizationService(OrganisationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public void saveOrganization(Organization organization) {
        organizationRepository.save(organization);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Optional<Organization> getOrganizationById(Long id) {
        return organizationRepository.findById(id);
    }

    public Organization updateOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
