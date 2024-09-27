package org.rakibhasan.blog.repositories;

import org.rakibhasan.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
