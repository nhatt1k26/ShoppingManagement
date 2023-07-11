package com.nhat.ShoppingManagement.security.repository;

import com.nhat.ShoppingManagement.security.models.ERole;
import com.nhat.ShoppingManagement.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
