package ua.foxminded.kucherenko.task3.services;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class CourseServiceTest {
    @MockBean
    private CourseRepository repository;

    @Autowired
    private CourseService courseService;

    @Test
    void getAllCourseIds() {
        final int coursesIdListSize = 10;
        final List<Integer> coursesIds = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        when(repository.getAllCourseIds()).thenReturn(coursesIds);

        final List<Integer> allSCoursesIds = courseService.getAllCourseIds();

        verify(repository, times(1)).getAllCourseIds();

        Assertions.assertEquals(coursesIdListSize, allSCoursesIds.size());
        Assertions.assertEquals(coursesIds, allSCoursesIds);
    }

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
}
