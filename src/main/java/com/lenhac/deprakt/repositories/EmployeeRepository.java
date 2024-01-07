package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee N on 06, Sat,Jan,2024.
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getOrganizationIdById(Long id);

}
