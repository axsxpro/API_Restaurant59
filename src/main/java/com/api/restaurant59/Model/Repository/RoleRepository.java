package com.api.restaurant59.Model.Repository;

import com.api.restaurant59.Model.Entity.Role;
import com.api.restaurant59.Model.Entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleType name);
}
