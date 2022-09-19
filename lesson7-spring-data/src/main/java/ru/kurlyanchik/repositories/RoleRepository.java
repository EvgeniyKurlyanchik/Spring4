package ru.kurlyanchik.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurlyanchik.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

}
