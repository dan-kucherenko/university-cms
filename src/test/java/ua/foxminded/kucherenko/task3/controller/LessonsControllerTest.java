package ua.foxminded.kucherenko.task3.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ua.foxminded.kucherenko.task3.models.Lesson;
import ua.foxminded.kucherenko.task3.services.LessonService;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(LessonsController.class)
public class LessonsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private LessonService lessonService;

    @Test
    @WithMockUser(username = "student", authorities = {"STUDENT"})
    public void shouldReturnOneLessonView() throws Exception {
        final List<Lesson> testLessonsList = List.of(new Lesson());
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Lesson> testLessonsPage = new PageImpl<>(testLessonsList, pageRequest, testLessonsList.size());
        when(lessonService.getAllLessons(0,10)).thenReturn(testLessonsPage);

        mvc
                .perform(MockMvcRequestBuilders.get("/lessons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show_pages/lessons"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("lessons"))
                .andExpect(MockMvcResultMatchers.model().attribute("lessons", testLessonsList));
    }
}
