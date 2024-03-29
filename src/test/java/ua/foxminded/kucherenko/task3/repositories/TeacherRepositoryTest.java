package ua.foxminded.kucherenko.task3.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.models.Teacher;

import java.util.List;

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
        final Teacher teacher1 = new Teacher(3, "Dr.", "Anderson");
        final Teacher teacher2 = new Teacher(4, "Prof.", "Taylor");
        teacher1.setDepartment(department);
        teacher2.setDepartment(department);
        final List<Teacher> expectedTeachers = List.of(teacher1, teacher2);

        final List<Teacher> actualTeachers = repository.getTeachersByDepartmentDepartmentId(departmentId);

        Assertions.assertNotNull(actualTeachers);
        Assertions.assertEquals(expectedTeachers, actualTeachers);
    }
}