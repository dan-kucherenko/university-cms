package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> getTeachersByDepartmentDepartmentId(int departmentId);
}
