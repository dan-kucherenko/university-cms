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
        final Teacher teacher1 = new Teacher();
        final Teacher teacher2 = new Teacher();
        teacher1.setDepartment(department);
        teacher2.setDepartment(department);
        final List<Teacher> expectedTeachers = List.of(teacher1, teacher2);

        when(teacherRepository.getTeachersByDepartmentDepartmentId(departmentId)).thenReturn(expectedTeachers);

        final List<Teacher> actualTeachers = teacherService.getTeachersByDepartment(departmentId);

        verify(teacherRepository, times(1)).getTeachersByDepartmentDepartmentId(departmentId);

        Assertions.assertNotNull(actualTeachers);
        Assertions.assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    void getTeacherById_TeacherId_ShouldThrowException() {
        final int teacherId = -4;
        Assertions.assertThrows(IllegalArgumentException.class, () -> teacherService.getTeacherById(teacherId));
    }

    @Test
    void getTeachersByDepartment_NegativeDepartmentId_ShouldThrowException() {
        final int departmentId = -2;
        Assertions.assertThrows(IllegalArgumentException.class, () -> teacherService.getTeachersByDepartment(departmentId));
    }

    @Test
    void updateTeacher_NegativeTeacherId_ShouldThrowException() {
        final int teacherId = -1;
        final Teacher teacher = new Teacher();
        Assertions.assertThrows(IllegalArgumentException.class, () -> teacherService.updateTeacher(teacherId, teacher));
    }

    @Test
    void deleteTeacher_NegativeTeacherId_ShouldThrowException() {
        final int teacherId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> teacherService.deleteTeacher(teacherId));
    }
}
