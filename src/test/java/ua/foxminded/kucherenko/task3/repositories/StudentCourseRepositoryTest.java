package ua.foxminded.kucherenko.task3.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class StudentCourseRepositoryTest {
    @Autowired
    private StudentCourseRepository repository;

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/courses_samples.sql",
            "/sample_data/students_samples.sql", "/sample_data/student_courses_samples.sql"})
    void countStudentCourses() {
        final int studentId = 1;

        final int expectedCoursesNum = 3;

        final int actualCoursesNum = repository.countCourseByStudentId(studentId);
        Assertions.assertEquals(expectedCoursesNum, actualCoursesNum);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/courses_samples.sql",
            "/sample_data/students_samples.sql", "/sample_data/student_courses_samples.sql"})
    void checkWhetherStudentCourseExists() {
        final int studentId = 1;
        final int courseId = 3;

        final boolean expectedResult = true;
        final boolean actualResult = repository.exists(studentId, courseId);

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/courses_samples.sql",
            "/sample_data/students_samples.sql", "/sample_data/student_courses_samples.sql"})
    @Transactional
    void addStudentToCourse() {
        final int studentId = 1;
        final int courseId = 4;

        Assertions.assertFalse(repository.exists(studentId, courseId));
        repository.addStudentToCourse(studentId, courseId);
        Assertions.assertTrue(repository.exists(studentId, courseId));
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/courses_samples.sql",
            "/sample_data/students_samples.sql", "/sample_data/student_courses_samples.sql"})
    @Transactional
    void removeStudentToCourse() {
        final int studentId = 1;
        final int courseId = 3;

        Assertions.assertTrue(repository.exists(studentId, courseId));
        repository.removeStudentFromCourse(studentId, courseId);
        Assertions.assertFalse(repository.exists(studentId, courseId));
    }
}
