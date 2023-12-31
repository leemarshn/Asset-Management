package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee N on 07, Sun,Jan,2024.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
