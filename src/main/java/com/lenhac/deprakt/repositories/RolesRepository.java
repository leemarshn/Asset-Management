package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Permission;
import com.lenhac.deprakt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    // Custom method to find a role by its name
//    Role findByRoleName(String roleName);

    // Custom method to find permissions by their names associated with a role
//    @Query("SELECT p.name FROM Role r JOIN r.permissions p WHERE r.roleId = :roleId")
//    List<String> findPermissionNamesByRoleId(@Param("roleId") Long roleId);
}
