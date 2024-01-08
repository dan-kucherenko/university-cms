package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Administrator;
import ua.foxminded.kucherenko.task3.models.Role;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
