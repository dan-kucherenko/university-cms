package ua.foxminded.kucherenko.task3.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ua.foxminded.kucherenko.task3.repositories.CourseRepository;

import java.util.Arrays;
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
        Integer[] coursesIds = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        when(repository.getAllCourseIds()).thenReturn(Arrays.asList(coursesIds));

        List<Integer> allSCoursesIds = courseService.getAllCourseIds();

        verify(repository, times(1)).getAllCourseIds();

        Assertions.assertEquals(coursesIdListSize, allSCoursesIds.size());
        Assertions.assertEquals(Arrays.asList(coursesIds), allSCoursesIds);
    }
}
