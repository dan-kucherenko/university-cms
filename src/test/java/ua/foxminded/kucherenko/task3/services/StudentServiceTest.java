package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    void getAllStudentIds() {
        final int studentsIdListSize = 7;
        final List<Integer> expectedStudentIds = List.of(1, 2, 3, 4, 5, 6, 7);

        when(studentRepository.getAllStudentIds()).thenReturn(expectedStudentIds);

        final List<Integer> allStudentIds = studentService.getAllStudentIds();

        verify(studentRepository, times(1)).getAllStudentIds();

        Assertions.assertEquals(studentsIdListSize, allStudentIds.size());
        Assertions.assertEquals(expectedStudentIds, allStudentIds);
    }

    @Test
    void getByCourse() {
        final String courseName = "English";
        final List<Student> expectedStudents = List.of(new Student());

        when(studentRepository.getByCourse(courseName)).thenReturn(expectedStudents);

        final List<Student> resultStudents = studentService.getStudentsByCourse(courseName);

        verify(studentRepository, times(1)).getByCourse(courseName);

        Assertions.assertEquals(expectedStudents.size(), resultStudents.size());
        Assertions.assertEquals(expectedStudents, resultStudents);
    }

    @Test
    void getIdByName() {
        final long expectedStudentId = 2;
        final String firstName = "Jane";
        final String lastName = "Smith";

        when(studentRepository.getIdByName(firstName, lastName)).thenReturn(Collections.singletonList(expectedStudentId));

        final List<Long> studentIds = studentService.getStudentIdsByName(firstName, lastName);

        verify(studentRepository, times(1)).getIdByName(firstName, lastName);

        Assertions.assertEquals(expectedStudentId, studentIds.get(0));
    }

    @Test
    void getStudentById_NegativeStudentId_ShouldThrowException() {
        final int studentId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.getStudentById(studentId));
    }

    @Test
    void updateStudent_NegativeYearOfStudy_ShouldThrowException() {
        final Integer yearOfStudy = -1;
        final Student student = new Student();
        student.setYearOfStudy(yearOfStudy);
        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.saveStudent(student));
    }

    @Test
    void deleteStudent_NegativeStudentId_ShouldThrowException() {
        final int studentId = -1;
        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.deleteStudent(studentId));
    }
}
