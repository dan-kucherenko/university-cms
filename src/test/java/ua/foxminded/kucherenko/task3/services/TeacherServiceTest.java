package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.Department;
import ua.foxminded.kucherenko.task3.models.Teacher;
import ua.foxminded.kucherenko.task3.repositories.TeacherRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class TeacherServiceTest {
    @Autowired
    private TeacherService teacherService;
    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    void getTeacherByDepartment() {
        final int departmentId = 1;
        final Department department = new Department(departmentId, "Engineering", "Technical");
        final List<Teacher> expectedTeachers = List.of(
                new Teacher(1, "John", "Doe", "john.doe@example.com", "1234567890", department),
                new Teacher(3, "Bob", "Johnson", "bob.johnson@example.com", "1112233445", department)
        );

        when(teacherRepository.getTeachersByDepartmentDepartmentId(departmentId)).thenReturn(expectedTeachers);

        final List<Teacher> actualTeachers = teacherService.getTeachersByDepartment(departmentId);

        verify(teacherRepository, times(1)).getTeachersByDepartmentDepartmentId(departmentId);

        Assertions.assertNotNull(actualTeachers);
        Assertions.assertEquals(expectedTeachers, actualTeachers);
    }
}
