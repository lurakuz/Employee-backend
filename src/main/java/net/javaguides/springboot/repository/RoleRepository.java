package net.javaguides.springboot.repository;

import net.javaguides.springboot.models.entity.ERole;
import net.javaguides.springboot.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole value);


}
