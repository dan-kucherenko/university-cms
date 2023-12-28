package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
