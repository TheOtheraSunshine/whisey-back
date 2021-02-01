package com.tvz.diplomski.wisey.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();

    Role findByIdRole(Long idRole);
}
