package com.lenhac.deprakt.services;

import com.lenhac.deprakt.models.Organization;
import com.lenhac.deprakt.models.Person;
import com.lenhac.deprakt.repositories.OrganisationRepository;
import com.lenhac.deprakt.repositories.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Service
@Transactional  // Ensure data consistency within transactions
public class PersonService {

    private final PersonRepository personRepository;
    private final OrganisationRepository organizationRepository;

    public PersonService(PersonRepository personRepository, OrganisationRepository organizationRepository) {
        this.personRepository = personRepository;
        this.organizationRepository = organizationRepository;
    }

    public Person savePerson(Person person, Long organizationId) {
        // Validation (adjust based on specific requirements)
        validatePersonData(person);

        // Set organization from Base
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new EntityNotFoundException("Organization not found"));
        person.setOrganization(organization);

        // Set createdBy and modifiedBy if applicable
//        if (person.getId() == null) {  // New person
//            // Handle createdBy assignment (e.g., set current user or default)
//            person.setCreatedBy(getCurrentUserOrSystemUser()); // Example placeholder
//        } else {
//            // Handle modifiedBy assignment (e.g., set current user or retain existing)
//            person.setModifiedBy(getCurrentUserOrSystemUser()); // Example placeholder
//        }

        return personRepository.save(person);
    }

    private void validatePersonData(Person person) {
        // Validate required fields
        if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }

        if (person.getLastName() == null || person.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }

        // Validate email format
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";
        if (!person.getEmail().matches(emailRegex)) {
            throw new IllegalArgumentException("Invalid email format");
        }

        // Validate name lengths
        if (person.getFirstName().length() > 50) {
            throw new IllegalArgumentException("First name is too long");
        }

        if (person.getLastName().length() > 50) {
            throw new IllegalArgumentException("Last name is too long");
        }
    }
}
