package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.kucherenko.task3.models.Student;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
class StudentRepositoryTest {
    @Autowired
    private StudentRepository repository;

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/students_samples.sql"})
    void getAllStudentIds() {
        final int studentsIdListSize = 7;
        final Integer[] expectedStudentIds = new Integer[]{1, 2, 3, 4, 5, 6, 7};
        List<Integer> allStudentIds = repository.getAllStudentIds();
        Assertions.assertEquals(studentsIdListSize, allStudentIds.size());
        Assertions.assertEquals(Arrays.stream(expectedStudentIds).toList(), allStudentIds);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/students_samples.sql",
            "/sample_data/courses_samples.sql", "/sample_data/student_courses_samples.sql"})
    void getByCourse() {
        final String courseName = "English";
        final List<Student> resultStudents = repository.getByCourse(courseName);
        final List<Student> expectedStudents = List.of(
                new Student(5, "1Charlie1", "Brown")
        );
        Assertions.assertEquals(expectedStudents.size(), resultStudents.size());
        Assertions.assertEquals(expectedStudents, resultStudents);
    }

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/groups_samples.sql", "/sample_data/students_samples.sql"})
    void getIdByName() {
        final int expectedStudentId = 2;
        final String firstName = "Jane";
        final String lastName = "Smith";

        final List<Integer> student = repository.getIdByName(firstName, lastName);
        Assertions.assertEquals(expectedStudentId, student.get(0));
    }
}
