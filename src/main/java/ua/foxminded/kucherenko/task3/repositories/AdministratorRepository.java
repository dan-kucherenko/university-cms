package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
}
