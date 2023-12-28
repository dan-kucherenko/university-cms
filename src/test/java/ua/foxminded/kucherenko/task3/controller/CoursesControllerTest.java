package ua.foxminded.kucherenko.task3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.kucherenko.task3.models.Course;
import ua.foxminded.kucherenko.task3.services.CourseService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CoursesController.class)
public class CoursesControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private CourseService courseService;

    @Test
    public void shouldReturnOneCourseView() throws Exception {
        final List<Course> testCoursesList = List.of(new Course(1, "TestCourse"));

        when(courseService.getAllCourses()).thenReturn(testCoursesList);

        mvc
                .perform(MockMvcRequestBuilders.get("/courses"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/courses"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("courses"))
                .andExpect(MockMvcResultMatchers.model().attribute("courses", testCoursesList));
    }
}
