package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleById(int id);
}
