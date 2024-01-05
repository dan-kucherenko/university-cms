package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Role;
import ua.foxminded.kucherenko.task3.models.Teacher;

import java.util.List;

@Repository

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> getTeachersByDepartmentDepartmentId(int departmentId);

    @Modifying
    @Transactional
    @Query("UPDATE Teacher t SET t.role = :role WHERE t.id = :id")
    void updateTeacherRoleById(int id, Role role);
}
