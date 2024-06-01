package com.example.auth.repository;

import com.example.auth.entity.Roles;
import com.example.auth.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles, RoleName> {
}
