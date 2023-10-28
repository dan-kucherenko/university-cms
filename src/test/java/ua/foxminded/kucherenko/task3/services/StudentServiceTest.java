package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;

    @Test
    void getAllStudentIds() {
        final int studentsIdListSize = 7;
        final Integer[] expectedStudentIds = new Integer[]{1, 2, 3, 4, 5, 6, 7};

        when(studentRepository.getAllStudentIds()).thenReturn(Arrays.asList(expectedStudentIds));

        List<Integer> allStudentIds = studentService.getAllStudentIds();

        verify(studentRepository, times(1)).getAllStudentIds();

        Assertions.assertEquals(studentsIdListSize, allStudentIds.size());
        Assertions.assertEquals(Arrays.asList(expectedStudentIds), allStudentIds);
    }

    @Test
    void getByCourse() {
        final String courseName = "English";
        final List<Student> expectedStudents = List.of(new Student(5, "1Charlie1", "Brown"));

        when(studentRepository.getByCourse(courseName)).thenReturn(expectedStudents);

        List<Student> resultStudents = studentService.getStudentsByCourse(courseName);

        verify(studentRepository, times(1)).getByCourse(courseName);

        Assertions.assertEquals(expectedStudents.size(), resultStudents.size());
        Assertions.assertEquals(expectedStudents, resultStudents);
    }


    @Test
    void getIdByName() {
        final int expectedStudentId = 2;
        final String firstName = "Jane";
        final String lastName = "Smith";

        when(studentRepository.getIdByName(firstName, lastName)).thenReturn(Collections.singletonList(expectedStudentId));

        List<Integer> studentIds = studentService.getStudentIdsByName(firstName, lastName);

        verify(studentRepository, times(1)).getIdByName(firstName, lastName);

        Assertions.assertEquals(expectedStudentId, studentIds.get(0));
    }
}
