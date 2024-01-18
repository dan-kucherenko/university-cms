package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("""
            SELECT NEW Student(s.id, s.firstName, s.lastName)
            FROM Student s
            INNER JOIN s.courses c
            WHERE c.courseName = :courseName
            """)
    List<Student> getByCourse(String courseName);

    @Query("SELECT s.id FROM Student s WHERE s.firstName = :firstName AND s.lastName = :lastName")
    List<Long> getIdByName(String firstName, String lastName);

    @Query("SELECT s.id FROM Student s")
    List<Integer> getAllStudentIds();

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = """
            INSERT INTO students (id, username, first_name, last_name, email, phone, role_id, password)
            VALUES (:id, :username, :firstName, :lastName, :email, :phone, :role, :password)
            """)
    void saveStudentFromUser(Long id, String username, String firstName, String lastName,
                                   String email, String phone, int role, String password);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.id = :id")
    void deleteStudentByUserId(long id);
}
