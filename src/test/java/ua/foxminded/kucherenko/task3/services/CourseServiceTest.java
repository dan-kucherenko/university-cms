package ua.foxminded.kucherenko.task3.services;

import org.junit.Assert;
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
class CourseServiceTest {
    @MockBean
    private CourseRepository courseRepository;
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @Test
    void getCourseById_NegativeCourseId_ThrowsException(){
        final int courseId = -1;
        Assert.assertThrows(IllegalArgumentException.class, ()-> courseService.getCourseById(courseId));
    }

    @Test
    void updateCourse_NegativeCourseId_ThrowsException(){
        final int courseId = -1;
        final Course course = new Course();
        Assert.assertThrows(IllegalArgumentException.class, () -> courseService.updateCourse(courseId, course));
    }

    @Test
    void deleteCourse_NegativeCourseId_ThrowsException(){
        final int courseId = -1;
        Assert.assertThrows(IllegalArgumentException.class, () -> courseService.deleteCourse(courseId));
    }

    @Test
    void addStudentToCourse() {
        final int studentId = 1;
        final int courseId = 4;
        final Student student = new Student(studentId, "John", "Doe");
        final Course course = new Course(courseId, "Chemistry");

        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(false);
        doNothing().when(studentCourseRepository).addStudentToCourse(studentId, courseId);

        Assertions.assertFalse(studentService.exists(studentId, courseId));
        when(studentRepository.getIdByName(student.getFirstName(), student.getLastName())).thenReturn(List.of(studentId));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        courseService.addStudentToCourse(student, course);
        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(true);
        Assertions.assertTrue(studentService.exists(studentId, courseId));

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

        Assertions.assertTrue(studentService.exists(studentId, courseId));
        when(studentRepository.getIdByName(student.getFirstName(), student.getLastName())).thenReturn(List.of(studentId));
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));
        courseService.removeStudentFromCourse(student, course);
        when(studentCourseRepository.exists(studentId, courseId)).thenReturn(false);
        Assertions.assertFalse(studentService.exists(studentId, courseId));

        verify(studentCourseRepository, times(3)).exists(studentId, courseId);
        verify(studentCourseRepository, times(1)).removeStudentFromCourse(studentId, courseId);
    }

    @Test
    void addStudentToCourseInvalidCourseId() {
        final Student student = new Student(1, "John", "Doe");
        final Course course = new Course(0, "InvalidCourse");

        Assertions.assertThrows(IllegalArgumentException.class, () ->  courseService.addStudentToCourse(student, course));
    }

    @Test
    void addStudentToCourseStudentNotFound() {
        final Student student = new Student(0, "Nonexistent", "Student");
        final Course course = new Course(1, "Math");

        when(studentRepository.getIdByName(any(), any())).thenReturn(Collections.emptyList());

        Assertions.assertThrows(IllegalArgumentException.class, () -> courseService.addStudentToCourse(student, course));
    }

    @Test
    void removeStudentFromCourseInvalidCourseId() {
        final Student student = new Student(1, "John", "Doe");
        final Course course = new Course(11, "InvalidCourse");

        Assertions.assertThrows(IllegalArgumentException.class, () -> courseService.removeStudentFromCourse(student, course));
    }

    @Test
    void removeStudentFromCourseStudentNotInCourse() {
        Student student = new Student(1, "John", "Doe");
        Course course = new Course(1, "Math");

        when(studentCourseRepository.exists(anyInt(), anyInt())).thenReturn(false);

        Assertions.assertThrows(IllegalArgumentException.class, () -> courseService.removeStudentFromCourse(student, course));
    }
}
