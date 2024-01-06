package com.lenhac.deprakt.repositories;

import com.lenhac.deprakt.models.Credentials;
import com.lenhac.deprakt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findByUsername(String username);
//
//    @Query("SELECT r.name FROM Credentials c JOIN Role r ON c.role.roleId = r.roleId WHERE c.username = :username")
//    String findRoleNameByUsername(@Param("username") String username);


}
