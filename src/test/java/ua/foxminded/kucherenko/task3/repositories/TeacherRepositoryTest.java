package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.models.Teacher;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository repository;

    @Test
    @Sql({"/database/drop_tables.sql", "/database/create_tables.sql", "/sample_data/departments_samples.sql",
            "/sample_data/teachers_samples.sql"})
    void getTeacherByDepartment() {
        final int departmentId = 1;
        final Department department = new Department(departmentId, "Engineering", "Technical");
        final List<Teacher> expectedTeachers = List.of(
                new Teacher(1, "John", "Doe", "john.doe@example.com", "1234567890", department),
                new Teacher(3, "Bob", "Johnson", "bob.johnson@example.com", "1112233445", department)

        );

        final List<Teacher> actualTeachers = repository.getByDepartmentId(departmentId);

        Assertions.assertNotNull(actualTeachers);
        Assertions.assertEquals(expectedTeachers, actualTeachers);
    }
}