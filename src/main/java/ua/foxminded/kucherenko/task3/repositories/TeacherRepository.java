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
    @Query(nativeQuery = true, value = """
            INSERT INTO teachers (id, username, first_name, last_name, email, phone, role_id, password)
            VALUES (:id, :username, :firstName, :lastName, :email, :phone, :role, :password)
            """)
    void saveTeacherFromUser(Long id, String username, String firstName, String lastName,
                             String email, String phone, int role, String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM Teacher t WHERE t.id = :id")
    void deleteTeacherByUserId(long id);
}
