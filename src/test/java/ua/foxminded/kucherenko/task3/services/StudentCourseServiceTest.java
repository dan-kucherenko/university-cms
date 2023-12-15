package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.models.Student;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentCourseRepository;
import ua.foxminded.kucherenko.task3.repositories.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class StudentCourseServiceTest {
    @MockBean
    private StudentCourseRepository studentCourseRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private CourseRepository courseRepository;
    @Autowired
    private StudentCourseService studentCourseService;

    @Test
    void addStudentToCourse() {
        final int studentId = 1;
        final int courseId = 4;
        final Student student = new Student(studentId, "John", "Doe");
        final Course course = new Course(courseId, "Chemistry");

        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(false);
        doNothing().when(studentCourseRepository).addStudentToCourse(studentId, courseId);

        Assertions.assertFalse(studentCourseService.exists(studentId, courseId));
        when(studentRepository.getIdByName(student.getFirstName(), student.getLastName())).thenReturn(List.of(studentId));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        studentCourseService.addStudentToCourse(student, course);
        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(true);
        Assertions.assertTrue(studentCourseService.exists(studentId, courseId));

        verify(studentCourseRepository, times(3)).exists(studentId, courseId);
        verify(studentCourseRepository, times(1)).addStudentToCourse(studentId, courseId);
    }

    @Test
    void removeStudentFromCourse() {
        final int studentId = 1;
        final int courseId = 3;
        final Student student = new Student(studentId, "John", "Doe");
        final Course course = new Course(courseId, "Physics");

        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(true);
        doNothing().when(studentCourseRepository).removeStudentFromCourse(studentId, courseId);

        Assertions.assertTrue(studentCourseService.exists(studentId, courseId));
        when(studentRepository.getIdByName(student.getFirstName(), student.getLastName())).thenReturn(List.of(studentId));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        studentCourseService.removeStudentFromCourse(student, course);
        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(false);
        Assertions.assertFalse(studentCourseService.exists(studentId, courseId));

        verify(studentCourseRepository, times(3)).exists(studentId, courseId);
        verify(studentCourseRepository, times(1)).removeStudentFromCourse(studentId, courseId);
    }

    @Test
    void addStudentToCourseInvalidCourseId() {
        final Student student = new Student(1, "John", "Doe");
        final Course course = new Course(0, "InvalidCourse");

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentCourseService.addStudentToCourse(student, course));
    }

    @Test
    void addStudentToCourseStudentNotFound() {
        final Student student = new Student(0, "Nonexistent", "Student");
        final Course course = new Course(1, "Math");

        when(studentRepository.getIdByName(any(), any())).thenReturn(Collections.emptyList());

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentCourseService.addStudentToCourse(student, course));
    }

    @Test
    void removeStudentFromCourseInvalidCourseId() {
        final Student student = new Student(1, "John", "Doe");
        final Course course = new Course(11, "InvalidCourse");

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentCourseService.removeStudentFromCourse(student, course));
    }

    @Test
    void removeStudentFromCourseStudentNotInCourse() {
        Student student = new Student(1, "John", "Doe");
        Course course = new Course(1, "Math");

        when(studentCourseRepository.exists(anyInt(), anyInt())).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> studentCourseService.removeStudentFromCourse(student, course));
    }

}