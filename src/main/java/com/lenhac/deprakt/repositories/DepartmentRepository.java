package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lee N on 07, Sun,Jan,2024.
 */

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
