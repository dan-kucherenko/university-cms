package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Teacher;

import java.util.List;

@Repository

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> getTeachersByDepartmentDepartmentId(int departmentId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.user.id = :userId")
    void deleteTeacherByUserId(long userId);
}
