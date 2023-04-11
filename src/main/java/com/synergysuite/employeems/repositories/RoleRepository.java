package com.synergysuite.employeems.repositories;

import com.synergysuite.employeems.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String role_user);
}
