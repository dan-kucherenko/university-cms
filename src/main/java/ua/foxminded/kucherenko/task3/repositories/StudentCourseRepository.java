package ua.foxminded.kucherenko.task3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.foxminded.kucherenko.task3.models.Student;

@Repository
public interface StudentCourseRepository extends JpaRepository<Student, Integer> {
    @Query("SELECT COUNT(c) FROM Student s JOIN s.courses c WHERE s.id = :studentId")
    int countCourseByStudentId(int studentId);

    @Query(value = "SELECT COUNT(*) > 0 FROM student_courses WHERE student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    boolean exists(int studentId, int courseId);

    @Modifying
    @Query(value = "INSERT INTO student_courses (student_id, course_id) VALUES (:studentId, :courseId)", nativeQuery = true)
    void addStudentToCourse(Integer studentId, Integer courseId);

    @Modifying
    @Query(value = "DELETE FROM student_courses WHERE student_id = :studentId AND course_id = :courseId", nativeQuery = true)
    void removeStudentFromCourse(Integer studentId, Integer courseId);
}
